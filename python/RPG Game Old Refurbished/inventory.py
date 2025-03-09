import general_prompt
import creatures
import items
from dataclasses import dataclass
from dataclasses import field


# ChatGPT Helped
@dataclass
class Inventory():
    """

    """
    # inventory_items: field = \
    #     field(default_factory=dict[str, dict[items.Items, int]])
    inventory_items: field = \
        field(default_factory=dict[items.Items, int])

    def add_item(self, item: items.Items):
        self.inventory_items[item] += 1
        pass

    def remove_item(self, item: items.Items):
        if item.type in self.inventory_items and \
           item in self.inventory_items[item.type]:
            self.inventory_items[item.type][item] -= 1
            if self.inventory_items[item.type][item] == 0:
                del self.inventory_items[item.type][item]

    def display_inventory_item_names(self):
        for item_types, items in self.inventory_items:
            print(f"{item_types}:")
            for item, quantity in items:
                print(f"{item}: {quantity}")

    def __str__(self):
        return self.display_inventory_item_names()


# Remove this eventually
def index_player_inventory(*type: items.Types):
    """

    """
    num = 1
    for item in type:
        print(f"{type}:")
        print(f"{num}: {item.values.name}\tAmount: {item.values}")
        for type in player_inventory:
            inventory_list = [type, [item]]
        num += 1
    choice = general_prompt.general_prompt().split()
    selected_type = choice[0]
    selected_index = choice[1]
    return inventory_list[selected_type, [selected_index]]


# use maps once there are multiple dicts??
# currently only shows ['Weak Health Potion']...
def display_player_inventory_item_names():
    """

    """
    player_inventory = creatures.player.creature_inventory.inventory_items

    for item_type, items in player_inventory:
        print(f"{item_type}:")
        for item, quantity in items:
            print(f"{item}: {quantity}")
