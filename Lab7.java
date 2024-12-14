import java.util.*;
class Customer {
    private int customerId;
    private String name;
    private int loyaltyPoints;

    //constructor to instantiate objects and get values
    public Customer(int customerId, String name, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    @Override
    //to string() is used to print output in readable format allowing for 
    //any data structure to print as its originating from tostring
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Loyalty Points: " + loyaltyPoints;
    }
}
//product class
class Product {
    private int productId;
    private String productName;
    private double price;

    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Price: $" + price;
    }
}
//order class
class Order {
    private int orderId;
    private Customer customer;
    private Product product;
    private Date orderDate;
    private Date deliveryDate;

    public Order(int orderId, Customer customer, Product product, Date orderDate, Date deliveryDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customer.getName() +
                ", Product: " + product.getProductName() + ", Delivery Date: " + deliveryDate;
    }
}

//using comparator to sort products based on price
class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

//comparator to sort customers by loyalty points
class CustomerLoyaltyComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints());  // Sort descending
    }
}

//comparator to sort orders by delivery date
class OrderDeliveryDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
    }
}

public class Lab7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //linked list better for insertion, deletion
        LinkedList<Customer> customers = new LinkedList<>();
        LinkedList<Product> products = new LinkedList<>();
        LinkedList<Order> orders = new LinkedList<>();
        
        
        System.out.print("enter number of customers: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine();  
        for (int i = 0; i < numCustomers; i++) {
            System.out.print("enter customer " + (i + 1) + " ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();  
            System.out.print("enter customer " + (i + 1) + " Name: ");
            String name = scanner.nextLine();
            System.out.print("enter customer " + (i + 1) + " Loyalty Points: ");
            int loyaltyPoints = scanner.nextInt();
            scanner.nextLine();  
            
            Customer customer = new Customer(customerId, name, loyaltyPoints);
            customers.add(customer);
        }

        
        System.out.print("enter number of products: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine();  
        for (int i = 0; i < numProducts; i++) {
            System.out.print("enter product " + (i + 1) + " ID: ");
            int productId = scanner.nextInt();
            scanner.nextLine();  
            System.out.print("enter product " + (i + 1) + " Name: ");
            String productName = scanner.nextLine();
            System.out.print("enter product " + (i + 1) + " Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();  
            
            Product product = new Product(productId, productName, price);
            products.add(product);
        }

        
        System.out.print("enter number of orders: ");
        int numOrders = scanner.nextInt();
        scanner.nextLine();  
        for (int i = 0; i < numOrders; i++) {
            System.out.print("Enter Order " + (i + 1) + " ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();  
            System.out.print("Enter Customer ID for Order " + (i + 1) + ": ");
            int customerId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter Product ID for Order " + (i + 1) + ": ");
            int productId = scanner.nextInt();
            scanner.nextLine();  

            //finding customer and product by ID
            Customer orderCustomer = null;
            Product orderProduct = null;
            for (Customer customer : customers) {
                if (customer.getCustomerId() == customerId) {
                    orderCustomer = customer;
                    break;
                }
            }
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    orderProduct = product;
                    break;
                }
            }

        System.out.println("Customers:");
        customers.forEach(System.out::println);
        System.out.println("Products:");
        products.forEach(System.out::println);
        System.out.println("Orders:");
        orders.forEach(System.out::println);

        //hashset for fast retrieval
        HashSet<Integer> customerIds = new HashSet<>();
        HashSet<Integer> productIds = new HashSet<>();
        
        for (Customer customer : customers) {
            customerIds.add(customer.getCustomerId());
        }
        for (Product product : products) {
            productIds.add(product.getProductId());
        }

        System.out.println("Customer IDs: " + customerIds);
        System.out.println("Product IDs: " + productIds);

        HashMap<Customer, HashSet<Product>> customerProducts = new HashMap<>();
        for (Customer customer : customers) {
            HashSet<Product> customerProductSet = new HashSet<>();
            for (Product product : products) {
                customerProductSet.add(product);
            }
            customerProducts.put(customer, customerProductSet);
        }

        customerProducts.forEach((customer, productSet) -> {
            System.out.println(customer.getName() + "'s Products: ");
            productSet.forEach(System.out::println);
        });

    
        TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());
        sortedProducts.addAll(products);

        TreeSet<Customer> sortedCustomers = new TreeSet<>(new CustomerLoyaltyComparator());
        sortedCustomers.addAll(customers);

        TreeSet<Order> sortedOrders = new TreeSet<>(new OrderDeliveryDateComparator());
        sortedOrders.addAll(orders);

        System.out.println("Sorted Products by Price:");
        sortedProducts.forEach(System.out::println);

        System.out.println("\nSorted Customers by Loyalty Points:");
        sortedCustomers.forEach(System.out::println);

        System.out.println("\nSorted Orders by Delivery Date:");
        sortedOrders.forEach(System.out::println);
        scanner.close();
    }
}
}