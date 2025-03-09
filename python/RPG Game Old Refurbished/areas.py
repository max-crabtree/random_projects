import random
import creatures
import battle
from dataclasses import dataclass


"""

"""
@dataclass
class Areas:
    name: str
    danger: float
    
    
    def __str__(self) -> str:
        return f"Name: {self.name}\tDanger: {self.danger}"


# defined areas with names and danger levels
plains: Areas = Areas("Plains", 0.1)
cave_l: Areas = Areas("Cave_l", 0.33)
cave_f: Areas = Areas("Cave_f", 0.33)
cave_r: Areas = Areas("Cave_r", 0.33)


# re-write?
area_location_dict: dict[str, dict[str, str]] = \
{
    plains.name: {"l": cave_l,  "f": cave_f, "r": cave_r,  "b": "..."},
    cave_l.name: {"l": "...",    "f": "...",   "r": plains, "b": "..."},
    cave_f.name: {"l": "...",    "f": "...",   "r": "...",    "b": plains},
    cave_r.name: {"l": plains, "f": "...",   "r": "...",    "b": "..."}
}


# dict[Areas.name, list[creatures.Creatures]]
valid_area_mob_spawn_dict: dict[str, list[creatures.Creatures]] = \
{
    "Plains": [creatures.goblin, creatures.zombie, creatures.skeleton],
    "Cave_l": [creatures.skeleton],
    "Cave_f": [creatures.skeleton],
    "Cave_r": [creatures.skeleton]
}


def display_area_stats(current_area: Areas):
    """
    
    """
    print(current_area)
   
 
def pick_random_area_mob(current_area: Areas):
    """
    
    """
    area_key: list[creatures.Creatures]
    rand: int
    mob_choice: creatures.Creatures
    
    area_key = valid_area_mob_spawn_dict[current_area.name]
    rand = random.randint(0, len(area_key) - 1)
    mob_choice = area_key[rand]
    will_mob_spawn(current_area, mob_choice)


def will_mob_spawn(current_area: Areas, mob: creatures.Creatures):
    """
    
    """
    mob_chance: int
    chance: int
    
    mob_chance = current_area.danger * 100 #make more complex
    chance = random.randint(0, 100)
    if chance < mob_chance:
        mob_spawn(mob)
    else:
        return    


def mob_spawn(mob: creatures.Creatures):
    """
    
    """
    print(f'A {mob.name} appears!')
    creatures.display_monster_stats(mob)
    battle.battle_dialouge(mob)


def change_area(current_area: Areas, direction: str) -> Areas:
    """
    
    """
    new_area: Areas
    new_area = area_location_dict[current_area][direction]
    #new_area = area_location_dict[areas_name_dict.values][direction]
    return new_area