import java.util.*;

public class TaskListManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> arrayListTasks = new ArrayList<>();
        List<String> linkedListTasks = new LinkedList<>();

        while (true) {
            System.out.println("\n=== Task List Manager ===");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Show Tasks (ArrayList)");
            System.out.println("5. Show Tasks (LinkedList)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    arrayListTasks.add(task);
                    linkedListTasks.add(task);
                    System.out.println("Task added.");
                }
                case 2 -> {
                    System.out.print("Enter task number to update: ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine(); // consume newline
                    if (index >= 0 && index < arrayListTasks.size()) {
                        System.out.print("Enter new task: ");
                        String newTask = scanner.nextLine();
                        arrayListTasks.set(index, newTask);
                        linkedListTasks.set(index, newTask);
                        System.out.println("Task updated.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter task number to delete: ");
                    int index = scanner.nextInt() - 1;
                    if (index >= 0 && index < arrayListTasks.size()) {
                        arrayListTasks.remove(index);
                        linkedListTasks.remove(index);
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                }
                case 4 -> displayTasks(arrayListTasks, "ArrayList");
                case 5 -> displayTasks(linkedListTasks, "LinkedList");
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void displayTasks(List<String> tasks, String listType) {
        System.out.println("\nTasks in " + listType + ":");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
