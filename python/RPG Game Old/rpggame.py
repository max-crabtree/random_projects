import random
import math

class Creatures:
    name = ""
    affinity = ""
    health = 0
    attack = 0
    level = 0

    def __init__(self):
        self.name = self.name
        self.affinity = self.affinity
        self.health = self.health
        self.attack = self.attack
        self.level = self.level

    def damageTaken(self, health, damage):
        self.health = health - damage

class Player(Creatures):
    health = 100
    attack = 10
    level = 1
    exp = 0
    turns = 0
    
    def levelUp(self):
        self.level = self.level + 1
        self.exp = 0
        self.expPerLevel()
        self.levelAdjustment()
    
    def expPerLevel(self):
        #maxExp = -1 + (10 * lev) + math.log(lev - 1)
        maxExp = (0.5 * self.level) * (-self.level + 200)
        return maxExp
    
    def expGain(self, gain):
        self.exp = self.exp + gain
        if (self.exp >= self.expPerLevel()):
            self.levelUp()

    def levelAdjustment(self): #temporary... should be a bit more complex
        self.health = self.health * (1.5 * self.level)
        self.attack = self.attack * (2 * self.level)
    
    #duration and effect amount will be dependant on the 'strength'
    def potionDrunk(self, potion):
        self.turns = 0
        #only loops once... move into a different method?
        if self.turns <= potion.duration:
            if potion.effect == "Health":
                self.health = self.health + 10 
    
    def incrementTurn(self):
        self.turns = self.turns + 1

class Monsters(Creatures):
    expDropped = 0
         
    def levelAdjustment(self): #temporary... should be a bit more complex
        self.health = self.health * (1.5 * self.level)
        self.attack = self.attack * (2 * self.level)
        self.expDropped = self.expDropped * (2.5 * self.level)

class Goblin(Monsters):
    def __init__(self):
        self.name = "Goblin"
        self.affinity = "Earth"
        self.health = 25
        self.attack = 5
        self.level = 1
        self.expDropped = 20

class Zombie(Monsters):
    def __init__(self):
        self.name = "Zombie"
        self.affinity = "Earth"
        self.health = 40
        self.attack = 7
        self.level = 1
        self.expDropped = 30

class Skeleton(Monsters):
    def __init__(self):
        self.name = "Skeleton"
        self.affinity = "Wind"
        self.health = 15
        self.attack = 15
        self.level = 1
        self.expDropped = 40

class Weapons:
    def __init__(self, type, material, attack, durability, rarity, effectiveAffinity):
        self.name = f'{material} {type}'
        self.type = type
        self.material = material
        self.attack = attack
        self.durability = durability
        self.rarity = rarity
        self.effectiveAffinity  = effectiveAffinity

    #eventually, multiplier will be effected by another skill
    #maybe 'strength'?
    def criticalHit(self):
        self.attack = 2 * self.attack 
    
    #where 2 is the multiplier for criticalHit()
    def resetAttack(self):
        self.attack = self.attack / 2

class Armour:
    def __init__(self, type, material, durability, rarity):
        self.name = f'{material} {type}'
        self.type = type
        self.material = material
        self.durability = durability
        self.rarity = rarity
  
class Potions:
    def __init__(self, effect, strength, rarity, duration):
        self.name = f'{strength} {effect} Potion'
        self.effect = effect
        self.strength = strength
        self.rarity = rarity
        self.duration = duration

class Areas:
    def __init__(self, name, danger):
        self.name = name
        self.danger = danger

player = Player()
goblin = Goblin()
zombie = Zombie()
skeleton = Skeleton()

initialWeapon = Weapons("Shortsword", "Copper", 30, 100, 0, "None")

wHealthPotion = Potions("Health", "Weak", 1, 5)

leatherHood = Armour("Hood", "Leather", 15, 0)
leatherShirt = Armour("Shirt", "Leather", 30, 0)
leatherPants = Armour("Pants", "Leather", 15, 0)
leatherBoots = Armour("Boots", "Leather", 10, 0)

plains = Areas("Plains", 0.1)
caveL = Areas("CaveL", 0.33)
caveF = Areas("CaveF", 0.33)
caveR = Areas("CaveR", 0.33)

areaLocationDict = {
            plains: {"l": caveL,  "f": caveF, "r": caveR,  "b": "..."},
            caveL: {"l": "...",    "f": "...",   "r": plains, "b": "..."},
            caveF: {"l": "...",    "f": "...",   "r": "...",    "b": plains},
            caveR: {"l": plains, "f": "...",   "r": "...",    "b": "..."}}

