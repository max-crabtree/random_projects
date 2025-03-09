/* Minimal Viable Product:
 * The critical improvements I need to make in the time left is to get the
 * correct functionality working for the converter, and to have it smoothly
 * work with the GUI that is to be implemented. I am hoping that the
 * functionality can be completed by the 29th, and the GUI by the deadline.
 *
 * Rambling Notes:
 * Need APPLET class (for GUI), need CONVERTER class (for conversion of
 * values)
 * if (less than % 5 && not 1) {
 *      increment II, III, IV...
 * 
 * count to end of input string everytime!!
 * example: 850
 * get 8, and then count down 2. which means 8 * 10^2
 * and then look at next number, 5 and count down 1, which means
 * 5 * 10^1.
 * and then zero we leave.
 * this then gives: DCCCL
 * DCCC = 800
 * L = 50
 * ^ put this into diagnostics!
 * 'pivot' around number? 5, 50, etc...
 * I II III IV V VI VII VIII IX X XI ...
 * cannot repeat more than 3 times
 * if ("smallerlarger") {
 *  == larger - smaller;
 *  }
 *  if ("largersmaller") {
 *  == larger + smaller;
 *  }
 * 
 *
 * Testing:
 *
 * NUM TO ROMAN:
 *      10/9:
 *      Input: "1 5 25 10 50 100 500 1000 1250"
 *      Output: "Error! Unknown Number: 25
 *               Error! Unknown Number: 1250
 *               I
 *               V
 *               X
 *               L
 *               C
 *               D
 *               M"
 *      This is the first test of the converter. This is working as expected; it
 *      outputs the letter depending on the value put in. It cannot process more
 *      complex numbers yet.
 *
 *      11/9:
 *      Input: "1 2 3 4 5"
 *      Output: "1.0
 *               2.0
 *               3.0
 *               4.0
 *               5.0"
 *      The functionality may have seemed to regress, but this is as expected. I am
 *      beginning to utilise the Convert class.
 *
 *      11/9:
 *      Input: "1832 289"
 *      Output: "Num: 1  Left: 3      
 *               Num: 8  Left: 2 
 *               Num: 3  Left: 1 
 *               Num: 2  Left: 0 
 *               Num: 2  Left: 2 
 *               Num: 8  Left: 1
 *               Num: 9  Left: 0"
 *      This test is hopefully going to be able to split the digits into their
 *      respective powers. So, 1 * 10^3, 8 * 10^2, 3 * 10^1, 2 * 10^0... etc.
 *
 *      11/9:
 *      Input: "2051 1151"
 *      Output: "Num: 2  Left: 3  Amount: 2000  Roman:   Total:
 *               Num: 0  Left: 2  Amount: 0     Roman:   Total:
 *               Num: 5  Left: 1  Amount: 50    Roman: L Total: L
 *               Num: 1  Left: 0  Amount: 1     Roman: I Total: LI
 *               Num: 1  Left: 3  Amount: 1000  Roman: M Total: M
 *               Num: 1  Left: 2  Amount: 100   Roman: C Total: MC
 *               Num: 5  Left: 1  Amount: 50    Roman: L Total: MCL
 *               Num: 1  Left: 0  Amount: 1     Roman: I Total: MCLI"
 *      A lot more diagnostics here. The goal of this program is hopefully becoming
 *      more obvious. It is currently working with the basic Roman Numerals by
 *      splitting the number using the 'Left' value. I still need to implement more
 *      complex behaviour for other numbers.
 *
 *      13/9:
 *      Input: "2051 1151"
 *      Output: "Current Number: 2   Amount: 2000  Roman: M Total: MM
 *               Current Number: 0   Amount: 0     Roman: M Total: MM
 *               Current Number: 5   Amount: 50    Roman: M Total: MMLXXXXX
 *               Current Number: 1   Amount: 1     Roman: I Total: MMLXXXXXI
 *               Roman Numeral is: MMLXXXXXI
 *               Current Number: 1   Amount: 1000  Roman: M Total: M
 *               Current Number: 1   Amount: 100   Roman: M Total: MC
 *               Current Number: 5   Amount: 50    Roman: M Total: MCLXXXXX
 *               Current Number: 1   Amount: 1     Roman: I Total: MCLXXXXXI
 *               Roman Numeral is: MCLXXXXXI"
 *      This test is starting to look somewhat like the Roman Numerals. I need to
 *      fix the issue with the unecessary "XXXXX" in each number. My main point of
 *      focus will be to work out how to 'pivot' between V, L, etc. when finding 4,
 *      6, 40, 60, etc.
 *
 *      22/9:
 *      Input: "2051 1151 4949"
 *      Output: "Current Number: 2   Amount: 2000  Roman: M Total: MM
 *               Current Number: 0   Amount: -     Roman: M Total: MM
 *               Current Number: 5   Amount: 50    Roman: M Total: MML
 *               Current Number: 1   Amount: 1     Roman: I Total: MMLI
 *               Roman Numeral is: MMLI
 *               Current Number: 1   Amount: 1000  Roman: M Total: M
 *               Current Number: 1   Amount: 100   Roman: M Total: MC
 *               Current Number: 5   Amount: 50    Roman: M Total: MCL
 *               Current Number: 1   Amount: 1     Roman: I Total: MCLI
 *               Roman Numeral is: MCLI
 *               Current Number: 4   Amount: 4000  Roman: ?? Total: ??
 *               Current Number: 9   Amount: 900   Roman: CM Total: ??CM
 *               Current Number: 4   Amount: 40    Roman: XL Total: ??CMXL
 *               Current Number: 9   Amount: 9     Roman: IX Total: ??CMXLIX
 *               Roman Numeral is: ??CMXLIX"
 *      This method now works correctly up to 3999. Beyond 3999, I will need
 *      to implement the 'overbar' and 'box' functionalities (multiplying by
 *      1000, 100000 respectively).
 *
 *      22/9:
 *      Input: "15500 1127 89520"
 *      Output: "Current Number: 1   Amount: 10000  Roman: X^ Total: X^
 *               Current Number: 5   Amount: 5000   Roman: V^ Total: X^V^
 *               Current Number: 5   Amount: 500    Roman: D  Total: X^V^D
 *               Current Number: 0   Amount: 0      Roman: -  Total: X^V^D
 *               Current Number: 0   Amount: 0      Roman: -  Total: X^V^D
 *               Roman Numeral is: X^V^D
 *               Current Number: 1   Amount: 1000  Roman: M   Total: M
 *               Current Number: 1   Amount: 100   Roman: C   Total: MC
 *               Current Number: 2   Amount: 20    Roman: C  Total: MCXX
 *               Current Number: 7   Amount: 7     Roman: VII Total: MCXXVII
 *               Roman Numeral is: MCXXVII
 *               Current Number: 8   Amount: 80000  Roman: null^ Total: null^
 *               Current Number: 9   Amount: 9000   Roman: null^ Total: null^null^
 *               Current Number: 5   Amount: 500    Roman: D     Total: null^null^D
 *               Current Number: 2   Amount: 20     Roman: C    Total: null^null^DXX
 *               Current Number: 0   Amount: 0      Roman: -     Total: null^null^DXX
 *               Roman Numeral is: null^null^DXX"
 *      Basic overbar implemented (denoted as ^), only works with the basic
 *      roman numerals. For some reason, XX is showing up as C in the Roman:
 *      section.
 *
 *      22/9:
 *      Input: "15500 1127 89520 3974013"
 *      Output: "Current Number: 1   Amount: 10000  Roman: X^ Total: X^
 *               Current Number: 5   Amount: 5000   Roman: V^ Total: X^V^
 *               Current Number: 5   Amount: 500    Roman: D  Total: X^V^D
 *               Current Number: 0   Amount: 0      Roman: -  Total: X^V^D
 *               Current Number: 0   Amount: 0      Roman: -  Total: X^V^D
 *               Roman Numeral is: X^V^D
 *               Current Number: 1   Amount: 1000  Roman: M   Total: M
 *               Current Number: 1   Amount: 100   Roman: C   Total: MC
 *               Current Number: 2   Amount: 20    Roman: C  Total: MCXX
 *               Current Number: 7   Amount: 7     Roman: VII Total: MCXXVII
 *               Roman Numeral is: MCXXVII
 *               Current Number: 8   Amount: 80000  Roman: L^X^X^X^ Total: L^X^X^X^
 *               Current Number: 9   Amount: 9000   Roman: I^X^     Total: L^X^X^X^I^X^
 *               Current Number: 5   Amount: 500    Roman: D        Total: L^X^X^X^I^X^D
 *               Current Number: 2   Amount: 20     Roman: C        Total: L^X^X^X^I^X^DXX
 *               Current Number: 0   Amount: 0      Roman: -        Total: L^X^X^X^I^X^DXX
 *               Roman Numeral is: L^X^X^X^I^X^DXX
 *               Current Number: 3   Amount: 3000000  Roman: M^         Total: M^M^M^
 *               Current Number: 9   Amount: 900000   Roman: C^M^       Total: M^M^M^C^M^
 *               Current Number: 7   Amount: 70000    Roman: L^X^X^     Total: M^M^M^C^M^L^X^X^
 *               Current Number: 4   Amount: 4000     Roman: I^V^       Total: M^M^M^C^M^L^X^X^I^V^
 *               Current Number: 0   Amount: 0        Roman: -          Total: M^M^M^C^M^L^X^X^I^V^
 *               Current Number: 1   Amount: 10       Roman: C          Total: M^M^M^C^M^L^X^X^I^V^X
 *               Current Number: 3   Amount: 3        Roman: I          Total: M^M^M^C^M^L^X^X^I^V^XIII
 *               Roman Numeral is: M^M^M^C^M^L^X^X^I^V^XIII
 *      The num to roman method now works for numbers up to 3999999. I will
 *      experiment with the vinculum box which may allow for numbers up to
 *      399999999!
 *
 *      1/10:
 *      Input: "15500 1127 89520 3974013"
 *      Output: "Current Number: 1   Amount: 10000  Roman: X^ Total: X^
 *               Current Number: 5   Amount: 5000   Roman: V^ Total: X^V^
 *               Current Number: 5   Amount: 500    Roman: D  Total: X^V^D
 *               Roman Numeral is: X^V^D
 *               Current Number: 1   Amount: 1000  Roman: M   Total: M
 *               Current Number: 1   Amount: 100   Roman: C   Total: MC
 *               Current Number: 2   Amount: 20    Roman: C   Total: MCXX
 *               Current Number: 7   Amount: 7     Roman: VII Total: MCXXVII
 *               Roman Numeral is: MCXXVII
 *               Current Number: 8   Amount: 80000  Roman: L^X^X^X^ Total: L^X^X^X^
 *               Current Number: 9   Amount: 9000   Roman: I^X^     Total: L^X^X^X^I^X^
 *               Current Number: 5   Amount: 500    Roman: D        Total: L^X^X^X^I^X^D
 *               Current Number: 2   Amount: 20     Roman: C        Total: L^X^X^X^I^X^DXX
 *               Roman Numeral is: L^X^X^X^I^X^DXX
 *               Current Number: 3   Amount: 3000000  Roman: M^         Total: M^M^M^
 *               Current Number: 9   Amount: 900000   Roman: C^M^       Total: M^M^M^C^M^
 *               Current Number: 7   Amount: 70000    Roman: L^X^X^     Total: M^M^M^C^M^L^X^X^
 *               Current Number: 4   Amount: 4000     Roman: I^V^       Total: M^M^M^C^M^L^X^X^I^V^
 *               Current Number: 1   Amount: 10       Roman: C          Total: M^M^M^C^M^L^X^X^I^V^X
 *               Current Number: 3   Amount: 3        Roman: I          Total: M^M^M^C^M^L^X^X^I^V^XIII
 *               Roman Numeral is: M^M^M^C^M^L^X^X^I^V^XIII
 *      This test looks much the same to the one prior. This now excludes the
 *      number 0 when iterating through. 
 *
 * ROMAN TO NUM:
 *      14/9:
 *      Input: "ABCDEFG"
 *      Output: "First: A   Second: B
 *               First: B   Second: C
 *               First: C   Second: D
 *               First: D   Second: E
 *               First: E   Second: F
 *               First: F   Second: G"
 *      First test of this method. It is currently for looping and checking
 *      the first and second character in pairs.
 *      
 *      18/9:
 *      Input: "XXV VI IX"
 *      Output: "Current: X     Number: 10
 *               Current: X     Number: 20
 *               Current: V     Number: 25
 *               Final Number is: 25
 *               Current: V     Number: 5
 *               Current: I     Number: 6
 *               Final Number is: 6
 *               Current: I     Number: 1
 *               Current: X     Number: 11
 *               Final Number is: 11
 *      No longer looks to the next value as I was having issues with indexes.
 *      This functionality is rather basic, just assigning certain chars to
 *      certain values and then adding them together.
 *
 *      23/9:
 *      Input: "XX IV GG GX"
 *      Output: "G is not a valid letter!
 *               G is not a valid letter!
 *               Current: X     Number: 10      Next: X
 *               G is not a valid number!Final Number is: 10
 *
 *               Current: X     Number: 20      Next: X
 *               Final Number is: 20
 *               Current: I     Number: 4       Next: V
 *               Final Number is: 4
 *               Final Number is: 0
 *               Final Number is: 0
 *               Final Number is: 0
 *               Current: X     Number: 10      Next: X
 *               Final Number is: 10
 *      Throws an error if it sees an unvalid character. Subtractive logic now
 *      works as well. I need to 'time' the output so it doesn't overlap/looks
 *      cleaner.
 *
 *      3/10:
 *      Input: "VII VIIII IV"
 *      Output: "Result: 5
 *               Result: 6
 *               Result: 6
 *               Result: 4
 *               No more than 3 of one numeral!"
 *      Completely re-writing this section. Does not add correctly (I believe
 *      it is missing the final numeral), but is able to check if
 *      there are > 4 numerals in a row. Subtraction behaviour slightly
 *      working.
 *
 * GUI:
 *      9/10:
 *      Input: "123"
 *      Output: "{"
 *      In the very early stages. I cannot show the design here, but I will
 *      get screenshots and compile it into a document. Currently takes in
 *      only numbers, and outputs the equivalent Unicode character.
 *
 * Reflection:
 * ...
 */

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Roman Numeral Converter.
 *
 * This class holds all of the conversion logic for this program. It holds two
 * encapsulated classes:
 * <ul>
 * <li><a href = #NumToRoman>NumToRoman</a>: This class holds the number to
 * roman numeral logic
 * <li><a href = #RomanToNum>RomanToNum</a>: This class holds the roman
 * numeral to number logic
 * </ul>
 * It takes in input from the <a href = #Main>main</a> class through use of
 * the <code>Scanner</code> class.
 */
