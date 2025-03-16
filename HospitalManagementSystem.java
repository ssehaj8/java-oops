import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class Patient
abstract class Patient {
    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public abstract double calculateBill();
}

// Interface MedicalRecord
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// Subclass InPatient
class InPatient extends Patient implements MedicalRecord {
    private int daysAdmitted;
    private double dailyCharge;
    private List<String> records = new ArrayList<>();

    public InPatient(String patientId, String name, int age, int daysAdmitted, double dailyCharge) {
        super(patientId, name, age);
        this.daysAdmitted = daysAdmitted;
        this.dailyCharge = dailyCharge;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * dailyCharge;
    }

    @Override
    public void addRecord(String record) {
        records.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records: " + records);
    }
}

// Subclass OutPatient
class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;
    private List<String> records = new ArrayList<>();

    public OutPatient(String patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateBill() {
        return consultationFee;
    }

    @Override
    public void addRecord(String record) {
        records.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records: " + records);
    }
}

// Main class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Patient> patients = new ArrayList<>();

        // Adding an In-Patient
        System.out.print("Enter In-Patient ID: ");
        String inPatientId = sc.nextLine();
        System.out.print("Enter Name: ");
        String inName = sc.nextLine();
        System.out.print("Enter Age: ");
        int inAge = sc.nextInt();
        System.out.print("Enter Days Admitted: ");
        int daysAdmitted = sc.nextInt();
        System.out.print("Enter Daily Charge: ");
        double dailyCharge = sc.nextDouble();
        sc.nextLine();
        InPatient inPatient = new InPatient(inPatientId, inName, inAge, daysAdmitted, dailyCharge);
        inPatient.addRecord("Initial Checkup: Stable Condition");
        patients.add(inPatient);

        // Adding an Out-Patient
        System.out.print("\nEnter Out-Patient ID: ");
        String outPatientId = sc.nextLine();
        System.out.print("Enter Name: ");
        String outName = sc.nextLine();
        System.out.print("Enter Age: ");
        int outAge = sc.nextInt();
        System.out.print("Enter Consultation Fee in rupees: ");
        double consultationFee = sc.nextDouble();
        sc.nextLine();
        OutPatient outPatient = new OutPatient(outPatientId, outName, outAge, consultationFee);
        outPatient.addRecord("Consultation: Prescribed Medication");
        patients.add(outPatient);

        // Display Patient Details
        System.out.println("\nPatient Details and Billing:");
        for (Patient patient : patients) {
            patient.getPatientDetails();
            System.out.println("Total Bill: Rs." + patient.calculateBill());
            if (patient instanceof MedicalRecord) {
                ((MedicalRecord) patient).viewRecords();
            }
            System.out.println("-------------------");
        }
    }
}


/*
I/P ->
Enter In-Patient ID: 2345432
Enter Name: Alice Hale
Enter Age: 25
Enter Days Admitted: 26
Enter Daily Charge: 2000

Enter Out-Patient ID: 456878
Enter Name: John White
Enter Age: 34
Enter Consultation Fee in rupees: 550


O/P ->
Patient Details and Billing:
Patient ID: 2345432
Name: Alice Hale
Age: 25
Total Bill: Rs.52000.0
Medical Records: [Initial Checkup: Stable Condition]
-------------------
Patient ID: 456878
Name: John White
Age: 34
Total Bill: Rs.550.0
Medical Records: [Consultation: Prescribed Medication]
-------------------

 */