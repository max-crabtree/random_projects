import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        int height = 0;

        System.out.println("Christmas Tree Generator");

        System.out.print("Input height of tree: ");

        try {
            height = inp.nextInt();
        } catch (InputMismatchException e) {
            System.err.println(height + " is not a valid number");
            return;
        }

        if (height <= 0) { 
            System.err.println("Height must be greater than 0"); 
            return; 
        }

        Tree tree = new Tree(height);

        tree.generateTree();
    }
}