class Converter {

    /** An assert to whether or not the program is in testing mode. */
    private static boolean assertsEnabled = false;
    static {
        assert assertsEnabled = true;
    }

    /** Placeholder for integer input. */
    private int num;

    /** Placeholder for String input. */
    private String roman;

    /** Holds the values of the roman numerals as Values, and the equivalent
     * roman numeral as Keys. */
    private static HashMap<Integer, String> basic = new HashMap<Integer, String>();

    /** Holds the values of the subtractive roman numerals as Values, and the equivalent
     * roman numeral as Keys. */
    private static HashMap<Integer, String> subtract = new HashMap<Integer, String>();

    /** Holds the basic roman numerals as Keys, and their equivalent values as Values. */
    HashMap<String, Integer> converted = new HashMap<String, Integer>();

    /** Holds the basic subtractive roman numerals as Keys, and their equivalent values as Values. */
    HashMap<String, Integer> s_converted = new HashMap<String, Integer>();

    /** NumToRoman object. */
    NumToRoman numToRoman = new NumToRoman();

    /** Initialise Variables. */
    Converter() {
        num = 0;
        roman = "";

        /* Put basic roman numeral values into HashMap */
        basic.put(1, "I");
        basic.put(5, "V");
        basic.put(10, "X");
        basic.put(50, "L");
        basic.put(100, "C");
        basic.put(500, "D");
        basic.put(1000, "M");
        basic.put(5000, "V^");
        basic.put(10000, "X^");
        basic.put(50000, "L^");
        basic.put(100000, "C^");
        basic.put(500000, "D^");
        basic.put(1000000, "M^");
        basic.put(5000000, "|V|");
        basic.put(10000000, "|X|");
        basic.put(50000000, "|L|");
        basic.put(100000000, "|C|");
        basic.put(500000000, "|D|");
        basic.put(1000000000, "|M|");

        /* Put subtractive values into HashMap */
        subtract.put(4, "IV");
        subtract.put(9, "IX");
        subtract.put(40, "XL");
        subtract.put(90, "XC");
        subtract.put(400, "CD");
        subtract.put(900, "CM");
        subtract.put(4000, "I^V^");
        subtract.put(9000, "I^X^");
        subtract.put(40000, "X^L^");
        subtract.put(90000, "X^C^");
        subtract.put(400000, "C^D^");
        subtract.put(900000, "C^M^");
        subtract.put(4000000, "|XL|");
        subtract.put(9000000, "|XC|");
        subtract.put(40000000, "|CD|");
        subtract.put(90000000, "|CM|");

        /* These HashMaps were originally temporary.
           Was going to implement entrySet behaviour, but could not get it 
           working. Could not get overbar values working in time. 
         */
        converted.put("I", 1);
        converted.put("V", 5);
        converted.put("X", 10);
        converted.put("L", 50);
        converted.put("C", 100);
        converted.put("D", 500);
        converted.put("M", 1000);

        s_converted.put("IV", 4);
        s_converted.put("IX", 9);
        s_converted.put("XL", 40);
        s_converted.put("XC", 90);
        s_converted.put("CD", 400);
        s_converted.put("CM", 900);
    }

