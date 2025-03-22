public class Game {
    private Player player;
    private Dealer dealer;
    private GameState state;
    private PayoutHandler payout;
    private CardManager cards;
    private PlayerActionHandler playerAction;
    private int bet;
    private int currentMove;
    //private boolean iterableMove;
    //private int numberOfSplits; // work on this
    private boolean isSplit;

    public Game(Player player, int bet, Dealer dealer, GameState state, PayoutHandler payout, CardManager cards, PlayerActionHandler playerAction) {
        this.player = player;
        this.dealer = dealer;
        this.state = state;
        this.payout = payout;
        this.cards = cards;
        this.playerAction = playerAction;

        this.bet = bet;
        this.currentMove = 1;
        //this.numberOfSplits = 0;
        this.isSplit = false;
    }

    public void start() {
        playGame();
    }

    private void checkInsurance() {
        if (RuleConstants.CAN_USE_INSURANCE && dealer.getHandValue() == CardRank.ACE.getValue()) {
            boolean playingInsurance = IOHandler.getPlayerYesOrNo("Would you like to play insurance?");
            if (playingInsurance) {
                playerAction.insurance();
            }
            return;
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
        if (!state.hasDealerGotBlackjack() && RuleConstants.CAN_LATE_SURRENDER) {
            playerAction.handleSurrender();
        }
    }

    private void doPlayerMoves() {
        while (state.isPlayerActive()) {
            if (currentMove != 1) {
                UserInterface.printHands(player, dealer);
            }
            currentMove++;
            if (RuleConstants.LIMIT_PLAYER_TURNS && (currentMove > RuleConstants.MAX_PLAYER_TURNS)) {
                UserInterface.printError("Max player turns reached!\n");
                state.setPlayerStand(true);
                break;
            }
            playerAction.doPlayerMove(IOHandler.getPlayerMove());
        }
    }

    // might move to a different class
    private void doDealerDelay() {
        try {
            Thread.sleep(RuleConstants.DEALER_DELAY_MS);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doDealerMoves() {
        while (state.isDealerActive()) {
            UserInterface.printHands(player, dealer);
            cards.addCardToHand(dealer.getHand());
            doDealerDelay();

            if (state.dealerWillStand()) {
                UserInterface.printHands(player, dealer);
                doDealerDelay();
                UserInterface.printGeneral("Dealer stands on %d!\n", dealer.getHandValue());
            }
        }
    }

    private void getAndConfirmResults() {
        if (player.getHandValue() > dealer.getHandValue() || state.dealerBusted()) {
            payout.win();
        }
        else if (player.getHandValue() == dealer.getHandValue()) {
            payout.pushBet();
        }
        else {
            UserInterface.printFail("Dealer wins!\nYou lose $%d!\n", bet);
        }
    }

    private void setupGame() {
        //cards.addCardToHand(dealer.getHand());
        dealer.getHand().addCard(new Card(CardRank.ACE, CardSuit.HEARTS));

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
            UserInterface.printGeneral("You surrendered...\n");
            player.setHand(new Hand());
            return;
        }

        if (state.playerBusted()) {
            player.setHand(new Hand());
            return;
        }

        if (state.hasPlayerGotBlackjack()) {
            UserInterface.printSuccess("You got a Blackjack!\n");
        }

        UserInterface.printGeneral("\nThe dealer now makes their moves...\n");

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