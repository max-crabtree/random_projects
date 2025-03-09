/* Reflection:
 * I have been able to make a functioning RPN calculator with a still
 * improving GUI. I feel that the good aspects of my calculator are the
 * different functions (which closely relate to an actual RPN calculator)
 * and the eventaul GUI design, if I can make it how I want it to look. Some
 * things I feel need improvement are certain aspects of the code
 * (implementing Observable, making the code more efficient) and, although it
 * isn't apart of the calculator, my own time managment. I do feel there are
 * definitely things to improve on for future projects, but it's good to have
 * this to look back on.
 *
 * Minimum Viable Product:
 * GUI Functionality and Looks: these are crucial and will be the key focuses
 * for the coming week. These significantly effect the end-user in a real
 * scenario, so they have to be as polished as possible. ~4 days??
 * User Stories and Acceptance Tests: need to focus on these so I can get the
 * buttons functioning as they should. ~time: as I do the GUI
 *
 * Aspirations:
 * There are many things I want to implement that I may still be able to, such
 * as: the Observable class, custom error messages, and an actual deg/rad
 * switch... but if I run out of time to add these functions in, I would've 
 * definitely wanted them in my assignment. The reason these functions are not 
 * in the Minimum Viable Product is because, even without them, the calculator 
 * would function well and fine, just not to its full potential.
 * As I am about to submit this assignment, I will note I wasn't able to
 * implement the Observable class or the SHIFT functions for the calculator.
 * On reflection, my time-management could've been a lot better, and although
 * my Kanban was kept relatively well-informed, I feel it may have been quite
 * broad on what my current goal was (In Progress). I am still very happy
 * overall with this project, and how my calculator turned out.
 */

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Provides all the functions for the calculator.
 *
 * Integer input is taken through the pushNumber() function and command
 * functions are called through the cases in the 'DoCalculation' method.
 * <p>
 * The behaviour of the stack is controlled in the pushNumber() and pullNumber() functions.
 */
public class RPN {

    /** First register of the stack. */
    private double x;

    /** Second register of the stack. */
    private double y;

    /** Third register of the stack. */
    private double z;

    /** Final (Top)  register of the stack. */
    private double t;

    /** DEG or RAD mode; true (DEG) is default. */
    private boolean isDegrees = true;

    /** Closely tied to the 'TestRunner' class.
     *
     * @return the output of the test that is checked
     */
    double getResult() {
        return x;
    }

    /** Pushes numbers onto the stack.
     *
     * @param num the user input
     */
    void pushNumber(double num) {
        t = z;
        z = y;
        y = x;
        x = num;
    }

    /** Pulls numbers up the stack. .*/
    void pullNumber() {
        y = z;
        z = t;
        //t = t, repeats t stack value
    }

    /** Addition. */
    void add() {
        x = x + y;
        pullNumber();
    }

    /** Subtraction. */
    void sub() {
        x = y - x;
        pullNumber();
    }

    /** Multiplication. */
    void mul() {
        x = x * y;
        pullNumber();
    }

    /** Division. */
    void div() {
        x = y / x;
        pullNumber();
    }

    /** Exponentiate. */
    void pow() {
        x = Math.pow(y, x);
        pullNumber();
    }

    /** Times Ten to the Power of */
    void powt() {
        x = y * (Math.pow(10, x));
        pullNumber();
    }

    /** Logarithm. */
    void log() {
        x = Math.log(x);
    }

    /** Square Root. */
    void sqr() {
        x = Math.sqrt(x);
    }

    /** Percent Of. */
    void per() {
        x = y*(x*0.01);
        pullNumber();
    }

    /** Remainder. */
    void rem() {
        x = y % x;
        pullNumber();
    }

    /** DEG Switch. */
    void deg() {
        isDegrees = true;
    }

    /** RAD Switch. */
    void rad() {
        isDegrees = false;
    }

    /** Sine Function. */
    void sin() {
        if (isDegrees == true) { //if in degrees...
            //if the input is in radians, Math.sin() outputs in degrees
            x = Math.toRadians(x);
        }
        x = Math.sin(x);
    }

    /** Inverse Sine Function. */
    void isin() {
        x = Math.asin(x);
        if (isDegrees == true) { //if in degrees...
            //the inverse trigonometric Math methods return in radians
            x = Math.toDegrees(x); //converts to degrees
        }
    }

