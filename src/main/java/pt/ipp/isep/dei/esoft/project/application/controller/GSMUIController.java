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
    private void handleRegisterGS(ActionEvent event) throws IOException {
        App.showRegisterGSUI();
    }


    @FXML
    private void handleAddEntryToDoList(ActionEvent event) throws IOException {
        App.showAddEntryToDoListUI();

    }

    @FXML
    private void handleAddEntryAgenda(ActionEvent event) throws IOException {
        App.showAddEntryAgendaUI();
    }

    @FXML
    private void handleAssignTeam(ActionEvent event) throws IOException {
        App.showAssignTeamUI();
    }

    @FXML
    private void handlePostponeEntry(ActionEvent event) throws IOException {
        App.showPostponeEntryAgendaUI();
    }

    @FXML
    private void handleCancelEntry(ActionEvent event) throws IOException {
        App.showCancelEntryAgendaUI();
    }

    @FXML
    private void handleAssignVehicle(ActionEvent event) throws IOException {
        App.showAssignVehicleUI();
    }

    @FXML
    private void handleListManagedGS(ActionEvent event) {
        try {
            Stage stage = new Stage();
            App app = new App();
            app.showListManagedGSUI(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLogout(ActionEvent event) throws Exception {
        App.showLoginUI();
    }

}