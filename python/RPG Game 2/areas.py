"""File for storing map and area spawn information"""

"""
Acknowledgement of external assistance (ChatGPT)

Issues I got help with: Very repetitive typing.

Assistance received: I got ChatGPT to give me multiple different Area locations (with different values), and then write them into the MAP variable. I had to go through afterwards to clean it up, but it saved me a lot of time. The same was done with MOB_AREAS.

What I learned: Some unique area names.
"""

__author__ = "Max Crabtree"

import creatures
from formatting import coloured_text, item_reveal
import colours
import items
import chance
import random
from dataclasses import dataclass
from enum import Enum


class AreaTypes(Enum):
    """
    More generalised area types for loot generation.
    """
    FOREST = "forest"
    CAVES = "caves"
    HILLS = "hills"
    PLAINS = "plains"
    DESERT = "desert"


class Danger(Enum):
    """
    Some basic area danger levels.
    """
    PEACEFUL = 0.2
    SLIGHTLY_DANGEROUS = 0.5
    DANGEROUS = 0.75


class Direction(Enum):
    """
    Possible directions the player can move in.
    """
    NORTH = "north"
    SOUTH = "south"
    EAST = "east"
    WEST = "west"


@dataclass
class Area:
    """
    Class for defining an area object; has a name, danger level, and general area type.
    """
    name: str
    danger: Danger
    area_type: AreaTypes

    def __str__(self) -> str:
        return f"Area: {self.name} | Danger: {self.danger.name.replace("_", " ").capitalize()}"


# Definition of the different areas
peaceful_forest = Area("Peaceful Forest", Danger.PEACEFUL, AreaTypes.FOREST)
peaceful_forest_fe = Area("Peaceful Forest Forward Edge", Danger.PEACEFUL, AreaTypes.FOREST)
peaceful_forest_le = Area("Peaceful Forest Left Edge", Danger.PEACEFUL, AreaTypes.FOREST)
peaceful_forest_re = Area("Peaceful Forest Right Edge", Danger.PEACEFUL, AreaTypes.FOREST)
caves = Area("Caves", Danger.DANGEROUS, AreaTypes.CAVES)
caves_fe = Area("Caves Forward Edge", Danger.DANGEROUS, AreaTypes.CAVES)
hills = Area("Hills", Danger.SLIGHTLY_DANGEROUS, AreaTypes.HILLS)
hills_fe = Area("Hills Forward Edge", Danger.SLIGHTLY_DANGEROUS, AreaTypes.HILLS)
plains = Area("Plains", Danger.PEACEFUL, AreaTypes.PLAINS)
desert = Area("Desert", Danger.DANGEROUS, AreaTypes.DESERT)
desert_fe = Area("Desert Forward Edge", Danger.DANGEROUS, AreaTypes.DESERT)

# The 'map', uses a dictionary for validating movement direction
MAP: dict[str, dict[Direction, Area]] = \
    {
    peaceful_forest.name: 
    {
        Direction.NORTH: peaceful_forest_fe,
        Direction.SOUTH: None,
        Direction.WEST: peaceful_forest_le,
        Direction.EAST: peaceful_forest_re
    },

    peaceful_forest_fe.name: 
    {
        Direction.NORTH: caves,
        Direction.SOUTH: peaceful_forest,
        Direction.WEST: None,
        Direction.EAST: plains
    },

    peaceful_forest_le.name:
    {
        Direction.NORTH: None,
        Direction.SOUTH: None,
        Direction.WEST: None,
        Direction.EAST: peaceful_forest
    },
    
    peaceful_forest_re.name:
    {
        Direction.NORTH: plains,
        Direction.SOUTH: None,
        Direction.WEST: peaceful_forest,
        Direction.EAST: None
    },
    
    caves.name: 
    {
        Direction.NORTH: caves_fe,
        Direction.SOUTH: peaceful_forest_fe,
        Direction.WEST: None,
        Direction.EAST: hills
    },

    caves_fe.name: 
    {
        Direction.NORTH: None,
        Direction.SOUTH: caves,
        Direction.WEST: None,
        Direction.EAST: None
    },

    hills.name: 
    {
        Direction.NORTH: hills_fe,
        Direction.SOUTH: plains,
        Direction.WEST: caves,
        Direction.EAST: None
    },

    hills_fe.name: 
    {
        Direction.NORTH: None,
        Direction.SOUTH: hills,
        Direction.WEST: None,
        Direction.EAST: desert
    },

    plains.name: 
    {
        Direction.NORTH: hills,
        Direction.SOUTH: peaceful_forest_re,
        Direction.WEST: None,
        Direction.EAST: None
    },

    desert.name: 
    {
        Direction.NORTH: desert_fe,
        Direction.SOUTH: None,
        Direction.WEST: hills_fe,
        Direction.EAST: None
    },

    desert_fe.name: 
    {
        Direction.NORTH: None,
        Direction.SOUTH: desert,
        Direction.WEST: None,
        Direction.EAST: None
    },
}

