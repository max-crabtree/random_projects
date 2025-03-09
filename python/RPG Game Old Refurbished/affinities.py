import random
from dataclasses import dataclass

"""

"""
@dataclass
class Affinities:

    name: str = ""


def initial_affinity() -> Affinities:
    """

    """
    affinity_list: list[Affinities]
    rand: int

    affinity_list = [earth, wind, fire, water]
    rand = random.randint(0, 3)
    return affinity_list[rand]


earth = Affinities("Earth")
wind = Affinities("Wind")
fire = Affinities("Fire")
water = Affinities("Water")
