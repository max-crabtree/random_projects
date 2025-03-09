#50/50

from numpy import random

print("50/50 Game")

input()

print("Will the vase fall or break?")

print()

vase = 1
hand = 2
floor = 3

if vase == 1:

	random.choice(vase + 1, vase + 2)


if vase == hand:

	print("The vase was caught!")


if vase == floor:

	print("The vase fell!")

