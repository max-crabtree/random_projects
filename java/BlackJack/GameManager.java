public class GameManager {
    private GameFilesManager gameFiles;

    public GameManager(GameFilesManager gameFiles) { 
        this.gameFiles = gameFiles;
    }

    /* Move to seperate class */
    public void startMenu() {
        Player p;
        while (true) {
            UserInterface.printMainMenu();
            switch (IOHandler.getPlayerLowercaseString("")) { // change menu function possibly
                case ("s"): RuleFileManager.viewSettings(); break;
                case ("l"): p = gameFiles.loadPlayerFromFile(); startGameLoop(p); return;
                case ("n"): p = createNewPlayer(); startGameLoop(p); return;
                case ("q"): return;
                default: continue;
            }
        }

        
    }

    private Player createNewPlayer() {
        String name = IOHandler.getPlayerLowercaseString("Enter your new player name here");
        name = IOHandler.capitaliseString(name);
        Player player = new Player(name, RuleConstants.STARTING_MONEY);

        gameFiles.addPlayerToFile(player);
        
        return player;
    }

    public void startGameLoop(Player player) {
        boolean gameIsActive = true;
        int bettingAmount = 0;
        boolean canWithdraw = false;

        while (gameIsActive) {
            gameIsActive = IOHandler.getPlayerYesOrNo("Would you like to play a game?");

            if (!gameIsActive) {
                break;
            }

            while (!canWithdraw) {
                bettingAmount = IOHandler.getPlayerInt(String.format("How much would you like to bet? (max: %d)\nMoney in the bank: $%d: ", RuleConstants.MAX_BET, player.getBankBalance()));
                
                try {
                    player.getBankAccount().withdraw(bettingAmount);
                    canWithdraw = true;
                } catch (InvalidWithdrawalException e) {
                    UserInterface.printError(e.getMessage());
                }
            }

            Dealer dealer = new Dealer();
            GameState state = new GameState(player, dealer);
            PayoutHandler payout = new PayoutHandler(state, player, bettingAmount);
            CardManager cards = new CardManager();
            PlayerActionHandler playerAction = new PlayerActionHandler(player, dealer, state, cards, bettingAmount, payout); // too many parameters??

            Game game = new Game(player, bettingAmount, dealer, state, payout, cards, playerAction);
            game.start();
            
            canWithdraw = false;
            player.setTotalSplits(0);
        }
        UserInterface.printCustom("Bye\n\n", TerminalColour.BG_YELLOW);
    }
}
    
