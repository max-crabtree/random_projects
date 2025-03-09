import time
from random import randint

print("Guess the right number.")

pnum = input("Type number here: ")

time.sleep(0.3)
print(".")
time.sleep(0.3)
print(".")
time.sleep(0.3)
print(".")
time.sleep(0.3)
print(".")
time.sleep(0.3)

rand = randint(1, 100)
rnum = print("The random number is:", rand, "!")

if pnum == rand:
 print("You won!")

else:
 print("You lost, good try.")

input()
exit()