    /** Number to Roman Numeral Logic.
     *
     * This class holds the logic that converts a inputted number into a
     * roman numeral. It takes in the input for the <a href =
     * #getNum>getNum</a> method in the <a href =
     * #Converter>Converter</a>class.
     */
    class NumToRoman {

        /** Final numeral that is outputted. */
        private StringBuffer total = new StringBuffer();

        /** Length (in digits) of input number. */
        protected int numLength;

        /** The number of digits left while iterating. */
        private int endLength;

        /** Digit at current index. */
        private int currentNum;

        /** Current power (degree) of number at current index. */
        private int currentPower;

        /** Value of number at current index. */
        private int powerValue;

        /** Placeholder for each numeral iteration. */
        private String numeral;

        /** Initialise Variables. */
        NumToRoman() {
            total.setLength(0);
            numLength = 0;
            endLength = 0;
            currentNum = 0;
            currentPower = 0;
            powerValue = 0;
            numeral = "";
        }

        /** Processes user input and outputs results.
         *
         * This method processes the user input by using the <a href =
         * #analyseNum>method</a> and generates an output.
         *
         * @return the final roman numeral String
         */
        protected String processNum() {

            /* Reset total value for later rounds. */
            total.setLength(0);
            numLength = String.valueOf(num).length();

            for (int i = 0; i < numLength; i++) {

                analyseNum(numLength, i);

                /* Special case, zero = N. */
                if (num == 0) {
                    total.append("N");
                }

                /* If there is nothing to add, skip. */
                if (currentNum == 0) {
                    continue;
                }

                /* If the number corresponds to a basic roman numeral... */
                if (basic.containsKey(powerValue) == true) {
                    basicRoman(powerValue);
                }
                else {
                    complexRoman(powerValue, currentPower);
                }

                /* If we are testing... */
                if (assertsEnabled) {

                    /* Cosmetic Purposes. */
                    if (powerValue == 0) {
                        numeral = "-";
                    }

                    String diagnostics = "Current Number: %1d\tAmount: %2d\tRoman: %3s\tTotal: %4s";
                    System.out.println(String.format(diagnostics, currentNum, currentNum * currentPower, numeral, total));
                }
            }
            System.out.println("Roman Numeral is: " + total);
            return total.toString();
        }

