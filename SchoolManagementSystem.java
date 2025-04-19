import java.io.*;
import java.util.*;

abstract class Person implements Serializable {
    protected String name;
    protected int age;
    protected String address;

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public abstract void displayInfo();
}

// =================== STUDENT CLASS ======================
class Student extends Person {
    private String studentId;
    private Map<String, Integer> grades;

    public Student(String name, int age, String address, String studentId) {
        super(name, age, address);
        this.studentId = studentId;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int score) {
        grades.put(subject, score);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0;
        int total = 0;
        for (int score : grades.values()) {
            total += score;
        }
        return (double) total / grades.size();
    }

    @Override
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Student ID: " + studentId);
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Grades:");
            grades.forEach((subject, score) -> System.out.println("  " + subject + ": " + score));
            System.out.printf("Average: %.2f\n", calculateAverage());
        }
    }
}

// =================== TEACHER CLASS ======================
class Teacher extends Person {
    private String employeeId;
    private List<String> subjects;

    public Teacher(String name, int age, String address, String employeeId) {
        super(name, age, address);
        this.employeeId = employeeId;
        this.subjects = new ArrayList<>();
    }

    public void assignSubject(String subject) {
        subjects.add(subject);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n--- Teacher Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Subjects: " + (subjects.isEmpty() ? "None assigned" : String.join(", ", subjects)));
    }
}

// =================== MAIN DRIVER WITH MENU ======================
public class SchoolManagementSystem {
    private static final String FILE_NAME = "school_data.ser";
    private static List<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n===== School Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Display All People");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> displayAll();
                case 4 -> saveData();
                case 5 -> loadData();
                case 6 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        Student student = new Student(name, age, address, id);
        while (true) {
            System.out.print("Add subject grade? (yes/no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("no")) break;
            System.out.print("Subject: ");
            String subject = scanner.nextLine();
            System.out.print("Score: ");
            int score = scanner.nextInt();
            scanner.nextLine();
            student.addGrade(subject, score);
        }

        people.add(student);
        System.out.println("Student added.");
    }

    private static void addTeacher() {
        System.out.println("\n--- Add New Teacher ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Employee ID: ");
        String id = scanner.nextLine();

        Teacher teacher = new Teacher(name, age, address, id);
        while (true) {
            System.out.print("Add subject? (yes/no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("no")) break;
            System.out.print("Subject: ");
            String subject = scanner.nextLine();
            teacher.assignSubject(subject);
        }

        people.add(teacher);
        System.out.println("Teacher added.");
    }

    private static void displayAll() {
        if (people.isEmpty()) {
            System.out.println("No records to display.");
        } else {
            for (Person p : people) {
                p.displayInfo();
            }
        }
    }

    private static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(people);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            people = (List<Person>) in.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }
}
