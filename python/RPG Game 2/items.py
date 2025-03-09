"""File containing Item / Weapon / Potion / Armour / Misc classes"""

"""
Acknowledgement of external assistance (ChatGPT)

Issues I got help with: Very repetitive typing.

Assistance received: I got ChatGPT to give me multiple different Weapon types (with different values), and then write them into the LOOT_POOL variable. I had to go through afterwards to clean it up, but it saved me a lot of time.

What I learned: Some unique weapon names and ideas.
"""

__author__ = "Max Crabtree"

from dataclasses import dataclass
from enum import Enum
import random


class Type(Enum):
    """
    Item types.
    """
    WEAPON = "Weapon"
    POTION = "Potion"
    ARMOUR = "Armour"
    MISC = "Miscellaneous"


class Effect(Enum):
    """
    The different potion effects.
    """
    HEALING = "Healing"
    DAMAGING = "Damaging"
    PROTECTING = "Protecting"


class Rarity(Enum):
    """
    The different item rarities.
    """
    COMMON = '-'
    UNCOMMON = '#'
    RARE = '%'
    EPIC = '!'
    LEGENDARY = "**"
    

@dataclass
class Item:
    """
    Class for defining an item; has a material, type, and rarity.
    """
    material: str
    type: Type
    rarity: Rarity
    
    def chance_to_spawn(self) -> float:
        """
        Chance for the item to spawn; based on rarity and returns a decimal value.
        """
        if self.rarity is Rarity.COMMON:
            return random.randrange(65, 100) / 100
        if self.rarity is Rarity.UNCOMMON:
            return random.randrange(50, 75) / 100
        if self.rarity is Rarity.RARE:
            return random.randrange(30, 45) / 100
        if self.rarity is Rarity.EPIC:
            return random.randrange(15, 30) / 100
        if self.rarity is Rarity.LEGENDARY:
            return random.randrange(1, 10) / 100


@dataclass
class Weapon(Item):
    """
    Class for defining a Weapon; has damage and a weapon_type.
    """
    damage: float
    weapon_type: str
    
    def __str__(self) -> str:
        return f"{str(self.rarity.name).capitalize()} {self.material} {self.weapon_type}"


@dataclass
class Potion(Item):
    """
    Class for defining a Potion; has an effect, strength, and duration.
    """
    material = None # How to remove?
    effect: Effect
    strength: float
    duration: int
    
    def __str__(self) -> str:
        return f"{str(self.rarity.name).capitalize()} {str(self.effect.name).capitalize()} Potion"


@dataclass
class Armour(Item):
    """
    Class for defining Armour; has durability.
    """
    durability: int
    
    def __str__(self) -> str:
        return f"{str(self.rarity.name).capitalize()} {self.material} Armour"


@dataclass
class Misc(Item):
    """
    Class for defining miscellaneous items; has a name.
    """
    name: str

# Armour definitions
iron_armour     =     Armour("Iron", Type.ARMOUR, Rarity.COMMON, 50)
steel_armour    =     Armour("Steel", Type.ARMOUR, Rarity.UNCOMMON, 70)
bronze_armour   =     Armour("Bronze", Type.ARMOUR, Rarity.COMMON, 40)
silver_armour   =     Armour("Silver", Type.ARMOUR, Rarity.RARE, 80)
gold_armour     =     Armour("Gold", Type.ARMOUR, Rarity.RARE, 60)
platinum_armour =     Armour("Platinum", Type.ARMOUR, Rarity.EPIC, 100)
diamond_armour  =     Armour("Diamond", Type.ARMOUR, Rarity.LEGENDARY, 120)

# Potion definitions
healing_potion            =     Potion(None, Type.POTION, Rarity.COMMON, Effect.HEALING, 20.0, 5)
greater_healing_potion    =     Potion(None, Type.POTION, Rarity.UNCOMMON, Effect.HEALING, 50.0, 5)
damaging_potion           =     Potion(None, Type.POTION, Rarity.COMMON, Effect.DAMAGING, 15.0, 3)
fire_potion               =     Potion(None, Type.POTION, Rarity.RARE, Effect.DAMAGING, 40.0, 3)
protection_potion         =     Potion(None, Type.POTION, Rarity.COMMON, Effect.PROTECTING, 10.0, 10)
greater_protection_potion =     Potion(None, Type.POTION, Rarity.UNCOMMON, Effect.PROTECTING, 30.0, 10)
legendary_healing_potion  =     Potion(None, Type.POTION, Rarity.LEGENDARY, Effect.HEALING, 100.0, 5)

