import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RuleFileManager {
    String fileName;
    IOHandler io;

    public RuleFileManager(String fileName, IOHandler io) {
        this.fileName = fileName;
        this.io = io;
    }

    /* Improve this code */
    public void loadRules() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));    
            parseRulesFile(reader);
        } catch (FileNotFoundException e) {
            boolean isCreatingNew = io.getPlayerYesOrNo(String.format("File %s not found! Would you like to create a new file with default values?: ", fileName));
            if (isCreatingNew) {
                makeNewDefaultFile();
            } else {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewSettings() {
        StringBuffer settings = new StringBuffer();

        for (Rule rule : Rule.values()) {
            settings.append(String.format("%s: %s\n", rule.name(), rule.getValue().toString()));
        }

        UserInterface.printGeneral(settings + "\n");
    }

    private void makeNewDefaultFile() {
        StringBuffer fileText = new StringBuffer();

        for (Rule rule : Rule.values()) {
            fileText.append(String.format("%s: %s\n", rule.name(), rule.getValue().toString()));
        }

        try {
            FileWriter newFile = new FileWriter(fileName);
            newFile.write(fileText.toString());
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRulesFile(BufferedReader reader) throws IOException {
        String line;
        String[] tokens;

        while ((line = reader.readLine()) != null) {
            tokens = line.split(":");
            if (tokens.length != 2) {
                continue;
            }
            setRulesValues(tokens);
        }
    }

    private void setRulesValues(String[] tokens) {
        Rule currentRule;

        for (int i = 0; i < Rule.values().length; i++) {
            currentRule = Rule.values()[i];
            if (currentRule.name().equals(tokens[0].trim())) {
                currentRule.setValue(tokens[1].trim());
                break;
            }
        }
    }
}
