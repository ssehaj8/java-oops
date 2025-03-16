import java.util.Scanner;

// Abstract class Transport
abstract class Transport {
    private String transportId;
    private String driverName;
    private double farePerKm;

    public Transport(String transportId, String driverName, double farePerKm) {
        this.transportId = transportId;
        this.driverName = driverName;
        this.farePerKm = farePerKm;
    }

    public String getTransportId() {
        return transportId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getFarePerKm() {
        return farePerKm;
    }

    public void showTransportDetails() {
        System.out.println("Transport ID: " + transportId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Fare per Km: Rs." + farePerKm);
    }

    public abstract double computeFare(double distance);
}

// Interface LocationTracker
interface LocationTracker {
    void fetchCurrentLocation();
    void modifyLocation(String newLocation);
}

// Subclass Cab
class Cab extends Transport implements LocationTracker {
    private String currentLocation;

    public Cab(String transportId, String driverName, double farePerKm, String currentLocation) {
        super(transportId, driverName, farePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double computeFare(double distance) {
        return distance * getFarePerKm();
    }

    @Override
    public void fetchCurrentLocation() {
        System.out.println("Cab Current Location: " + currentLocation);
    }

    @Override
    public void modifyLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Cab location updated to: " + newLocation);
    }
}

// Subclass Motorbike
class Motorbike extends Transport implements LocationTracker {
    private String currentLocation;

    public Motorbike(String transportId, String driverName, double farePerKm, String currentLocation) {
        super(transportId, driverName, farePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double computeFare(double distance) {
        return distance * getFarePerKm();
    }

    @Override
    public void fetchCurrentLocation() {
        System.out.println("Motorbike Current Location: " + currentLocation);
    }

    @Override
    public void modifyLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Motorbike location updated to: " + newLocation);
    }
}

// Subclass Rickshaw
class Rickshaw extends Transport implements LocationTracker {
    private String currentLocation;

    public Rickshaw(String transportId, String driverName, double farePerKm, String currentLocation) {
        super(transportId, driverName, farePerKm);
        this.currentLocation = currentLocation;
    }

    @Override
    public double computeFare(double distance) {
        return distance * getFarePerKm();
    }

    @Override
    public void fetchCurrentLocation() {
        System.out.println("Rickshaw Current Location: " + currentLocation);
    }

    @Override
    public void modifyLocation(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Rickshaw location updated to: " + newLocation);
    }
}

// Main class
public class RideHailingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Transport Type (Cab/Motorbike/Rickshaw): ");
        String type = sc.nextLine();

        System.out.print("Enter Transport ID: ");
        String transportId = sc.nextLine();
        System.out.print("Enter Driver Name: ");
        String driverName = sc.nextLine();
        System.out.print("Enter Fare per Km: ");
        double farePerKm = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Current Location: ");
        String location = sc.nextLine();

        Transport transport = null;

        switch (type.toLowerCase()) {
            case "cab":
                transport = new Cab(transportId, driverName, farePerKm, location);
                break;
            case "motorbike":
                transport = new Motorbike(transportId, driverName, farePerKm, location);
                break;
            case "rickshaw":
                transport = new Rickshaw(transportId, driverName, farePerKm, location);
                break;
            default:
                System.out.println("Invalid transport type!");
                System.exit(0);
        }

        transport.showTransportDetails();
        System.out.print("Enter Distance (in km): ");
        double distance = sc.nextDouble();
        System.out.println("Total Fare: Rs." + transport.computeFare(distance));

        if (transport instanceof LocationTracker) {
            ((LocationTracker) transport).fetchCurrentLocation();
            System.out.print("Enter new location to update: ");
            sc.nextLine(); // Consume newline
            String newLocation = sc.nextLine();
            ((LocationTracker) transport).modifyLocation(newLocation);
        }
    }
}


/*
I/P->
Enter Transport Type (Cab/Motorbike/Rickshaw): Cab
Enter Transport ID: AU1232
Enter Driver Name: Sehaj
Enter Fare per Km: 80
Enter Current Location: Chitkara University, Rajpura, Punjab

O/P->
Transport ID: AU1232
Driver Name: Sehaj
Fare per Km: Rs.80.0

I/P ->
Enter Distance (in km): 120

O/P ->
Total Fare: Rs.9600.0
Cab Current Location: Chitkara University, Rajpura, Punjab

I/P ->
Enter new location to update: Ludhiana

O/P ->
Cab location updated to: Ludhiana

 */