# Weapon definitions
fists             =     Weapon("Player", Type.WEAPON, Rarity.COMMON, 2.0, "Fists")
iron_sword        =     Weapon("Iron", Type.WEAPON, Rarity.COMMON, 5.0, "Sword")
steel_sword       =     Weapon("Steel", Type.WEAPON, Rarity.UNCOMMON, 6.5, "Sword")
bronze_dagger     =     Weapon("Bronze", Type.WEAPON, Rarity.COMMON, 4.0, "Dagger")
silver_rapier     =     Weapon("Silver", Type.WEAPON, Rarity.RARE, 7.0, "Rapier")
gold_axe          =     Weapon("Gold", Type.WEAPON, Rarity.RARE, 5.5, "Axe")
platinum_hammer   =     Weapon("Platinum", Type.WEAPON, Rarity.EPIC, 8.0, "Hammer")
diamond_blade     =     Weapon("Diamond", Type.WEAPON, Rarity.LEGENDARY, 9.5, "Blade")
wooden_club       =     Weapon("Wood", Type.WEAPON, Rarity.COMMON, 2.0, "Club")
bone_spear        =     Weapon("Bone", Type.WEAPON, Rarity.UNCOMMON, 3.5, "Spear")
obsidian_dagger   =     Weapon("Obsidian", Type.WEAPON, Rarity.RARE, 6.0, "Dagger")
mithril_mace      =     Weapon("Mithril", Type.WEAPON, Rarity.EPIC, 7.5, "Mace")
dragonbone_sword  =     Weapon("Dragonbone", Type.WEAPON, Rarity.LEGENDARY, 10.0, "Sword")
oak_staff         =     Weapon("Oak", Type.WEAPON, Rarity.COMMON, 3.0, "Staff")
yew_longbow       =     Weapon("Yew", Type.WEAPON, Rarity.UNCOMMON, 4.5, "Longbow")
ebony_crossbow    =     Weapon("Ebony", Type.WEAPON, Rarity.RARE, 7.0, "Crossbow")
glass_blade       =     Weapon("Glass", Type.WEAPON, Rarity.RARE, 5.0, "Blade")
iron_mace         =     Weapon("Iron", Type.WEAPON, Rarity.COMMON, 4.0, "Mace")
steel_spear       =     Weapon("Steel", Type.WEAPON, Rarity.UNCOMMON, 5.5, "Spear")
bronze_club       =     Weapon("Bronze", Type.WEAPON, Rarity.COMMON, 3.0, "Club")
silver_axe        =     Weapon("Silver", Type.WEAPON, Rarity.RARE, 6.5, "Axe")
golden_dagger     =     Weapon("Gold", Type.WEAPON, Rarity.RARE, 4.5, "Dagger")
platinum_sword    =     Weapon("Platinum", Type.WEAPON, Rarity.EPIC, 7.0, "Sword")
diamond_spear     =     Weapon("Diamond", Type.WEAPON, Rarity.LEGENDARY, 9.0, "Spear")
wooden_dagger     =     Weapon("Wood", Type.WEAPON, Rarity.COMMON, 1.5, "Dagger")
bone_club         =     Weapon("Bone", Type.WEAPON, Rarity.UNCOMMON, 3.0, "Club")
obsidian_axe      =     Weapon("Obsidian", Type.WEAPON, Rarity.RARE, 5.5, "Axe")
mithril_spear     =     Weapon("Mithril", Type.WEAPON, Rarity.EPIC, 7.5, "Spear")
dragonbone_mace   =     Weapon("Dragonbone", Type.WEAPON, Rarity.LEGENDARY, 9.5, "Mace")
oak_bow           =     Weapon("Oak", Type.WEAPON, Rarity.COMMON, 2.5, "Bow")
yew_crossbow      =     Weapon("Yew", Type.WEAPON, Rarity.UNCOMMON, 4.0, "Crossbow")
ebony_spear       =     Weapon("Ebony", Type.WEAPON, Rarity.RARE, 6.5, "Spear")
glass_hammer      =     Weapon("Glass", Type.WEAPON, Rarity.RARE, 4.5, "Hammer")

# The potential items that can spawn in a given (generalised) area
LOOT_POOL: dict[str, list[Item]] = \
    {
    "forest": 
    [
        iron_sword,
        bronze_dagger,
        wooden_club,
        bone_spear,
        oak_staff,
        yew_longbow,
        healing_potion,
        damaging_potion,
        protection_potion
    ],
    "caves": 
    [
        steel_sword,
        obsidian_dagger,
        mithril_mace,
        iron_armour,
        bronze_armour,
        silver_armour,
        healing_potion,
        greater_healing_potion,
        damaging_potion,
        fire_potion,
        protection_potion
    ],
    "hills": 
    [
        steel_sword,
        steel_armour,
        bronze_dagger,
        bronze_armour,
        wooden_club,
        bone_spear,
        healing_potion,
        greater_healing_potion,
        damaging_potion,
        protection_potion
    ],
    "plains": 
    [
        iron_sword,
        wooden_club,
        healing_potion,
        greater_healing_potion,
        damaging_potion,
        protection_potion
    ],
    "desert": 
    [
        steel_sword,
        obsidian_dagger,
        mithril_mace,
        platinum_armour,
        diamond_armour,
        greater_healing_potion,
        fire_potion,
        protection_potion
    ]
}
   