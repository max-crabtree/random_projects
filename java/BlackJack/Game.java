public class Game {
    private Player player;
    private Dealer dealer;
    private IOHandler io;
    private GameState state;
    private PayoutHandler payout;
    private CardManager cards;
    private PlayerActionHandler playerAction;
    private int bet;
    private int currentMove;
    //private boolean iterableMove;
    //private int numberOfSplits; // work on this
    private boolean isSplit;

    public Game(Player player, int bet) {
        this.player = player;
        this.dealer = new Dealer();
        this.io = new IOHandler();
        this.state = new GameState(player, dealer);
        this.payout = new PayoutHandler(player, bet);
        this.cards = new CardManager();
        this.playerAction = new PlayerActionHandler(player, io, state, cards, bet, payout);
        this.bet = bet;
        this.currentMove = 1;
        //this.numberOfSplits = 0;
        this.isSplit = false;

        playGame();
    }

    private void checkInsurance() {
        if (RuleConstants.CAN_USE_INSURANCE && dealer.getHandValue() == CardRank.ACE.getValue()) {
            boolean playingInsurance = io.getPlayerYesOrNo("Would you like to play insurance?");
            playerAction.insurance(playingInsurance);
        }
    }

    private void checkEarlySurrender() {
        Deck currentDeck = cards.getRandomDeck();

        if (RuleConstants.CAN_EARLY_SURRENDER) {
            playerAction.handleSurrender();
        } else if (cards.peekNextCard().getValue() + dealer.getHandValue() == RuleConstants.BLACKJACK_VALUE) {
            cards.addCardToHand(currentDeck, dealer.getHand());
        }
    }

    private void checkLateSurrender() {
        if (!dealer.hasBlackjack() && RuleConstants.CAN_LATE_SURRENDER) {
            playerAction.handleSurrender();
        }
    }

    private void doPlayerMoves() {
        while (state.isPlayerActive()) {
            UserInterface.printHands(player, dealer);
            currentMove++;
            if (RuleConstants.LIMIT_PLAYER_TURNS && (currentMove > RuleConstants.MAX_PLAYER_TURNS)) {
                UserInterface.printError("Max player turns reached!");
                state.setPlayerStand(true);
                break;
            }
            playerAction.doPlayerMove(io.getPlayerMove());
        }
    }

    private void doDealerMoves() {
        while (state.isDealerActive()) {
            UserInterface.printHands(player, dealer);
            cards.addCardToHand(dealer.getHand());
            try {
                Thread.sleep(RuleConstants.DEALER_DELAY_MS);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAndConfirmResults() {
        if (player.getHandValue() > dealer.getHandValue() || state.dealerBusted()) {
            UserInterface.printSuccess("Player wins!\n");
            payout.win();
        }
        else if (player.getHandValue() == dealer.getHandValue()) {
            UserInterface.printGeneral("Hands are equal so there is a push!\n");
            payout.pushBet();
        }
        else {
            UserInterface.printFail("Dealer wins!\n");
            UserInterface.printFail(String.format("You lose $%d!\n", bet));
        }
    }

    private void setupGame() {
        cards.addCardToHand(dealer.getHand());

        UserInterface.printGeneral("\n" + dealer.toString() + "\n");

        checkInsurance();

        cards.addCardToHand(player.getHand());
        cards.addCardToHand(player.getHand());

        boolean dealerHasTenOrAce = (dealer.getHandValue() == CardRank.TEN.getValue() || dealer.getHandValue() == CardRank.ACE.getValue());
        if (dealerHasTenOrAce) {
            checkEarlySurrender();
        }

        checkLateSurrender();
    }

    private void playGame() {
        setupGame();

        // should Deck be determined at start of each Move? (for peekNextCard)

        doPlayerMoves();

        if (state.hasPlayerSurrendered()) {
            UserInterface.printGeneral("You surrendered...");
            player.setHand(new Hand());
            return;
        }

        if (state.playerBusted()) {
            UserInterface.printHands(player, dealer);
            player.setHand(new Hand());
            return;
        }

        if (state.hasPlayerGotBlackjack()) {
            UserInterface.printSuccess("You got a Blackjack!");
        }

        UserInterface.printGeneral("\nThe dealer now makes their moves...");

        doDealerMoves();

        getAndConfirmResults();

        if (isSplit) {
            player.setHand(player.getSplitHand());
            playGame(); // Bad
        }

        player.setHand(new Hand());
        return;
    }
}