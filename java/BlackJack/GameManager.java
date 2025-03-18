public class GameManager {
    private IOHandler io;

    public GameManager(IOHandler io) { 
        this.io = io;
    }

    /* Move to seperate class */
    public void startMenu() {
        while (true) {
            UserInterface.printMainMenu();
            switch (io.getPlayerLowercaseString()) {
                case ("s"): RuleFileManager.viewSettings(); break;
                //case ("l"): loadPlayer(); return;
                //case ("n"): createNewPlayer(); return;
                case ("g"): startGameLoop(new Player(1000)); // temporary
                case ("q"): return;
                default: continue;
            }
        }

        
    }

    public Player selectPlayer() {
        return new Player(0);
    }

    public void startGameLoop(Player player) {
        boolean gameIsActive = true;
        int bettingAmount = 0;
        boolean canWithdraw = false;

        while (gameIsActive) {
            io.flushStream();
            gameIsActive = io.getPlayerYesOrNo("Would you like to play a game?");

            if (!gameIsActive) {
                break;
            }

            while (!canWithdraw) {
                final int MAX_BET = RuleConstants.MAX_BET;

                bettingAmount = io.getPlayerInt(String.format("How much would you like to bet? (max: %d)\nMoney in the bank: $%d: ", MAX_BET, player.getBankBalance()));
                
                try {
                    player.getBankAccount().withdraw(bettingAmount);
                    canWithdraw = true;
                } catch (InvalidWithdrawalException e) {
                    UserInterface.printError(e.getMessage());
                }
            }

            Dealer dealer = new Dealer();
            IOHandler io = new IOHandler();
            GameState state = new GameState(player, dealer);
            PayoutHandler payout = new PayoutHandler(player, bettingAmount);
            CardManager cards = new CardManager();
            PlayerActionHandler playerAction = new PlayerActionHandler(player, dealer, io, state, cards, bettingAmount, payout); // too many parameters??

            Game game = new Game(player, bettingAmount, dealer, io, state, payout, cards, playerAction);
            game.start();
            
            canWithdraw = false;
            player.setTotalSplits(0);
        }
        UserInterface.printCustom("Bye\n\n", TerminalColour.BG_YELLOW);
    }
}
    
