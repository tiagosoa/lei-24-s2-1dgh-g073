package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

public class CollaboratorUIController {

    @FXML
    private Label welcomeLabel; // Reference to the welcome message label in the FXML

    private String collaboratorName; // Collaborator's name

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
