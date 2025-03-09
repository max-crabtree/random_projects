public enum Move { 
    HIT, STAND, DOUBLE_DOWN, SPLIT;

    @Override
    public String toString() {
        switch(this) {
            case HIT: return         "Hit";
            case STAND: return       "Stand";
            case DOUBLE_DOWN: return "Double Down";
            case SPLIT: return       "Split";
            default: return          "?";
        }
    }
}
