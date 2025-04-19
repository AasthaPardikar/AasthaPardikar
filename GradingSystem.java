import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private double[] marks;
    private char[] grades;

    public Student(String name, int rollNumber, double[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public Student(String name, int rollNumber, char[] grades) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grades = grades;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    // Calculate result using marks
    public String calculateResult() {
        if (marks == null) return "No marks available.";

        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        double avg = total / marks.length;

        String grade;
        if (avg >= 90) grade = "A";
        else if (avg >= 80) grade = "B";
        else if (avg >= 70) grade = "C";
        else if (avg >= 60) grade = "D";
        else grade = "F";

        return String.format("Percentage: %.2f%%, Grade: %s", avg, grade);
    }

    // Overloaded: Calculate result using grades
    public String calculateResult(char[] grades) {
        if (grades == null) return "No grades available.";

        int totalPoints = 0;
        for (char g : grades) {
            switch (g) {
                case 'A': totalPoints += 4; break;
                case 'B': totalPoints += 3; break;
                case 'C': totalPoints += 2; break;
                case 'D': totalPoints += 1; break;
                case 'F': totalPoints += 0; break;
                default: return "Invalid grade found.";
            }
        }
        double gpa = (double) totalPoints / grades.length;
        return String.format("GPA: %.2f", gpa);
    }
}

public class GradingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter roll number: ");
            int roll = scanner.nextInt();

            System.out.print("Evaluate using (1) Marks or (2) Grades? Enter 1 or 2: ");
            int option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Enter number of subjects: ");
                int subjectCount = scanner.nextInt();
                double[] marks = new double[subjectCount];
                for (int j = 0; j < subjectCount; j++) {
                    System.out.print("Enter mark for subject " + (j + 1) + ": ");
                    marks[j] = scanner.nextDouble();
                }
                students.add(new Student(name, roll, marks));
            } else if (option == 2) {
                System.out.print("Enter number of subjects: ");
                int subjectCount = scanner.nextInt();
                char[] grades = new char[subjectCount];
                scanner.nextLine(); // consume newline
                for (int j = 0; j < subjectCount; j++) {
                    System.out.print("Enter grade for subject " + (j + 1) + " (A-F): ");
                    grades[j] = scanner.nextLine().toUpperCase().charAt(0);
                }
                students.add(new Student(name, roll, grades));
            } else {
                System.out.println("Invalid option. Skipping student.");
            }
            scanner.nextLine(); // Consume newline
        }

        // Display Results
        System.out.println("\n=== Student Results ===");
        for (Student s : students) {
            System.out.println("\nStudent: " + s.getName() + " | Roll No: " + s.getRollNumber());
            if (s.calculateResult().contains("Percentage"))
                System.out.println(s.calculateResult());
            else
                System.out.println(s.calculateResult(s.grades)); // pass grades array
        }

        scanner.close();
    }
}
