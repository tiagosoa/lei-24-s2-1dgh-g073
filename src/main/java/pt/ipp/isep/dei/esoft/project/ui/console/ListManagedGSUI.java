package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListManagedGSController;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;

/**
 * User Interface for listing managed Green Spaces.
 */
public class ListManagedGSUI implements Runnable {
    private final ListManagedGSController controller;
    private GSM gsm;
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructor that initializes the controller and retrieves the GreenSpaceRepository.
     */
    public ListManagedGSUI() {
        controller = new ListManagedGSController();
        this.greenSpaceRepository = getController().getGreenSpaceRepository();
    }

    private ListManagedGSController getController() {
        return controller;
    }

    /**
     * Executes the UI functionality.
     */
    public void run() {
        displayManagedGreenSpaces();
    }

    /**
     * Displays the Green Spaces managed by the logged-in GSM.
     */
    public void displayManagedGreenSpaces() {
        gsm = getController().getGSMFromSession();
        List<GreenSpace> managedGreenSpaces = greenSpaceRepository.getManagedGreenSpaces(gsm);
        if (managedGreenSpaces.isEmpty()) {
            System.out.println("No green spaces managed by this GSM.");
        } else {
            System.out.println("Green Spaces managed by " + gsm.getEmail() + ":");
            for (GreenSpace gs : managedGreenSpaces) {
                System.out.println("Name: " + gs.getName() + ", Type: " + gs.getType() + ", Area: " + gs.getArea() + " hectares");
            }
        }
    }
}