In Progress:
- submit

Blocked:
- Move error output outside of RPN class and generate better test measures
- implement Observable
- SHIFT functionality using KeyboardFocusManager?

Pending:
- always show 0.0, not empty
- make deg/rad an actual SWITCH

Ideas: 
- Add more functionality to GUI
- How to print stack? different text box/es?

Done:
- Skeleton of calculator
- popNumber() and pushNUmber()
- Methods for add(), sub(), mul(), div(), and sin()
- Testing method integrated
- Calculator function separated from main()
- Add more methods: cos, tan, etc.; remember to add tests BEFORE the methods
- Add DEG/RAD switch
- Fix sin(), cos(), tan() function
- Implement JavaDoc into current methods
- Add clear screen/stack function
- Add Pi method
- Implement missed function TESTS
- Add Square root
- percentage method
- Undefined number error? (like division by 0)
- Add more comments to clarify code, and JavaDoc documentation
- inverse trigonometric functions
- remainder function
- log function
- JavaDoc on tests
- User Stories and Acceptance Tests section added
- Acceptance Test: GUI Basic Operators
- Acceptance Test: Stack Manipulation
- Stack Manipulation GUI Implemented
- Implement sync function
- Add GUI ideas to gui_ideas.md
- Acceptance Test: Trigonometric Functions
- Add trigonometric buttons/functions
- Fix textbox not clearing
- 10^x function
- Format trig functions GUI
- Format basic operations GUI
- Implement BorderLayout onto current panels
- Panel for functions (ENTER, DEG/RAD, etc...) and add to main Panel
- javax.swing implementation for borders
- Canvas Feedback implemented
- Work on formatting and positioning panel/s
- Acceptance Test for log, root, power, etc. 
- Add buttons for log, root, power, etc.
- Work on gui_ideas.md
- implement gui ideas into calc
- fix cases (capitalisation)
- apply DRY principle to trig functions?
- Booleans (isDegrees and boxClear) ***important***
- GUI SHIFT button?
- Minimum Viable Product
- Acceptance Test for percentage of, remainder, pi
- implement percentage, pi, percentage of buttons
- polish GUI
- JAVADOC ***IMPORTANT***
- finish ACCEPTANCE TEST ***important***

***

User Stories and Acceptance Tests:


Title: GUI Number Pad

As a: student,
I want: to be able to use numbers,
So that: I can actually do my maths.

Number Pad:
Given: a number 0-9 on each button,
When: a button of choice is selected,
Then: the corresponding number should push into the x variable on the stack.

Decimal Point:
Given: the number 9 in the display,
When: the . button is pressed,
Then: the display will read 9.,
If: another number is pressed,
Then: the number should go behind the decimal.


Title: GUI Basic Operators

As a: student,
I want: to be able to perform basic operations for maths,
So that: I can pass my tests.

Addition:
Given: the stack: x: 1, y: 2
When: the + button is pressed,
Then: the output should be 3.

Subtraction:
Given: the stack: x: 1, y: 2
When: the - button is pressed,
Then: the output should be 1.

Multiplication:
Given: the stack: x: 5, y: 10
When: the * button is pressed,
Then: the output should be 50.

Division:
Given: the stack: x: 4, y: 20
When: the / button is pressed,
Then: the output should be 5.


Title: Stack Manipulation

As a: student,
I want: to manipulate the stack with button presses,
So that: I can perform multiple calculations fluidly.

Enter:
Given: 45 in the display,
When: the ENTER button is pressed,
Then: x = 45.

Clear Stack:
Given: the stack: x: 45, y: 8, z: 0, t: 3,
When: the CLEAR button is pressed,
Then: the stack should clear.


Title: Trigonometric Functions

As a: student,
I want: to perform trigonometric functions,
So that: I can do my trigonometric questions in maths class.

Degrees Switch:
When: the DEG button is pressed,
Then: the calculator is in Degrees mode.

Radians Switch:
When: the RAD button is pressed,
Then: the calculator is in Radians mode.

Sine:
Given: x = 90,
And: in DEG Mode,
When: the SIN button is pressed,
Then: the output will be 1.
If: in RAD Mode,
Then: the output will be ~0.8939966636.

Cosine:
Given: x = 90,
And: in DEG Mode,
When: the COS button is pressed,
Then: the output will be 0.
If: in RAD Mode,
Then: the output will be ~-0.4480736161.

Tangent:
Given: x = 45,
And: in DEG Mode,
When: the TAN button is pressed,
Then: the output will be 1.
If: in RAD Mode,
Then: the output will be ~1.619775191.

Inverse Sine:
Given: x = 1,
And: in DEG Mode,
When: the ASIN button is pressed,
Then: the output will be 90.
If: in RAD Mode,
Then: the output will be ~1.570796327.

Inverse Cosine:
Given: x = 0,
And: in DEG Mode,
When: the ACOS button is pressed,
Then: the output will be 90.
If: in RAD Mode,
Then: the output will be ~1.570796327.

Inverse Tangent:
Given: x = 1,
And: in DEG Mode,
When: the ATAN button is pressed,
Then: the output will be 45.
If: in RAD Mode,
Then: the output will be ~0.7853981634.

Pi:
Given: x = 4,
And: in DEG Mode,
When: the PI button is pressed,
And: the * button is pressed,
Then: the output will be ~12.56637061.
If: in RAD Mode,
Then: the output will be ~12.56637061.


Title: Logarithmic/Exponential Functions

As a: student,
I want: to perform logarithmic and exponential functions
So that: I can do my function studies.

Log:
Given: x = 100,
When: when the LOG button is pressed,
Then: the output will be 2.

Power:
Given: the stack: x: 3, y: 2,
When: the y^x button is pressed,
Then: the output will be 8.

Times Ten to the Power of:
Given: the stack: x = 3, y: 2,
When: the y*10^x button is pressed,
Then: the output will be 8000.

Square Root:
Given: x = 16,
When: the ROOT button is pressed,
Then: the output will be 4.


Title: Percentages

As a: student,
I want: to be able to perform percentage calculations, 
So that: I can do my statistics.

Percentage Of:
Given: the stack: x: 20, y: 10,
When: the % button is pressed,
Then: the output will be 2.

Remainder:
Given: the stack: x: 7, y:15,
When: the %% button is pressed,
Then: the output will be 1.
