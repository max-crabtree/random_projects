public class Main {
    private static final String RULES_FILE_NAME = "rules.txt";

    private static IOHandler io = new IOHandler();
    private static RuleFileManager file = new RuleFileManager(RULES_FILE_NAME, io);
    private static RuleValidator rules = new RuleValidator();
    private static GameManager gameManager = new GameManager(io);

    public static void main(String[] args) {
        boolean isOkay;

        file.loadRules();

        isOkay = rules.isValidRules();

        if (!isOkay) {
            return;
        }

        gameManager.startMenu();

        //Player player = gameManager.selectPlayer();
//
        //Player player = new Player(RuleConstants.STARTING_MONEY);
        //gameManager.startGameLoop(player);
    }
}