        /** Analyse user input and generate useful values for later
         * calculations.
         * 
         * This method analyses the integer input and generates multiple
         * values used for calculations in the <a href =
         * #complexRoman>complexRoman</a> method. 
         *
         * @param length the total length of the number
         * @param index the current index of the <code>length</code>
         */
        private void analyseNum(int length, int index) {
            endLength = (length - index) - 1;
            /* The '0' equates the char back to its numerical value (not
             * ASCII). */
            currentNum = String.valueOf(num).charAt(index) - '0';
            currentPower = (int)Math.pow(10, endLength);
            powerValue = currentNum * currentPower;
        }

        /** Basic roman numeral behaviour.
         *
         * This method checks the <code>basic</code> HashMap, and simply
         * <code>append</code>s the respective value to the
         * <code>total</code>.
         *
         * @param value the <code>powerValue</code> at the current
         * <code>index</code>
         */
        private void basicRoman(int value) {
            numeral = basic.get(value);
            total.append(numeral);
        }

        /** More complex subtractive/additive behaviour.
         *
         * This method utilises many values from the <a href =
         * #analyseNum>analyseNum</a> method. By using the roman numeral
         * rules, the values are determined using a few conditional statements
         * that hold reference to the degree of ten and how it relates to the
         * 'basic' roman numerals.
         *
         * @param degreeValue the <code>powerValue</code> at the current index
         * @param degree the degree of 10 at current index
         */
        private void complexRoman(int degreeValue, int degree) {
            /* Always equates to first digit:
             * eg. degreeValue = 3000 therefore degree = 1000
             * therefore 3000 / 1000 = 3
             */
            int firstDigit = degreeValue / degree;

            /* Subtractive Behaviour */
            if (firstDigit == 4 || firstDigit == 9) {
                numeral = subtract.get(degreeValue);
                total.append(numeral);
            }

            /* Additive Behaviour */
            else if (5 < firstDigit && firstDigit < 9)  {
                /* If first digit is 6, 7 or 8 */
                StringBuffer additive = new StringBuffer();
                /* Take 5 from value so middle value is not repeated
                 * example: 8000 -> 8 - 5 -> 3... so only append 3 values */
                for (int i = 0; i < (firstDigit) - 5; i++) {
                    additive.append(basic.get(degree));
                }
                /* Add additive value to middle value */
                numeral = basic.get(5 * degree) + additive;
                total.append(numeral);
            }

            /* Two and Three */
            else {
                /* Only runs if other conditionals don't go through. Appends
                 * the basic degree numeral. */
                StringBuffer twoAndThree = new StringBuffer();
                for (int i = 0; i < (firstDigit); i++) {
                    twoAndThree.append(basic.get(degree));
                    /* If input is 2000 -> (i < (2000 / 1000)) -> append X
                     * twice */
                }
                numeral = twoAndThree.toString();
                total.append(numeral);
            }
        }
    }

