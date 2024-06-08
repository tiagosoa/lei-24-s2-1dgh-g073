package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.*;

import java.io.IOException;


public class CollaboratorUIController {

    @FXML
    private TextField greenSpaceNameField;

    @FXML
    private ComboBox<String> greenSpaceTypeComboBox;

    @FXML
    private TextField greenSpaceAreaField;


    @FXML
    private void handleConsultTasks(ActionEvent event) {
        Stage stage = new Stage();
        App app = new App();
        app.showConsultTasksUI(stage);
    }


    @FXML
    private void handleRecordTask(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showRecordTaskUI(stage);
    }

}