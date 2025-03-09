public class GameState {
    private Player player;
    private Dealer dealer;
    private boolean playerStand;
    private boolean dealerStand;
    private boolean playerSurrendered;

    public GameState(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        this.playerStand = false;
        this.dealerStand = false;
        this.playerSurrendered = false;
    }

    public boolean isPlayerActive() {
        return !(playerStand || playerSurrendered || playerBusted() || hasPlayerGotBlackjack());
    }

    public boolean isDealerActive() {
        return !(dealerStand || dealerBusted() || hasDealerGotBlackjack());
    }

    public boolean playerBusted() {
        return player.getHandValue() > RuleConstants.BLACKJACK_VALUE;
    }

    public boolean dealerBusted() {
        return dealer.getHandValue() > RuleConstants.BLACKJACK_VALUE;
    }

    public boolean hasPlayerStand() {
        return playerStand;
    }

    public void setPlayerStand(boolean playerStand) {
        this.playerStand = playerStand;
    }

    public boolean hasDealerStand() {
        return dealerStand;
    }

    public void setDealerStand(boolean dealerStand) {
        this.dealerStand = dealerStand;
    }

    public boolean hasPlayerSurrendered() {
        return playerSurrendered;
    }

    public void setPlayerSurrendered(boolean playerSurrendered) {
        this.playerSurrendered = playerSurrendered;
    }

    public boolean hasPlayerGotBlackjack() {
        return player.hasBlackjack();
    }

    public boolean hasDealerGotBlackjack() {
        return dealer.hasBlackjack();
    }
}
