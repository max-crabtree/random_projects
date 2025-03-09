"""Some functions for prompts"""

from formatting import coloured_text, fancy_border, INVALID_INPUT
import colours


def y_n_prompt(prompt: str) -> bool:
    """
    A prompt for a yes or no question; returns True if yes, False if no.
    """
    choice: str

    while True:
        choice = input(prompt + ":\n").lower()
        if choice in ["y", "yes", "sure"]:
            return True
        elif choice in ["n", "no", "nope"]:
            return False
        else:
            print(coloured_text(f"{choice} is not a valid input!", colours.RED))


def invalid_input():
    """
    Function to display an invalid input message and prompt the user to view commands.
    """
    print(INVALID_INPUT)
    if y_n_prompt("View commands?"):
        display_commands()


# will probably move
def display_commands():
    """
    Displays the commands from commands.txt.
    """
    commands_file: str

    fancy_border("Commands", '=', colours.MAGENTA)
    commands_file = str(open("commands.txt", "r").read())
    print(commands_file)
