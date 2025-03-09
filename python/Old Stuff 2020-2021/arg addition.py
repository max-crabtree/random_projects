from typing import Literal


def func(y,x,arg):
  z = float(x); arg; float(y) #fix
  print(f"{y} {str(arg)} {x} = {z}")
arg = input("What argument?: ")
numy = input("First Number: ")
numx = input("Second Number: ")
func(numy,numx,arg)