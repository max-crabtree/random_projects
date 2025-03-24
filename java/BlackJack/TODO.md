Fix issue with insurance going multiple times
Move deposit ALL into payout
Add "helper" mode; gives best possible move to take for winning
Implement split number checking
Make customisations for TerminalColour
Add a simple GUI: Gameplay, dropdowns for rules(?), etc...
Add "max cards in hand" limitation, rule, and logic
Make RuleFileManager better, loadPlayerFromFile as well
Merge together RuleFileManager and GameFilesManager probably...
Change Deck type to Array / Stack, not linked list
Refactor some(?) classes to static if needed
Make player BackAccount update in player.txt
Expand into other card games? (Poker, etc...)
Make each player have their own settings, rules, games, etc...
Support for multiple players
Fix weird issue where:
        hands are not printed
        says what move to make
        dealer wins instantly
Fix aces adjusting when they don't need to
    Went from BlackJack to 11?
    Dealer still stands too early due to ace I think
Add warning for players with $0?