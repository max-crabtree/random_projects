import random
import general_prompt
import creatures
import items
import inventory


def battle_dialouge(enemy: creatures.Monsters):
    """
    
    """
    choice: str
    
    while enemy.health > 0:
        print("Attack: a\n",
              "Run: r\n",
              "Inventory: i\n",
              "View monsters inventory: m",
              sep="")
        choice = general_prompt.general_prompt()
        match choice:
            case "a":
                battle_attack(enemy)
            case "r":
                battle_run(enemy)
            case "i":
                battle_check_inventory(enemy)
            case "m":
                creatures.view_monsters_inventory(enemy)
            case _:
                print(f'{choice} is not a valid key! Try again!')
    print(f'{enemy.name} died!')
    print(f'You gained {enemy.exp_dropped} EXP!')
    creatures.player.exp_gain(enemy.exp_dropped)
    generate_new(enemy)
    general_prompt.general_prompt()


def generate_new(dead_enemy: creatures.Monsters):
    """
    
    """
    dead_enemy = dead_enemy.__init__()


#add index for user selection (0, 1, etc.)
def choose_weapon() -> list[int]:
    """
    
    """
    weapon_list: list[items.Weapons] = []
    for i, weapon in enumerate(inventory.player_inventory[inventory.Types.WEAPONS]):
        print(f"{i}: {weapon.name}\n",
              f"Attack: {weapon.attack}\n",
              f"Durability: {weapon.durability}\n",
              f"Effective Affinity: {weapon.effective_affinity}\n")
        weapon_list.append(weapon)
    item_choice = int(general_prompt.general_prompt())
    return weapon_list[item_choice]
    
        
def battle_attack(enemy: creatures.Monsters):
    """
    
    """
    weapon_choice: list[items.Weapons]
    
    print("What do you attack with?")
    weapon_choice = choose_weapon()
    print(f'You attack with the {weapon_choice.name}!')
    attack_result(enemy, weapon_choice)
    #show effectiveness of each weapon against enemy (affinity)


def chance_of_attack_hit(enemy: creatures.Monsters) -> int:
    """
    
    """
    #return (creatures.player.level / enemy.level) - 0.25
    return 1 #testing purposes
   
    
def is_critical_hit() -> bool:
    """
    
    """
    #will be affected by a modifier... 'strength'? or 'dexterity'? maybe
    percent: int
    rand: int
    percent = 100
    rand = random.randint(0, 100)
    if rand < percent:
        return True
    else:
        return False


def attack_result(enemy: creatures.Monsters, player_weapon: items.Weapons):
    """
    
    """
    chance_of_hit: int
    rand_chance: int
    chance_of_hit = chance_of_attack_hit(enemy)
    if chance_of_hit <= 0:
        print(f'You missed the {enemy.name}!')
        enemy_attack(enemy)
    elif chance_of_hit >= 1:
        print(f'You hit the {enemy.name}!')
        if (is_critical_hit()):
            player_weapon.critical_hit()
            print("It was a critical hit!")
        enemy.damage_taken(enemy.health, player_weapon.attack)
        print (f'You dealt {player_weapon.attack} damage to the {enemy.name}!')
        print(f'The {enemy.name} now has {enemy.health} health!')
        player_weapon.reset_attack()
        if (enemy.health > 0):
            enemy_attack(enemy)
    else:
        rand_chance = random.randint(0, 100)
        print(f'You have a {chance_of_hit} chance of hitting')
        enemy_attack(enemy)
    #evasion stat?
 
   
#add enemy critical attack?
#add enemy weapons first
def enemy_attack(enemy: creatures.Monsters):
    """
    
    """
    print(f'The {enemy.name} goes to attack!')     
    chance_of_hit = chance_of_attack_hit(creatures.player, enemy)
    if chance_of_hit <= 0:
        print(f'{enemy.name} missed!')
    elif chance_of_hit >= 1:
        print(f'{enemy.name} hit!')
        creatures.player.damage_taken(creatures.player.health, enemy.attack)
        print(f'{enemy.name} dealt {enemy.attack} damage!')
        print(f'You now have {creatures.player.health} health!')


def battle_run(enemy: creatures.Monsters):
    """
    
    """
    chance_of_escape: int
    print("You attempt to run away!")
    #chanceOfEscape = (player.level / enemy.level) - 0.25
    #implement evasion stat later
    chance_of_escape = 1 #testing purposes
    if chance_of_escape <= 0:
        print("You couldn't escape!")
        enemy_attack(enemy)
    elif chance_of_escape >= 1:
        print("You escaped!")
        enemy.health = 0 #break the loop, better way?


def battle_check_inventory(enemy: creatures.Monsters):
    """
    
    """
    choice: list[items.Weapons | items.Potions]
    print("Battle Inventory is:")
    #better way to do this?
    choice = inventory.index_player_inventory(
             inventory.player_inventory[inventory.Types.WEAPONS], 
             inventory.player_inventory[inventory.Types.POTIONS], names_only=False)
    if choice in inventory.player_inventory[inventory.Types.WEAPONS]:
        attack_result(enemy, choice)
        #add different functionality? like viewing weapon
    elif choice in inventory.player_inventory[inventory.Types.POTIONS]:
        items.potion_interact(choice)