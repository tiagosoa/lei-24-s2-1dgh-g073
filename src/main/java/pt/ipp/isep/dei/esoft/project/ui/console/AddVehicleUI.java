package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VFM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

/**
 * Create Job UI (console). This option is only available for administrators for demonstration purposes.
 */
public class AddVehicleUI implements Runnable {

    private final AddVehicleController controller;
    private String vehicleModel;

    private String vehicleBrand;

    private String vehicleType;
    private double vehicleTareWeight;
    private double vehicleGrossWeight;
    private double vehicleCurrentKm;
    private LocalDate vehicleRegisterDate;
    private LocalDate vehicleAcquisitionDate;
    private int vehicleMaintenanceFrequencyKm;
    private String vehiclePlateNumber;
    private LocalDate vehicleLastMaintenanceDate;

    public AddVehicleUI() {
        controller = new AddVehicleController();
    }

    private AddVehicleController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Add Vehicle ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        VFM vfm = getController().getVFMFromSession();
        Optional<Vehicle> vehicle = getController().addVehicle(vehicleModel,vehicleBrand,vehicleType, vehicleTareWeight, vehicleGrossWeight, vehicleCurrentKm, vehicleRegisterDate, vehicleAcquisitionDate, vehicleMaintenanceFrequencyKm, vehiclePlateNumber, vehicleLastMaintenanceDate, vfm);

        if (vehicle.isPresent()) {
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
        }
    }

    private void requestData() {

        //Request the Vehicle Data from the console
        vehicleModel = requestVehicleModel();
        vehicleBrand = requestVehicleBrand();
        vehicleType = requestVehicleType();
        vehicleTareWeight = requestVehicleTareWeight();
        vehicleGrossWeight = requestVehicleGrossWeight();
        vehicleCurrentKm = requestVehicleCurrentKm();
        vehicleRegisterDate = requestVehicleRegisterDate();
        vehicleAcquisitionDate = requestVehicleAcquisitionDate();
        vehicleMaintenanceFrequencyKm = requestVehicleMaintenanceFrequencyKM();
        vehiclePlateNumber = requestVehiclePlateNumber();
        vehicleLastMaintenanceDate = requestVehicleLastMaintenanceDate();
    }




    private String requestVehicleModel() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle model: ");
        return input.nextLine();
    }
    private String requestVehicleBrand() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle brand: ");
        return input.nextLine();
    }
    private String requestVehicleType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle type: ");
        return input.nextLine();
    }

    private double requestVehicleTareWeight() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Tare Weight (in kg): ");
        return input.nextDouble();
    }

    private double requestVehicleGrossWeight() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Gross Weight (in kg): ");
        return input.nextDouble();
    }

    private double requestVehicleCurrentKm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Current Km: ");
        return input.nextDouble();
    }

    private LocalDate requestVehicleRegisterDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Register Date (format: dd-MM-yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }

    private LocalDate requestVehicleAcquisitionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Acquisition Date (format: dd-MM-yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }
    private int requestVehicleMaintenanceFrequencyKM() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Maintenance/Check-up Frequency (in kms): ");
        return input.nextInt();
    }

    private String requestVehiclePlateNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle plate number: ");
        return input.nextLine();
    }

    private LocalDate requestVehicleLastMaintenanceDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle's last maintenance date (format: dd-MM-yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input.nextLine(), formatter);
    }
}
