# TO-DO



## In Progress

- Fix inventory issues

## Blocked



## Pending

- Re-write movement (into match case statements)
- Re-format code (again) to pycodestyle and clean up comments
- Add terminal colouring (rarity of item, time of day, etc.)
- Difficulty changes depending on time of day
- Make affinity object/class (enum?)
- Get potion duration working
- Randomise mob levels at spawn (based on danger of area)
- Make general functions for chance, etc.
- What items spawn in areas?
- Damage efficiency (affinity, weapon used, etc...)
- Critical hits from enemies
- Item collection
- Looting mob bodies
- Weave affinity effect throughout
- Evasion stat for dodging and escaping fight
- User input error checking
- Pretty up the text
- Strength and dexterity stat for critical hits and damage amount
- Fix overflowing battleDialouge statements after killing enemy
- Areas are more interactable (explore areas to find treasure?)
- Ability to rest for x amount of time
- Add weapon characteristics (rusty, damaged, amazing, etc.)

## Ideas

- Towns and NPCs
- Shops
- Magic... ties in with affinity
- End goal?
- Mob name randomiser
- Unique mob behaviour (Skeletons: ranged = true... Zombies: undead = true... etc.)
- Hunger and thirst
- Craft items

## Done

- Basic game functions added (intro, spawn, etc.)
- Basic classes made (Creatures, Areas, Weapons)
- Basic level adjustments implemented (on spawn)
- Basic player creation
- Ability to choose weapon to attack with
- Area danger affecting mob spawn
- Creature -> Monster and Player class inheritance
- Fix expDropped issue in Monsters class
- Battle attacking
- Save monster and battle state (generate new object every battle?)
- Add area specific mobs
- Battle running away
- Add critical hits
- Mob death and exp dropping
- Battle check inventory (necessary?)
- Area movement
- Rewrite Monster objects (make classes for each creature)
- Reset Monster object after death
- Add index to weapon choice
- Inventory potion interaction
- Re-write
- Fix inventory printing
- Add time functionality (ticks, times of day?)
- Make items class
- Add @dataclass to classes
- Slightly reformat code
- Refactor inventory to a class?
- Re-write classes for @dataclass
- Make materials and affinity classes