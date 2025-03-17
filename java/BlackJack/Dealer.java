public class Dealer {

    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public int getHandValue() {
        return hand.getHandValue();
    }

    public boolean hasBlackjack() {
        return (getHandValue() == RuleConstants.BLACKJACK_VALUE);
    }

    @Override
    public String toString() {
        return "Dealer " + hand.toString(); 
    }
}