    /** Roman Numeral to Number Logic.
     *
     * This class holds the logic for the conversion of a basic roman numeral
     * to a number. It takes the input from the <a href =
     * #getRoman>getRoman</a> method. 
     * <p>
     * It implements the multiple roman numeral rules (nearly) successfully,
     * and shows relevant error messages. If the numeral is valid, the roman
     * numerals are simply equated to their respective number value.
     */
    class RomanToNum {

        /** Current roman numeral at index. */
        String current;

        /** Increases additively with the number value each pass. */
        int result;

        /** Value of final number that increases with the result each pass. */
        int number;

        /** Iterate through String as a pair. */
        String pair;

        /** Iterate through String as a quad. */
        String quad;

        /** Boolean to check if roman numeral is valid for processing. */
        boolean isValid;

        /** Initialise Variables. */
        RomanToNum() {
            current = "";
            result = 0;
            number = 0;
            pair = "";
            quad = "";
            isValid = true;
        }

        /** Process the input, and output final number if valid.
         *
         * By utilising the <a href = #rulesCheck>rulesCheck()</a> and <a href
         * = #outputNum>outputNum()</a> methods, the roman numeral is
         * processed and the final number is outputted.
         */
        protected void processRoman() {
            /* If the numeral is valid */
            if (rulesCheck() == true) {
                outputNum();
                System.out.println("Final Number is: " + number);
            }
            /* If the numeral is not valid */
            else {
                System.err.println("Try again!");
            }
        }

