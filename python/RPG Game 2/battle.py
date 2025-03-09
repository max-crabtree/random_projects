"""Battle logic and functions"""

__author__ = "Max Crabtree"

from formatting import coloured_text, item_reveal
import prompts
import items
import creatures
import colours
import chance


def mob_appears(mob: creatures.Monster):
    """
    Alerts a mob appearing and prompts the user.
    """
    print(f"A {coloured_text(str(mob), colours.RED)} appears!!")
    battle_prompt(mob, "What will you do?")


def choose_weapon() -> items.Weapon:
    """
    Prompts the user to select a weapon from their inventory. Returns the selected weapon.
    """
    equipped_weapon: items.Item

    print("Attack with what?")
    print()
    if len(creatures.player.inventory) == 0:
        print("You will have to attack with your fists!")
        equipped_weapon = items.fists
        return equipped_weapon

    creatures.player.view_inventory(battle=True)
    equipped_weapon = creatures.player.select_inventory_item(items.Weapon)
    return equipped_weapon


def does_hit(mob: creatures.Monster) -> bool:
    """
    Uses the player level and mob level to calculate whether an attack hits. Returns a bool.
    """
    player: creatures.Player = creatures.player
    calculation: float
    will_hit: bool
    
    calculation = (player.level * (player.health / mob.health)) / player.level + mob.level
    will_hit = chance.get_chance(calculation)
    
    return will_hit


def mob_attack():
    """
    NotImplemented
    """
    # raise NotImplementedError
    print("The mob attacks!")


def attack(mob: creatures.Monster, weapon: items.Weapon) -> bool:
    """
    Gives the user feedback on their hit. Returns a bool on whether the monster is dead or not.
    """
    prior_mob_health = mob.health
    print(f"You try to attack the {str(mob)} with the {item_reveal(weapon)}!")
    if not does_hit(mob):
        print(coloured_text(f"You missed the {str(mob)}!", colours.RED))
        return
    
    print(coloured_text(f"You hit the {str(mob)}!", colours.GREEN))
    mob.health -= weapon.damage
    print(f"{str(mob)} health: {coloured_text(prior_mob_health, colours.RED)} -> {coloured_text(mob.health, colours.GREEN)}")
    
    if mob.health <= 0:
        return True
    return False
        
    
def consume():
    """
    NotImplemented
    """
    consumable: items.Item
    
    print("Consume what?")
    print()
    if len(creatures.player.inventory) == 0 or \
    not creatures.player.confirm_inventory_types(items.Potion):
        print(coloured_text("You have nothing to consume!", colours.RED))
        return
    
    creatures.player.view_inventory(battle=True)
    
    consumable = creatures.player.select_inventory_item(items.Potion)
    
    # Consume...


def defend():
    """
    NotImplemented
    """
    raise NotImplementedError


def will_escape(mob: creatures.Monster) -> bool:
    """
    Calculates the escape chance and returns a bool.
    """
    calculation: float
    will_escape: bool
    
    calculation = (creatures.player.level * creatures.player.health) / ((creatures.player.level * creatures.player.health) + (mob.level * mob.health))
    will_escape = chance.get_chance(calculation)
    return will_escape


def run_away(mob: creatures.Monster) -> bool:
    """
    Tells the user if they got away or not. Handles mob 'disappearance' and attack advantage if the escape attempt fails. Returns a bool.
    """
    print("You attempt to run away!")
    
    if not will_escape(mob):
        print(coloured_text("You don't get away!", colours.RED))
        print(coloured_text(f"The {str(mob)} has the advantage!", colours.RED))
        mob_attack()
        return False
    else:
        print(coloured_text("You escape!", colours.GREEN))
        mob.health = 0 # To break out of the loop
    
    return True

def battle_prompt(mob: creatures.Monster, prompt: str):
    """
    The prompt for battle. Takes in common battle commands such as attack, consume, defend, etc.
    """
    weapon: items.Weapon
    choice: str
    escaped: bool = False
    is_dead: bool = False

    # I initially used mob.health > 0 here, but found issues with the mob attacking while "dead"
    while True:
        choice = input(prompt + ":\n").lower().split()
        match choice:
            case ["attack" | "a"]:
                weapon = choose_weapon()
                is_dead = attack(mob, weapon)
                if is_dead:
                    break
            case ["consume" | "c"]:
                consume()
            case ["defend" | "d"]:
                defend()
            case ["run" | "r"]:
                escaped = run_away(mob)
            case "?":
                prompts.display_commands()
            case _:
                prompts.invalid_input()
    
    if not escaped:
        print(f"You killed the {coloured_text(str(mob), colours.RED)}!")
        print(f"You gained {coloured_text(mob.exp_dropped, colours.GREEN)} EXP!")
        creatures.player.exp_gain(mob.exp_dropped)
         # Regenerates the mob object so that it doesn't die immediately once seen again
        mob = mob.__init__()
