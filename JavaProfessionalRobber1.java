import java.util.Scanner;

// this is an abstract class for the robber. it doesn't have the full implementation yet.
abstract class Robber {
    // this method is supposed to be implemented by a class that extends robber.
    abstract void RobbingClass();  
}

// this interface is for machine learning-related methods. doesn't affect robbery logic here.
interface TestInterface {
    // this method is a default one, so it has a basic implementation that prints a message
    default void MachineLearning() {
        System.out.println("i love machine learning");
    }

    // this is an abstract class that extends robber, and has methods for different types of houses.
    abstract class JavaProfessionalRobber extends Robber {
        // these are abstract methods that will be implemented later for row houses, round houses, etc.
        abstract void RowHouses(int[] money);  
        abstract void RoundHouses(int[] money);  
        abstract void SquareHouse(int[] money);  
        abstract void MultiHouseBuilding(int[][] money);  
    }
}

// this class extends JavaProfessionalRobber and gives actual code for the robbery logic.
class JavaProfessionalRobber1 extends TestInterface.JavaProfessionalRobber {
    public void RobbingClass() {
        System.out.println("MSC AI & ML");
    }
      // implementing RowHouses method 
      public void RowHouses(int[] money) {
        System.out.println("Row houses method");
    }

    // implementing RoundHouses method 
    public void RoundHouses(int[] money) {
        System.out.println("Round houses method");
    }

    // implementing SquareHouse method
    public void SquareHouse(int[] money) {
        System.out.println("Square house method");
    }

    // implementing MultiHouseBuilding method 
    public void MultiHouseBuilding(int[][] money) {
        System.out.println("Multi house building method");
    }
    // this is the main robbery method, using dynamic programming to find max money.
    public static int maxRobbery(int[] money) {
        // get the number of houses (money array length)
        int n = money.length;
        
        // if no houses, no money can be robbed.
        if (n == 0) {
            return 0;
        }
        // if there's only one house, rob it.
        else if (n == 1) {
            return money[0];
        }
        // if there are two houses, choose the one with the higher money.
        else if (n == 2) {
            return Math.max(money[0], money[1]);
        }

        // create an array to store the max money robbed up to each house.
        int[] dp = new int[n];
        
        // first house is robbed fully
        dp[0] = money[0];
        // second house: pick the bigger value between the first and second house.
        dp[1] = Math.max(money[0], money[1]);

        // go through the rest of the houses and decide whether to rob it or not
        for (int i = 2; i < n; i++) {
            // rob the current house + previous non-adjacent house, or skip it.
            dp[i] = Math.max(dp[i - 1], money[i] + dp[i - 2]);
        }

        // return the max money robbed from all houses.
        return dp[n - 1];
    }

    // this method is for handling multiple houses in a building, using a 2D array.
    public static int maxRobberyMultiHouses(int[][] multiHouses) {
        // this variable keeps track of total money robbed.
        int totalRobbed = 0;
        
        // loop through each building (each row in the 2D array).
        for (int[] houses : multiHouses) {
            // add the money robbed from each building (house array) to the total.
            totalRobbed += maxRobbery(houses);
        }
        
        // return the total amount of money robbed from all buildings.
        return totalRobbed;
    }

    // this method helps us get user input for a single building (one array of houses).
    public static int[] getHouseMoneyInput(Scanner scanner) {
        // ask how many houses are in the building
        System.out.print("enter number of houses: ");
        int n = scanner.nextInt();
        
        // create an array to store the money in each house
        int[] money = new int[n];
        
        // ask the user to input the money in each house
        System.out.println("enter money in each house:");
        for (int i = 0; i < n; i++) {
            money[i] = scanner.nextInt();
        }
        
        // return the array of money
        return money;
    }

    // this method helps get input for multiple buildings (2D array).
    public static int[][] getMultiHouseMoneyInput(Scanner scanner) {
        // ask how many buildings (rows in the 2D array)
        System.out.print("enter number of buildings: ");
        int b = scanner.nextInt();
        
        // create a jagged array for multiple buildings
        int[][] multiHouses = new int[b][];
        
        // get input for each building (each row of the jagged array)
        for (int i = 0; i < b; i++) {
            System.out.println("enter money for building " + (i + 1) + ":");
            multiHouses[i] = getHouseMoneyInput(scanner);
        }
        
        // return the 2D array
        return multiHouses;
    }

    public static void main(String[] args) {
        // create a scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // ask for row houses and take input
        System.out.println("row houses:");
        int[] rowHouses = getHouseMoneyInput(scanner);

        // ask for round houses and take input
        System.out.println("\nround houses:");
        int[] roundHouses = getHouseMoneyInput(scanner);

        // ask for square house and take input
        System.out.println("\nsquare house:");
        int[] squareHouse = getHouseMoneyInput(scanner);

        // ask for multi-house building and take input
        System.out.println("\nmulti-house building:");
        int[][] multiHouseBuilding = getMultiHouseMoneyInput(scanner);

        // calculate and display the max money robbed from each type of house/building
        System.out.println("\nmaximum money robbed from row houses: " + maxRobbery(rowHouses));
        System.out.println("maximum money robbed from round houses: " + maxRobbery(roundHouses));
        System.out.println("maximum money robbed from square house: " + maxRobbery(squareHouse));
        System.out.println("maximum money robbed from multi-house building: " + maxRobberyMultiHouses(multiHouseBuilding));

        // close the scanner
        scanner.close();
    }
}