        /** Validate input.
         *
         * By using the multiple rule checking methods:
         *
         *<a href = #checkNumeralValid>checkNumeralValid</a>
         *<a href = #checkNumeralSize>checkNumeralSize</a>
         *<a href = #checkNumeralAmount>checkNumeralAmount</a>
         *
         * The input is validated.
         *
         * @return <code>true</code> if number is valid. <code>false</code> if
         * not
         */
        private boolean rulesCheck() {
            checkNumeralValid();
            if (isValid == true) {
                checkNumeralSize();
                checkNumeralAmount();
            }
            return isValid;
        }

        /** Check for mis-input.
         *
         * Checks if the character at the current index is contained within
         * the <code>converted</code> HashMap. If it isn't,
         * <code>isValid</code> is set to <code>false</code>.
         */
        private void checkNumeralValid() {
            for (int i = 0; i < roman.length(); i++) {
                current = Character.toString(roman.charAt(i));
                if (converted.containsKey(current) == false) {
                    System.err.println("[" + current + "] Invalid Numeral!");
                    isValid = false;
                }
            }
        }

        /** Compare first and second char in pair.
         *
         * If the second numeral is greater in value than the first,
         * <code>isValid</code> is set to <code>false</code>. The values for
         * each of the characters are sourced from the <code>converter</code>
         * HashMap.
         * <p>
         * The values compared exclude any values contained in the
         * <code>s_converted</code> HashMap.
         */
        private void checkNumeralSize() {
            for (int i = 0; i < roman.length(); i++) {
                if (i + 2 <= roman.length()) {
                    pair = roman.substring(i, i + 2);
                    int first = converted.get(Character.toString(pair.charAt(0)));
                    int second = converted.get(Character.toString(pair.charAt(1)));
                    if (first < second && !(s_converted.containsKey(pair))) {
                        System.err.println("[" + pair + "] Second number too large!");
                        isValid = false;
                        break;
                    }
                }
            }
        }

        /** Compare groups of characters.
         *
         * If one unique numeral appears consectutively in a group of 4,
         * <code>isValid</code> is set to <code>false</code>.
         */
        private void checkNumeralAmount() {
            for (int i = 0; i < roman.length(); i++) {
                if (i + 4 <= roman.length()) {
                    quad = roman.substring(i, i + 4);
                    char first = quad.charAt(0);
                    char second = quad.charAt(1);
                    char third = quad.charAt(2);
                    char fourth = quad.charAt(3);
                    if (first == second && second == third && third == fourth) {
                        System.err.println("[" + quad + "] Too many numerals in a row!");
                        isValid = false;
                        break;
                    }
                }
            }
        }

        /** Output number.
         *
         * This is run once the input is validated. It checks whether the
         * <code>current</code> character is subtractive or basic, and invokes
         * the relevant method.
         *
         * @return final number to be outputted
         */
        private int outputNum() {
            for (int i = 0; i < roman.length(); i++) {

                current = Character.toString(roman.charAt(i));

                /* If current numeral is subtractive */
                if (isSubtractive(i) == true) {
                    complexNum(pair);
                    i = i + 1;
                }
                /* These methods don't work. */
                //else if (isOverbar(i) == true) {
                //    basicNum();
                //}
                //else if (isSubtractiveOverbar(i) == true) {
                //    complexNum(current);
                //}
                else { //or basic...
                    basicNum(current);
                }

                result = number;

                if (assertsEnabled) { //if testing...
                    String diagnostics = "Current: %1s\tResult: %2d\tNumber: %3d\tPair: %4s\tQuad: %5s";
                    System.out.println(String.format(diagnostics, current, result, number, pair, quad));
                }
            }
            return number;
        }

        /** If numeral is subtractive.
         *
         * This runs through the input in pairs, and if it finds a
         * 'subtractive' numeral, it <code>get</code>s the equivalent value
         * from the <code>s_converted</code> HashMap.
         *
         * @param index current index of the character in the
         * <code>roman</code> String
         * @return <code>true</code> if the current number is subtractive,
         * <code>false</code> if not
         */
        private boolean isSubtractive(int index) {
            if (index + 2 <= roman.length()) {
                pair = roman.substring(index, index + 2);
                if (s_converted.containsKey(pair)) {
                    return true;
                }
            }
            return false;
        }

