public class CardManager {
    private final int NUMBER_OF_DECKS = RuleConstants.DECK_COUNT;

    private Deck[] decks;

    public CardManager() {
        this.decks = new Deck[NUMBER_OF_DECKS];

        for (int i = 0; i < NUMBER_OF_DECKS; i++) {
            decks[i] = new Deck();
            decks[i].shuffleDeck();
        }
    }

    public Deck getRandomDeck() {
        int rand = (int) (Math.random() * decks.length);
        return decks[rand];
    }

    public Card peekNextCard() {
        return decks[0].getTopCard(); // change this implementation?
    }

    public void addCardToHand(Hand hand) {
        Deck deck = getRandomDeck();
        hand.addCard(deck.getTopCard());
        deck.removeTopCard();

        hand.calculateAceValues();
    }
}
