import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class Employee
abstract class Employee {
    private int employeeId;
    private String name;
    private double baseSalary;

    // Constructor
    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();

    // Method to display employee details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Final Salary: " + calculateSalary());
    }
}

// Interface Department
interface Department {
    void assignDepartment(String department);
    String getDepartmentDetails();
}

// Subclass FullTimeEmployee
class FullTimeEmployee extends Employee implements Department {
    private String department;
    private double bonus;

    // Constructor
    public FullTimeEmployee(int employeeId, String name, double baseSalary, double bonus) {
        super(employeeId, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + department;
    }
}

// Subclass: PartTimeEmployee
class PartTimeEmployee extends Employee implements Department {
    private String department;
    private int hoursWorked;
    private double hourlyRate;

    // Constructor
    public PartTimeEmployee(int employeeId, String name, double baseSalary, int hoursWorked, double hourlyRate) {
        super(employeeId, name, baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (hoursWorked * hourlyRate);
    }

    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + department;
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        // Creating a Full-Time Employee
        System.out.print("Enter Full-Time Employee ID: ");
        int ftId = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Enter Full-Time Employee Name: ");
        String ftName = sc.nextLine();
        System.out.print("Enter Base Salary: ");
        double ftBaseSalary = sc.nextDouble();
        System.out.print("Enter Bonus: ");
        double bonus = sc.nextDouble();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Assign Department: ");
        String ftDepartment = sc.nextLine();
        FullTimeEmployee ftEmployee = new FullTimeEmployee(ftId, ftName, ftBaseSalary, bonus);
        ftEmployee.assignDepartment(ftDepartment);
        employees.add(ftEmployee);

        // Creating a Part-Time Employee
        System.out.print("\nEnter Part-Time Employee ID: ");
        int ptId = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Enter Part-Time Employee Name: ");
        String ptName = sc.nextLine();
        System.out.print("Enter Base Salary: ");
        double ptBaseSalary = sc.nextDouble();
        System.out.print("Enter Hours Worked: ");
        int hoursWorked = sc.nextInt();
        System.out.print("Enter Hourly Rate: ");
        double hourlyRate = sc.nextDouble();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Assign Department: ");
        String ptDepartment = sc.nextLine();
        PartTimeEmployee ptEmployee = new PartTimeEmployee(ptId, ptName, ptBaseSalary, hoursWorked, hourlyRate);
        ptEmployee.assignDepartment(ptDepartment);
        employees.add(ptEmployee);

        // Display employee details using polymorphism
        System.out.println("\nEmployee Details:");
        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println(((Department) emp).getDepartmentDetails());
            System.out.println("-----------------------");
        }
    }
}

/*
I/P ->
Enter Full-Time Employee ID: 12345
Enter Full-Time Employee Name: Sehaj
Enter Base Salary: 50000
Enter Bonus: 5000
Assign Department: IT

Enter Part-Time Employee ID: 4567543
Enter Part-Time Employee Name: John Smith
Enter Base Salary: 25000
Enter Hours Worked: 8
Enter Hourly Rate: 250
Assign Department: IT

O/P->
Employee Details:
Employee ID: 12345
Name: Sehaj
Base Salary: 50000.0
Final Salary: 55000.0
Department: IT
-----------------------
Employee ID: 4567543
Name: John Smith
Base Salary: 25000.0
Final Salary: 27000.0
Department: IT
-----------------------
 */