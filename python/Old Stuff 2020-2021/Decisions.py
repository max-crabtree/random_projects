import random
import time

def intro():
  print("This is a game, in which decisions are made...") 
  time.sleep(1) 
  print("Hit ENTER to continue") 
  input()
  
areas = {"Unknown Fields":{"1":"Beach","2":"Cave","3":"Forest"}, "Ominous Cave":{ "1":"Deep Cave","2":"Harsh Plains","3":"Sulfuric Desert"}, "Beach":{"1":"???","2":"???","3":"???"}, "Cave":{"1":"???","2":"???","3":"???"}, 
         "Forest":{"1":"???","2":"???","3":"???"}, "Deep Cave":{"1":"Deeper Cave","2":"???","3":"???","i":"Small Dagger"}, "Harsh Plains":{"1":"???","2":"???","3":"???"}, "Sulfuric Desert":{"1":"???","2":"???","3":"???"}, 
         "Deeper Cave":{"1":"The Core","2":"???","3":"???", "i":"Iron Greatsword"}, "The Core":{"1":"???","2":"???","3":"???"}, "???":{"1":"???","2":"???","3":"???"}
}

monstdict = {"Rat":[5,2,0], "Dweller":[15,8,5]}

def main(x): 
  currentlocation = [key for key in areas.keys()][x]
  inventory = []
  plhealth = 100
  while True:
    def monstersf(x):
      class monsters:
        def __init__(mon, name, health, attack, affinity):
          mon.name = name
          mon.health = health
          mon.attack = attack
          mon.affinity = affinity
      currentmonster = [key for key in monstdict.keys()][x]
      mon = monsters([currentmonster], monstdict[currentmonster][0], monstdict[currentmonster][1], monstdict[currentmonster][2])
      def fight():
        while True:
          time.sleep(0.5)
          print(f"You see a {currentmonster}!")
          time.sleep(1)
          monstats = print(f"Name: {mon.name}\nHealth: {mon.health} | Attack: {mon.attack} | Affinity: {mon.affinity}")
          monstats
          def monsattack():
            print(f"{currentmonster} is going to attack!")#add defend, run, etc?? 
            time.sleep(1)
            if random.random() <= 0.5: 
              plhealth == plhealth - mon.attack #FIX HEALTH GLOBAL ISSUE!!
              print(f"You lost {mon.attack} health!")
            else:
              print(f"The {currentmonster} missed!")
            if plhealth == 0:
              print("You died...")
              time.sleep(2)
              print("--GAME OVER--")
              exit()
          time.sleep(0.5)
          def playerfight():
            print("What do you do?")
            time.sleep(0.5)
            print("a: Attack | d: Defend\nh: Heal   | r:Run")
            def attack():
              #what item? #what attack #damage?
              print("What item do you use?")
              for num, i in enumerate(inventory):
                print(f"{num} : {i}")              #python ignoring this??
              
              decision = input(">> ") 
              decision = decision.split() #fix decision every new function?
              if decision[0] == num in enumerate(inventory):
                print(f"You chose the {enumerate(inventory)[0]}!") #make smarter // if decision = x / you chose blah blah x...
                print("Would you like to see the stats? y/n")
              decision = input(">> ") 
              decision = decision.split()
              if decision[0] == "y":
                #add statistics to items (through dict? like monsters)
                print("stats")
              if decision[0] == "n":
                #dont know
                print("N/a") 
              else:
                print("This is not an option...")         
            def defend():
              print("You defend")
            def heal():
              print("You heal")
            def run(): #**
              if monstersf(x<5 == True):
                chance = 0.80
              if monstersf(x>10 == True):
                chance = 0.25
              if monstersf(x>15 == True):
                chance = 0.05
              if random.random() <= chance:       #run() does not work**
                print("You escaped!")
                time.sleep(1)
              else:
                print("You can't escape...")
                time.sleep(1)
                playerfight()  
            decision = input(">> ") 
            decision = decision.split()
            if decision[0] == "a": attack()
            if decision[0] == "d": defend()
            if decision[0] == "h": heal()
            if decision[0] == "r": run()
          playerfight()
          monsattack()
          break
      fight()
    time.sleep(1) 
    print(f"Current Location: {str(currentlocation)}")
    print(f"Inventory: {str(inventory)}") 
    print(f"Health: {str(plhealth)}")
    if random.random() <= 0.4:
      monstersf(random.randint(0,1))
    else:
      time.sleep(1) 
      print("What will you do?")
      print("\n".join(f"{k}:  Go to the {v}"                           #<<
      for k, v in sorted(areas[currentlocation].items(), key=lambda t: #<< StackOverflow copy
        str(t[0]))))                                                   #<<
      decision = input(">> ") 
      decision = decision.split()
      if decision[0] in areas[currentlocation]: 
        currentlocation = areas[currentlocation][decision[0]] 
      else: 
        print("This is not an option...")
      if 'i' in areas[currentlocation]:
        print(f"\nCurrent Location: {str(currentlocation)}") 
        time.sleep(1)
        item = areas[currentlocation]['i']
        print(f"You see a {item}")
        time.sleep(1)
        print("Do you pick it up y/n?")
        decision = input(">> ")
        decision = decision.split()
        if decision[0] == "y":
          print(f"You picked up the {item}!")
          inventory.append(item)
          del areas[currentlocation]['i']
        elif decision[0] == "n":
          print(f"You leave the {item} alone")
          del areas[currentlocation]['i']
        else: 
          print("This is not an option...")
  
def sun(): 
  print("It seems you have spawned on a Sun... you die instantly.")
  time.sleep(0.5)
  exit()
def pplanet(): 
  print("You have spawned on a peaceful planet, nought of hostilities")
  main(0)
def eplanet(): 
  print("This planet seems strange... be careful")
  main(1)

def spawnarea():
  spawn = random.randint(3, 3)
  if spawn == 1: sun()
  if spawn == 2: pplanet()
  if spawn == 3: eplanet()

intro()
spawnarea()

#make message timing thing? later
#make information area
#make inventory cleaner + item collection multiple times??
#use items???
#planet = difficulty (add more planets)!
#how to do fighting etc??
#for monsters use func(x) // x = randint(1,10) // less numbers for peaceful!!
#extra prints = dict{{"p" = "Extra"}} // FOR "p" in dict etc etc...
#add quests?? vendors cities???????????????????