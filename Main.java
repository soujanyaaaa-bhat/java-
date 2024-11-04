//creating a base class called employeepay with attributes employee_name of string type,
//employee_id of integer type and designation of static string type as for an orgnaisation it is required to be static i.e. common
class Employeepay {
    String employeeName;
    int employeeId;
    static String designation;

//using "this" keyword to point to the instant object or current object
    public Employeepay(int employeeId, String employeeName) {
        this.employeeId= employeeId;
        this.employeeName= employeeName;
    }
//creating a method called calculate_bonus with no return type to later override 
   public void calculateBonus() {
    System.out.println("bonus is above as follows");
    }
//creating a method called disp_info that displays information of our employees including id, name, designation
    public void dispInfo() {
        System.out.println("employee id is " +employeeId);
        System.out.println("employee name is " +employeeName);
        System.out.println("designation is " +designation);
    }
}


//creating a derived class extending from base class with newer attributes namely hours_worked of int type,
// and hourly_rate of type double 
class HourlyEmployee extends Employeepay {
    int hoursWorked;
    double hourlyRate; 

    public HourlyEmployee(int employeeId, String employeeName, double hourlyRate, int hoursWorked) {
    super(employeeId, employeeName);
    this.hourlyRate= hourlyRate;
    this.hoursWorked=hoursWorked;
    }
    //overriding method calculatebonus from base class employee_pay
    public void calculateBonus() {
    System.out.println("bonus is above as follows");
    }   
//creating a new method weekly_salary that returns hourly_rate*hoursworked
    public double weeklySalary() {
    return hourlyRate * hoursWorked;
    }
//overriding method from base class to display hourly_rate, hoursworked and weeklysalary
    public void dispInfo(){
    super.dispInfo();
    System.out.println("hourly rate is " +hourlyRate);
    System.out.println("hours worked is "+hoursWorked);
    System.out.println("weekly salary is "+ weeklySalary());
    }
}

class SalariedEmployee extends Employeepay {
    double monthlySalary;
    public SalariedEmployee(int employeeId, String employeeName, double monthlySalary) {
        super(employeeId, employeeName);
        this.monthlySalary=monthlySalary;
    }
    public double weeklySalary() {
        return monthlySalary/4;
    }
    public void calculateBonus() {
        System.out.println("bonus is above as follows");
    }
    public void dispInfo(){
        super.dispInfo();
        System.out.println("monthly salary is " +monthlySalary);
        System.out.println("salary is "+weeklySalary());
    }
}

class ExecutiveEmployee extends SalariedEmployee {
    double bonusPercentage;
    public ExecutiveEmployee(int employee_id, String employeeName, double monthlySalary, double bonusPercentage) {
        super(employee_id, employeeName, monthlySalary);
        this.bonusPercentage = bonusPercentage;
    }

    public void calculateBonus() {
        super.calculateBonus(); 
        System.out.println("bonus is above as follows");
    }

    public void dispInfo() {
        super.dispInfo();
        System.out.println("bonus percentage: " + bonusPercentage);
    }
}

public class Main {
    public static void main(String[] args) {
        Employeepay.designation = "General Staff";

        HourlyEmployee hourlyEmp = new HourlyEmployee(101, "suresh", 20.5, 40);
        SalariedEmployee salariedEmp = new SalariedEmployee(102, "leena", 4000);
        ExecutiveEmployee execEmp = new ExecutiveEmployee(103, "margaret", 8000, 15);

        System.out.println("hourly employee information:");
        hourlyEmp.dispInfo();
        hourlyEmp.calculateBonus();

        System.out.println("\nsalaried employee information:");
        salariedEmp.dispInfo();
        salariedEmp.calculateBonus();

        System.out.println("\nexecutive employee information:");
        execEmp.dispInfo();
        execEmp.calculateBonus();
    }
}