playerInventory = {"Weapons": {},
                   "Potions": {},
                   "Armour" : {}}

validAreaMobSpawnDict = {"Plains": [goblin, zombie, skeleton],
                         "CaveL": [skeleton],
                         "CaveF": [skeleton],
                         "CaveR": [skeleton]}

def intro():
    print("Simple RPG Game")
    input("Press any key to continue: ")

def initialAffinity():
    affinityList = ["Earth", "Wind", "Fire", "Water"]
    rand = random.randint(0, 3)
    return affinityList[rand]
        
def createPlayer():
    print("Create your player here")
    chosenName = input("What is your name?: ")
    player.name = chosenName
    print("Your affinity is randomly chosen!")
    affinity = initialAffinity()
    print(f'You got: {affinity}!')
    player.affinity = affinity
    #better way to do this?
    playerInventory["Weapons"][initialWeapon] = 1
    playerInventory["Potions"][wHealthPotion] = 1
    playerInventory["Armour"][leatherHood] = 1
    playerInventory["Armour"][leatherShirt] = 1
    playerInventory["Armour"][leatherPants] = 1
    playerInventory["Armour"][leatherBoots] = 1
    print("Here are your stats:")
    displayPlayerStats()
    return player

#use maps once there are multiple dicts??
#currently only shows ['Weak Health Potion']...
def displayPlayerInventoryItemNames():
    for item in playerInventory["Weapons"] and playerInventory["Potions"]:
        itemNames = []
        itemNames.append(item.name)
    return itemNames
#scrap ^
#rewrite into indexInventory()

def displayPlayerStats():
    print(f'Name: {player.name}')
    print(f'Affinity: {player.affinity}')
    print(f'Health: {player.health}')
    print(f'Attack: {player.attack}')
    print(f'Level: {player.level} | {player.exp} / {player.expPerLevel()}')
    print(f'Inventory: {displayPlayerInventoryItemNames()}')

def displayMonsterStats(target):
    print(f'Name: {target.name}')
    print(f'Affinity: {target.affinity}')
    print(f'Health: {target.health}')
    print(f'Attack: {target.attack}')
    print(f'Level: {target.level}')
    #make monster inventory?    

def displayPotionStats(potion):
    print(f'Name: {potion.name}')
    print(f'Effect: {potion.effect}')
    print(f'Strength: {potion.strength}')
    print(f'Rarity: {potion.rarity}')
    print(f'Duration: {potion.duration}')

def displayAreaStats(currentArea):
    print(f'Name: {currentArea.name}')
    print(f'Danger: {currentArea.danger}')
    
def pickRandomAreaMob(currentArea):
    areaKey = validAreaMobSpawnDict[currentArea.name]
    rand = random.randint(0, len(areaKey) - 1)
    mobChoice = areaKey[rand]
    willMobSpawn(currentArea, mobChoice)

def willMobSpawn(currentArea, mob):
    mobChance = currentArea.danger * 100 #make more complex
    chance = random.randint(0, 100)
    if chance < mobChance:
        mobSpawn(mob)
    else:
        return    

def generalPrompt():
    player.incrementTurn()
    print("What do you do?")
    decision = input(">> ")
    return decision

def changeArea(currentArea, direction):
    newArea = areaLocationDict[currentArea][direction]
    return newArea

def doTurn(currentArea):
    while player.health > 0:
        player.incrementTurn()
        displayAreaStats(currentArea)
        pickRandomAreaMob(currentArea)
        choice = generalPrompt()
        match choice:
            case "f" | "l" | "r" | "b":
                currentArea = changeArea(currentArea, choice)
            case "i":
                displayPlayerInventoryItemNames()
            case "d":
                displayPlayerStats() #make so it doesnt do a turn?

def initialSpawn(initial):
    print("You spawn on these peaceful plains...")
    doTurn(initial)

def mobSpawn(mob):
    print(f'A {mob.name} appears!')
    displayMonsterStats(mob)
    battleDialouge(mob)

def generateNew(deadEnemy):
    deadEnemy = deadEnemy.__init__()

def battleDialouge(enemy):
    while enemy.health > 0:
        print(
        '''What will you do?
    Attack: a
    Run: r
    Inventory: i''')
        choice = generalPrompt()
        match choice:
            case "a":
                battleAttack(enemy)
            case "r":
                battleRun(enemy)
            case "i":
                battleCheckInventory(enemy)
            case _:
                print(f'{choice} is not a valid key! Try again!')
    print(f'{enemy.name} died!')
    print(f'You gained {enemy.expDropped} EXP!')
    player.expGain(enemy.expDropped)
    generateNew(enemy)
    generalPrompt()

