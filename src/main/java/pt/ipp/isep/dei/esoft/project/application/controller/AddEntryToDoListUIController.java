package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;

import java.util.List;
import java.util.Optional;

public class AddEntryToDoListUIController {

    @FXML
    private TextField entryTitleField;

    @FXML
    private TextArea entryDescriptionField;

    @FXML
    private ComboBox<String> entryUrgencyComboBox;

    @FXML
    private TextField entryDurationField;

    @FXML
    private ComboBox<GreenSpace> greenSpaceComboBox;

    private final AddEntryToDoListController controller;

    public AddEntryToDoListUIController() {
        this.controller = new AddEntryToDoListController();
    }

    @FXML
    public void initialize() {
        List<GreenSpace> greenSpaces = controller.getGreenSpaceRepository().getGreenSpaceList();
        greenSpaceComboBox.setItems(FXCollections.observableArrayList(greenSpaces));
        greenSpaceComboBox.setCellFactory(param -> new ListCell<GreenSpace>() {
            @Override
            protected void updateItem(GreenSpace item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        greenSpaceComboBox.setButtonCell(new ListCell<GreenSpace>() {
            @Override
            protected void updateItem(GreenSpace item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }


    @FXML
    private void handleSubmit() {
        String entryTitle = entryTitleField.getText();
        String entryDescription = entryDescriptionField.getText();
        String entryUrgency = entryUrgencyComboBox.getValue();
        String entryDuration = entryDurationField.getText();
        GreenSpace associatedGreenSpace = greenSpaceComboBox.getValue();

        if (entryTitle.isEmpty() || entryDescription.isEmpty() || entryUrgency == null || entryDuration.isEmpty() || associatedGreenSpace == null) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        TDLEntry entry = controller.addEntry(entryTitle, entryDescription, entryUrgency, entryDuration);
        if (entry != null) {
            showAlert("Success", "Entry successfully added!");
        } else {
            showAlert("Failure", "Entry not added!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

