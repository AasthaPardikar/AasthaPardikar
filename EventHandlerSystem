import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Random;

public class EventHandlerSystem {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Event Handler System with Lambda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        // Create buttons
        JButton timeButton = new JButton("Show Current Time");
        JButton factorialButton = new JButton("Calculate Factorial");
        JButton quoteButton = new JButton("Show Random Quote");
        JButton exitButton = new JButton("Exit");

        // Add action listener using Lambda - Show Time
        timeButton.addActionListener(e -> {
            LocalTime time = LocalTime.now();
            JOptionPane.showMessageDialog(frame, "Current Time: " + time.toString());
        });

        // Add action listener using Lambda - Factorial
        factorialButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter a number:");
            try {
                int num = Integer.parseInt(input);
                if (num < 0) throw new NumberFormatException();
                long result = 1;
                for (int i = 2; i <= num; i++) {
                    result *= i;
                }
                JOptionPane.showMessageDialog(frame, "Factorial of " + num + " is: " + result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid non-negative integer.");
            }
        });

        // Add action listener using Lambda - Random Quote
        quoteButton.addActionListener(e -> {
            String[] quotes = {
                "Believe you can and you're halfway there.",
                "Push yourself, because no one else is going to do it for you.",
                "Failure is not the opposite of success; it's part of success.",
                "Dream big and dare to fail.",
                "Don’t stop until you’re proud."
            };
            int index = new Random().nextInt(quotes.length);
            JOptionPane.showMessageDialog(frame, quotes[index]);
        });

        // Add action listener using Lambda - Exit
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to frame
        frame.add(timeButton);
        frame.add(factorialButton);
        frame.add(quoteButton);
        frame.add(exitButton);

        // Show frame
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
