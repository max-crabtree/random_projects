public class CardManager {
    private final int NUMBER_OF_DECKS = RuleConstants.DECK_COUNT;
    private int randomDeckValue = 0;

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
        this.randomDeckValue = rand;
        return decks[rand];
    }

    public Card peekNextCard() {
        return decks[randomDeckValue].getTopCard(); // change this implementation?
    }

    public void addCardToHand(Hand hand) {
        Deck deck = getRandomDeck();
        hand.addCard(deck.getTopCard());
        deck.removeTopCard();

        hand.calculateAceValues();
    }

    public void addCardToHand(Deck deck, Hand hand) {
        hand.addCard(deck.getTopCard());
        deck.removeTopCard();

        hand.calculateAceValues();
    }
}
