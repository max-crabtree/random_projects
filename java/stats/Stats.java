import java.util.Scanner;

public class Stats { //the class
    public static void main(String args[]) { //where the code executes from
        Scanner input = new Scanner(System.in); //sc = the input

        float min = 0; //defining variables to be accessed/changed in loop
        float max = 0;
        float count = 0;
        float sum = 0;
        float avg = 0;

        //checks for next input and if an integer, executes
        while (input.hasNextInt()) { //until non-integer or ctrl+d...
            float number = input.nextInt(); //variable for the current number
            count = count + 1;
            sum = sum + number;
            avg = sum / count;
            /*check if current or previous number is greater or less than
             *define the min and max from there...
             *
             *i couldnt figure out a way to store the previous value of the loop to compare
             *to the current value...
             */
        }

        System.out.println("Min: " + min); //outputs at the end of the loop
        System.out.println("Max: " + max);
        System.out.println("Sum: " + sum);
        System.out.println("Count: " + count);
        System.out.println("Mean: " + avg);
    }
}
