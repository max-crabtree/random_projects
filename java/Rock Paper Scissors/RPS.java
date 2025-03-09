import java.util.Scanner;
import java.util.Random;

public class RPS {
    char[] MOVES = {'r', 'p', 's'};
    Scanner inp = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String args[]) {
        boolean gameIsRunning = true;

        System.out.println("Rock Paper Scissors");
        System.out.print("How many rounds?: ");    
        int numberOfRounds = Integer(inp.nextLine());

        while (gameIsRunning) {
            gameIsRunning = runGame(numberOfRounds);
        }
    }

    public boolean runGame(int rounds) {
        for (int i = 1; i < rounds + 1; i++) {
            System.out.println("Round " + i + "\n");
            
            //char playerChoice = getPlayerChoice();
            System.out.print("Choose your move: ");
            char playerChoice = (inp.next()).charAt(0);
            if (!isValidChoice(playerChoice)) {
                System.out.println("Invalid choice!");
                return false;
            }

            char aiChoice = MOVES[rand.nextInt(4)];

            System.out.println(String.format("Player: %c\tAI: %c", playerChoice, aiChoice));

        }
        return false;
    }

    public  boolean isValidChoice(char choice) {
        for (int i = 0; i < MOVES.length - 1; i++) {
            if (choice != MOVES[i]) {
                return false;
            }
        }
        return true;
    }
}