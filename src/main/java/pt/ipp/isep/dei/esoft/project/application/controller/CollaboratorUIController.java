package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

public class CollaboratorUIController {

    @FXML
    private TextField greenSpaceNameField;

    @FXML
    private ComboBox<String> greenSpaceTypeComboBox;

    @FXML
    private TextField greenSpaceAreaField;

    @FXML
    private void handleConsultTasks(ActionEvent event) throws Exception {
        App.showConsultTasksUI();
    }

    @FXML
    private void handleRecordTask(ActionEvent event) throws Exception {
        App.showRecordTaskUI();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws Exception {
        App.showLoginUI();
    }
}
