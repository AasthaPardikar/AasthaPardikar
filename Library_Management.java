import java.util.Scanner;

final class Book {
    private String title;
    private String author;
    private int copies;

    public Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    public final void displayBook() {
        System.out.println("Title  : " + title);
        System.out.println("Author : " + author);
        System.out.println("Copies : " + copies);
        System.out.println();
    }

    public boolean borrowBook() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    public String getTitle() {
        return title;
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public void borrow(Book[] books, String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                if (book.borrowBook()) {
                    System.out.println(name + " borrowed \"" + book.getTitle() + "\"");
                } else {
                    System.out.println("No copies available for \"" + book.getTitle() + "\"");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class Library_Management {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] books = new Book[3];
        books[0] = new Book("Java Basics", "Author A", 2);
        books[1] = new Book("Python 101", "Author B", 1);
        books[2] = new Book("C++ Mastery", "Author C", 3);

        System.out.print("Enter Member Name: ");
        String memberName = scanner.nextLine();
        Member member = new Member(memberName);

        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            book.displayBook();
        }

        System.out.print("Enter book title to borrow: ");
        String selectedBook = scanner.nextLine();
        member.borrow(books, selectedBook);

        System.out.println("\nUpdated Book List:");
        for (Book book : books) {
            book.displayBook();
        }

        scanner.close();
    }
}
