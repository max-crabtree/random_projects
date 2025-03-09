/**
 * Simple class that defines a {@code Card} object for use in Blackjack.
 * Each {@code Card} has a {@code CardRank} and {@code CardSuit}.
 */
public class Card {
    private CardRank rank; /* The rank of the card (ACE-KING), doesn't include Joker's */
    private CardSuit suit; /* The suit of the card, purely for printing purposes */

    /**
     * Constructs a {@code Card} with the rank and suit as {@code null}.
     */
    public Card() {
        this.rank = null;
        this.suit = null;
    }

    /**
     * Constructs a {@code Card} with the {@code CardRank} as {@code rank} and {@code CardSuit} as {@code null}.
     * 
     * @param rank the {@code CardRank} to give to the {@code Card}
     */
    public Card(CardRank rank) {
        this.rank = rank;
        this.suit = null;
    }

    /**
     * Constructs a {@code Card} with the {@code CardRank} as {@code rank} and {@code CardSuit} as {@code suit}.
     * 
     * @param rank the {@code CardRank} to give to the {@code Card}
     * @param suit the {@code CardSuit} to give to the {@code Card}
     */
    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    /**
     * Gets the {@code int} value of the current {@code Card}'s {@code CardRank}.
     * 
     * @return the value of the {@code rank} 
     */
    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return String.format("[%s|%s]", rank.toString(), suit.toString());
    }
}
