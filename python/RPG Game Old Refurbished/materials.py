from dataclasses import dataclass

"""

"""
@dataclass
class Materials:
    name: str = ""
    sharpness: int = 0
    durability: int = 0

copper = Materials("Copper", 6, 3)

# these material details will be used for more complex damage/tool information