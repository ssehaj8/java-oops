import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class Vehicle
abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public abstract double calculateRentalCost(int days);

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + type);
        System.out.println("Rental Rate per Day: " + rentalRate);
    }
}

// Interface Insurable
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// Subclass Car
class Car extends Vehicle implements Insurable {
    public Car(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Car", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.05;
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance Rate: 5% of rental rate";
    }
}

// Subclass Bike
class Bike extends Vehicle {
    public Bike(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Bike", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }
}

// Subclass Truck
class Truck extends Vehicle implements Insurable {
    public Truck(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Truck", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days * 1.2; // Additional charge for heavy vehicles
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.10;
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance Rate: 10% of rental rate";
    }
}

// Main class
public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();

        // Add a Car
        System.out.print("Enter Car Number: ");
        String carNumber = sc.nextLine();
        System.out.print("Enter Car Rental Rate per Day: ");
        double carRate = sc.nextDouble();
        sc.nextLine(); // Consume newline
        Car car = new Car(carNumber, carRate);
        vehicles.add(car);

        // Add a Bike
        System.out.print("\nEnter Bike Number: ");
        String bikeNumber = sc.nextLine();
        System.out.print("Enter Bike Rental Rate per Day: ");
        double bikeRate = sc.nextDouble();
        sc.nextLine(); // Consume newline
        Bike bike = new Bike(bikeNumber, bikeRate);
        vehicles.add(bike);

        // Add a Truck
        System.out.print("\nEnter Truck Number: ");
        String truckNumber = sc.nextLine();
        System.out.print("Enter Truck Rental Rate per Day: ");
        double truckRate = sc.nextDouble();
        sc.nextLine(); // Consume newline
        Truck truck = new Truck(truckNumber, truckRate);
        vehicles.add(truck);

        // Calculate rental and insurance cost
        System.out.print("\nEnter number of rental days: ");
        int days = sc.nextInt();

        System.out.println("\nVehicle Rental Details:");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
            double rentalCost = vehicle.calculateRentalCost(days);
            System.out.println("Rental Cost for " + days + " days: " + rentalCost);
            if (vehicle instanceof Insurable) {
                double insuranceCost = ((Insurable) vehicle).calculateInsurance();
                System.out.println("Insurance Cost: " + insuranceCost);
                System.out.println(((Insurable) vehicle).getInsuranceDetails());
            }
            System.out.println("---------------");
        }
    }
}


/*
I/P ->
Enter Car Number: PB 10 AX 4643
Enter Car Rental Rate per Day: 1000

Enter Bike Number: CU 03 M 6182
Enter Bike Rental Rate per Day: 550

Enter Truck Number: JK 9 OJ 4569
Enter Truck Rental Rate per Day: 4500

Enter number of rental days: 4


O/P->
Vehicle Rental Details:
Vehicle Number: PB 10 AX 4643
Type: Car
Rental Rate per Day: 1000.0
Rental Cost for 4 days: 4000.0
Insurance Cost: 50.0
Car Insurance Rate: 5% of rental rate
---------------
Vehicle Number: CU 03 M 6182
Type: Bike
Rental Rate per Day: 550.0
Rental Cost for 4 days: 2200.0
---------------
Vehicle Number: JK 9 OJ 4569
Type: Truck
Rental Rate per Day: 4500.0
Rental Cost for 4 days: 21600.0
Insurance Cost: 450.0
Truck Insurance Rate: 10% of rental rate
 */