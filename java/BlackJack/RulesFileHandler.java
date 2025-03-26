import java.io.IOException;

public class RulesFileHandler extends FileHandler {

    public RulesFileHandler(String filename) throws IOException {
        super(filename);
        
        if (fileString.isEmpty()) {
            createDefaultRulesFile();
        }
    }

    private void createDefaultRulesFile() {
        for (Rule r : Rule.values()) {
            addKeyValuePair(r.name(), r.getValue());
        }
    }

    public void loadRules() {
        for (int i = 0; i < Rule.values().length; i++) {
            Rule currentRule = Rule.values()[i];
            currentRule.setValue(getValueOf(currentRule.name(), i*2).toString());
        }
    }
}