#add index for user selection (0, 1, etc.)
def chooseWeapon():
    index = 0
    weaponList = []
    for weapon in playerInventory["Weapons"]:
        print(f'''{index}: {weapon.name}
        Attack: {weapon.attack}
        Durability: {weapon.durability}
        Effective Affinity: {weapon.effectiveAffinity}\n''')
        index = index + 1
        weaponList.append(weapon)
    itemChoice = int(generalPrompt())
    return weaponList[itemChoice]
        
def battleAttack(enemy):
    print("What do you attack with?")
    weaponChoice = chooseWeapon()
    print(f'You attack with the {weaponChoice.name}!')
    attackResult(enemy, weaponChoice)
    #show effectiveness of each weapon against enemy (affinity)

def chanceOfAttackHit(player, enemy):
    #return (player.level / enemy.level) - 0.25
    return 1 #testing purposes
    
def isCriticalHit():
    #will be affected by a modifier... 'strength'? or 'dexterity'? maybe
    percent = 100
    rand = random.randint(0, 100)
    if rand < percent:
        return True
    else:
        return False

def attackResult(enemy, playerWeapon):
    chanceOfHit = chanceOfAttackHit(player, enemy)
    #chanceOfHit = (player.level / enemy.level) - 0.25
    #turn into function?
    if chanceOfHit <= 0:
        print(f'You missed the {enemy.name}!')
        enemyAttack(enemy)
    elif chanceOfHit >= 1:
        print(f'You hit the {enemy.name}!')
        if (isCriticalHit()):
            playerWeapon.criticalHit()
            print("It was a critical hit!")
        enemy.damageTaken(enemy.health, playerWeapon.attack)
        print (f'You dealt {playerWeapon.attack} damage to the {enemy.name}!')
        print(f'The {enemy.name} now has {enemy.health} health!')
        playerWeapon.resetAttack()
        if (enemy.health > 0):
            enemyAttack(enemy)
    else:
        randChance = random.randint(0, 100)
        print(f'You have a {chanceOfHit} chance of hitting')
        enemyAttack(enemy)
    #evasion stat?
   
#add enemy critical attack?
#add enemy weapons first
def enemyAttack(enemy):
    print(f'The {enemy.name} goes to attack!')     
    chanceOfHit = chanceOfAttackHit(player, enemy)
    if chanceOfHit <= 0:
        print(f'{enemy.name} missed!')
    elif chanceOfHit >= 1:
        print(f'{enemy.name} hit!')
        player.damageTaken(player.health, enemy.attack)
        print(f'{enemy.name} dealt {enemy.attack} damage!')
        print(f'You now have {player.health} health!')

def battleRun(enemy):
    print("You attempt to run away!")
    #chanceOfEscape = (player.level / enemy.level) - 0.25
    #implement evasion stat later
    chanceOfEscape = 1 #testing purposes
    if chanceOfEscape <= 0:
        print("You couldn't escape!")
        enemyAttack(enemy)
    elif chanceOfEscape >= 1:
        print("You escaped!")
        enemy.health = 0 #break the loop, better way?

def usePotion(potion):
    print(f'You drunk the {potion.name}!')
    player.potionDrunk(potion)

def potionInteract(potion):
    print(f'You selected the {potion.name}!')
    choice = generalPrompt()
    match choice:
        case "d":
            usePotion(potion)
        case "v":
            displayPotionStats(potion)
        case "c":
            return

def indexInventory(*type):
    index = 0
    itemList = []
    for item in type:
        for objKey in item.keys():
            itemKey = objKey
        for objValue in item.values():
            itemAmount = objValue 
        print(f'{index} {itemKey.name}: {itemAmount}')
        itemList.append(itemKey)
        index = index + 1
    choice = int(generalPrompt())
    return itemList[choice]

def battleCheckInventory(enemy):
    print("Inventory is:")
    #better way to do this?
    choice = indexInventory(playerInventory["Weapons"], playerInventory["Potions"])
    if choice in playerInventory["Weapons"]:
        attackResult(enemy, choice)
        #add different functionality? like viewing weapon
    elif choice in playerInventory["Potions"]:
        potionInteract(choice)

intro()
createPlayer()
initialSpawn(plains)