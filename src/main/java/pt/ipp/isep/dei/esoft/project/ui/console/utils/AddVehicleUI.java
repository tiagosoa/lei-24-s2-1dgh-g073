package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.AddVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VFM;

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
    private String taskCategoryDescription;
    private String employeeEmail;

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
        Optional<Vehicle> vehicle = getController().addVehicle(vehicleModel,vehicleBrand,vehicleType, vfm);

        if (vehicle.isPresent()) {
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
        }
    }

    private void requestData() {

        //Request the Job name from the console
        vehicleModel = requestVehicleModel();
        vehicleBrand = requestVehicleBrand();
        vehicleType = requestVehicleType();

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
}
