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

//if ((RuleConstants.DEALER_HITS_ON_SOFT_STAND && 
//dealer.getHand().getHandValue() == RuleConstants.DEALER_STAND_VALUE && 
//dealer.getHand().contains(new Card(CardRank.ACE))) ||
//dealer.getHandValue() > RuleConstants.DEALER_STAND_VALUE && 
//dealer.getHandValue() <= RuleConstants.BLACKJACK_VALUE) 
//{
//UserInterface.printHands(player, dealer);
//UserInterface.printGeneral(String.format("Dealer stands on %d!", dealer.getHandValue()));
//state.setDealerStand(true);
//}
