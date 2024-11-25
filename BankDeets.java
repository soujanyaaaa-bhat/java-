import java.util.Scanner;

// interface 'BankInterface' implementing two methods to get balance and interest rate
interface BankInterface {
    double getBalance();
    double getInterestRate();
}

// abstract class 'Bank' implements the interface
abstract class Bank implements BankInterface {
    protected double balance; 

    public Bank(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance; 
    }

}
class BankA extends Bank {
    public BankA(double balance) {
        super(balance); 
        
    }
    @Override
    public double getInterestRate() {
        return 0.07; 
    }
}

// Subclass BankB
class BankB extends Bank {
    public BankB(double balance) {
        super(balance); 
    }

    @Override
    public double getInterestRate() {
        return 0.074; 
    }
}

// Subclass BankC
class BankC extends Bank {
    public BankC(double balance) {
        super(balance); 
    }

    @Override
    public double getInterestRate() {
        return 0.079; 
    }
}

// Main class
public class BankDeets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance for Bank A: ");
        double initialBalanceA = scanner.nextDouble();
        BankInterface bankA = new BankA(initialBalanceA);

        System.out.print("Enter initial balance for Bank B: ");
        double initialBalanceB = scanner.nextDouble();
        BankInterface bankB = new BankB(initialBalanceB);

        System.out.print("Enter initial balance for Bank C: ");
        double initialBalanceC = scanner.nextDouble();
        BankInterface bankC = new BankC(initialBalanceC);

        // Displaying details for each bank
        System.out.println("\nBank A:");
        System.out.println("Balance: " + bankA.getBalance());
        System.out.println("Interest Rate: " + bankA.getInterestRate());

        System.out.println("\nBank B:");
        System.out.println("Balance: " + bankB.getBalance());
        System.out.println("Interest Rate: " + bankB.getInterestRate());

        System.out.println("\nBank C:");
        System.out.println("Balance: " + bankC.getBalance());
        System.out.println("Interest Rate: " + bankC.getInterestRate());

        scanner.close();
    }
}
