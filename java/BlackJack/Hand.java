public class Hand {
    final int MAX_NUMBER_OF_CARDS = 100;
    
    private int numberOfCards;
    private Card[] cards;

    public Hand() {
        this.cards = new Card[MAX_NUMBER_OF_CARDS];
        this.numberOfCards = 0;
    }

    public Hand(Card card) {
        this.cards = new Card[MAX_NUMBER_OF_CARDS];
        cards[0] = card;
        this.numberOfCards = 1;
    }

    public boolean isEmpty() {
        return (cards[0] == null);
    }

    public int getHandValue() {
        int tempValue = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            tempValue += cards[i].getValue();
        }
        return tempValue;
    }

    public Card[] getCards() {
        return cards;
    }

    public int getNumberOfCards() {
        int tempNum = 0;

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            tempNum++;
        }

        numberOfCards = tempNum;
        return numberOfCards;
    }

    public void addCard(Card card) {
        cards[numberOfCards] = card;
        numberOfCards++;
    }

    public void calculateAceValues() {
        for (int i = 0; i < getNumberOfCards(); i++) {
            if (cards[i].getRank() != CardRank.ACE) {
                continue;
            }

            if (getHandValue() <= RuleConstants.BLACKJACK_VALUE) {
                continue;
            }
            
            if (getHandValue() > RuleConstants.BLACKJACK_VALUE) {
                cards[i].getRank().setAceToOne();
            }
        }
    }

    public boolean contains(Card card) {
        for (int i = 0; i < getNumberOfCards(); i++) {
            if (cards[i].getRank() == card.getRank()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer finalString = new StringBuffer();

        if (isEmpty()) {
            return "Hand is empty!";
        }

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            finalString.append(cards[i].toString() + " ");
        }

        return String.format("Hand: %s | Value: %d\n", finalString.toString(), getHandValue());
    }
}
