// Interface 'WaterConservationSystem'

import java.util.Scanner;

interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

// Abstract class 'RainySeasonConservation'
abstract class RainySeasonConservation implements WaterConservationSystem {
    // Abstract method to calculate trapped water
    public abstract int calculateTrappedWater(int[] blockHeights);
}

// Concrete class 'CityBlockConservation' extends the abstract class
class CityBlockConservation extends RainySeasonConservation {
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        // Logic to calculate trapped water (adapted from your original logic)
        int maxHeight = 0;
        int previousHeight = 0;
        int previousHeightIndex = 0;
        int totalWater = 0;
        int tempWater = 0;

        // Find the first peak (all water before will not be collected)
        while (previousHeightIndex < blockHeights.length && blockHeights[previousHeightIndex] > maxHeight) {
            maxHeight = blockHeights[previousHeightIndex];
            previousHeightIndex++;
            if (previousHeightIndex == blockHeights.length) {
                // In case of stairs (no water collected)
                return totalWater;
            } else {
                previousHeight = blockHeights[previousHeightIndex];
            }
        }

        for (int i = previousHeightIndex; i < blockHeights.length; i++) {
            if (blockHeights[i] >= maxHeight) {
                // Collect all temporary water
                totalWater += tempWater;
                tempWater = 0;
                maxHeight = blockHeights[i]; // New max height
            } else {
                // Calculate temporary water trapped
                tempWater += maxHeight - blockHeights[i];
                if (blockHeights[i] > previousHeight) {
                    // Collect some water when height increases
                    int collWater = (i - previousHeightIndex) * (blockHeights[i] - previousHeight);
                    totalWater += collWater;
                    tempWater -= collWater;
                }
            }

            // Update previousHeight and previousHeightIndex if heights change
            if (blockHeights[i] != previousHeight) {
                previousHeight = blockHeights[i];
                previousHeightIndex = i;
            }
        }

        return totalWater;
    }
}

// Main class to test the implementation
public class WaterConservation
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for the number of blocks
        System.out.print("Enter the number of blocks in the cityscape: ");
        int n = scanner.nextInt();

        // Array to hold the block heights
        int[] blockHeights = new int[n];

        // Prompt user to input the heights of the blocks
        System.out.println("Enter the heights of the blocks:");
        for (int i = 0; i < n; i++) {
            System.out.print("Height of block " + (i + 1) + ": ");
            blockHeights[i] = scanner.nextInt();
        }

        // Create an instance of CityBlockConservation
        WaterConservationSystem system = new CityBlockConservation();

        // Calculate and display the volume of trapped water
        int trappedWater = system.calculateTrappedWater(blockHeights);
        System.out.println("\nTotal water trapped between blocks: " + trappedWater + " units.");

        // Close the scanner
        scanner.close();
    }
}

    
