package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for adding a new vehicle to the system.
 */
public class AddVehicleUI implements Runnable {

    private final AddVehicleController controller;
    private String vehicleModel, vehicleBrand, vehicleType, vehiclePlateNumber;
    private double vehicleTareWeight, vehicleGrossWeight, vehicleCurrentKm;
    private LocalDate vehicleRegisterDate, vehicleAcquisitionDate, vehicleLastMaintenanceDate;
    private int vehicleMaintenanceFrequencyKm;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructor for AddVehicleUI.
     */
    public AddVehicleUI() {
        controller = new AddVehicleController();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    private AddVehicleController getController() {
        return controller;
    }

    /**
     * Runs the Add Vehicle user interface.
     */
    public void run() {
        System.out.println("\n\n--- Add Vehicle ------------------------");
        requestData();
        submitData();
    }

    // Submits the data entered by the user to create a new vehicle
    private void submitData() {
        VFM vfm = getController().getVFMFromSession();
        Optional<Vehicle> vehicle = getController().addVehicle(vehicleModel, vehicleBrand, vehicleType, vehicleTareWeight, vehicleGrossWeight, vehicleCurrentKm, vehicleRegisterDate, vehicleAcquisitionDate, vehicleMaintenanceFrequencyKm, vehiclePlateNumber, vehicleLastMaintenanceDate, vfm);

        if (vehicle.isPresent()) {
            vehicleRepository.add(vehicle.get());
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
        }
    }

    // Requests input data from the user to create a new vehicle
    private void requestData() {
        vehicleModel = requestInput("Vehicle model");
        vehicleBrand = requestInput("Vehicle brand");
        vehicleType = requestInput("Vehicle type");
        vehicleTareWeight = requestDoubleInput("Vehicle Tare Weight (in kg)");
        vehicleGrossWeight = requestDoubleInput("Vehicle Gross Weight (in kg)");
        vehicleCurrentKm = requestDoubleInput("Vehicle Current Km");
        vehicleRegisterDate = requestDateInput("Vehicle Register Date (format: dd-MM-yyyy)");
        vehicleAcquisitionDate = requestDateInput("Vehicle Acquisition Date (format: dd-MM-yyyy)");
        vehicleMaintenanceFrequencyKm = requestIntInput("Vehicle Maintenance/Check-up Frequency (in kms)");
        vehiclePlateNumber = requestInput("Vehicle plate number");
        vehicleLastMaintenanceDate = requestDateInput("Vehicle's last maintenance date (format: dd-MM-yyyy)");
    }

    // Requests a string input from the user
    private String requestInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message + ": ");
        return input.nextLine();
    }

    // Requests a double input from the user
    private double requestDoubleInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message + ": ");
        return input.nextDouble();
    }

    // Requests an integer input from the user
    private int requestIntInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message + ": ");
        return input.nextInt();
    }

    // Requests a date input from the user
    private LocalDate requestDateInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message + ": ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }
}