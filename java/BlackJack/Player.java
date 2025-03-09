public class Player {
    private Hand hand;
    private Hand splitHand;
    private BankAccount bank;
    private int totalSplits;

    public Player(int money) {
        this.hand = new Hand();
        this.bank = new BankAccount(money);
        this.totalSplits = 0;
    }

    public BankAccount getBankAccount() {
        return bank;
    }

    public int getBankBalance() {
        return bank.getBankBalance();
    }

    public int getHandValue() {
        return hand.getHandValue();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        splitHand = null; // Function only used when setting split hand to main hand
    }

    public Hand getSplitHand() {
        return splitHand;
    }

    public int getTotalSplits() {
        return totalSplits;
    }

    public void setTotalSplits(int totalSplits) {
        this.totalSplits = totalSplits;
    }

    public void makeSplitDeck(Card cardAtTop) {
        splitHand = new Hand(cardAtTop);
        totalSplits++;
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public boolean hasBlackjack() {
        return (getHandValue() == RuleConstants.BLACKJACK_VALUE);
    }

    @Override
    public String toString() {
        return String.format("Player %s", hand.toString());
    }
}
