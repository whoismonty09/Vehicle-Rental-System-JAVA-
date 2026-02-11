import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    int vehicleId;
    String model;
    boolean isRented;

    Vehicle(int vehicleId, String model) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.isRented = false;
    }
}

class Rental {
    int vehicleId;
    String customerName;

    Rental(int vehicleId, String customerName) {
        this.vehicleId = vehicleId;
        this.customerName = customerName;
    }
}

public class Main {

    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n🚗 Vehicle Rental System");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. View Rentals");
            System.out.println("5. Return Vehicle");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    viewVehicles();
                    break;
                case 3:
                    rentVehicle();
                    break;
                case 4:
                    viewRentals();
                    break;
                case 5:
                    returnVehicle();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 6);
    }

    static void addVehicle() {
        System.out.print("Enter vehicle ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        vehicles.add(new Vehicle(id, model));
        System.out.println("Vehicle added successfully.");
    }

    static void viewVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (Vehicle v : vehicles) {
            String status = v.isRented ? "Rented" : "Available";
            System.out.println("ID: " + v.vehicleId + ", Model: " + v.model + ", Status: " + status);
        }
    }

    static void rentVehicle() {
        System.out.print("Enter vehicle ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Vehicle v : vehicles) {
            if (v.vehicleId == id && !v.isRented) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine();

                v.isRented = true;
                rentals.add(new Rental(id, name));
                System.out.println("Vehicle rented successfully.");
                return;
            }
        }

        System.out.println("Vehicle not available.");
    }

    static void viewRentals() {
        if (rentals.isEmpty()) {
            System.out.println("No rentals found.");
            return;
        }

        for (Rental r : rentals) {
            System.out.println("Vehicle ID: " + r.vehicleId + ", Customer: " + r.customerName);
        }
    }

    static void returnVehicle() {
        System.out.print("Enter vehicle ID to return: ");
        int id = scanner.nextInt();

        for (Rental r : rentals) {
            if (r.vehicleId == id) {
                rentals.remove(r);
                for (Vehicle v : vehicles) {
                    if (v.vehicleId == id) {
                        v.isRented = false;
                        break;
                    }
                }
                System.out.println("Vehicle returned successfully.");
                return;
            }
        }

        System.out.println("Rental not found.");
    }
          }
