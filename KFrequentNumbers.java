import java.util.*;

public class KFrequentNumbers {
    // Static variable to store the input array of N numbers
    static int[] nums;

    // Static method to identify K numbers with the highest occurrences
    public static int[] topKFrequent(int k) {
        // Create array of lists to be used as buckets
        // We need to sort by frequency 
        List<Integer>[] freqSorted = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        // Find how often each number occurred
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Iterate through frequency map and add to freqSorted key at position frequency
        for (int key : frequencyMap.keySet()) {
            if (freqSorted[frequencyMap.get(key)] == null)
                freqSorted[frequencyMap.get(key)] = new ArrayList<>();
            freqSorted[frequencyMap.get(key)].add(key);
        }

        // Iterate again starting from right to left 
        for (int i = freqSorted.length - 1; i >= 0 && res.size() < k; i--) {
            if (freqSorted[i] != null) {
                // Sort the list at this frequency in descending order
                Collections.sort(freqSorted[i], Collections.reverseOrder());
                res.addAll(freqSorted[i]);
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    // Driver's code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for the array size
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Initialize the static array based on user input
        nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // User input for K
        System.out.print("Enter the value of K: ");
        int k = scanner.nextInt();

        // Function call to get the top K frequent numbers
        int[] result = KFrequentNumbers.topKFrequent(k);

        // Print the result
        System.out.print(k + " number/s with most occurrences are: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println(); // Print a new line after output

        scanner.close(); // Close the scanner
    }
}