    /** Cosine Function. */
    void cos() {
        if (isDegrees == true) {
            x = Math.toRadians(x);
        }
        x = Math.cos(x);
    }

    /** Inverse Cosine Function. */
    void icos() {
        x = Math.acos(x);
        if (isDegrees == true) {
            x = Math.toDegrees(x);
        }
    }

    /** Tangent Function. */
    void tan() {
        if (isDegrees == true) {
            x = Math.toRadians(x);
        }
        x = Math.tan(x);
    }

    /** Inverse Tangent Function. */
    void itan() {
        x = Math.atan(x);
        if (isDegrees == true) {
            x = Math.toDegrees(x);
        }
    }

    /** Pi. */
    void pi() {
        pushNumber(x);
        x = Math.PI;
    }

    /** Clear Function. */
    void clear() {
        x = 0;
        y = 0;
        z = 0;
        t = 0;
    }

    /** Closely tied to 'TestRunner' class.
     *
     * @return the output to be converted to a String
     */
    String display() {
        return "" + x;
    }

    /** Stack output.
     *
     * @return current stack
     */
    public String toString() {
        return "X: " + x + "\t"
             + "Y: " + y + "\t"
             + "Z: " + z + "\t"
             + "T: " + t;
    }
}

/** Provides a testing harness and a simple enum to define basic tests.
 * 
 * This class operates using enum definitions and a simple static method to
 * iterate through the enumerated tests and run them based on a simple testing
 * methodology.  Through the `runTests()` method it prints out the behaviour
 * of the tests to `stderr` and collates the final results.
 * <p>
 * The behaviour of this TestRunner is tightly coupled to the
 * `Main.doCalculation(Scanner scn)` static method, and the specific
 * `getResult()` and `display()` methods defined on the `RPN` class.
 */
class TestRunner {

    /** The collection of Tests to be run.
     *
     * The Test enum class exists to provide a simple and succinct way to
     * define a large range of tests with similar behaviours, which can be
     * iterated over as part of the testing behaviour.
     * <p>
     * This currently provides for an exact numeric result, a numeric result
     * with a tolerance margin, or a String presentational result (based on
     * the `getDisplay()` method of the RPN class).
     */
    enum Test {

        /** Check Addition function works. */
        TEST_ADD("2 3 +", 2.0 + 3.0),

        /** Check Subtraction function works. */
        TEST_SUB("2 3 -", -1.0),

        /** Check Multiplication function works. */
        TEST_MUL("2 5 *", 10.0),

        /** Check Division function works. */
        TEST_DIV("20 4 /", 5.0),

        /** Check Exponentiate function works. */
        TEST_POW("2 5 ^", 32.0),

        /** Check Times Ten to the Power of function works. */
        TEST_E10("5 3 ^10", 5000.0),

        /** Check Logarithm function works. */
        TEST_LOG("15 log", 2.708050201, 1.0e-9),

        /** Check Square Root function works. */
        TEST_SQR("16 root", 4.0),

        /** Check Percent of function works. */
        TEST_PER("200 10 %", 20.0),

        /** Check Remainder function works. */
        TEST_REM("26 15 %%", 11.0),

        /** Check Sine function works. */
        TEST_SIN("90 sin", 1.0, 1.0e-10),

        /** Check Inverse Sine function works. */
        TEST_ISIN("1 asin", 90.0, 1.0e-10),

        /** Check Cosine function works. */
        TEST_COS("90 cos", 0.0, 1.0e-10),

        /** Check Inverse Cosine function works. */
        TEST_ICOS("0 acos", 90.0, 1.0e-10),

        /** Check Tangent function works. */
        TEST_TAN("45 tan", 1.0, 1.0e-10),

        /** Check Inverse Tangent function works. */
        TEST_ITAN("1 atan", 45.0, 1.0e-10),

        /** Check DEG Switch function works. */
        TEST_DEG("deg 90 sin", 1.0, 1.0e-10),

        /** Check RAD Switch function works. */
        TEST_RAD("rad 90 sin", 0.8939966636, 1.0e-10),

        /** Check Pi function works. */
        TEST_PI("2 pi *", 6.283185307, 1.0e-9),

