program 1: we are asked to validate a credit card number based on various conditions. this is originally based on luhn's algorithm.
the program first asks user for input of numbers and checks for conditions like if it is lesser than 8 no's or greater than 9 no's it is not a valid credit card number if yes it goes to the switch case to check for 
the mod of 10, the remainder and the division to remove the last digit, the number is then reversed, digits in odd positions are doubled and summated, and then subtracting last digit from 10.
if last digit equals the calculated digit then it is valid credit card number.
program 2: for alphabet war game, predefined strengths and user defined strengths are given. a switch case with strengths of alphabets is given. this is a fun game that exhibits use of constructor, overloading methods.
program 3: a hash map strategy is used to find the k frequent numbers. this uses a static method and static variable. a frequency map is created where frequently sorted elements are added. this allows us to sort the list in a descending order
program 4: for a share trade a 3dimensional array is done to check for buying and selling of stocks for profit without doing it twice.
program 5: using inheritance, super keyword and method overriding an employee payroll system is created where inputs are given and their hourly and monthly salary is calculated



lab 4
# java professional robber program
this program helps calculate the maximum money that can be robbed from different types of houses. it uses dynamic programming to find the best solution for the robbery problem.
## features
- **row houses**: robbery from row houses.
- **round houses**: robbery from round houses.
- **square house**: robbery from square houses.
- **multi-house building**: robbery from multiple buildings, each with its own set of houses.
## program structure
- **robber class (abstract)**: this is an abstract class with an unimplemented method `robbingclass()`.
- **testinterface interface**: this interface has some machine learning methods and an abstract class `javaprofessionalrobber`, which is extended by `javaprofessionalrobber1`.
- **javaprofessionalrobber1 class**: this class implements the robbery logic for different house types and calculates the maximum money that can be robbed using dynamic programming.
