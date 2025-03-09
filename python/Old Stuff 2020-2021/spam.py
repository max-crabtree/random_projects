#GOT FROM TUTORIAL

import pyautogui 
import time

message = input("Enter message: ")
number = input("How many times?: ")

print("Timer!")

timer = 5

while(timer != 0):

	print(timer)
	time.sleep(1)
	timer -= 1

print("Working...")

for i in range(0, int(number)):

	pyautogui.typewrite(message + '\n')
