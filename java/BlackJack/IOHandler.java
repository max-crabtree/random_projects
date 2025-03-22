import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {
    private static Scanner inp = new Scanner(System.in);

    public IOHandler() {}

    public static Move getPlayerMove() {
        while (true) {
            UserInterface.printGeneral("What move would you like to make?: ");
            switch (inp.nextLine().toLowerCase()) {
                case "h": case "hit": return Move.HIT;
                case "s": case "stand": return Move.STAND;
                case "dd": case "double down": return Move.DOUBLE_DOWN;
                case "split": return Move.SPLIT;
                default:
                    UserInterface.printGeneral("Possible moves are: ");
                    for (Move move : Move.values()) { 
                        UserInterface.printGeneral(move.toString().toLowerCase() + " "); 
                    }
                    UserInterface.printGeneral("\n");
                    continue;
            }
        }
    }

    public static boolean getPlayerYesOrNo(String prompt) {
        while (true) {
            UserInterface.printGeneral(prompt + ": ");
            switch (inp.nextLine().toLowerCase()) {
                case "n": case "no": case "nah": return false;
                case "y": case "yes": case "yeah": return true;
                default: continue;
            }
        }
    }

    public static int getPlayerInt(String prompt) {
        int inputtedInt;

        while (true) {
            UserInterface.printGeneral(prompt + ": ");
            try {
                inputtedInt = inp.nextInt();
                flushStream();
                return inputtedInt;
            } catch (InputMismatchException e) {
                flushStream();
                continue;
            }
        }
    }

    public static String getPlayerLowercaseString(String prompt) {
        UserInterface.printGeneral(prompt + ": ");
        return inp.nextLine().toLowerCase();
    }

    public static void flushStream() {
        inp.nextLine();
    }

    public static String capitaliseString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
