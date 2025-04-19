import java.io.*;
import java.util.*;

public class ExamResultLogger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("results.txt");

        try (FileWriter fw = new FileWriter(file, true)) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            System.out.print("Enter marks: ");
            int marks = sc.nextInt();
            String status = marks >= 40 ? "Pass" : "Fail";
            fw.write(name + " - " + marks + " - " + status + "\n");
            System.out.println("Result saved.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}