        //private boolean isOverbar(int index) {
        //    if (index + 2 <= roman.length()) {
        //        pair = roman.substring(index, index + 2);
        //        if (Character.toString(pair.charAt(1)) == "^") {
        //            current = pair;
        //            return true;
        //        }
        //    }
        //    return false;
        //}

        //private boolean isSubtractiveOverbar(int index) {
        //    if (index + 4 <= roman.length()) {
        //        quad = roman.substring(index, index + 4);
        //        if (Character.toString(quad.charAt(1)) == "^" &&
        //            Character.toString(quad.charAt(3)) == "^") {
        //            current = quad;
        //            return true;
        //        }
        //    }
        //    return false;
        //}

        /** Basic roman numeral process.
         *
         * Simply <code>get</code>s the roman numeral from the
         * <code>converted</code> HashMap, and sets <code>number</code> equal
         * to it plus <code>result</code>. The <code>result</code> variable
         * keeps the previous value of the <code>number</code> variable, thus
         * iteratively increasing the value each time until complete.
         *
         * @param basic the current basic numeral
         */
        private void basicNum(String basic) {
            number = converted.get(basic) + result;
        }

        /** Subtractive roman numeral process.
         *
         * Very similar behaviour to the <a href = #basicNum>basicNum</a>
         * method. The only difference being that the values are accessed from
         * the <code>s_converted</code> HashMap.
         *
         * @param subtractive the current subtractive pair
         */
        private void complexNum(String subtractive) {
            number = s_converted.get(subtractive) + result;
        }
    }

    /** Check user integer input.
     *
     * Checks if the number the user has typed is too large, and if it is it
     * displays an error message.
     * If not, the number is processed in the NumToRoman class.
     * <p>
     * Also runs the methods that return certain values for Applet access.
     *
     * @param input the users integer input
     */
    public void getNum(int input) {
        num = input;
        if (num > 399999999) {
            System.err.println(num + " is too large!");
        }
        else {
            numToRoman.processNum();
            returnRoman();
            returnLength();
        }
    }

    /** Check user String input.
     *
     * Simply checks the users String input and feeds it into the <a href =
     * #NumToRoman>NumToRoman</a> class.
     *
     * @param input the users String input
     */
    public void getRoman(String input) {
        RomanToNum romanToNum = new RomanToNum();
        roman = input;
        romanToNum.processRoman();
    }

    /** Length of number for Applet access.
     *
     * @return length of input number
     */
    public int returnLength() {
        return numToRoman.numLength;
    }

    /** Roman value for Applet access.
     *
     * @return final roman numeral value
     */
    public StringBuffer returnRoman() {
        return numToRoman.total;
    }
}

/* User Manual:
 * The GUI will be relatively simple, with a title and multiple text boxes
 * that will fill with information (diagnostics perhaps?) as they type
 * their input. One goal is for the boxes to update as the user types
 * information in; I think that it would be quite a unique feature.
 * The main sections will be:
 * - A place to type a number/roman number
 * - A box for output to be displayed
 * - Buttons for overbar, insert fractions (if implemented)
 */

/* ALL OF THIS APPLET BEHAVIOUR SOURCED FROM DUANE */