        /** Check Clear function works. */
        TEST_CLR("2 3 + c", 0.0),

        /** Check output works. */
        TEST_OUT("2 3 *", "6.0");

        /** value/command sequence to be processed. */
        final String input;
        /** desired output format (optional). */
        final String output;
        /** expected final value in the `x` register. */
        final double result;
        /** tolerance on the expected final value (rounding error). */
        final double epsilon;

        /** Set up a test with a result and an accuracy parameter (to satisfy
         * rounding errors).
         *
         * @param input the input string to execute
         * @param result the expected result
         * @param epsilon the absolute error margin allowed
         */
        Test(String input, double result, double epsilon) {
            this.input = input;
            this.output = null;
            this.result = result;
            this.epsilon = epsilon;
        }

        /** Setup a test with a result that must be matched exactly (no
         * rounding error allowed).
         *
         * @param input the input string to execute
         * @param result the expected result
         */
        Test(String input, double result) {
            this(input, result, 0.0);
        }

        /** Setup a test with a formatted output which must be matched.
         *
         * This test format allows us to check that the number formatting
         * works correctly.
         *
         * @param input the input string to execute
         * @param output the output string to match for the displayed output
         */
        Test(String input, String output) {
            this.input = input;
            this.output = output;
            this.result = 0.0;
            this.epsilon = 0.0;
        }
    }

    /** Run all defined tests in the `Test` enum class.
     *
     * This runs all of the defined tests in the order of their definition,
     * and outputs to `stderr` the result of each test as a pass or fail.  It
     * also counts the number of passes and prints a final result showing how
     * many tests passed out of the total provided.
     *
     * @return `true` if every test passes.
     */
    public static boolean runTests() {
        int passed = 0; // total tests passed
        int total = 0; // total tests run

        /* For each test in the `Test` enum class */
        for (Test t : Test.values()) {

            /* Create a scanner for the input string and run the calculator
             * over that sequence of values/commands */
            RPN calc = new RPN();
            Scanner sc = new Scanner(t.input);
            Main.doCalculation(calc, sc);

            /* Check if the test passed, and capture the output from the
             * calculator for diagnostics below. */
            boolean pass;
            String out;
            String exp;
            if (t.output != null) {
                /* Handle the 'display' tests */
                out = calc.display();
                exp = t.output;

                /* we passed if the strings are equal */
                pass = (t.output.equals(out));

            } else {
                /* Handle the numeric tests */
                double res = calc.getResult();
                out = "" + res; // convert result to output string
                exp = "" + t.result; // generate string of expected result
                if (t.epsilon != 0.0) { // with tolerance
                    exp = exp + " +/-" + t.epsilon;
                }

                /* we passed if the absolute difference between the expected
                 * and actual result is less than the tolerance. */
                pass = (Math.abs(t.result - res) <= t.epsilon);
            }

            /* Print out the pass/fail and document the test input together
             * with its output and expected output. */
            if (!pass) {
                System.err.println(
                        String.format("[31m%s:[0m '%s' => %s != %s",
                            t.name(), t.input, out, exp));
            } else {
                System.err.println(
                        String.format("\t[32m%s: '%s' => %s == %s[0m",
                            t.name(), t.input, out, exp));
                passed = passed + 1;
            }

            /* Increment the total test count */
            total = total + 1;
        }

        /* Print out the summary and return false if any test failed. */
        System.err.println("Tests : " + passed + "/" + total + " Passed");
        return (passed == total);
    }
}

/** Emulates the old Applet behaviour which is no longer supported.
 *
 * This class serves to provide the same sort of hooks (`init`, `start`,
 * `paint`, `stop`, and `destroy`) that the Applet mechanism used to provide,
 * while automatically handling all of the Frame construction, setup, and
 * teardown. */
class FakeApplet extends Panel {
    /** Automatically run after the constructor completes, but before any
     * other method. */
    public void init() {
        System.err.println("init");
    }
    /** Called whenever the Applet is (re)started.
     *
     * This includes both initially, but also after a `stop()` call when the
     * Applet is resumed (for example, after being minimized). */
    public void start() {
        System.err.println("start");
    }
    /** Called for every frame update to handle any custom drawing.
     *
     * It is important to note that 'widgets' automatically paint themselves
     * and so don't need to be handled here. */
    public void paint(Graphics g) {
        super.paint(g);
    }
    /** Called whenever the Applet is stopped.
     *
     * This includes both when it is minimized, and also just before
     * destruction when it is being closed. */
    public void stop() {
        System.err.println("stop");
    }
    /** Automatically run just before the Applet is destroyed. */
    public void destroy() {
        System.err.println("destroy");
    }

