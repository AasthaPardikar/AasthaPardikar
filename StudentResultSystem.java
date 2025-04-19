import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class StudentResultSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TreeSet automatically sorts and avoids duplicates
        Set<Integer> scores = new TreeSet<>();

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        System.out.println("Enter scores:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Student " + i + " score: ");
            int score = scanner.nextInt();
            boolean added = scores.add(score);
            if (!added) {
                System.out.println("Duplicate score! Ignored.");
            }
        }

        System.out.println("\nSorted Scores (Ascending):");
        for (int score : scores) {
            System.out.println(score);
        }

        scanner.close();
    }
}
