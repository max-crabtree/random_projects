import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {
    Scanner inp = new Scanner(System.in);

    public Move getPlayerMove() {
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
                        UserInterface.printGeneral(move + " "); 
                    }
                    UserInterface.printGeneral("\n");
                    continue;
            }
        }
    }

    public boolean getPlayerYesOrNo(String prompt) {
        while (true) {
            UserInterface.printGeneral(prompt + ": ");
            switch (inp.nextLine().toLowerCase()) {
                case "n": case "no": case "nah": return false;
                case "y": case "yes": case "yeah": return true;
                default: continue;
            }
        }
    }

    public int getPlayerInt(String prompt) {
        int inputtedInt;

        while (true) {
            UserInterface.printGeneral(prompt + ": ");
            try {
                inputtedInt = inp.nextInt();
                return inputtedInt;
            } catch (InputMismatchException e) {
                flushStream();
                continue;
            }
        }
    }

    public String getPlayerLowercaseString() {
        return inp.nextLine().toLowerCase();
    }

    public void flushStream() {
        inp.nextLine();
    }
}
