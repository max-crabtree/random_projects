print("Email Creator")
print()

fname = input("First Name: ")
lname = input("Last Name: ")
def numb():
   numb = input("Any numbers? (Max of 3): ")
   if len(numb) > 3:
       print("Too many numbers!")
       numb()
numb()

name = str(fname), str(lname)

creation = "@".join(name)
print(creation)


