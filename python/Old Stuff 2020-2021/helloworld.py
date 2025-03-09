print("Hello, World!")

###############################################################

if 5 > 2:
  print("5 is greater than 2!")

###############################################################

print("--Need indentation--")

###############################################################

print("variables")


a = "Yes"
b = "No"
c = "And"

print(a)
print(c)
print(b)

###############################################################

#comments? cool
#how do I put spaces in-between the lines?
#comments can be used to hide code
"""
strange
"""

###############################################################

d = "9"
d = "8"

print(d)

#variables can be cancelled with another line of code

###############################################################

e, f = "igloo", "grass"

print(e)
print(f)

###############################################################

g = "dream"
print(g + " team")

###############################################################

h = 480
i = 8973459
print(h * i)

###############################################################

#end for now 1233 lol


#global variables
#if outside function
#if in func it is local

###############################################################

j = 25

#data types

print(type(j))
#this is int

###############################################################

import random
print(random.randrange(0, 999999999999999999))
#randomizer

###############################################################

k = int(574)

print(k)

###############################################################

#strings is basically typing text

print("Very easy")
print('Single Quotes')

###############################################################

#variable strings

l = "Variables can write text"
print(l)

###############################################################

#arrays elements?

m = "Arrays and Stuff"
print(m[12])
print(m[13])
print(m[14])
print(m[15])

print("pretty cool")

###############################################################

#slicing is similar

n = "RACECAR"
print(n[0:4])
print(n[4:7])
#cool
#can use negative numbers to start at the end instead

###############################################################

o = "Python is cool, I'm doing next year for Computing class. Should be pretty fun."
print(len(o))
#the length is 78 

###############################################################

#string methods

#turn lowercase
p = "THIS SHOULD BE LOWERCASE"
print(p.lower())

#turn uppercase
q = "this should be uppercase"
print(q.upper())

#replace letters
r = "Hello, Horld"
print(r.replace("H", "W"))

s = "Wello, World"
print(r.replace("W", "H"))

###############################################################

t = print(r[0:5])

u = print(s[7:12])

###############################################################

#splitting strings

v = "Splitting Stuff"
print(v.split(" "))

###############################################################

#checking if something is in the string

w = "That game is lame"
x = "ame" in w
y = "ame" not in w
print(x)
print(y)

###############################################################

#combining variables

z = "Python"
aa = " Programming"
ab = z + aa
print(ab)

################################################################

#formatting

ac = "840"
ad = "The number is {}" #brackets = ac
print(ad.format(ac))

#can be placed in an order too

ae = "The"
af = "number"
ag = "is"
ah = "950"
ai = "{} {} {} {}"
print(ai.format(ae, af, ag, ah))

################################################################

#boolean true and false

print(100>1.00)
print(7.09 > 7.90)

aj = 497
ak = 49.7

if aj > ak:
 print("497 is greater than 49.7")

 ###############################################################

 #lists

 al = ["list0", "list1", "list2"]
 print(al)

 #go by index number

 print(al[2]) #opens list2

 #basically array elements

 #################################################################


input()