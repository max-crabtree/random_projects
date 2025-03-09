import inventory
import items
import affinities
from dataclasses import dataclass

"""

"""
@dataclass
class Creatures:
    name: str = ""
    affinity: affinities.Affinities = affinities.Affinities()
    health: float = 0.0
    attack: float = 0.0
    level: int = 0
    creature_inventory: inventory.Inventory = inventory.Inventory()
    #creature_inventory: field = field(default_factory=inventory.Inventory) #???


    def damage_taken(self, health: float, damage: float):
        """
        
        """
        self.health = health - damage


"""

"""
@dataclass
class Player(Creatures):
    name: str = ""
    affinity: affinities.Affinities = affinities.Affinities()
    health: float = 100
    attack: float = 10
    level: int = 1
    exp: float = 0
    turns: int = 0


    def level_up(self):
        """
        
        """
        self.level = self.level + 1
        self.exp = 0
        self.exp_per_level()
        self.level_adjustment()
    
    
    def exp_per_level(self) -> float:
        """
        
        """
        #maxExp = -1 + (10 * lev) + math.log(lev - 1)
        max_exp: float
        max_exp = (0.5 * self.level) * (-self.level + 200)
        return max_exp
    
    
    def exp_gain(self, gain: float):
        """
        
        """
        self.exp = self.exp + gain
        if (self.exp >= self.exp_per_level()):
            self.level_up()


    def level_adjustment(self): #temporary... should be a bit more complex
        """
        
        """
        self.health = self.health * (1.5 * self.level)
        self.attack = self.attack * (2 * self.level)
    
    
    #remove this method
    #duration and effect amount will be dependant on the 'strength'
    def potion_drunk(self, potion: items.Potions):
        """
        
        """
        self.turns = 0
        #only loops once... move into a different method?
        if self.turns <= potion.duration:
            if potion.effect == "Health":
                self.health = self.health + 10 
    
    
    def increment_turn(self):
        """
        
        """
        self.turns = self.turns + 1


"""

"""
@dataclass
class Monsters(Creatures):
    exp_dropped: float = 0.0
        
         
    def level_adjustment(self): #temporary... should be a bit more complex
        """
        
        """
        self.health = self.health * (1.5 * self.level)
        self.attack = self.attack * (2 * self.level)
        self.exp_dropped = self.exp_dropped * (2.5 * self.level)
    
    
    #def generate_monster_inventory(name: str):
    #    if name == "Goblin":
    #        Goblin.inventory = \
    #        {
    #            inventory.Types.WEAPONS: {items.rusty_iron_dagger, 1}
    #        }


"""

"""
@dataclass
class Goblin(Monsters):
    name = "Goblin"
    affinity = affinities.earth
    health = 25
    attack = 5
    level = 1
    exp_dropped = 20


"""

"""
@dataclass
class Zombie(Monsters):
    name = "Zombie"
    affinity = affinities.earth
    health = 40
    attack = 7
    level = 1
    exp_dropped = 30


"""

"""
@dataclass
class Skeleton(Monsters):
    name = "Skeleton"
    affinity = affinities.wind
    health = 15
    attack = 15
    level = 1
    exp_dropped = 40


#
player: Creatures = Player()
goblin: Monsters = Goblin()
zombie: Monsters = Zombie()
skeleton: Monsters = Skeleton()


def display_monster_stats(target: Monsters):
    """
    
    """
    print(f'Name: {target.name}')
    print(f'Affinity: {target.affinity}')
    print(f'Health: {target.health}')
    print(f'Attack: {target.attack}')
    print(f'Level: {target.level}')
    #make monster inventory?  


def view_monsters_inventory(monster: Monsters):
    """
    
    """
    # MERGE WITH INVENTORY.py!!!!
    for item_type, items in monster.monster_inventory.items():
        print(f"{item_type.name}:")
        for item, quantity in items.items():
            print(f"{item.name}: {quantity}")


def display_player_stats():
    """
    
    """
    print(f'Name: {player.name}')
    print(f'Affinity: {player.affinity}')
    print(f'Health: {player.health}')
    print(f'Attack: {player.attack}')
    print(f'Level: {player.level} | {player.exp} / {player.exp_per_level()}')
    print('Inventory is:')
    player.creature_inventory.display_inventory_item_names() 