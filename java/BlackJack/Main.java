import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        
        boolean isOkay;
        RulesFileHandler rulesFile;
        FileHandler playersFile;
        InitialiseGame init;

        RuleConstants.updateRulesFileName();

        rulesFile = new RulesFileHandler(RuleConstants.RULES_FILE_NAME);
        rulesFile.loadRules();

        RuleConstants.updateAll();

        isOkay = RuleValidator.isValidRules();

        if (!isOkay) { return; }

        playersFile = new FileHandler(RuleConstants.PLAYERS_FILE_NAME);
        init = new InitialiseGame(rulesFile, playersFile);

        init.startMenu();
    }
}
