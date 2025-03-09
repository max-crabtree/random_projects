/**
 * Simple class for a {@code BankAccount} used by the {@code Player} for making bets in BlackJack.
 */
public class BankAccount {
    private int bankBalance; /* The current amount stored in the account */

    /**
     * Creates a new {@code BankAccount} object with ${@code bankBalance} in it initially.
     * 
     * @param bankBalance the amount the {@code BankAccount} holds when initialised
     */
    public BankAccount(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    /**
     * Get the current amount of money within the {@code BankAccount}.
     * This value determines how much money can be bet, and if the game will continue.
     * If it equals 0, the {@code Player} loses.
     * 
     * @return the current balance
     */
    public int getBankBalance() {
        return bankBalance;
    }

    /**
     * Adds the specified ${@code amount} to the {@code Player}'s {@code BankAccount}.
     * 
     * @param amount the amount to be deposited into the {@code BankAccount}
     */
    public void deposit(int amount) {
        bankBalance += amount;
    }

    /**
     * Attempts to withdraw ${@code withdrawAmount} from the {@code Player}'s {@code BankAccount}.
     * 
     * @param withdrawAmount the amount withdrawn from the {@code Player}'s {@code BankAccount}
     * @return {@code true} if the withdrawal is successful
     * @throws InvalidWithdrawalException if {@code withdrawAmount} is greater than the balance or {@code MAX_BET}, or it equals 0
     */
    public boolean withdraw(int withdrawAmount) throws InvalidWithdrawalException {
        if (withdrawAmount > bankBalance ||
            withdrawAmount > RuleConstants.MAX_BET ||
            withdrawAmount == 0) {
            throw new InvalidWithdrawalException(String.format("You cannot withdraw $%d!\n%s", withdrawAmount, this.toString()));
        }
        bankBalance -= withdrawAmount;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Current balance: $%d", bankBalance);
    }
}
