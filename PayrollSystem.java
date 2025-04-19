import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

interface Payable {
    double calculatePay();
    void printPaySlip();
}

abstract class Employee implements Payable {
    protected String name;
    protected int id;
    protected String department;

    public Employee(String name, int id, String department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    public abstract double calculatePay();

    public void printPaySlip() {
        System.out.println("=====================================");
        System.out.println("Employee ID   : " + id);
        System.out.println("Name          : " + name);
        System.out.println("Department    : " + department);
        System.out.printf("Total Pay     : $%.2f%n", calculatePay());
        System.out.println("=====================================");
    }

    public void savePaySlipToFile() {
        try (FileWriter writer = new FileWriter("Payslip_" + id + ".txt")) {
            writer.write("======= PAY SLIP =======\n");
            writer.write("Employee ID   : " + id + "\n");
            writer.write("Name          : " + name + "\n");
            writer.write("Department    : " + department + "\n");
            writer.write(String.format("Total Pay     : $%.2f\n", calculatePay()));
            writer.write("========================\n");
            System.out.println("Payslip saved as Payslip_" + id + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving payslip: " + e.getMessage());
        }
    }
}

class FullTimeEmployee extends Employee {
    private double baseSalary;
    private double bonus;
    private double deductions;

    public FullTimeEmployee(String name, int id, String department, double baseSalary, double bonus, double deductions) {
        super(name, id, department);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    @Override
    public double calculatePay() {
        return baseSalary + bonus - deductions;
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    private int overtimeHours;

    public PartTimeEmployee(String name, int id, String department, double hourlyRate, int hoursWorked, int overtimeHours) {
        super(name, id, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculatePay() {
        double basePay = hourlyRate * hoursWorked;
        double overtimePay = 1.5 * hourlyRate * overtimeHours;
        return basePay + overtimePay;
    }
}

public class PayrollSystem {
    private static final ArrayList<Employee> employeeList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Employee Payroll System ===");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display All Payslips");
            System.out.println("4. Save All Payslips to File");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addFullTimeEmployee();
                case 2 -> addPartTimeEmployee();
                case 3 -> displayPayslips();
                case 4 -> savePayslips();
                case 5 -> {
                    running = false;
                    System.out.println("Exiting Payroll System. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addFullTimeEmployee() {
        System.out.println("Enter Full-Time Employee Details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        System.out.print("Base Salary: ");
        double base = scanner.nextDouble();
        System.out.print("Bonus: ");
        double bonus = scanner.nextDouble();
        System.out.print("Deductions: ");
        double deduct = scanner.nextDouble();

        Employee e = new FullTimeEmployee(name, id, dept, base, bonus, deduct);
        employeeList.add(e);
        System.out.println("Full-Time Employee added successfully!");
    }

    private static void addPartTimeEmployee() {
        System.out.println("Enter Part-Time Employee Details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        System.out.print("Hourly Rate: ");
        double rate = scanner.nextDouble();
        System.out.print("Hours Worked: ");
        int hours = scanner.nextInt();
        System.out.print("Overtime Hours: ");
        int ot = scanner.nextInt();

        Employee e = new PartTimeEmployee(name, id, dept, rate, hours, ot);
        employeeList.add(e);
        System.out.println("Part-Time Employee added successfully!");
    }

    private static void displayPayslips() {
        for (Employee e : employeeList) {
            e.printPaySlip();
        }
    }

    private static void savePayslips() {
        for (Employee e : employeeList) {
            e.savePaySlipToFile();
        }
    }
}
