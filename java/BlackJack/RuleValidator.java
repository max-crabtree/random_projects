public class RuleValidator {

    public RuleValidator() {}
    
    public boolean isValidRules() {
        Rule[] cantBeZeroOrLessRules = { 
            Rule.BLACKJACK_VALUE, Rule.BLACKJACK_PAYOUT_MULTIPLIER, Rule.BLACKJACK_VALUE, 
            Rule.BLACKJACK_PAYOUT_MULTIPLIER, Rule.INSURANCE_PAYOUT_MULTIPLIER, Rule.DEALER_STAND_VALUE,
            Rule.DECK_COUNT, Rule.MAXIMUM_BET, Rule.MAX_SPLITS_ALLOWED, Rule.MAX_PLAYER_TURNS , Rule.STARTING_MONEY
        };

        Rule[] cantBeDoubleRules = {
            Rule.BLACKJACK_VALUE, Rule.DEALER_STAND_VALUE, Rule.DECK_COUNT, Rule.MAXIMUM_BET,
            Rule.MAX_SPLITS_ALLOWED, Rule.MAX_PLAYER_TURNS, Rule.DEALER_DELAY_MS, Rule.STARTING_MONEY
        };

        for (Rule rule : Rule.values()) {
            if (rule.getValue() == null) {
                UserInterface.printError(String.format("%s value is NULL!", rule.name()));
                return false;
            }
        }

        if (ruleIsZeroOrLess(cantBeZeroOrLessRules)) {
            return false;
        }

        if (ruleIsDouble(cantBeDoubleRules)) {
            return false;
        }

        if (RuleConstants.DEALER_STAND_VALUE > RuleConstants.BLACKJACK_VALUE) {
            UserInterface.printError("Invalid rules set! DEALER_STAND_VALUE must be less than BLACKJACK_VALUE!");
            return false;
        }

        /* Add more... */

        return true;
    }

    private boolean ruleIsZeroOrLess(Rule[] rules) {
        Object ruleValue;
        for (Rule rule : rules) {
            ruleValue = rule.getValue();
            if (ruleValue instanceof Integer && (int)ruleValue <= 0 ||
                ruleValue instanceof Double  && (double)ruleValue <= 0) {
                UserInterface.printError(String.format("%s cannot be <= 0!\n", rule.name()));
                return true;
            }
        }
        return false;
    }

    private boolean ruleIsDouble(Rule[] rules) {
        Object ruleValue;
        for (Rule rule : rules) {
            ruleValue = rule.getValue();
            if (ruleValue instanceof Double) {
                UserInterface.printError(String.format("%s cannot be a Double!\n", rule.name()));
                return true;
            }
        }
        return false;
    }

    public static boolean canDoubleDown(Player player) {
        if (!RuleConstants.CAN_DOUBLE_DOWN) {
            UserInterface.printError("Double down disabled in rules!\n");
            return false;
        }

        if (!RuleConstants.CAN_DOUBLE_DOWN_AFTER_SPLIT && player.getTotalSplits() > 0) {
            UserInterface.printError("Double down after split disabled in rules!\n");
            return false;
        }
        
        return true;
    }

    public static boolean canSplit(Player player) {
        if (player.getTotalSplits() + 1 > RuleConstants.MAX_SPLITS_ALLOWED) {
            UserInterface.printError("Maximum number of splits reached!\n");
            return false;
        }

        if (!RuleConstants.CAN_SPLIT) {
            UserInterface.printError("Splitting disabled in rules!\n");
            return false;
        }

        Card[] playerCards = player.getHand().getCards();
        
        // current move must be ONE as well! add this later
        if (playerCards[0].getValue() != playerCards[1].getValue()) {
            UserInterface.printError("You can't split!\n");
            return false;
        }

        return true;
    }
}
