public class PlayerActionHandler {
    private Player player;
    private Dealer dealer;
    private GameState state;
    private CardManager cards;
    private int bet;
    private PayoutHandler payout;

    public PlayerActionHandler(Player player, Dealer dealer, GameState state, CardManager cards, int bet, PayoutHandler payout) {
        this.player = player;
        this.dealer = dealer;
        this.state = state;
        this.cards = cards;
        this.bet = bet;
        this.payout = payout;
    }
    
    public void doPlayerMove(Move move) {
        switch (move) {
            case Move.HIT: hit(); break;
            case Move.STAND: state.setPlayerStand(true); break;
            case Move.DOUBLE_DOWN: doubleDown(); break;
            case Move.SPLIT: split(); break;
            default: break;
        }
    }

    public void hit() {
        cards.addCardToHand(player.getHand());

        if (state.playerBusted()) {
            bust();
        }
    }

    public void bust() {
        UserInterface.printHands(player, dealer);
        UserInterface.printFail(String.format("You busted! You lost $%d\n%s\n", bet, player.getBankAccount().toString()));
    }

    public void doubleDown() {
        boolean canWithdraw;

        if (!RuleValidator.canDoubleDown(player)) {
            doPlayerMove(IOHandler.getPlayerMove());
            return;
        }

        canWithdraw = false;

        try {
            player.getBankAccount().withdraw(bet);
            canWithdraw = true;
        } catch (InvalidWithdrawalException e) {
            UserInterface.printError(e.getMessage());
        }

        if (canWithdraw) {
            UserInterface.printGeneral(String.format("You double down, bringing your bet to $%d!\n", bet * 2));
            cards.addCardToHand(player.getHand());
            bet = bet * 2;
            state.setPlayerStand(true);
        }
    }

    public void split() {
        if (!RuleValidator.canSplit(player)) {
            doPlayerMove(IOHandler.getPlayerMove());
            return;
        }

        // player.makeSplitDeck(playerCards[1]); // make this logic better
        /* More split logic here */
    }

    public void handleSurrender() {
        UserInterface.printHands(player, dealer);
        state.setPlayerSurrendered(IOHandler.getPlayerYesOrNo("Would you like to surrender and get back half your bet?"));
        if (state.hasPlayerSurrendered()) {
            payout.surrenderBet();
        }
    }

    public void insurance(boolean isActive) {
        UserInterface.printGeneral(String.format("You will wager another $%d for insurance...\n", bet / 2));

        try {
            player.getBankAccount().withdraw(bet / 2);
        } catch (InvalidWithdrawalException e) {
            UserInterface.printError(e.getMessage());
        }

        //cards.addCardToHand(dealer.); ...
        if (state.hasDealerGotBlackjack()) {
            UserInterface.printSuccess("Your insurance bet was successful! $%d has been deposited into your account\n");
            payout.insuranceBet();
        } else {
            UserInterface.printGeneral("Your insurance bet was unsuccessful! Your insurance bet has been withdrawn from your account\n");
        }
    }
}
