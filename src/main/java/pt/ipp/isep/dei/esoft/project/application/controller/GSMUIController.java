package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.*;

import java.io.IOException;


public class GSMUIController {

    @FXML
    private TextField greenSpaceNameField;

    @FXML
    private ComboBox<String> greenSpaceTypeComboBox;

    @FXML
    private TextField greenSpaceAreaField;


    @FXML
    private void handleRegisterGS(ActionEvent event) {
        Stage stage = new Stage();
        App app = new App();
        app.showRegisterGSUI(stage);
    }


    @FXML
    private void handleAddEntryToDoList(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showAddEntryToDoListUI(stage);

    }

    @FXML
    private void handleAddEntryAgenda(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showAddEntryAgendaUI(stage);
    }

    @FXML
    private void handleAssignTeam(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showAssignTeamUI(stage);
    }

    @FXML
    private void handlePostponeEntry(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showPostponeEntryAgendaUI(stage);
    }

    @FXML
    private void handleCancelEntry(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        App app = new App();
        app.showCancelEntryAgendaUI(stage);
    }

    @FXML
    private void handleAssignVehicle(ActionEvent event) {
        new AssignVehicleUI().run();
    }

    @FXML
    private void handleListManagedGS(ActionEvent event) {
        new ListManagedGSUI().run();
    }
}
