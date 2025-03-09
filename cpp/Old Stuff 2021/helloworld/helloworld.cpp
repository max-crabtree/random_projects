#include <iostream>
#include <string> //necessary for strings

using namespace std;

void func1(int num18 = 0, string words13 = "Unknown"); //function declaration can be split from defintion for better, optimised code //parameters must be in declaration too //0 is default value; so if no arguments it will = 0 << this must be in declaration**
int func2(int num19 = 0, int num20 = 0); //defaults are 0
int func3(int num21 = 0, int num22 = 0);
double func3(double num21 = 0, double num22 = 0); //overloaded functions: same name, different parameter

class class1{ //define class name
  public: //access //will learn more .. .. ..
    int num25; //attributes
    string words13;
    void func4(string words14){ //functions can be called in classes with objects
      cout << "\nFunction within a class using " << words14 << "!\n";
    }
    void func5(string words15); //declaration
};

class class2{
  public:
    int num26;
    string words16;
    class2(int num27, string words17); //called whenever object is made
};

class class3{
  private: //private, cannot be accessed outside of the class | preffered way to handle things
    int num28; //private
  public:
    void func6(int num29){ //new parameter
      num28 = num29; //the parameter = num28 (private) (used to set value)
    }
    int func7(){
      return num28; //now returns parameter (num28) (with new value)
    }
}; //the above method is a lot more versatile in comparison to leaving everything public...

class class4{
  public:
    int num30;
};

class class5 : public class4{ //inherits public attributes from class4 //turns it all private //multilevel is possible if necessary** //multiple base classes can be obtained with a comma separted list**
  private: //due to this //protected is essentially private, but able to be accessed from inheritated classes
    string words18; //private string
  public:
    void func9(int num31, string words19){ //same as above, making private attributes public
      num30 = num31; 
      words18 = words19;
    }
    int func10(){
      return num30;
    }
    string func11(){
      return words18;
    }
};

void class1::func5(string words15){
  cout << "Functions can also be called from outside the class!" << words15 << "\n"; //definition (outside of class)
}

class2::class2(int num27, string words17){ //can also be defined within class
  num26 = num27; //this = the attributes
  words16 = words17;
}

