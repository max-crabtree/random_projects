import java.util.Scanner;

public class Guess {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int i =10;
        while (sc.hasNextInt()) {
            int guess = sc.nextInt();
            if (guess < i) {
                System.out.println("Higher");
            } else if (guess > i) {
                System.out.println("Lower");
            } else {
                break;
            }
        }
        System.out.println("You Win");

//        int i;
//        int x;
//        System.out.println("Hello World!");
//        System.out.println("I am learning Java.");
//        System.out.println("It is very fun!");
//        i = 5;
//        x = 10;
//        System.out.println(i + " + " + i + " = " + x + ".");
//        i = 10;
//        x = 20;
//        System.out.println(i + " + " + i + " = " + x + ".");
//        int y = 10;
//        while (y > 0) {
//            System.out.println(y);
//            y = y - 1;
//        }
//        System.out.println("Zero");
    }
}

