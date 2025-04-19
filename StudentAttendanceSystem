import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentAttendanceSystem {
    private static final String FILE_NAME = "attendance.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Student Attendance System ===");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View All Attendance Records");
            System.out.println("3. Search Attendance by Student Name");
            System.out.println("4. Search Attendance by Date");
            System.out.println("5. Delete All Attendance Records");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> markAttendance();
                case 2 -> viewAllRecords();
                case 3 -> searchByName();
                case 4 -> searchByDate();
                case 5 -> deleteAllRecords();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private static void markAttendance() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String entry = name + " - " + dateTime;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(entry);
            writer.newLine();
            System.out.println("Attendance marked successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    private static void viewAllRecords() {
        System.out.println("\n--- Attendance Records ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasRecords = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasRecords = true;
            }
            if (!hasRecords) {
                System.out.println("No records found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }

    private static void searchByName() {
        System.out.print("Enter Student Name to Search: ");
        String keyword = scanner.nextLine().toLowerCase();
        searchRecords(keyword, true);
    }

    private static void searchByDate() {
        System.out.print("Enter Date to Search (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        searchRecords(date, false);
    }

    private static void searchRecords(String keyword, boolean searchByName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if ((searchByName && line.toLowerCase().contains(keyword)) || (!searchByName && line.contains(keyword))) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No matching records found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }

    private static void deleteAllRecords() {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            writer.print("");
            System.out.println("All records deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error deleting the file contents.");
        }
    }
}
