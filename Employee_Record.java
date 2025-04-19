import java.io.*;
import java.util.Scanner;

class Employee implements Serializable {
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee Name : " + name);
        System.out.println("Employee ID   : " + id);
        System.out.println("Salary        : ₹" + salary);
    }
}

public class Employee_Record {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            Employee emp = new Employee(name, id, salary);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
            out.writeObject(emp);
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"));
            Employee savedEmp = (Employee) in.readObject();
            in.close();

            System.out.println("\nDeserialized Employee Record:");
            savedEmp.display();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
