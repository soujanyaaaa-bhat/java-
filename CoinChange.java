import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CoinChange {

    // This is a recursive function that will find the number of ways to make the target sum using coins.
    // It starts from a particular coin and either includes or excludes it in the combination.
    public static int countWays(int[] coins, int sum, int index, int currentSum) {
        // If the current sum is equal to the target sum, we've found a valid combination.
        // So we return 1 to count it.
        if (currentSum == sum) {
            return 1; // We found a valid combination that makes up the sum.
        }

        // If the current sum exceeds the target sum, or we've exhausted all coins, this path is not valid.
        // We return 0 because no valid combination exists in this path.
        if (currentSum > sum || index == coins.length) {
            return 0; // Invalid path
        }

        // Try to include the coin at index in the combination
        // If we include the coin, we call the function again with the same coin (because we can use it multiple times)
        int includeCoin = countWays(coins, sum, index, currentSum + coins[index]);

        // Try to exclude the coin at index and move to the next coin in the array
        int excludeCoin = countWays(coins, sum, index + 1, currentSum);

        // The total ways are the sum of the two possibilities: including or excluding the coin.
        return includeCoin + excludeCoin;
    }

    // This method will divide the work into multiple threads for parallel computation
    // This will speed up the calculation by using more than one thread.
    public static int calculateCombinations(int[] coins, int sum, int numThreads) throws InterruptedException, ExecutionException {
        // We use an ExecutorService to manage the threads. ExecutorService helps us to easily manage and handle threads.
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> results = new ArrayList<>(); // A list to store the results from each thread.

        // We want to divide the coins into equal chunks for each thread.
        // So we calculate how many coins each thread should handle.
        int chunkSize = coins.length / numThreads; // Dividing coins equally across threads

        // Now we divide the work into threads
        // Each thread will compute the number of ways to make the sum using its assigned chunk of coins.
        for (int i = 0; i < numThreads; i++) {
            // We calculate the starting index and the ending index for each thread's chunk.
            int startIndex = i * chunkSize; // The starting coin for this thread
            int endIndex = (i == numThreads - 1) ? coins.length : (i + 1) * chunkSize; // The ending coin for this thread

            final int start = startIndex;
            final int end = endIndex;

            // Submit the task to the executor. Each task will count the ways for a specific chunk of coins.
            // Each thread will start counting from the coin at index 'start' to the coin at index 'end'.
            results.add(executor.submit(() -> {
                int localCount = 0; // Local count to hold the result for this thread's chunk
                for (int j = start; j < end; j++) {
                    // For each coin in the chunk, we call countWays to calculate the number of combinations
                    // that form the sum using this coin and the rest of the coins.
                    localCount += countWays(coins, sum, j, 0); // Start recursion from coin[j] with currentSum = 0
                }
                return localCount; // Return the result for this thread's chunk
            }));
        }

        // Now we need to gather all the results from each thread and combine them.
        int totalWays = 0;
        for (Future<Integer> result : results) {
            totalWays += result.get(); // Add up the results from all threads
        }

        // Shutdown the executor to free up resources
        executor.shutdown();

        // Finally, return the total number of ways to form the sum using all coins.
        return totalWays;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3}; 
        int sum = 4; 
        int numThreads = 2; 

        try {
            int result = calculateCombinations(coins, sum, numThreads);
            System.out.println("Number of ways to make sum " + sum + ": " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); 
        }

        
        // Example 2: Coins = {2, 5, 3, 6}, Sum = 10
        int[] coins2 = {2, 5, 3, 6}; 
        int sum2 = 10; 
        try {
            
            int result2 = calculateCombinations(coins2, sum2, numThreads);
            System.out.println("Number of ways to make sum " + sum2 + ": " + result2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); 
        }
    }
}

