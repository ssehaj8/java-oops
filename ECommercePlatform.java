import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class Product
abstract class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double calculateDiscount();

    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
    }
}

// Interface Taxable
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

// Subclass Electronics
class Electronics extends Product implements Taxable {
    private double discountRate = 0.10;
    private double taxRate = 0.15;

    public Electronics(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + (taxRate * 100) + "%";
    }
}

// Subclass Clothing
class Clothing extends Product implements Taxable {
    private double discountRate = 0.20;
    private double taxRate = 0.05;

    public Clothing(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + (taxRate * 100) + "%";
    }
}

// Subclass Groceries
class Groceries extends Product {
    private double discountRate = 0.05;

    public Groceries(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }
}

// Main class
public class ECommercePlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        // Adding an Electronics product
        System.out.print("Enter Electronics Product ID: ");
        int elecId = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Enter Electronics Product Name: ");
        String elecName = sc.nextLine();
        System.out.print("Enter Electronics Price: ");
        double elecPrice = sc.nextDouble();
        Electronics elecProduct = new Electronics(elecId, elecName, elecPrice);
        products.add(elecProduct);

        // Adding a Clothing product
        System.out.print("\nEnter Clothing Product ID: ");
        int clothId = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Enter Clothing Product Name: ");
        String clothName = sc.nextLine();
        System.out.print("Enter Clothing Price: ");
        double clothPrice = sc.nextDouble();
        Clothing clothProduct = new Clothing(clothId, clothName, clothPrice);
        products.add(clothProduct);

        // Adding a Groceries product
        System.out.print("\nEnter Groceries Product ID: ");
        int grocId = sc.nextInt();
        sc.nextLine(); // Consume leftover newline
        System.out.print("Enter Groceries Product Name: ");
        String grocName = sc.nextLine();
        System.out.print("Enter Groceries Price: ");
        double grocPrice = sc.nextDouble();
        Groceries grocProduct = new Groceries(grocId, grocName, grocPrice);
        products.add(grocProduct);

        // Display final prices
        System.out.println("\nProduct Details:");
        for (Product product : products) {
            product.displayDetails();
            double discount = product.calculateDiscount();
            double tax = (product instanceof Taxable) ? ((Taxable) product).calculateTax() : 0;
            double finalPrice = product.getPrice() + tax - discount;
            System.out.println("Discount: " + discount);
            System.out.println("Tax: " + tax);
            if (product instanceof Taxable) {
                System.out.println(((Taxable) product).getTaxDetails());
            }
            System.out.println("Final Price: " + finalPrice);
            System.out.println("----------------------");
        }
    }
}


/*
I/P->
Enter Electronics Product ID: 3456543
Enter Electronics Product Name: Music Player
Enter Electronics Price: 45000

Enter Clothing Product ID: 234321
Enter Clothing Product Name: Denim Shorts
Enter Clothing Price: 2500

Enter Groceries Product ID: 865446
Enter Groceries Product Name: Wheat Flour
Enter Groceries Price: 650


O/P ->
Product Details:
Product ID: 3456543
Name: Music Player
Price: 45000.0
Discount: 4500.0
Tax: 6750.0
Tax Rate: 15.0%
Final Price: 47250.0
----------------------
Product ID: 234321
Name: Denim Shorts
Price: 2500.0
Discount: 500.0
Tax: 125.0
Tax Rate: 5.0%
Final Price: 2125.0
----------------------
Product ID: 865446
Name: Wheat Flour
Price: 650.0
Discount: 32.5
Tax: 0.0
Final Price: 617.5
----------------------
 */