# What mobs you may encounter in each area
MOB_AREAS: dict[str, list[creatures.Monster]] = \
    {
    peaceful_forest.name: 
    [
        creatures.zombie,
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ],
    
    peaceful_forest_fe.name: 
    [
        creatures.zombie,
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ],
    
    peaceful_forest_le.name: 
    [
        creatures.zombie,
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ],
    
    peaceful_forest_re.name: 
    [
        creatures.zombie,
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ],
    
    caves.name: 
    [
        creatures.zombie,
        creatures.skeleton,
        creatures.orc
    ],
    
    caves_fe.name: 
    [
        creatures.zombie,
        creatures.skeleton,
        creatures.orc
    ],
    
    hills.name: 
    [
        creatures.skeleton,
        creatures.orc,
        creatures.wolf
    ],
    
    hills_fe.name: 
    [
        creatures.skeleton,
        creatures.orc,
        creatures.wolf
    ],
    
    plains.name: 
    [
        creatures.goblin,
        creatures.orc,
        creatures.spider
    ],
    
    desert.name: 
    [
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ],
    
    desert_fe.name: 
    [
        creatures.goblin,
        creatures.spider,
        creatures.wolf
    ]
}


# Avoiding a circular import
import prompts


def interact_prompt(current_area: Area, prompt: str) -> Area:
    """
    The prompt that is used for interacting with the area and checking general information.
    """
    choice: str

    while True:
        choice = input(prompt + ":\n").lower().split()
        match choice:
            # This match case knowledge was assisted by the information on the site:
            # https://peps.python.org/pep-0636/
            case ["move" | "mv",
                  ("north" | "south" | "east" | "west") as direction] \
                    if has_neighbour(current_area, Direction(direction)):
                current_area = change_area(current_area, Direction(direction))
                return current_area
            case ["move" | "mv", _]:
                print(coloured_text("Invalid direction!", colours.RED))
            case ["look" | "lk"]:
                look_around(current_area)
            case ["inventory" | "inv"]:
                creatures.player.view_inventory()
            case ["stats" | "s"]:
                creatures.show_player_stats()
            case "?":
                prompts.display_commands()
            case _:
                prompts.invalid_input()


def has_neighbour(current_area: Area, direction: Direction) -> bool:
    """
    Returns True if the current area has a neighbour in ther given direction, False if otherwise.
    """
    if MAP[current_area.name][direction] is None:
        return False
    return True


def change_area(current_area: Area, direction: Direction) -> Area:
    """
    Changes the current area. Returns the new area.
    """
    new_area: Area

    new_area = MAP[current_area.name][direction]
    return new_area


def look_around(current_area: Area):
    """
    Look around the current area. You might find something!
    """
    pick_up: bool
    potential_item: items.Item
    
    print("You look around and see...")
    potential_item = pick_item_from_area(current_area)
    if not chance.get_chance(potential_item.chance_to_spawn()):
        print(coloured_text("Nothing!", colours.RED))
        return
    
    print(item_reveal(potential_item))
    pick_up = prompts.y_n_prompt("Pick up the item?")
    if pick_up:
        creatures.player.add_item(potential_item)
        print(f"You pick up the {item_reveal(potential_item)}!")
    else:
        print(f"You leave the {item_reveal(potential_item)} alone.")


def will_mob_spawn(current_area: Area) -> bool:
    """
    Returns a True or False on whether a mob will spawn.
    """
    spawn_chance: bool
    
    spawn_chance = chance.get_chance(current_area.danger.value)
    return spawn_chance


def choose_mob(current_area: Area) -> creatures.Monster:
    """
    Chooses a random mob from MOB_AREAS in the current area.
    """
    rand_choice: int
    
    rand_choice = random.randint(0, len(MOB_AREAS[current_area.name]) - 1)
    
    return MOB_AREAS[current_area.name][rand_choice]


def pick_item_from_area(area: Area) -> items.Item:
    """
    Picks an item from the LOOT_POOL (in items.py) for the current area.
    """
    randnum: int
    area_type: str = area.area_type.value
    
    for _ in items.LOOT_POOL[area_type]:
        randnum = random.randrange(0, len(items.LOOT_POOL[area_type]) - 1)
        return items.LOOT_POOL[area_type][randnum]
    