public class Deck {
    private final int NUMBER_OF_SUITES = 4;
    private final int CARDS_IN_A_SUIT = 13;

    private Stack cards = new Stack();

    public Deck() {
        cards = constructDeck();
    }

    private Stack constructDeck() {
        CardSuit[] cardSuites = CardSuit.values();
        CardRank[] cardRanks = CardRank.values();
        CardSuit currentSuit;
        CardRank currentRank;

        for (int i = 0; i < NUMBER_OF_SUITES; i++) {
            currentSuit = cardSuites[i];
            for (int j = 0; j < CARDS_IN_A_SUIT; j++) {
                currentRank = cardRanks[j];
                cards.push(new Card(currentRank, currentSuit));
            }
        }

        return cards;
    }

    public Card getTopCard() {
        return (Card)cards.getTop().getData();
    }

    public void removeTopCard() {
        cards.pop();
    }

    public void shuffleDeck() {
        int rand;
        Card tempCard;
        Card[] cardsArray;

        cardsArray = cards.cardsToArray();
        cards.emptyStack();

        /* Fisher-Yates Shuffle */
        for (int i = cardsArray.length; i > 0; i--) {
            rand = (int) (Math.random() * i);
            tempCard = cardsArray[i - 1];
            cardsArray[i - 1] = cardsArray[rand];
            cardsArray[rand] = tempCard;
        }

        for (int i = 0; i < cardsArray.length; i++) {
            cards.push(cardsArray[i]);
        }
    }

    @Override
    public String toString() {
        StringBuffer finalString = new StringBuffer();
        Node currentCard = cards.getTop();
        int counter = 1;

        while (currentCard != null) {
            finalString.append(currentCard.getData().toString() + "\t");
            if (counter == CARDS_IN_A_SUIT) {
                finalString.append("\n");
                counter = 0;
            }
            currentCard = currentCard.getNext();
            counter++;
        }

        return finalString.toString();
    }
}
