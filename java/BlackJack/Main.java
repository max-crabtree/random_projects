import java.io.IOException;

public class Main {
    private static final String RULES_FILE_NAME = "rules.txt";
    private static final String PLAYERS_FILE_NAME = "players.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        
        FileHandler rulesFile;
        FileHandler playersFile;
        InitialiseGame init;

        rulesFile = new FileHandler(RULES_FILE_NAME);
        playersFile = new FileHandler(PLAYERS_FILE_NAME);
        init = new InitialiseGame(rulesFile, playersFile);

        init.startMenu();
    }
}