    /** Wait until the Event Dispatch Thread is empty before proceeding.
     *
     * This ensures that anything that is still trying to happen has happened
     * before proceeding. This is most important for ensuring that the
     * 'init()' routine is complete before trying to interact with the applet.
     */
    public static void sync() {
        /* Ensure the EDT queue is empty and any 'invokeLater's are done */
        System.err.println("waitEDT");
        try {
            EventQueue.invokeAndWait(new Runnable() { public void run() {}});
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("emptyEDT");
    }

    /** Provides a mechanism to hold the main thread of the program until this
     * Applet has closed.
     *
     * This doesn't need to be used for correct operation of an Applet
     * program, but if you would like to retrieve some data once the Applet
     * has closed (i.e. to save state or print out some statistics) in the
     * main thread (instead of using the Applet's `destroy()` method) then you
     * need to use this method to 'block' the main thread.
     */
    public synchronized void waitForClose() {
        sync();

        /* Check if we're still on the screen (i.e. can still notify) */
        System.err.println("isDisplay");
        if (!isDisplayable()) {
            return;
        }

        /* Wait for notification */
        System.err.println("waitNotify");
        try {
            this.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("close");
    }

    /** Create a FakeApplet of size 300x300 pixels.
     *
     * When this constructor is called the Applet will immediately construct
     * itself, and then on the Event Dispatch Thread it will run its `init()`
     * method, display itself, and then run the `start()` method.
     */
    public FakeApplet() {
        this(300, 300);
    }

    /** Create a FakeApplet of a custom size.
     *
     * Note that the size is the frame size, not the canvas size (which will
     * be smaller due to decorations.
     *
     * @param width width of the frame in pixels
     * @param height height of the frame in pixels
     */
    public FakeApplet(int width, int height) {
        super();

        Frame frame = new Frame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                start();
            }
            public void windowIconified(WindowEvent e) {
                stop();
            }
            public void windowDeiconified(WindowEvent e) {
                start();
            }
            public void windowClosing(WindowEvent e) {
                stop();
                destroy();
                frame.dispose();
                synchronized (FakeApplet.this) {
                    FakeApplet.this.notify();
                }
            }
        });
        frame.setSize(width, height);
        frame.add(this);

        /* Defer init and display until the constructor is done */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                init();
                frame.setVisible(true);
            }
        });
    }
}

/** Constructs the Applet's graphics and connects it to the calculators
 * functions.
 *  
 * This class uses the java.awt's Panel, Container, and Component classes to
 * construct the graphics. By connecting to the RPN calculator class, it
 * connects the functions to the button by using cases and the ActionEvent
 * class.
 * <p>
 * By using different LayoutManagers (GridLayout and BorderLayout), the Panels
 * are able to be manipulated to different orientations and positions.
 */
class MyApplet extends FakeApplet {

    /** The calculator engine that we run on. */
    private RPN calc;

    /** The display of the calculator. */
    private TextField display;

    /** Whether the display should clear once another input is detected. */
    public boolean boxClear;

    /** Whether the calculator is in DEG or RAD (DEG is default). */
    public boolean isDegrees = true;

