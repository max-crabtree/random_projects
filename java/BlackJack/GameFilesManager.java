import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameFilesManager {
    private static final String PLAYER_FILE_DELIM = " - ";
    private File pathToStoreDir, pathToGamesDir, pathToPlayersFile;

    public GameFilesManager(String storeDir, String gamesDir, String playersFileName) {
        this.pathToStoreDir = new File(storeDir);
        this.pathToGamesDir = new File(storeDir + gamesDir);
        this.pathToPlayersFile = new File(storeDir + playersFileName);

        pathToStoreDir.mkdirs(); // make store directory (if it doesn't exist)
        pathToGamesDir.mkdirs(); // make games directory ..

        try {
            pathToPlayersFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerToFile(Player player) {
        try {
            FileWriter writer = new FileWriter(pathToPlayersFile, true);
            writer.write(String.format("%s%s$%d\n", player.getName(), PLAYER_FILE_DELIM, player.getBankBalance()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // completely refactor this function
    public Player loadPlayerFromFile() {
        String line;
        String chosenPlayer;
        String[] tok;
        String[] playerMoneyArray = new String[2 * RuleConstants.MAX_NUMBER_OF_PLAYERS];
        StringBuffer fileStr = new StringBuffer();

        try {
            int currentToken = 0;
            BufferedReader reader = new BufferedReader(new FileReader(pathToPlayersFile));
            while ((line = reader.readLine()) != null) {
                fileStr.append(line + "\n");
                tok = line.split(PLAYER_FILE_DELIM);
                playerMoneyArray[currentToken++] = tok[0];
                playerMoneyArray[currentToken++] = tok[1];
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserInterface.printGeneral(fileStr.toString());
        while (true) {
            chosenPlayer = IOHandler.getPlayerLowercaseString("Which player would you like to load? (type name)");
            chosenPlayer = IOHandler.capitaliseString(chosenPlayer);

            // MAX_PLAYER_NUMS again
            String currentToken;
            for (int i = 0; i < 2 * 50; i++) {
                currentToken = playerMoneyArray[i];
                if (currentToken.equals(null)) {
                    break;
                }

                if (currentToken.equals(chosenPlayer)) {
                    // cut off $
                    return new Player(chosenPlayer, Integer.parseInt(playerMoneyArray[i+1].substring(1)));
                }
            }
        }
    }
}
