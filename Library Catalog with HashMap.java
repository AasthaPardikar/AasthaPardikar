import java.util.*;

public class LibraryCatalogWithHashMap {
    static class Book {
        String title, author;
        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
        public String toString() {
            return title + " by " + author;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Book> catalog = new HashMap<>();

        while (true) {
            System.out.println("1. Add Book\n2. View Book\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter book ID: ");
                String id = sc.nextLine();
                System.out.print("Enter title: ");
                String title = sc.nextLine();
                System.out.print("Enter author: ");
                String author = sc.nextLine();
                catalog.put(id, new Book(title, author));
            } else if (choice == 2) {
                System.out.print("Enter book ID: ");
                String id = sc.nextLine();
                System.out.println(catalog.getOrDefault(id, new Book("Not found", "")));
            } else {
                break;
            }
        }
    }
}
