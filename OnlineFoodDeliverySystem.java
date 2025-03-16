import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class FoodItem
abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price: Rs." + price);
        System.out.println("Quantity: " + quantity);
    }

    public abstract double calculateTotalPrice();
}

// Interface Discountable
interface Discountable {
    double applyDiscount();
    void getDiscountDetails();
}

// Subclass VegItem
class VegItem extends FoodItem implements Discountable {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public double applyDiscount() {
        // 20% discount
        return calculateTotalPrice() * 0.20;
    }

    @Override
    public void getDiscountDetails() {
        System.out.println("Veg item discount: 20%");
    }
}

// Subclass NonVegItem
class NonVegItem extends FoodItem implements Discountable {
    private static final double NON_VEG_SURCHARGE = 2.0; // Additional charge per item

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (getPrice() + NON_VEG_SURCHARGE) * getQuantity();
    }

    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.07; // 5% discount
    }

    @Override
    public void getDiscountDetails() {
        System.out.println("Non-veg item discount: 7%");
    }
}

// Main class
public class OnlineFoodDeliverySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FoodItem> orderList = new ArrayList<>();

        // Adding a Veg Item
        System.out.print("Enter Veg Item Name: ");
        String vegName = sc.nextLine();
        System.out.print("Enter Price in rupees: ");
        double vegPrice = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int vegQuantity = sc.nextInt();
        sc.nextLine();
        VegItem vegItem = new VegItem(vegName, vegPrice, vegQuantity);
        orderList.add(vegItem);

        // Adding a Non-Veg Item
        System.out.print("\nEnter Non-Veg Item Name: ");
        String nonVegName = sc.nextLine();
        System.out.print("Enter Price in rupees: ");
        double nonVegPrice = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int nonVegQuantity = sc.nextInt();
        sc.nextLine();
        NonVegItem nonVegItem = new NonVegItem(nonVegName, nonVegPrice, nonVegQuantity);
        orderList.add(nonVegItem);

        // Display Order Details
        System.out.println("\nOrder Summary:");
        double totalBill = 0;
        for (FoodItem item : orderList) {
            item.getItemDetails();
            double discount = 0;
            if (item instanceof Discountable) {
                discount = ((Discountable) item).applyDiscount();
                ((Discountable) item).getDiscountDetails();
            }
            double finalPrice = item.calculateTotalPrice() - discount;
            System.out.println("Total Price after Discount: Rs" + finalPrice);
            System.out.println("---------------------");
            totalBill += finalPrice;
        }

        System.out.println("Grand Total: Rs." + totalBill);
    }
}


/*
I/P ->
Enter Veg Item Name: Shahee Paneer
Enter Price in rupees: 250
Enter Quantity: 1

Enter Non-Veg Item Name: Butter Chicken
Enter Price in rupees: 360
Enter Quantity: 2


O/P ->
Order Summary:
Item: Shahee Paneer
Price: Rs.250.0
Quantity: 1
Veg item discount: 20%
Total Price after Discount: Rs200.0
---------------------
Item: Butter Chicken
Price: Rs.360.0
Quantity: 2
Non-veg item discount: 7%
Total Price after Discount: Rs673.3199999999999
---------------------
Grand Total: Rs.873.3199999999999
 */