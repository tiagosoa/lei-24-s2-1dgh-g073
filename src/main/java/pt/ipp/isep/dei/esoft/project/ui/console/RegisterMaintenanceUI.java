package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterMaintenanceUI implements Runnable {

    private final RegisterMaintenanceController controller;
    private String vehiclePlateNumber;
    private int currentKM;
    private LocalDate maintenanceDate;

    private VehicleRepository vehicleRepository;

    public RegisterMaintenanceUI() {
        this.controller = new RegisterMaintenanceController();
        this.vehicleRepository = new VehicleRepository();
    }

    private RegisterMaintenanceController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Maintenance ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Boolean> result = getController().registerMaintenance(vehiclePlateNumber, currentKM, maintenanceDate);

        if (result.isPresent() && result.get()) {
            System.out.println("\nMaintenance successfully registered!");
        } else {
            System.out.println("\nFailed to register maintenance!");
        }
    }

    private void requestData() {
        vehiclePlateNumber = requestVehiclePlateNumber();
        currentKM = requestCurrentKMs();
        maintenanceDate = requestMaintenanceDate();
    }

    private String requestVehiclePlateNumber() {
        List<Vehicle> vehicles = getController().getVehicles();
        // Show list of registered vehicles
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
        } else {
            System.out.println("Vehicle List:");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println((i + 1) + ". " + vehicles.get(i).getPlateNumber());
            }
            int vehicleIndex = readInput(1, vehicles.size()) - 1;
            return vehicles.get(vehicleIndex).getPlateNumber();
        }
        return null;
    }

    private int readInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.print("Select the vehicle by entering a number between " + min + " and " + max + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        scanner.nextLine();
        return input;
    }

    private int requestCurrentKMs() {
        Scanner input = new Scanner(System.in);
        System.out.print("Current kilometers: ");
        return input.nextInt();
    }

    private LocalDate requestMaintenanceDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle's maintenance date (format: dd-MM-yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }
}