int main() { //function definition
  cout << "Hello World!\n"; //prints "Hello World!"
  cout << "I am learning C++!\n\n"; //prints "I am learning C++!"
  
  int inumber = 20; //integer (no decimals)
  float fnumber = 20.2780932; //integer (7 decimals)
  double dnumber = 20.218584059305823; //integer (decimals up to 15) better overall
  char letter = 'A'; //a single letter (single quotes)
  string words = "A string of words"; //multiple letters (words) (double quotes)
  bool torf = true; //true or false (1,0)
  cout << "In " << inumber << " years, something will happen...\n"; //used in cout statements
  cout << fnumber << " is a precise number\n";
  cout << dnumber << " is an even more precise number\n";                     
  cout << letter << " is the first letter in the alphabet\n";      
  cout << words << "\n";                                                
  cout << torf << "\n\n";                                             
  
  inumber = 21; //updating value                                                 
  cout << "Actually " << inumber << " years...\n"; //value is updated
  int num1 = 2;
  double num2 = 2.2;
  double sum = num1 + num2; //combining variables (int + double is fine)
  cout << "2 + 2.2 = " << sum << "\n"; //sum
  int num3 = 5, num4 = 90, num5 = -65; //multiple variables of the same type
  cout << "5 + 90 * -65 = " << num3 + num4 * num5 << "\n\n"; //sum variable not necessary
  const double num6 = 66.66666; //number cannot be changed ever (use of variables that wont change)
  //num6 = 77.77777 //if this was code, there would be an error
  
  char inp;
  cout << "Type a letter: "; //no space = on same line
  cin >> inp; //cin = input = inp
  cout << "You typed: " << inp << "\n"; //print input (cin)
  //simple calculator
  double calc1, calc2;  
  cout << "Type a number: ";
  cin >> calc1;
  cout << "Type another number: ";
  cin >> calc2;
  double calcsum = calc1 + calc2;
  cout << "The first number plus the second is: " << calcsum << "\n";

  double num7 = 56e93; //scientific notation can be used
  cout << "Using scientific notation: " << num7 << "\n";
  
  /* *doesnt work*
  char letter1 = 146, letter2 = 225, letter3 = 245; //ascii values can be used for chars 
  cout << letter1 << "\n" << letter2 << "\n" << letter3 << "\n";
  */
  
  double num8 = 56.6734;
  double sum1 = num8 + num8 - num8 * num8 / num8; //operators (% can only be used on integers) (use fmod() from <cmath> for % on doubles/floats)
  cout << sum1 << "\n" << ++sum1 << "\n" << --sum1 << "\n"; //++ adds 1 | -- takes 1
  int num9 = 10;
  num9 += 10; // num9 = num9 + 10 //add
  num9 -= 10; // num9 = num9 - 10 //subtract
  num9 *= 10; // num9 = num9 * 10 //multiplay
  num9 /= 10; // num9 = num9 / 10 //divide
  num9 %= 10; // num9 = num9 % 10 //division remainder
  num9 &= 10; // num9 = num9 & 10 //...
  num9 |= 10; // num9 = num9 | 10 //...
  num9 ^= 10; // num9 = num9 ^ 10 //power
  num9 >>= 10;// num9 = num9 >> 10 //...
  num9 <<= 10;// num9 = num9 << 10 //...

  //these return either true or false (1,0)
  int num10 = 10, num11 = 15;
  cout << (num10 == num11); //equal to
  cout << (num10 != num11); //not equal
  cout << (num10 > num11); //greater than
  cout << (num10 < num11); //less than
  cout << (num10 >= num11); //greater than or equal to
  cout << (num10 <= num11); //less than or equal to
  cout << (num10 < num11 && num10 < num11); //true if both are true
  cout << (num10 < num11 || num10 > num11); //true if one is true
  cout << !(num10 < num11 && num10 < num11) << "\n\n"; //false if both are true

  string words1 = "Name:";
  string words2 = "C++";
  string fullwords = words1 + " " + words2; //" " adds space between
  cout << fullwords << "\n";
  string fullwordsapp = words1.append(words2); //can be appended if necessary
  cout << fullwordsapp << "\n";
  string words3 = "70";
  string words4 = "50";
  string fullwordsnumb = words3 + words4; //string numbers can be added with + (7050 not 120)
  int num12 = 76;
  //string fullwordsint = words4 + num12 //this would cause error (no int and string together)
  string words5 = "C++ is very cool!";
  cout << words5 << "\n";
  cout << "The length of the above statement is: " << words5.length() << " characters!" << "\n"; //.length() or .size() ...used to get length of string 
  string words6 = "Yes";
  cout << words6[2] << words6[1] << words6[0] << "\n"; //get character from string (0 = Y | 1 = e | 2 = s)
  words6[2] = 'p'; //swaps third character with 'p'
  cout << words6 << "\n";
  string words7;
  cout << "Type 'Hello World!': "; //dont have \n above getline(); misreads
  getline(cin, words7); //getline reads line, first parameter 'cin' gets input, words7 is the input
  cout << "Output: " << words7 << "\n\n"; //is then printed as a full string with spaces

  //many math things with <cmath> header...
  /*
  cout << 10 > 9; //outputs 1 because true
  cout << 10 == 9 << "\n\n"; //outputs 0 because false //doesnt work...
  //can be used with int's, double's, etc...
  */

  int num13 = 16;
  int num14 = 14;
  int num15 = 20;
  if(num13 > num14){ //if num12 bigger than num13 execute between {}
    cout << num13 << " is larger than " << num14 << "\n";
  }
  if(num13 < num14){ //same as above
    cout << num14 << " is larger than " << num13 << "\n";
  } 
  else if(num14 < num15){ //if if statement is false, add new condition that is true...
    cout << num14 << " is smaller than " << num15 << "\n";
  }
  else{ //if if statement is false, this code will execute
    cout << num14 << " is not larger than " << num13 << "\n"; //wont execute due to else if
  }
  string words8 = (num13 > num14) ? num13 + " is larger than " + num14 : num14 + " is not larger than " + num13; //compact way to write simple if else statements(var = condition ? true : false) //only prints 'han'?? 
  cout << words8 << "\n";
  cout << "Type a number(1-4)\n1: Yes\n2: No\n3: Maybe\n4: Not\n: ";
  int(letter2);
  cin >> letter2; //getting input that = letter2 var
  switch(letter2){ //executes code that = case name
    case 1: //if letter2 = 1, it would output "Yes"... etc etc.
      cout << "Yes" << "\n";
      break; //breaks code in current 'block' (switch); speeds up execution
    case 2:
      cout << "No" << "\n";
      break;
    case 3:
      cout << "Maybe" << "\n";
      break;
    case 4:
      cout << "Not" << "\n";
      break;
    default: //if no case match, this runs
      cout << "Incorrect number typed!" << "\n";
  }
  int num16 = 0;
  while(num16 <= 20){ //repeats while condition is true
    cout << num16 << "!\n"; //continues until num16 >= 20
    num16++; //adds one each loop
  }
  num16 = 0;
  do{ //very similar to while, but always executes at least once, then waits until statement true
    cout << num16 << "!\n";
    num16 = num16 + 4;
  }
  while(num16 <= 20); //connected to 'do'
  for(num16 = 0; num16 <= 16; num16 = num16 + 2){
    cout << num16 << "!\n";
  }
  //break can be used to stop a loop early //continue breaks one cycle of the loop and then continues again (skipping over a single number)
  cout << "\n";

  string words9[3] = {"Hello", "Yes", "No"}; //assign 3 values to one variable (number in [])
  cout << words9[0] << " " << words9[2] << "\n"; //access and print values
  words9[0] = "Bye"; //change [0] value
  cout << words9[0] << "\n"; //prints new value
  for(int num17 = 0; num17 <= 2; num17++){ //for loop 
    cout << num17 << " : " << words9[num17] << "\n"; //prints number and corresponding index value (words9[num17] = words9[0,1,2,3])
  }
  cout << "\n";

  string words10 = "Cheese";
  string &words11 = words10; //reference to words10; so it will also display "Cheese" when called
  cout << words10 << " " << words11 << "\n"; //"Cheese Cheese"
  cout << &words10 << "\n"; //outputs memory address //ability to manipulate data? IMPORTANT**
  string* words12 = &words10; //pointer (*) stores memory address as value
  cout << words12 << "\n"; //outputs memory address
  cout << *words12 << "\n"; //is a pointer but outputs original value
  *words12 = "NotCheese"; //change value of pointer
  cout << *words12 << "\n"; //different value
  cout << words12; //memory address stays the same
  cout << "\n\n";

  func1(2, "First"); //calls function from definition from declaration (top of page)
  func1(3, "Second");
  func1(4, "Third"); //parameters (x) variables within functions //need to be same number and order as declared
  func1(); //if no parameter = default value (0) or "Unknown" ^^ is declared in declaration at top
  cout << func2(10, 20); //prints return value with cout
  int num21 = func2(1230034520, 238974628); //return value can be placed in a variable too
  cout << num21 << "\n";
  int num23 = func3(10, 60);
  double num24 = func3(22.2, 56.83); //same name different parameters
  cout << "Output: " << num23 << "\n"; 
  cout << "Output: " << num24 << "\n";
  cout << "\n";
   
  class1 object1; //create object
  class1 object2; //there can be multiple in one class
  object1.num25 = 27; //define attributes on object
  object1.words13 = "This says 27";
  object2.num25 = 87; //different values on different object
  object2.words13 = "This says 87";
  cout << object1.num25 << " " << object1.words13 << "\n"; //print values
  cout << object2.num25 << " " << object2.words13 << "\n";
  object1.func4("object 1"); //the object chosen changes the parameter
  object2.func4("object 2");
  object1.func5("[1]"); //func5 from outside class, still calls object!
  object2.func5("[2]");
  class2 object3(12, "12 is the value of object3"); //inside the () is the attribute values
  class2 object4(34, "34 is the value of object4");
  cout << object3.num26 << " " << object3.words16 << "\n"; //each attribute for each object being printed
  cout << object4.num26 << " " << object4.words16 << "\n";
  class3 object5;
  //object5.num28 = 59; //this code does not work because num28 is private...
  object5.func6(59); //much better way, more secure and safe //this sets the value
  cout << object5.func7() << " is a private number!\n"; //and then prints
  class5 object6;
  object6.func9(1000, "Inherited string"); //putting values on inherited attributes
  cout << object6.func10() << " " << object6.func11() << "\n";
  cout << "\n"

  //try throw and catch are used for error messages: try allows testing, throw excepts an error and catch executes code if an error occurs in try...
  //the <fstream> class can be used for many things file-wise...**

  return 0; //ends function
} //closes function

void func1(int num18, string words13){ //void = no return 0 needed //parameter for function = num18 //can be multiple parameters
  cout << "\nThis is a successful function!\n"; //function definition can be split from declaration!
  cout << num18 << " balls\n"; //num18 is swapped for arguments in called functions above
  cout << words13 << " Function\n"; //same as above
}

int func2(int num19, int num20){ //references (&x, &y...) can be used in parameters, giving ease to swap them around if needed
  cout << "\nType a number: " << num19;
  cout << "\nType a number: " << num20 << "\n";
  return num19 + num20; //outputs when printed
}

int func3(int num21, int num22){
  return num21 + num22;
} //both functions have same name, but different parameter types | overloaded

double func3(double num21, double num22){
  return num21 + num22;
}
