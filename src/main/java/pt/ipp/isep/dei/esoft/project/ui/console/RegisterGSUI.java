package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGSController;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.Optional;
import java.util.Scanner;

/**
 * Register Green Space UI (console). This class provides a console interface for registering a new green space.
 */
public class RegisterGSUI implements Runnable {

    private final RegisterGSController controller;
    private String greenSpaceType;
    private double greenSpaceArea;
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructor for RegisterGSUI.
     */
    public RegisterGSUI() {
        controller = new RegisterGSController();
        this.greenSpaceRepository = getController().getGreenSpaceRepository();
    }

    /**
     * Get the RegisterGSController associated with this UI.
     *
     * @return the RegisterGSController
     */
    private RegisterGSController getController() {
        return controller;
    }

    /**
     * Run the RegisterGS UI.
     */
    public void run() {
        System.out.println("\n\n--- Register Green Space ------------------------");

        requestData();

        submitData();
    }

    /**
     * Submit the data entered by the user to register a new green space.
     */
    private void submitData() {

        GSM gsm = getController().getGSMFromSession();
        Optional<GreenSpace> greenSpace = getController().registerGreenSpace(greenSpaceType, greenSpaceArea, gsm);

        if (greenSpace.isPresent()) {
            greenSpaceRepository.addGreenSpace(greenSpace.get());
            System.out.println("\nGreen Space successfully registered!");
        } else {
            System.out.println("\nGreen Space not registered!");
        }
    }

    /**
     * Request data from the user to register a new green space.
     */
    private void requestData() {
        //Request the GS data from the console
        greenSpaceType = requestGSType();
        greenSpaceArea = requestGSArea();
    }

    /**
     * Request the Green Space Type from the user via console input.
     *
     * @return the Green Space Type selected by the user
     */
    private String requestGSType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the type of green space:");
        System.out.println("1. Garden");
        System.out.println("2. Medium-sized Park");
        System.out.println("3. Large-sized Park");
        System.out.print("Enter your choice: ");
        int type = scanner.nextInt();

        // Validate input and assign the type accordingly
        String greenSpaceType = null;
        switch (type) {
            case 1:
                greenSpaceType = "Garden";
                break;
            case 2:
                greenSpaceType = "Medium-sized Park";
                break;
            case 3:
                greenSpaceType = "Large-sized Park";
                break;
            default:
                System.out.println("Invalid choice. Exiting...");

        }
        return greenSpaceType;
    }
    private double requestGSArea() {
        Scanner input = new Scanner(System.in);
        System.out.print("Green Space area: ");
        double area = 0;
        try {
            area = input.nextDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return area;
    }
}