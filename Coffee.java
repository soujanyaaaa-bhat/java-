import java.util.LinkedList;

public class Coffee {
    public static void main(String[] args)
        throws InterruptedException
    {
        // Object of a class that has both barista() and customer() methods
        final CoffeeShop coffeeShop = new CoffeeShop();

        // Create barista thread (Producer)
        Thread baristaThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    coffeeShop.barista();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create customer thread (Consumer)
        Thread customerThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    coffeeShop.customer();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        baristaThread.start();
        customerThread.start();

        // Wait for both threads to finish
        baristaThread.join();
        customerThread.join();
    }

    // This class has a counter, barista (prepares coffee) and customer (takes coffee)
    public static class CoffeeShop {

        // Create a list (counter) shared by barista and customer
        // Size of list (counter) is 3 (maximum capacity)
        LinkedList<Integer> counter = new LinkedList<>();
        int capacity = 3;

        // Function called by barista (Producer thread)
        public void barista() throws InterruptedException
        {
            int coffeeId = 0;
            int maxCoffees = 5;  // Stop after 5 coffees
            while (coffeeId < maxCoffees) {
                synchronized (this)
                {
                    // Barista waits if the counter is full
                    while (counter.size() == capacity)
                        wait();

                    System.out.println("Barista prepared coffee - Coffee ID: " + coffeeId);

                    // Insert the coffee in the counter (list)
                    counter.add(coffeeId++);

                    // Notify the customer that a coffee is available
                    notify();

                    // Simulate some delay for preparation
                    Thread.sleep(1000);
                }
            }
            System.out.println("Barista has finished preparing coffee after making " + maxCoffees + " coffees.");
        }

        // Function called by customer (Consumer thread)
        public void customer() throws InterruptedException
        {
            while (true) {
                synchronized (this)
                {
                    // Customer waits if the counter is empty
                    while (counter.size() == 0)
                        wait();

                    // Retrieve the coffee from the counter (remove the first coffee)
                    int coffeeId = counter.removeFirst();

                    System.out.println("Customer took coffee - Coffee ID: " + coffeeId);

                    // Notify the barista that there is space available for more coffee
                    notify();

                    // Simulate some delay for taking coffee
                    Thread.sleep(1000);
                }
                // Stop if no more coffee is produced
                if (counter.isEmpty() && Thread.activeCount() == 2) {
                    break; // Exit the loop once all coffees have been consumed
                }
            }
            System.out.println("Customer has finished taking coffee.");
        }
    }
}