/** Emulates the old Applet behaviour which is no longer supported.
 *
 * This class serves to provide the same sort of hooks (`init`, `start`,
 * `paint`, `stop`, and `destroy`) that the Applet mechanism used to provide,
 * while automatically handling all of the Frame construction, setup, and
 * teardown. 
 *
 * @author Duane Leslie
 */
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

    /** Create a FakeApplet of size 600x300 pixels.
     *
     * When this constructor is called the Applet will immediately construct
     * itself, and then on the Event Dispatch Thread it will run its `init()`
     * method, display itself, and then run the `start()` method.
     */
    public FakeApplet() {
        this(600, 300);
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

/** Constructs the Applet's graphics and connects it to the converters
 * functions.
 */
class MyApplet extends FakeApplet {

    /** Converter object. */
    private Converter convert;

    /** TextField for user input. */
    private TextField input;

    /** Runs once the Applet has started. 
     * 
     * Contains the java.awt classes to construct the graphics that are then
     * painted by the paint() method.
     */
    public void init() {
        super.init();

        convert = new Converter();

        Color cream = new Color(193, 175, 156); //custom colour

        Panel core = new Panel(new BorderLayout());
        core.setBackground(cream);

        /* Title and Info */
        JLabel titleAndInfo = new JLabel(
          "<html>"
        + "<font size = +2><b><u>"
        + "Roman Numeral Converter"
        + "</u></b></font>"
        + "<br>"
        + "Type a number or roman numeral, and the result will be outputted."
        + "</html>");

        /* All the different Panels */
        Panel diagnostics = new Panel(new BorderLayout());

        Panel diagHeading = new Panel(new GridLayout(1, 3));

        JPanel diagGrid = new JPanel(new BorderLayout());

        JPanel gridLeft = new JPanel(new GridLayout(9, 1));

        JPanel gridCenter = new JPanel(new GridLayout(9, 1));

        JPanel gridRight = new JPanel(new GridLayout(9, 1));

        input = new TextField(10);

        JLabel output = new JLabel("Output is: ");

        /* This listens to any actions performed */
        ActionListener getNum = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    /* Feed TextField contents into getNum method */
                    convert.getNum(Integer.parseInt(input.getText()));
                }
                catch (NumberFormatException r) {
                    output.setText("Input a valid number!");
                }
                convert.getNum(Integer.parseInt(input.getText()));
                switch (e.getActionCommand()) {
                    case ("ENTER"): //if ENTER button clicked
                        input.setText("");
                        output.setText("Output is: " + convert.returnRoman());
                        break;
                }
            }
        };

        JButton enter = new JButton("ENTER");
        enter.addActionListener(getNum);
        core.add(enter, BorderLayout.EAST);

        String gridAmount = Character.toString(convert.returnLength());

        /* Headings */
        for (String heading : "Current Numeral % Amount % Total".split("%")) {
            JLabel label = new JLabel(heading, SwingConstants.CENTER);
            diagHeading.add(label);
        }

        /* Generate grid boxes */
        for (int i = 0; i < 9; i++) {
            JLabel gridL = new JLabel("left");
            JLabel gridC = new JLabel("center");
            JLabel gridR = new JLabel("right");
            gridLeft.add(gridL);
            gridCenter.add(gridC);
            gridRight.add(gridR);
        }

        /* Add Panels to Core Panels for formatting on the screen */
        diagnostics.add(diagHeading, BorderLayout.CENTER);
        diagnostics.add(output, BorderLayout.NORTH);
        diagnostics.add(diagGrid, BorderLayout.SOUTH);

        diagGrid.add(gridLeft, BorderLayout.WEST);
        diagGrid.add(gridCenter, BorderLayout.CENTER);
        diagGrid.add(gridRight, BorderLayout.EAST);

        core.add(titleAndInfo, BorderLayout.NORTH);
        core.add(input, BorderLayout.CENTER);
        core.add(diagnostics, BorderLayout.SOUTH);

        add(core);
    }

    /** Paints the container. */
    public void paint(Graphics g) {
        super.paint(g);
    }
}

/** Main class.
 *
 * Where the code is first compiled. The user input is taken in here, and is
 * analysed by the Scanner class for conversion in the Converter class. This
 * is dependant on the type.
 */
class Main {

    /** An assert to whether or not the program is in testing mode. */
    private static boolean assertsEnabled = false;
    static {
        assert assertsEnabled = true;
    }

    /** User Manual text document name. */
    private static final String USER_MANUAL = "user_manual.txt";

    /** Get user input.
     *
     * By using the Scanner class to analyse the type of input, it runs either
     * the <a href = #getNum>getNum</a> method or the <a href =
     * #getRoman>getRoman</a> method.
     *
     * @param convert Converter class object
     * @param input user Scanner input
     */
    public static void getInput(Converter convert, Scanner input) {
        while (input.hasNext()) { //while there is something...
                if (input.hasNextInt()) { //if it is an integer
                    convert.getNum(input.nextInt());
                }
                /* Couldn't get this working */
                //if (input.hasNext("u")) { 
                //    convert.getRoman(input.next());
                //    userManual();
                //}
                else if (input.hasNext()) { //specifically a String!
                    convert.getRoman(input.next());
                }
            }
        }

    /** Access Command-line User Manual contents.
     *
     * This method gets an external text document and views the contents.
     */
    public static void userManual() {
        String read = "";
        try { //try this code
            read = Files.readString(Path.of(USER_MANUAL));
        }
        catch (IOException e) { //catch an exception
            System.err.println("IOException: " + e.getMessage());
        }
        finally { //if an exception is caught, print this and end program
            if (USER_MANUAL == null) {
                System.err.println("No file to read!");
            }
        }
        System.out.println(read);
    }

    /** The main method.
     *
     * This is where the code is first compiled, and the multiple objects are
     * cerated for each of the classes.
     *
     * @param args ...
     */
    public static void main(String[] args) {
        System.err.println("Hello World!");
        Scanner input = new Scanner(System.in);
        System.out.println("Roman Numeral Generator");
        System.out.println("Hit 'u' to view the user manual");
        Converter converter = new Converter();

        MyApplet applet = new MyApplet();

        //this is ignored?
        if (input.hasNext("g")) {
            applet.sync(); //syncs MyApplet()
            applet.waitForClose();
        }
        if (input.hasNext("u")) {
            userManual();
        }

        getInput(converter, input);

        if (assertsEnabled) {
            System.err.println("Testing Mode Active!");
        }
    }
}
