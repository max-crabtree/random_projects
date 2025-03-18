public enum Rule {
    USE_DOUBLE_DOWN(true),
    USE_SPLIT(true),
    PAYOUT_MULTIPLIER(1.5),
    DEALER_STAND_VALUE(17),
    DEALER_HITS_ON_SOFT_STAND_VALUE(true),
    DEALER_DELAY_MS(1500),
    MAX_SPLITS_ALLOWED(3),
    USE_LATE_SURRENDER(true),
    USE_EARLY_SURRENDER(false),
    BLACKJACK_PAYOUT_MULTIPLIER(2.0),
    BLACKJACK_VALUE(21),
    ALLOW_DOUBLE_DOWN_AFTER_SPLIT(true),
    DECK_COUNT(4),
    USE_INSURANCE(true),
    INSURANCE_PAYOUT_MULTIPLIER(2.0),
    LIMIT_PLAYER_TURNS(true),
    MAX_PLAYER_TURNS(5),
    MAXIMUM_BET(500),
    STARTING_MONEY(1000);
    
    private Object defaultValue;

    Rule(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /* Better way to do this? */
    public Object getValue() {
        String value = defaultValue.toString();

        if (isInteger(value)) {
            return Integer.parseInt(value);
        } else if (isDouble(value)) {
            return Double.parseDouble(value);
        } else if (isBoolean(value)) {
            return Boolean.parseBoolean(value);
        } else {
            return null;
        }    
    }

    public void setValue(String value) {
        this.defaultValue = value;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String value) {
        if (value.toLowerCase().equals("true") ||
            value.toLowerCase().equals("false")) {
            return true;
        }
        return false;
    }
}
