import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class PasswordGenerator {
    private static final String FILE_NAME = "generated_passwords.txt";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Password Generator ===");
        System.out.print("How many passwords do you want to generate? ");
        int count = scanner.nextInt();

        System.out.print("Enter desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Include symbols? (yes/no): ");
        boolean includeSymbols = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");

        List<String> generated = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String password = generatePassword(length, includeSymbols, includeNumbers);
            String strength = analyzeStrength(password);
            System.out.println("Password " + (i + 1) + ": " + password + " [" + strength + "]");
            generated.add(password + " [" + strength + "]");
        }

        savePasswordsToFile(generated);
        scanner.close();
    }

    public static String generatePassword(int length, boolean includeSymbols, boolean includeNumbers) {
        StringBuilder charPool = new StringBuilder(UPPER + LOWER);
        if (includeNumbers) charPool.append(DIGITS);
        if (includeSymbols) charPool.append(SYMBOLS);

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        Predicate<Character> isValid = ch -> true;
        if (!includeSymbols) {
            isValid = ch -> !SYMBOLS.contains(ch.toString());
        }

        for (int i = 0; i < length; i++) {
            char ch;
            do {
                ch = charPool.charAt(random.nextInt(charPool.length()));
            } while (!isValid.test(ch));
            password.append(ch);
        }

        return password.toString();
    }

    public static String analyzeStrength(String password) {
        int score = 0;
        if (password.length() >= 12) score++;
        if (password.matches(".[A-Z].")) score++;
        if (password.matches(".[a-z].")) score++;
        if (password.matches(".\\d.")) score++;
        if (password.matches(".[" + Pattern.quote(SYMBOLS) + "].")) score++;

        return switch (score) {
            case 5 -> "Very Strong";
            case 4 -> "Strong";
            case 3 -> "Moderate";
            case 2 -> "Weak";
            default -> "Very Weak";
        };
    }

    public static void savePasswordsToFile(List<String> passwords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            for (String password : passwords) {
                writer.write(password);
                writer.newLine();
            }
            System.out.println("Passwords saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving passwords to file.");
        }
    }
}
