class Objects:
    def __init__(self, name):
        self.name = name

obj1 = Objects("Obj1")
obj2 = Objects("Obj2")

nestedDict = {"Dict1": {obj1: 1},
              "Dict2": {obj2: 2}}

for hello in nestedDict["Dict1"]:
    print(hello)
    print(hello.name)