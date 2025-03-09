public class PayoutHandler {
    Player player;
    int bet;
    
    public PayoutHandler(Player player, int bet) {
        this.player = player;
        this.bet = bet;
    }

    public void pushBet() {
        UserInterface.printGeneral(String.format("Hands are equal so there is a push! Your account has been deposited the initial bet of $%d", bet));
        player.getBankAccount().deposit(bet);
    }

    public void win() {
        double mult;
        int finalAmount;

        if (player.hasBlackjack()) {
            mult = RuleConstants.BLACKJACK_PAYOUT_MULTIPLIER;
        } else {
            mult = RuleConstants.PAYOUT_MULTIPLIER;
        }

        finalAmount = (int)(bet * mult);
        player.getBankAccount().deposit(finalAmount);
        UserInterface.printSuccess(String.format("Congratulations! Your account has been deposited $%d!\n", finalAmount));
    }

    public void insuranceBet() {
        player.getBankAccount().deposit((int)(bet * RuleConstants.INSURANCE_PAYOUT_MULTIPLIER));
    }

    public void surrenderBet() {
        player.getBankAccount().deposit(bet / 2);
    }
}
