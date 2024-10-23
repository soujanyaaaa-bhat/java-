//importing the scanner class from the java.util package which is allowing me to
//read input from the user 
import java.util.Scanner;
//creating a public class called luhn formula as credit card validation is based on hans luhn's algorithm for IBM
public class LuhnFormula {
//creating a private integer variable to store the credit card number entered by user, it cannot be accessed directly outside of class
    private int creditCardNum;
// creating a constructor that takes parameter and assigns to creditCardnum and this keyword will help us differentiate between class field and parameter
    public LuhnFormula(int creditCardNum) {
        this.creditCardNum = creditCardNum;
    }
//main method 
//asking input from user and reading it and initialising an instance created with credit card number 
    public static void main(String[] args) {
       try (Scanner input = new Scanner(System.in)) {
        System.out.print("Enter a credit card number: ");
        int creditCardNum = input.nextInt();

        LuhnFormula validator = new LuhnFormula(creditCardNum);
        validator.validateCreditCard(); }
    }

    // Method to validate the credit card number
    //size of number is determined and the credit card number must have between 8 and 9 digits, if not it prints message and exits
    public void validateCreditCard() {
        int size = getSize(creditCardNum);

        if (size < 8 || size > 9) {
            System.out.println("Invalid credit card number.");
            return;
        }

        // Step a: Remove the last digit
        int lastDigit = creditCardNum % 10;
        int remainingNum = creditCardNum / 10;

        // Step b: Reverse the remaining digits
        int reversedNum = reverse(remainingNum);

        // Step c: Double the digits in odd positions and sum all digits
        int sum = sumOfProcessedDigits(reversedNum);

        // Step d: Subtract last digit from 10
        int calculatedDigit = (10 - (sum % 10)) % 10;

        // Step e: Compare calculated result with the last digit
        if (calculatedDigit == lastDigit) {
            System.out.println(creditCardNum + " is valid.");
        } else {
            System.out.println(creditCardNum + " is INVALID.");
        }
    }

    // Method to get the number of digits in the credit card number
    //converts number to a string and returns length
    private int getSize(int number) {
        return String.valueOf(number).length();
    }

    // Method to reverse the digits of a number
    //using a loop to construct the reversed number 
    private int reverse(int number) {
        int reversed = 0;
        while (number > 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return reversed;
    }

    // Method to process digits: double odd-position digits and sum them all
    //if result greater than 9 in odd position, it subtracts 9 from the digit
    private int sumOfProcessedDigits(int number) {
        int sum = 0;
        boolean oddPosition = true;

        while (number > 0) {
            int digit = number % 10;

            // Double the digit if it's in an odd position
            if (oddPosition) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit - 9;  // Sum the digits if it's double-digit
                }
            }

            sum += digit;
            oddPosition = !oddPosition;
            number /= 10;
        }

        return sum;
        
    }
}