public enum CardSuit { 
    HEARTS, DIAMONDS, CLUBS, SPADES;

    @Override
    public String toString() {
        switch(this) {
            case HEARTS:   return "H";
            case DIAMONDS: return "D";
            case CLUBS:    return "C";
            case SPADES:   return "S";
            default:       return "?";
        }
    }
}
