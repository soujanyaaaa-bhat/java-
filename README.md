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

  ## for the first program we are using interface to help display balance and interest rate for three different banks. second program involves
## find the first "peak" by looping through the list until the tower height stops increasing. water before this is not collected 
## for all towers:
## if the height of the subsequent tower decreases or stays the same, add water to a "potential collection" bucket, equal to the difference between the tower height and the previous max tower height.
## If the height of the subsequent tower increases, we collect water from the previous bucket (subtract from the "potential collection" bucket and add to the collected bucket) and also add water to the potential bucket equal to the difference between the tower height and the previous max tower height.
## If we find a new max tower, then all the "potential water" is moved into the collected bucket and this becomes the new max tower height.



Coin Change & Coffee Shop Simulation

This repository contains two distinct problems solved using multithreading concepts in Java:

Part A: Coin Change Problem with Multithreading

Description
The Coin Change Problem involves determining the number of ways to make a specific sum using a set of coin denominations. The solution is implemented recursively, and multithreading is used to divide the work into multiple tasks, improving performance for large inputs.

Recursive Solution: The countWays() method recursively computes the number of ways to make a given sum using the available coins.
Multithreading: The calculateCombinations() method splits the work into multiple threads using Java's ExecutorService. Each thread calculates the number of ways for a specific chunk of coins, which are then combined to get the final result.
Example
Input:
Coins: {1, 2, 3}
Sum: 4
Output:
Number of ways to make sum 4: 4
Input:
Coins: {2, 5, 3, 6}
Sum: 10
Output:
Number of ways to make sum 10: 5
How to Run:
Clone this repository.
Navigate to the CoinChange class in your preferred Java IDE or command line.
Run the main() method to see the results.
Part B: Coffee Shop Simulation with Multithreading

Description
This program simulates a coffee shop where two threads interact:

A barista (producer) prepares coffee cups and adds them to a shared counter.
A customer (consumer) picks up the coffee from the counter.
A synchronization mechanism (using wait() and notify()) ensures that the barista and customer do not interact incorrectly, such as when the counter is full or empty.
Barista (Producer): The barista prepares a maximum of 5 cups of coffee, adding them to a counter with a fixed capacity of 3.
Customer (Consumer): The customer picks up coffee from the counter and waits if no coffee is available.
