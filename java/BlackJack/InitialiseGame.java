public class InitialiseGame {
    FileHandler rulesFile;
    FileHandler playersFile;

    public InitialiseGame(FileHandler rulesFile, FileHandler playersFile) {
        this.rulesFile = rulesFile;
        this.playersFile = playersFile;
    }

    /* Move to separate class */
    public void startMenu() {
        Player p;

        while (true) {
            UserInterface.printStartMenu();
            switch (IOHandler.getPlayerLowercaseString("")) {
                case ("s"): rulesFile.toString(); continue;
                case ("l"): p = loadPlayer(); startGameLoop(p); continue;
                case ("n"): p = createNewPlayer(); startGameLoop(p); continue;
                case ("q"): return;
                default: continue;
            }
        }

        
    }

    private Player loadPlayer() {
        String playerName = IOHandler.getPlayerLowercaseString("What player would you like to load?");
        playerName = IOHandler.capitaliseString(playerName);
        return new Player(playerName, (int)playersFile.getValueOf(playerName));
    }

    private Player createNewPlayer() {
        String name = IOHandler.getPlayerLowercaseString("Enter your new player name here");
        name = IOHandler.capitaliseString(name);
        Player player = new Player(name, RuleConstants.STARTING_MONEY);

        playersFile.addKeyValuePair(name, RuleConstants.STARTING_MONEY);
        
        return player;
    }

    private void startGameLoop(Player player) {
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
    
