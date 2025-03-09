import creatures
import general_prompt
import materials
from enum import Enum
from dataclasses import dataclass


class Types(Enum):
    """

    """
    WEAPONS = "Weapons"
    POTIONS = "Potions"
    ARMOUR = "Armour"


"""

"""
@dataclass
class Items:
    material: materials.Materials = materials.Materials()
    type: Types = Types(Types.WEAPONS or Types.POTIONS or Types.ARMOUR)
    durability: int = 0
    rarity: str = "Common"
    
    # get_name method? if potion name =, if weapon name =, etc...


"""

"""
@dataclass
class Armour(Items):
    type = Types.ARMOUR
    #name: str = f"{Items.rarity} {Items.material} {type}"


    #def __str__(self) -> str:
    #    return f"{self.name}\tDurability: {self.durability}"
    def __str__(self) -> str:
        return f"{self.rarity} {self.material} {self.type}"


leather_hood = Armour("Hood", "Leather", 15, 0)
leather_shirt = Armour("Shirt", "Leather", 30, 0)
leather_pants = Armour("Pants", "Leather", 15, 0)
leather_boots = Armour("Boots", "Leather", 10, 0)


"""

"""
@dataclass
class Potions(Items):
    effect: str = ""
    strength: str = ""
    name = f"{Items.rarity} {strength} {effect} Potion"
    duration: int = 0
    type = Types.POTIONS


    def display_potion_stats(self):
        print(f'Name: {self.name}')
        print(f'Effect: {self.effect}')
        print(f'Strength: {self.strength}')
        print(f'Rarity: {self.rarity}')
        print(f'Duration: {self.duration}')
       
        
    def use_potion(self):
        print(f'You drunk the {self.name}!')
        creatures.player.potion_drunk(self)
    
    
    def potion_interact(self):
        print(f'You selected the {self.name}!')
        choice = general_prompt.general_prompt()
        match choice:
            case "d":
                creatures.player.use_potion(self)
            case "v":
                self.display_potion_stats(self)
            case "c":
                return
        
        
weak_health_potion = Potions("Health", "Weak", 1, 5)


"""

"""
@dataclass
class Weapons(Items):
    attack: int = 0
    effective_affinity: str = ""
    type = Types.WEAPONS


    #eventually, multiplier will be effected by another skill
    #maybe 'strength'?
    def critical_hit(self):
        self.attack = 2 * self.attack 
    
    
    #where 2 is the multiplier for criticalHit()
    def reset_attack(self):
        self.attack = self.attack / 2
    
    def __str__(self):
        return f"{self.material} {self.type}"


#copper_shortsword = Weapons("Shortsword", "Copper", 30, 100, 0, "None")
#copper_shortsword = Weapons(materials.copper, "Shortsword", 100, attack=30)
copper_shortsword = Weapons(materials.copper, durability=100, attack=30)

# goblin weapon
#rusty_iron_dagger = Weapons("Dagger", "Rusty Iron", 10, 150, 0, "None")