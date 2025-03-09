"""File for the Creature / Player / Monster classes"""

"""
Acknowledgement of external assistance (ChatGPT)

Issues I got help with: Accessing the inventory items through user-inputted indices.

Assistance received: Given some hints toward using a different data type so that the keys of the inventory can be indexed properly.
This is seen in get_inventory_item_index() with keys_index.

What I learned: If a data type cannot be indexed over, figure out a better way to express that data to achieve the goals you want to. In this case, turning the dictionary keys into a list!

Note: Also helped to generate some of the Monster objects; just seemed repetitive, and I adjusted numbers accordingly afterwards.
"""

__author__ = "Max Crabtree"

import items
import colours
from formatting import coloured_text, fancy_border, INVALID_INPUT
from dataclasses import dataclass
import math


@dataclass
class Creature:
    """
    Class for defining a Creature object; has a name, health, attack, and level. Has a few subclasses such as Player and Monster.
    """
    name: str
    health: float
    attack: float
    level: int
    inventory: dict[str, (items.Item, int)]
    
    def health_change(self, change: float):
        """
        Changes the health of a Creature.
        """
        self.health += change
    
    def level_adjust(self):
        """
        Adjusts the health and attack of a Creature depending on it's level.
        """
        self.health += round(math.log(self.level) * 2.0, 2)
        self.attack += round(math.log(self.level) * 1.5, 2)
        

@dataclass
class Player(Creature):
    """
    Class for defining the Player; includes an exp level.
    """
    name = ""
    health = 100.0
    attack = 10.0
    level = 1
    exp: int = 0
    inventory = {}
    equipped_weapon: items.Weapon
    
    def add_item(self, item: items.Item):
        """
        Add an item to the player's inventory.
        """
        quantity: int = 0
        
        if not str(item) in self.inventory.keys():
            self.inventory[str(item)] = (item, quantity) # Initialise object in inventory
        if len(self.inventory) <= 10: # Maximum number of items in inventory is 10
            self.inventory[str(item)] = (item, quantity + 1)
        else:
            print(coloured_text("Inventory is too full!", colours.RED))
    
    def drop_item(self, item: str):
        """
        'Drops' an item, i.e. removes it from inventory.
        """
        quantity: int
        
        if item not in self.inventory.values():
            print(coloured_text("Item does not exist!", colours.RED))
        else:
            self.inventory[str(item)] = (item, quantity - 1)
            if self.inventory[str(item)] is None:
                del self.inventory[str(item)]
    
    def equip_weapon(self, item: items.Weapon):
        """
        NotImplemented
        """
        self.equipped_weapon = item
    
    def unequip_weapon(self):
        """
        NotImplemented
        """
        self.equipped_weapon = None
    
    def view_inventory(self, battle: bool = False):
        """
        View the player's inventory items and their quantities. If a battle is happening, the fancy border does not appear.
        """
        if not battle:
            fancy_border("Player Inventory", '$', colours.CYAN)
        if len(self.inventory) == 0:
            print(coloured_text("Inventory is empty!", colours.RED))
            return
        for i, (item, amount) in enumerate(self.inventory.items(), 1):
            # "amount[1]" to get the 2nd value in the tuple
            print(f"{i}.\t{item}:\t{amount[1]}")
    
    def select_inventory_item(self, *valid_classes: items.Item.__subclasses__) -> items.Item:
        """
        Selects and returns an inventory item based on user input.
        """
        selection: items.Item

        while True:
            choice = int(input())
            if choice <= 0 or choice > len(self.inventory):
                print(INVALID_INPUT)
            
            selection = self.get_inventory_item_index(choice)
            
            break
            # if selection in valid_classes:
            #     break 
            # print(coloured_text("Invalid item type!", colours.RED))
                
        return selection
    
    def get_inventory_item_index(self, index: int) -> items.Item:
        """
        
        """
        keys_index: list[str] = list(self.inventory.keys())
        key_selection: str
        
        key_selection = keys_index[index - 1]
        
        return self.inventory[key_selection][0]
        
    
    def confirm_inventory_types(self, *valid_types: items.Item.__subclasses__) -> bool:
        """
        Returns True if the item class is in valid_types, False if not.
        """
        for item in self.inventory.values[0]:
            if item in valid_types:
                return True
        return False
    
    def level_up(self):
        """
        Increments the player level by one. Adjusts the players statistics and exp per level.
        """
        self.level += 1
        self.level_adjust()
        self.exp_per_level()
        self.exp = 0
        
        # Put this into battle.py??
        fancy_border("Level Up", '*', colours.YELLOW)
        coloured_text(f"You are now level {self.level}!", colours.GREEN)
    
    def exp_per_level(self) -> float:
        """
        Calculates the exp needed per level. Uses a linear function.
        """
        max_exp: float
        
        max_exp = 10 * self.level + 10
        return max_exp
    
    def exp_gain(self, new_exp: float):
        """
        When new exp is gained. Controls the level up when needed.
        """
        self.exp += new_exp
        
        if self.exp >= self.exp_per_level():
            self.level_up()


@dataclass
class Monster(Creature):
    """
    Class for defining a Monster.
    """
    exp_dropped: float
    
    def exp_adjust(self):
        """
        Adjusts the Monsters exp_dropped dependent on it's level.
        """
        self.exp_dropped += math.log(self.level) * 2.5
    
    def __str__(self):
        return self.name

# Initialise player statistics
player: Player = Player("", 100.0, 10.0, 1, {})

# Monster definitions
zombie: Monster = Monster("Zombie", 20.0, 5.0, 1, {}, 10.0)
skeleton: Monster = Monster("Skeleton", 10.0, 10.0, 1, {}, 8.0)
goblin: Monster = Monster("Goblin", 30.0, 7.5, 1, {}, 12.0)
orc: Monster = Monster("Orc", 40.0, 12.0, 2, {}, 20.0)
spider: Monster = Monster("Spider", 15.0, 8.0, 1, {}, 6.0)
wolf: Monster = Monster("Wolf", 25.0, 9.0, 1, {}, 8.0)


def show_player_stats():
    """
    Show the player's statistics.
    """
    fancy_border("Player Statistics", '~', colours.CYAN)
    print(f"Name:     \t{player.name}\n",
          f"Health:   \t{player.health}\n",
          f"Attack:   \t{player.attack}\n",
          f"Level:    \t{player.level}\n",
          f"EXP:      \t{player.exp}/{player.exp_per_level()}", sep="")


def save_stats_to_file(player: Player):
    raise NotImplementedError
