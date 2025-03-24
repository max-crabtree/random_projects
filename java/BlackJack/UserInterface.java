public class UserInterface {

    public UserInterface() {}

    public static void printGeneral(String message, Object... formatArgs) {
        System.out.print(TerminalColour.WHITE.apply(String.format(message, formatArgs)));
    }

    public static void printSuccess(String message, Object... formatArgs) {
        System.out.print(TerminalColour.GREEN.apply(String.format(message, formatArgs)));
    }

    public static void printFail(String message, Object... formatArgs) {
        System.out.print(TerminalColour.BG_RED.apply(String.format(message, formatArgs)));
    }

    public static void printError(String message, Object... formatArgs) {
        System.out.print(TerminalColour.RED.apply("XX " + message + " XX"));
    }

    public static void printCustom(String message, TerminalColour colour, Object... formatArgs) {
        System.out.print(colour.apply(String.format(message, formatArgs)));
    }

    public static void printHands(Player player, Dealer dealer) {
        System.out.print("\n" + dealer.toString());
        System.out.print(player.toString() + "\n");
    }

    public static void printStartMenu() {
        printCustom("s) view settings\n" + 
                    "l) load existing player\n" +
                    "n) create new player\n" +
                    "q) quit game\n", TerminalColour.YELLOW);
    }
}
