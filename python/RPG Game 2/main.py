"""The starting point of the game"""

__author__ = "Max Crabtree"

import areas
import creatures
import colours
from formatting import fancy_border, coloured_text
import prompts
import battle


def start_game(current_area: areas.Area):
    """
    Starts the game. Handles area zone printing and mob checking. Quits the game on death.
    """
    mob: creatures.Monster
    
    while creatures.player.health > 0:
        if current_area.danger is areas.Danger.PEACEFUL:
            fancy_border(str(current_area), '+', colours.GREEN)
        elif current_area.danger is areas.Danger.SLIGHTLY_DANGEROUS:
            fancy_border(str(current_area), '/', colours.YELLOW)
        else:
            fancy_border(str(current_area), 'X', colours.RED)
        
        if areas.will_mob_spawn(current_area):
            mob = areas.choose_mob(current_area)
            battle.mob_appears(mob)
        current_area = areas.interact_prompt(current_area, "What do you do?")
    
    fancy_border("You died", 'X', colours.RED)

    if prompts.y_n_prompt("Do you want to save your stats?"):
        creatures.save_stats_to_file(creatures.player)
    
    print(coloured_text("Quitting...", colours.RED))
    quit()


def main():
    choice: bool
    current_area: areas.Area

    fancy_border("The Sword", '*', colours.YELLOW)

    choice = prompts.y_n_prompt("Would you like to view the commands?")
    if choice:
        prompts.display_commands()

    creatures.player.name = input("\nWhat is your name?: ")
    creatures.show_player_stats()

    print("You spawn within a peaceful forest...")
    current_area = areas.peaceful_forest
    start_game(current_area)


if __name__ == "__main__":
    main()
