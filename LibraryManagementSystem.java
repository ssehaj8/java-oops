import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class LibraryItem
abstract class LibraryItem {
    private String itemId;
    private String title;
    private String author;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }

    public abstract int getLoanDuration();
}

// Interface Reservable
interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

// Subclass Book
class Book extends LibraryItem implements Reservable {
    public Book(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        // 20 days loan duration
        return 20;
    }

    @Override
    public void reserveItem() {
        System.out.println("Book reserved: " + getTitle());
    }

    @Override
    public boolean checkAvailability() {
        return true; // Assume always available for simplicity
    }
}

// Subclass Magazine
class Magazine extends LibraryItem {
    public Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        // 8 days loan duration
        return 8;
    }
}

// Subclass DVD
class DVD extends LibraryItem implements Reservable {
    public DVD(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        // 5 days loan duration
        return 5;
    }

    @Override
    public void reserveItem() {
        System.out.println("DVD reserved: " + getTitle());
    }

    @Override
    public boolean checkAvailability() {
        return false; // Assume not available for simplicity
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<LibraryItem> libraryItems = new ArrayList<>();

        // Adding a Book
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine();
        System.out.print("Enter Book Title: ");
        String bookTitle = sc.nextLine();
        System.out.print("Enter Author: ");
        String bookAuthor = sc.nextLine();
        Book book = new Book(bookId, bookTitle, bookAuthor);
        libraryItems.add(book);

        // Adding a Magazine
        System.out.print("\nEnter Magazine ID: ");
        String magId = sc.nextLine();
        System.out.print("Enter Magazine Title: ");
        String magTitle = sc.nextLine();
        System.out.print("Enter Publisher: ");
        String magAuthor = sc.nextLine();
        Magazine magazine = new Magazine(magId, magTitle, magAuthor);
        libraryItems.add(magazine);

        // Adding a DVD
        System.out.print("\nEnter DVD ID: ");
        String dvdId = sc.nextLine();
        System.out.print("Enter DVD Title: ");
        String dvdTitle = sc.nextLine();
        System.out.print("Enter Director: ");
        String dvdAuthor = sc.nextLine();
        DVD dvd = new DVD(dvdId, dvdTitle, dvdAuthor);
        libraryItems.add(dvd);

        // Display Item Details and Loan Duration
        System.out.println("\nLibrary Items:");
        for (LibraryItem item : libraryItems) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            if (item instanceof Reservable) {
                System.out.println("Reservable: " + ((Reservable) item).checkAvailability());
            }
            System.out.println("---------------------");
        }
    }
}


/*
I/P ->
Enter Book ID: 6857
Enter Book Title: Wings of Fire
Enter Author: APJ Abdul Kalam

Enter Magazine ID: 34532
Enter Magazine Title: Reader's Digest
Enter Publisher: ABC Publishing

Enter DVD ID: 6543
Enter DVD Title: XYAZ
Enter Director: John White


O/P ->
Library Items:
Item ID: 6857
Title: Wings of Fire
Author: APJ Abdul Kalam
Loan Duration: 20 days
Reservable: true
---------------------
Item ID: 34532
Title: Reader's Digest
Author: ABC Publishing
Loan Duration: 8 days
---------------------
Item ID: 6543
Title: XYAZ
Author: John White
Loan Duration: 5 days
Reservable: false
---------------------
 */