import java.util.Scanner;

public class AlphabetWarGame {
    // Strength values for left and right side letters
    private int[] leftStrengths;
    private int[] rightStrengths;

    // Default constructor with predefined strengths
    public AlphabetWarGame() {
        this.leftStrengths = new int[]{0, 1, 2, 3, 4}; // s, b, p, w
        this.rightStrengths = new int[]{0, 1, 2, 3, 4}; // z, d, q, m
    }

    // Constructor allowing custom strengths
    public AlphabetWarGame(int[] leftStrengths, int[] rightStrengths) {
        this.leftStrengths = leftStrengths;
        this.rightStrengths = rightStrengths;
    }

    // Method to determine the winner using a single string input
    public String alphabetWar(String fight) {
        return alphabetWar(fight, leftStrengths, rightStrengths);
    }

    // Overloaded method to determine the winner using separate left and right words
    public String alphabetWar(String left, String right) {
        return alphabetWar(left + right, leftStrengths, rightStrengths);
    }

    // Private method to calculate the result based on the given fight string
    private String alphabetWar(String fight, int[] leftStrengths, int[] rightStrengths) {
        int sum = 0;

        for (int i = 0; i < fight.length(); i++) {
            char letter = fight.charAt(i);
            switch (letter) {
                case 'w':
                    sum += leftStrengths[4]; // Strength of 'w' = 4
                    break;
                case 'p':
                    sum += leftStrengths[3]; // Strength of 'p' = 3
                    break;
                case 'b':
                    sum += leftStrengths[2]; // Strength of 'b' = 2
                    break;
                case 's':
                    sum += leftStrengths[1]; // Strength of 's' = 1
                    break;
                case 'm':
                    sum -= rightStrengths[4]; // Strength of 'm' = 4
                    break;
                case 'q':
                    sum -= rightStrengths[3]; // Strength of 'q' = 3
                    break;
                case 'd':
                    sum -= rightStrengths[2]; // Strength of 'd' = 2
                    break;
                case 'z':
                    sum -= rightStrengths[1]; // Strength of 'z' = 1
                    break;
                default:
                    break;
            }
        }

        return determineResult(sum);
    }

    // Helper method to determine the result based on the sum
    private String determineResult(int sum) {
        if (sum == 0) {
            return "Let's fight again!";
        } else if (sum < 0) {
            return "Right side wins!";
        } else {
            return "Left side wins!";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlphabetWarGame game = new AlphabetWarGame(); // Default constructor

        System.out.println("Enter the letters for the battle: ");
        String userInput = scanner.nextLine();

        String result = game.alphabetWar(userInput);
        System.out.println(result);

        // Example of using the constructor with custom strengths
        int[] customLeftStrengths = {0, 2, 3, 4, 5}; // Custom strengths for left side
        int[] customRightStrengths = {0, 2, 3, 4, 5}; // Custom strengths for right side
        AlphabetWarGame customGame = new AlphabetWarGame(customLeftStrengths, customRightStrengths);

        // Testing method overloading with separate words
        System.out.println("Testing custom strengths with left and right inputs:");
        String resultCustom = customGame.alphabetWar("wwww", "zz");
        System.out.println(resultCustom); // Output based on custom strengths

        scanner.close();
    }
}