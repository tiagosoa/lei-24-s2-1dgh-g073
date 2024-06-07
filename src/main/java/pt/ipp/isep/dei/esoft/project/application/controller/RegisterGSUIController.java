package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGSController;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.Optional;


public class RegisterGSUIController {

    @FXML
    private TextField greenSpaceNameField;

    @FXML
    private ComboBox<String> greenSpaceTypeComboBox;

    @FXML
    private TextField greenSpaceAreaField;

    private final RegisterGSController controller;

    public RegisterGSUIController() {
        this.controller = new RegisterGSController();
    }

    @FXML
    private void handleSubmit() {
        String greenSpaceName = greenSpaceNameField.getText();
        String greenSpaceType = greenSpaceTypeComboBox.getValue();
        double greenSpaceArea;

        try {
            greenSpaceArea = Double.parseDouble(greenSpaceAreaField.getText().replace(',', '.'));
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for the area.");
            return;
        }

        GSM gsm = controller.getGSMFromSession();
        Optional<GreenSpace> greenSpace = controller.registerGreenSpace(greenSpaceName, greenSpaceType, greenSpaceArea, gsm);

        if (greenSpace.isPresent()) {
            showAlert("Success", "Green Space successfully registered!");
        } else {
            showAlert("Failure", "Green Space not registered!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

