import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static final String STORE_DIR = "store/";
    private static final String GAMES_DIR = "games/";
    protected static final char DELIMITER = ':';

    protected String filename;
    protected File file;
    protected StringBuilder fileString;
    protected StringBuilder[] tokenisedFileString;
    
    public FileHandler(String filename) throws IOException {
        this.filename = filename;
        this.file = createFile();

        if (!file.exists()) { // Do I need this?
            file.createNewFile();
        }

        this.fileString = fileToString();
        this.tokenisedFileString = splitFileAtDelimAndNewline();
    }

    private File createFile() {
        File file;

        if (filename.contains("game")) { // maybe change
            file = new File(STORE_DIR + GAMES_DIR + filename);
        } else {
            file = new File(STORE_DIR + filename);
        }

        return file;
    }

    private StringBuilder fileToString() {
        StringBuilder fileStr = new StringBuilder();
        int c;
        
        try {
            FileReader reader = new FileReader(file);
            while ((c = reader.read()) != -1) {
                fileStr.append((char)c);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileStr;
    }

    private StringBuilder[] splitFileAtDelimAndNewline() {
        String[] tokens = fileString.toString().split(String.format("[%s\n]", DELIMITER));
        StringBuilder[] keyAndValuePairs = new StringBuilder[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            keyAndValuePairs[i] = new StringBuilder(tokens[i].trim());
        }

        return keyAndValuePairs;
    }

    private void updateFileContents() throws IOException {
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < tokenisedFileString.length; i++) {
            writer.write(tokenisedFileString[i].toString());
            if (i % 2 == 0) {
                writer.write(String.format(" %c ", DELIMITER));   
            } else {
                writer.write('\n');
            }
        }

        writer.close();
    }

    public Object getValueOf(String key) {
        for (int i = 0; i < tokenisedFileString.length; i++) {
            if (tokenisedFileString[i].toString().equals(key)) {
                return tokenisedFileString[i+1];
            }
        }
        return null;
    }

    public Object getValueOf(String key, int start) {
        if (start % 2 != 0) { return null; }
        for (int i = start; i < tokenisedFileString.length; i++) {
            if (tokenisedFileString[i].isEmpty()) { continue; }
            if (tokenisedFileString[i].toString().equals(key)) {
                return tokenisedFileString[i+1];
            }
        }
        return null;
    }

    public void setValueOf(String key, Object newValue) {
        for (int i = 0; i < tokenisedFileString.length; i++) {
            if (tokenisedFileString[i].toString().equals(key)) {
                tokenisedFileString[i+1] = new StringBuilder(newValue.toString());
                break;
            }
        }

        try {
            updateFileContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addKeyValuePair(String key, Object value) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(String.format("\n%s %c %s", key, DELIMITER, value.toString()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public void removeKeyValuePair(String key) {
    //    for (int i = 0; i < tokenisedFileString.length; i++) {
    //        if (tokenisedFileString[i].toString().equals(key)) {
    //            tokenisedFileString[i] = new StringBuilder("");
    //            tokenisedFileString[i + 1] = new StringBuilder("");
    //            break;
    //        }
    //    }
    //
    //    try {
    //        updateFileContents();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    @Override
    public String toString() {
        return fileToString().toString() + "\n";
    }
}
