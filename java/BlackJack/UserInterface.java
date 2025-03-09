public class UserInterface {

    public UserInterface() {}

    public static void printGeneral(String message) {
        System.out.print(TerminalColour.WHITE.apply(message));
    }

    public static void printSuccess(String message) {
        System.out.print(TerminalColour.GREEN.apply(message));
    }

    public static void printFail(String message) {
        System.out.print(TerminalColour.BG_RED.apply(message));
    }

    public static void printError(String message) {
        System.out.print(TerminalColour.RED.apply("XX " + message + " XX"));
    }

    public static void printCustom(String message, TerminalColour colour) {
        System.out.print(colour.apply(message));
    }

    public static void printHands(Player player, Dealer dealer) {
        System.out.print("\n" + dealer.toString());
        System.out.print(player.toString() + "\n");
    }

    public static void printMainMenu() {
        printCustom("s) view settings\n" + 
                    "l) load existing player\n" +
                    "n) create new player\n:", TerminalColour.YELLOW);
    }
}
