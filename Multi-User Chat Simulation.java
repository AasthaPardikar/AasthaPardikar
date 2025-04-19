import java.util.*;

public class MultiUserChatSimulation {
    static class UserThread extends Thread {
        String username;

        UserThread(String username) {
            this.username = username;
        }

        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(username + ": message " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<UserThread> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter username: ");
            String name = sc.nextLine();
            users.add(new UserThread(name));
        }
        for (UserThread user : users) user.start();
    }
}
