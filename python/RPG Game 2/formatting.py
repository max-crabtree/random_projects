"""Functions for formatting terminal text"""

__author__ = "Max Crabtree"

import colours
import items

INVALID_INPUT = f"{colours.RED}Invalid input!{colours.RESET}"

def coloured_text(text: str, colour: str) -> str:
      """
      Returns a "colour" coloured version of "text".
      """
      return f"{colour}{text}{colours.RESET}"


def fancy_border(words: str, surrounding: str, colour: str = colours.WHITE):
    """
    Creates a fancy border of "surrounding" around "words", and coloured "colour".
    """
    
    print()
    print(colour + surrounding * (len(words) + 4),
          f"{surrounding} {words.upper()} {surrounding}",
          surrounding * (len(words) + 4) + colours.RESET, sep="\n")
    print()


def item_reveal(item: items.Item) -> str:
      """
      Fancily reveals an item. The look is dependant on the item's rarity.
      """
      colour: str
      item_rarity: items.Rarity = item.rarity
      symbol: str = item.rarity.value
      item_name: str = str(item)
      
      match item_rarity:
            case items.Rarity.COMMON:    colour = colours.WHITE
            case items.Rarity.UNCOMMON:  colour = colours.GREEN
            case items.Rarity.RARE:      colour = colours.BLUE
            case items.Rarity.EPIC:      colour = colours.MAGENTA
            case items.Rarity.LEGENDARY: colour = colours.YELLOW
      
      return coloured_text(f"{symbol} {item_name} {symbol}", colour)
