import areas
import creatures
import items
import inventory
import general_prompt
import affinities


def create_player():
    """
    
    """
    chosen_name: str
    affinity: affinities.Affinities
    player_inventory: inventory.Inventory = creatures.player.creature_inventory
    
    print("Create your player here")
    chosen_name = input("What is your name?: ")
    creatures.player.name = chosen_name
    print("Your affinity is randomly chosen!")
    affinity = affinities.initial_affinity()
    print(f'You got: {affinity.name}!')
    creatures.player.affinity = affinity
    player_inventory.add_item(items.copper_shortsword)
    player_inventory.add_item(items.leather_hood)
    player_inventory.add_item(items.leather_shirt)
    player_inventory.add_item(items.leather_pants)
    player_inventory.add_item(items.leather_boots)
    player_inventory.add_item(items.weak_health_potion)
    print("Here are your stats:")
    creatures.display_player_stats()
    return creatures.player


def do_turn(current_area):
    """
    
    """
    while creatures.player.health > 0:
        creatures.player.increment_turn()
        areas.display_area_stats(current_area)
        areas.pick_random_area_mob(current_area)
        choice = general_prompt.general_prompt()
        match choice:
            case "f" | "l" | "r" | "b":
                current_area = areas.change_area(current_area, choice)
            case "i":
                inventory.display_player_inventory_item_names()
            case "d":
                creatures.display_player_stats() #make so it doesnt do a turn?
            case _:
                display_help()


def display_help():
    print("\nCommands are:\n",
          "f (forward) | l (left) | r (right) | b (back)\n",
          "\tDirections you can move in\n",
          "i\n",
          "\tDisplay player inventory items\n",
          "d\n",
          "\tDisplay player statistics\n",
          sep="")


def main():
    print("Simple RPG Game")
    input("Press any key to continue: ")
    create_player()
    print("You spawn on these peaceful plains...")
    do_turn(areas.plains)


if __name__ == "__main__":
    main()