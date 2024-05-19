package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for registering maintenance for vehicles.
 */
public class RegisterMaintenanceUI implements Runnable {

    private final RegisterMaintenanceController controller;
    private String vehiclePlateNumber;
    private int currentKM;
    private LocalDate maintenanceDate;

    /**
     * Constructor for RegisterMaintenanceUI.
     * Initializes the controller for registering maintenance.
     */
    public RegisterMaintenanceUI() {
        this.controller = new RegisterMaintenanceController();
    }

    /**
     * Retrieves the controller for registering maintenance.
     *
     * @return the RegisterMaintenanceController
     */
    private RegisterMaintenanceController getController() {
        return controller;
    }

    /**
     * Runs the maintenance registration process.
     * Displays the initial message and proceeds to request and submit data.
     */
    public void run() {
        System.out.println("\n\n--- Register Maintenance ------------------------");

        requestData();
        submitData();
    }

    /**
     * Submits the maintenance data to the controller for registration.
     * Prints a success or failure message based on the result.
     */
    private void submitData() {
        Optional<Boolean> result = getController().registerMaintenance(vehiclePlateNumber, currentKM, maintenanceDate);
        System.out.println(result.orElse(false) ? "\nMaintenance successfully registered!" : "\nFailed to register maintenance!");
    }

    /**
     * Requests the necessary data for maintenance registration.
     * Calls methods to request vehicle plate number, current kilometers, and maintenance date.
     */
    private void requestData() {
        vehiclePlateNumber = requestVehiclePlateNumber();
        currentKM = requestCurrentKMs();
        maintenanceDate = requestMaintenanceDate();
    }

    /**
     * Requests the user to input the vehicle plate number.
     * Displays a list of vehicles and prompts the user to select one.
     *
     * @return the selected vehicle's plate number
     */
    private String requestVehiclePlateNumber() {
        List<Vehicle> vehicles = getController().getVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return null;
        } else {
            System.out.println("Vehicle List:");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println((i + 1) + ". " + vehicles.get(i).getPlateNumber());
            }
            int vehicleIndex = readInput(1, vehicles.size()) - 1;
            return vehicles.get(vehicleIndex).getPlateNumber();
        }
    }

    /**
     * Reads and validates user input within a specified range.
     *
     * @param min the minimum allowed input value
     * @param max the maximum allowed input value
     * @return the validated user input
     */
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

    /**
     * Requests the user to input the current kilometers of the vehicle.
     *
     * @return the current kilometers input by the user
     */
    private int requestCurrentKMs() {
        Scanner input = new Scanner(System.in);
        System.out.print("Current kilometers: ");
        return input.nextInt();
    }

    /**
     * Requests the user to input the maintenance date of the vehicle.
     *
     * @return the maintenance date input by the user
     */
    private LocalDate requestMaintenanceDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle's maintenance date (format: dd-MM-yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }
}