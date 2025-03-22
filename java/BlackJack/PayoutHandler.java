public class PayoutHandler {
    private GameState state;
    private Player player;
    private int bet;
    
    public PayoutHandler(GameState state, Player player, int bet) {
        this.state = state;
        this.player = player;
        this.bet = bet;
    }

    public void pushBet() {
        UserInterface.printGeneral("Hands are equal so there is a push! Your account has been deposited the initial bet of $%d\n", bet);
        player.getBankAccount().deposit(bet);
    }

    public void win() {
        double mult;
        int finalAmount;

        UserInterface.printSuccess("Player wins!\n");

        if (state.hasPlayerGotBlackjack()) {
            mult = RuleConstants.BLACKJACK_PAYOUT_MULTIPLIER;
        } else {
            mult = RuleConstants.PAYOUT_MULTIPLIER;
        }

        finalAmount = (int)(bet * mult);
        player.getBankAccount().deposit(finalAmount);
        UserInterface.printSuccess("Congratulations! Your account has been deposited $%d!\n", finalAmount);
    }

    public void insuranceBet() {
        player.getBankAccount().deposit((int)(bet * RuleConstants.INSURANCE_PAYOUT_MULTIPLIER));
    }

    public void surrenderBet() {
        player.getBankAccount().deposit(bet / 2);
    }
}
