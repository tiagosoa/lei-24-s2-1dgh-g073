package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.esoft.project.application.controller.ListManagedGSController;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;

public class ListManagedGSUIController {


    @FXML
    private VBox greenSpacesContainer;

    private final ListManagedGSController controller;

    public ListManagedGSUIController() {
        this.controller = new ListManagedGSController();
    }

    @FXML
    public void initialize() {
        displayManagedGreenSpaces();
    }

    private void displayManagedGreenSpaces() {
        GSM gsm = controller.getGSMFromSession();
        GreenSpaceRepository greenSpaceRepository = controller.getGreenSpaceRepository();
        List<GreenSpace> managedGreenSpaces = greenSpaceRepository.getManagedGreenSpaces(gsm);

        managedGreenSpaces.sort((gs1, gs2) -> Double.compare(gs2.getArea(), gs1.getArea())); // Sort by area in descending order

        if (managedGreenSpaces.isEmpty()) {
            Label noGreenSpacesLabel = new Label("No green spaces managed by this GSM.");
            greenSpacesContainer.getChildren().add(noGreenSpacesLabel);
        } else {
            for (GreenSpace gs : managedGreenSpaces) {
                Label greenSpaceLabel = new Label(
                        "Name: " + gs.getName() +
                                ", Type: " + gs.getType() +
                                ", Area: " + gs.getArea() + " hectares"
                );
                greenSpacesContainer.getChildren().add(greenSpaceLabel);
            }
        }
    }
}
