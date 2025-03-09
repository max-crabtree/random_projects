public enum CardRank { 
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    private boolean aceIsOne = false;

    public int getValue() {
        switch(this) {
            case ACE:   if (aceIsOne) { return 1; } else { return 11; }
            case TWO:   return 2;
            case THREE: return 3;
            case FOUR:  return 4;
            case FIVE:  return 5;
            case SIX:   return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE:  return 9;
            case TEN:   return 10;
            case JACK:  return 10;
            case QUEEN: return 10;
            case KING:  return 10;
            default:    return 0;
        }
    }

    public void setAceToOne() {
        this.aceIsOne = true;
    }

    @Override
    public String toString() {
        switch (this) {
            case ACE:   return "A";
            case TWO:   return "2";
            case THREE: return "3";
            case FOUR:  return "4";
            case FIVE:  return "5";
            case SIX:   return "6";
            case SEVEN: return "7";
            case EIGHT: return "8";
            case NINE:  return "9";
            case TEN:   return "10";
            case JACK:  return "J";
            case QUEEN: return "Q";
            case KING:  return "K";
            default:    return "0";
        }
    }
}

