"""Centralises functions regarding the chance of an action happening"""

import random

def get_chance(value: int | float) -> bool:
    """
    Returns True or False at a chance of value, eg. value = 0.45 means there is a 45% chance of returning True.
    """
    rand_num: int
    
    rand_num = random.randint(0, 100)
    
    if rand_num <= value * 100:
        return True
    return False