    /** Runs once the Applet has started. 
     * 
     * Contains the java.awt classes to construct the graphics that are then painted by
     * the paint() method.
     */
    public void init() {
        super.init();

        //create new calculator engine
        calc = new RPN();

        //custom colour for calculator
        Color cream = new Color(193, 175, 156);

        //this panel controls the main Panel and the display positioning
        Panel core = new Panel(new BorderLayout());
        core.setBackground(cream);

        //this panel controls the buttons layouts
        Panel main = new Panel(new BorderLayout());
        main.setBackground(cream);

        //calculator display
        display = new TextField(20);

        //0-9, . and PI
        Panel nums = new Panel();
        nums.setLayout(new GridLayout(4, 3));
        //gets the correct orientation for normal calculators
        nums.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //operators (+, -, *, /)
        Panel ops = new Panel();
        ops.setLayout(new GridLayout(4, 1));

        //functions (ENTER, CLEAR, DEG, RAD)
        Panel funcs = new Panel();
        funcs.setLayout(new GridLayout(2, 3));

        //trigonometry (SIN, COS, TAN, ASIN, ACOS, ATAN)
        Panel trig = new Panel();
        trig.setLayout(new GridLayout(2, 3));

        //exponentials ()
        Panel expo = new Panel();
        expo.setLayout(new GridLayout(2, 2));

        //degrees and radians buttons defined
        Button degrees = new Button("DEG");
        Button radians = new Button("RAD");

        /** Listens to actions and prints to the display.
         *
         * Prints button input to the display which allows for operations to then be
         * performed on them.
         */
        ActionListener digListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (boxClear == true) {
                    //clears display box
                    display.setText("");
                }

                //gives more clarity to the user about which mode is on
                if (isDegrees == true) { //if degrees mode...
                    degrees.setForeground(Color.green);
                    radians.setForeground(Color.red);
                }
                else { //if radians mode...
                    radians.setForeground(Color.green);
                    degrees.setForeground(Color.red);
                }

                display.setText(display.getText() + e.getActionCommand());
                boxClear = false;
            }
        };

        /** Listens to actions and performs set function.
         *
         * By accessing the RPN class through the calc object, functions are
         * performed according to the cases which correspond to each of the
         * buttons names.
         */
        ActionListener opListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calc.pushNumber(Double.parseDouble(display.getText()));
                switch (e.getActionCommand()) {
                    case "ENTER":
                        boxClear = true;
                        break;
                    case "+": //addition
                        calc.add();
                        break;
                    case "-": //subtraction
                        calc.sub();
                        break;
                    case "*": //multiplication
                        calc.mul();
                        break;
                    case "/": //division
                        calc.div();
                        break;
                    case ".": //decimal point
                        break;
                    case "y^x": //power
                        calc.pow();
                        break;
                    case "y*10^x": //times ten to the power of
                        calc.powt();
                        break;
                    case "LOG": //log
                        calc.log();
                        break;
                    case "ROOT": //square root
                        calc.sqr();
                        break;
                    case "%": //percentage of
                        calc.per();
                        break;
                    case "%%": //remainder
                        calc.rem();
                        break;
                    case "DEG": //degrees mode
                        isDegrees = true;
                        calc.deg();
                        break;
                    case "RAD": //radians mode
                        isDegrees = false;
                        calc.rad();
                        break;
                    case "SIN": //sine
                        calc.sin();
                        break;
                    case "ASIN": //inverse sine
                        calc.isin();
                        break;
                    case "COS": //cosine
                        calc.cos();
                        break;
                    case "ACOS": //inverse cosine
                        calc.icos();
                        break;
                    case "TAN": //tangent
                        calc.tan();
                        break;
                    case "ATAN": //inverse tangent
                        calc.itan();
                        break;
                    case "PI": //pi
                        calc.pi();
                        break;
                    case "CLEAR": //clear stack
                        calc.clear();
                        boxClear = true;
                        break;
                    default :
                        break;
                }
                display.setText(calc.display());
            }

        };

        //numbers 1-9
        for (int i = 9; i > 0; i--) {
            Button b = new Button("" + i);
            b.addActionListener(digListener);
            nums.add(b);
        }

        //pi
        Button pi = new Button("PI");
        pi.addActionListener(opListener);
        nums.add(pi);

        //decimal point and 0
        for (String s : ". 0".split(" ")) {
            Button b = new Button(s);
            b.addActionListener(digListener);
            nums.add(b);
        }

        //operators
        for (String s : "/ * - +".split(" ")) {
            Button b = new Button(s);
            b.addActionListener(opListener);
            ops.add(b);
        }

        //degrees switch
        degrees.addActionListener(opListener);
        funcs.add(degrees);

        //radians switch
        radians.addActionListener(opListener);
        funcs.add(radians);

        //enter and clear buttons
        for (String s : "ENTER CLEAR".split(" ")) {
            Button b = new Button(s);
            b.addActionListener(opListener);
            funcs.add(b);
        }

        //trigonometric functions
        for (String s : "SIN COS TAN ASIN ACOS ATAN".split(" ")) {
            Button b = new Button(s);
            b.addActionListener(opListener);
            trig.add(b);
        }

        //exponential functions
        for (String s : "LOG y^x y*10^x ROOT % %%".split(" ")) {
            Button b = new Button(s);
            b.addActionListener(opListener);
            expo.add(b);
        }

        //main Panel formatting
        main.add(nums);
        main.add(ops, BorderLayout.WEST);
        main.add(funcs, BorderLayout.EAST);
        main.add(trig, BorderLayout.NORTH);
        add(main);

        //core Panel formatting
        core.add(expo);
        core.add(main, BorderLayout.SOUTH);
        core.add(display, BorderLayout.NORTH);
        add(core);
    }

    /** Return the calculator engine. 
     *
     * @return calc returns the calculator engine
     */
    RPN getCalc() {
        return calc;
    }

    /** Paints the container. */
    public void paint(Graphics g) {
        super.paint(g);
    }
}

