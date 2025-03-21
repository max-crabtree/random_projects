public class Main {
    private static final String RULES_FILE_NAME = "rules.txt";
    private static final String STORE_DIR = "store/";
    private static final String PLAYERS_FILE_NAME = "players.txt";
    private static final String GAMES_DIR = "games/";

    private static RuleFileManager ruleFile = new RuleFileManager(STORE_DIR + RULES_FILE_NAME);
    private static GameFilesManager gameFiles = new GameFilesManager(STORE_DIR, GAMES_DIR, PLAYERS_FILE_NAME);
    private static RuleValidator rules = new RuleValidator();
    private static GameManager gameManager = new GameManager(gameFiles);

    public static void main(String[] args) {
        boolean isOkay;

        ruleFile.loadRules();

        isOkay = rules.isValidRules();

        if (!isOkay) {
            return;
        }

        gameManager.startMenu();
    }
}
