public class GameState {
    private Player player;
    private Dealer dealer;
    private boolean playerStand;
    private boolean playerSurrendered;

    public GameState(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        this.playerStand = false;
        this.playerSurrendered = false;
    }

    public boolean isPlayerActive() {
        return !(playerStand || playerSurrendered || playerBusted() || hasPlayerGotBlackjack());
    }

    public boolean isDealerActive() {
        return !(dealerWillStand() || dealerBusted() || hasDealerGotBlackjack());
    }

    public boolean playerBusted() {
        return player.getHandValue() > RuleConstants.BLACKJACK_VALUE;
    }

    public boolean dealerBusted() {
        return dealer.getHandValue() > RuleConstants.BLACKJACK_VALUE;
    }

    public void setPlayerStand(boolean playerStand) {
        this.playerStand = playerStand;
    }

    // may redo this...
    public boolean dealerWillStand() {
        boolean dealerSoftStand = RuleConstants.DEALER_HITS_ON_SOFT_STAND && dealer.getHandValue() == RuleConstants.DEALER_STAND_VALUE;
        boolean dealerHandContainsAce = dealer.getHand().contains(new Card(CardRank.ACE));
        boolean dealerStandValueButNotBust = dealer.getHandValue() > RuleConstants.DEALER_STAND_VALUE && dealer.getHandValue() <= RuleConstants.BLACKJACK_VALUE;
        
        return (dealerSoftStand && dealerHandContainsAce) || (dealerStandValueButNotBust);
    }

    public boolean hasPlayerSurrendered() {
        return playerSurrendered;
    }

    public void setPlayerSurrendered(boolean playerSurrendered) {
        this.playerSurrendered = playerSurrendered;
    }

    public boolean hasPlayerGotBlackjack() {
        return player.getHandValue() == RuleConstants.BLACKJACK_VALUE;
    }

    public boolean hasDealerGotBlackjack() {
        return dealer.getHandValue() == RuleConstants.BLACKJACK_VALUE;
    }
}
