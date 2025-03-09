public enum Decorations {
    RED_BAUBLE("\u001B[31m●\u001B[0m"),       // Red bauble
    GREEN_BAUBLE("\u001B[32m●\u001B[0m"),     // Green bauble
    BLUE_BAUBLE("\u001B[34m●\u001B[0m"),      // Blue bauble
    GOLD_STAR("\u001B[33m★\u001B[0m"),        // Gold star
    SILVER_STAR("\u001B[37m☆\u001B[0m"),      // Silver star
    RED_LIGHT("\u001B[31m⬤\u001B[0m"),       // Red light
    GREEN_LIGHT("\u001B[32m⬤\u001B[0m"),     // Green light
    YELLOW_LIGHT("\u001B[33m⬤\u001B[0m"),    // Yellow light
    WHITE_LIGHT("\u001B[97m⬤\u001B[0m"),     // White light
    GOLD_TINSEL("\u001B[33m~~~~~\u001B[0m"),  // Gold tinsel
    SILVER_TINSEL("\u001B[37m~~~~~\u001B[0m"),// Silver tinsel
    CANDY_CANE("\u001B[31m|\u001B[0m\u001B[97m|\u001B[0m"), // Candy cane
    SNOWFLAKE("\u001B[36m❄\u001B[0m"),       // Snowflake
    BLINKING_RED_LIGHT("\u001B[5;31m⬤\u001B[0m"),  // Blinking red light
    BLINKING_GREEN_LIGHT("\u001B[5;32m⬤\u001B[0m"), // Blinking green light
    BLINKING_YELLOW_LIGHT("\u001B[5;33m⬤\u001B[0m"), // Blinking yellow light
    BLINKING_WHITE_LIGHT("\u001B[5;97m⬤\u001B[0m"),  // Blinking white light
    BLINKING_GOLD_STAR("\u001B[5;33m★\u001B[0m"),    // Blinking gold star
    BLINKING_SILVER_STAR("\u001B[5;37m☆\u001B[0m");  // Blinking silver star

    private final String value;

    private Decorations(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
