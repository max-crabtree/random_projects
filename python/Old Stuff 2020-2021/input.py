def game():

	print()

	print("--INPUT GAME--")
	
	print("ENTER to start")

	input()

	print("input")

	print()

	print("^^^What goes after?^^^")

	print()

	keyInput = input()

	print()

	print("You chose", keyInput + ".")

	print()

	if keyInput == "game":

		print("Correct.")

	if not keyInput == "game":

		print("Incorrect.")

		print()
		print()

		print("Again?")
		print("Type 'yes' for yes and 'no' for no.")

	print()

	if input() == "yes":

		game()

	if input() == "no":

		exit()


def exit():

	print()
	print()
	
	print("Hit ENTER to close.")

	input()

############################################ functions

game()