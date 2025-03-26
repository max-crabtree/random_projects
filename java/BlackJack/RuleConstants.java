public class RuleConstants {
    public static int BLACKJACK_VALUE;
    public static int DEALER_STAND_VALUE;
    public static int DEALER_DELAY_MS;
    public static int MAX_SPLITS_ALLOWED;
    public static int MAX_PLAYER_TURNS;
    public static int MAX_BET;
    public static int MAX_NUMBER_OF_PLAYERS;
    public static int DECK_COUNT;
    public static int STARTING_MONEY;
    public static double PAYOUT_MULTIPLIER;
    public static double BLACKJACK_PAYOUT_MULTIPLIER;
    public static double INSURANCE_PAYOUT_MULTIPLIER;
    public static boolean LIMIT_PLAYER_TURNS;
    public static boolean CAN_SPLIT;
    public static boolean CAN_DOUBLE_DOWN;
    public static boolean CAN_EARLY_SURRENDER;
    public static boolean CAN_LATE_SURRENDER;
    public static boolean CAN_USE_INSURANCE;
    public static boolean DEALER_HITS_ON_SOFT_STAND;
    public static boolean CAN_DOUBLE_DOWN_AFTER_SPLIT;
    public static String RULES_FILE_NAME;
    public static String PLAYERS_FILE_NAME;
    public static String GAMES_FILE_NAME;

    public static void updateAll() {
        BLACKJACK_VALUE =                 (int) Rule.BLACKJACK_VALUE.getValue();
        DEALER_STAND_VALUE =              (int) Rule.DEALER_STAND_VALUE.getValue();
        DEALER_DELAY_MS =                 (int) Rule.DEALER_DELAY_MS.getValue();
        MAX_SPLITS_ALLOWED =              (int) Rule.MAX_SPLITS_ALLOWED.getValue();
        MAX_PLAYER_TURNS =                (int) Rule.MAX_PLAYER_TURNS.getValue();
        MAX_BET =                         (int) Rule.MAXIMUM_BET.getValue();
        MAX_NUMBER_OF_PLAYERS =           (int) Rule.MAX_NUMBER_OF_PLAYERS.getValue();
        DECK_COUNT =                      (int) Rule.DECK_COUNT.getValue();
        STARTING_MONEY =                  (int) Rule.STARTING_MONEY.getValue();
        PAYOUT_MULTIPLIER =            (double) Rule.PAYOUT_MULTIPLIER.getValue();
        BLACKJACK_PAYOUT_MULTIPLIER =  (double) Rule.BLACKJACK_PAYOUT_MULTIPLIER.getValue();
        INSURANCE_PAYOUT_MULTIPLIER =  (double) Rule.INSURANCE_PAYOUT_MULTIPLIER.getValue();
        LIMIT_PLAYER_TURNS =          (boolean) Rule.LIMIT_PLAYER_TURNS.getValue();
        CAN_SPLIT =                   (boolean) Rule.USE_SPLIT.getValue();
        CAN_DOUBLE_DOWN =             (boolean) Rule.USE_DOUBLE_DOWN.getValue();
        CAN_EARLY_SURRENDER =         (boolean) Rule.USE_EARLY_SURRENDER.getValue();
        CAN_LATE_SURRENDER =          (boolean) Rule.USE_LATE_SURRENDER.getValue();
        CAN_USE_INSURANCE =           (boolean) Rule.USE_INSURANCE.getValue();
        DEALER_HITS_ON_SOFT_STAND =   (boolean) Rule.DEALER_HITS_ON_SOFT_STAND_VALUE.getValue();
        CAN_DOUBLE_DOWN_AFTER_SPLIT = (boolean) Rule.ALLOW_DOUBLE_DOWN_AFTER_SPLIT.getValue();
        RULES_FILE_NAME =              (String) Rule.RULES_FILE_NAME.getValue();
        PLAYERS_FILE_NAME =            (String) Rule.PLAYERS_FILE_NAME.getValue();
        GAMES_FILE_NAME =              (String) Rule.GAMES_FILE_NAME.getValue();
    }

    public static void updateRulesFileName() {
        RULES_FILE_NAME = (String) Rule.RULES_FILE_NAME.getValue();
    }
}
