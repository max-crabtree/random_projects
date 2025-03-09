from random import randint
from time import sleep

def wholegame():
 print("\nRock Paper Scissors Game\n")
 sleep(1)
 gamenumber = input("How many rounds would you like to play?: \n")
 print(f"Best of {gamenumber}!\n")
 pscore and cscore = 0
 currentround = 1
 def rpsGame():
  global currentround
  print(f"Round {currentround}\n")
  currentround = currentround + 1
  player = input("Rock (r), Paper (p) or Scissors (s)? \n")
 
  if player == 'r':
    player = "Rock"
    pasart = "o"
  
  if player == 'p':
    player = "Paper"
    pasart = "--"
  
  if player =='s':
    player = "Scissors"
    pasart = "o<"
  
  chosen = randint(1, 3)
 
  if chosen == 1:
    computer = 'Rock'
    casart = "o"
  
  if chosen == 2:
    computer = 'Paper'
    casart = "--"
  
  if chosen == 3:
    computer = 'Scissors'
    casart = "o<"

  print(f"{pasart} vs {casart}")
  
  print(f"{player} vs {computer}\n")
 
  global pscore
  global cscore
  sleep(1.5)
  
  if player == computer:
    print("It's a draw")
    pscore = pscore + 1
    cscore = cscore + 1
   
  if player == 'Rock' and computer == 'Paper':
    print("Computer Wins!")
    cscore = cscore + 1
 
  if player == 'Paper' and computer == 'Scissors':
    print("Computer Wins!")
    cscore = cscore + 1
  
  if player == 'Scissors' and computer == 'Rock':
    print("Computer Wins!")
    cscore = cscore + 1 
 
  if player == 'Rock' and computer == 'Scissors':
    print("Player Wins!")
    pscore = pscore + 1 
 
  if player == 'Paper' and computer == 'Rock':
    print("Player Wins!")
    pscore = pscore + 1  
  
  if player == 'Scissors' and computer == 'Paper':
    print("Player Wins!")
    pscore = pscore + 1
    
  sleep(1)
  
  print() 
  print("Player has", pscore, "points!")
  print("Computer has", cscore, "points!")
  
  print()
    
  game = rpsGame()
 
 for game in range(1, int(gamenumber)):
   rpsGame()
 
 cwinner = cscore > pscore
 pwinner = pscore > cscore
 tie = pscore == cscore or cscore == pscore

 sleep(2)
 
 print("The game is over!")

 sleep(2)

 print("The overall winner is...")
 
 sleep(2)
 
 if cwinner: 
   print("The Computer!")
 
 if pwinner:
   print("The Player!")
 
 if tie:
   print("No one, it's a tie!")
 
 sleep(2)

 print()
 
 again = input("Would you like to play again? (y/n) ")
 
 if again == "y":
   wholegame()
 
 if again == "n":
   exit()

wholegame()