/** Contains and isolates the direct function of the calculator.
 * 
 * This class takes user input using the Scanner utility to generate integers
 * on the stack for use with commands using switch and case expressions.
 * <p>
 * This class isolates and contains the calculators functions from the main()
 * method, allowing for clean and organised code.
 */
class Main {

    /** An assertion to whether or not the 'TestRunner' class is running. */
    public static boolean assertsEnabled = false;

    /** Input of the Calculator.
     *
     * Links the users input to the 'RPN' class which allows user input to use
     * the calculator.
     *
     * @param calc the calculator engine
     * @param scn user input
     */
    public static void doCalculation(RPN calc, Scanner scn) {

        while (scn.hasNext()) {
            if (scn.hasNextDouble()) {  //have a number
                calc.pushNumber(scn.nextDouble()); //input = double num
            } else {  //have a command
                String cmd = scn.next();
                switch (cmd) {
                    case "+": //do addition
                        calc.add();
                        break;
                    case "-": //do subtraction
                        calc.sub();
                        break;
                    case "*": //do multiplication
                        calc.mul();
                        break;
                    case "/": //do division
                        calc.div();
                        break;
                    case "^": //power
                        calc.pow();
                        break;
                    case "^10": //times ten to the power of
                        calc.powt();
                        break;
                    case "log": //logarithm
                        calc.log();
                        break;
                    case "root": //square root
                        calc.sqr();
                        break;
                    case "%": //percentage of
                        calc.per();
                        break;
                    case "%%": //remainder
                        calc.rem();
                        break;
                    case "deg": //deg function
                        calc.deg();
                        break;
                    case "rad": //rad function
                        calc.rad();
                        break;
                    case "sin": //sin function
                        calc.sin();
                        break;
                    case "asin": //inverse sin function
                        calc.isin();
                        break;
                    case "cos": //cos function
                        calc.cos();
                        break;
                    case "acos": //inverse cos function
                        calc.icos();
                        break;
                    case "tan": //tan function
                        calc.tan();
                        break;
                    case "atan": //inverse tan function
                        calc.itan();
                        break;
                    case "pi": //pi function
                        calc.pi();
                        break;
                    case "c": //clear stack
                        calc.clear();
                        break;
                    case "s": //output stack
                        System.out.println(calc);
                        break;
                    case "p": //prints x
                        System.out.println(calc.display());
                        break;
                    default: //command error message
                        System.err.println("Unknown Command [" + cmd + "]");
                        break;
                }
            }
        }
    }

    /** The main() method where the code is run.
     *
     * The 'TestRunner' class is enabled here and the input for the
     * 'doCalculation' method is defined here.
     *
     * @param args stores java command line arguments
     */
    public static void main(String[] args) {

        System.err.println("Hello World");

        assert assertsEnabled = true; //asserts the 'TestRunner' class to run
        if (assertsEnabled) {
            System.err.println("Testing Mode Active");
        }
        assert TestRunner.runTests() : "Testing Regime Failed";

        MyApplet applet = new MyApplet();
        applet.sync(); //syncs MyApplet()

        Scanner scn = new Scanner(System.in); //input
        doCalculation(applet.getCalc(), scn);

        applet.waitForClose();
    }
}
