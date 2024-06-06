package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.esoft.project.ui.console.*;

public class GSMUIController {

    @FXML
    private void handleRegisterGS(ActionEvent event) {
        new RegisterGSUI().run();
    }

    @FXML
    private void handleAddEntryToDoList(ActionEvent event) {
        new AddEntryToDoListUI().run();
    }

    @FXML
    private void handleAddEntryAgenda(ActionEvent event) {
        new AddEntryAgendaUI().run();
    }

    @FXML
    private void handleAssignTeam(ActionEvent event) {
        new AssignTeamUI().run();
    }

    @FXML
    private void handlePostponeEntry(ActionEvent event) {
        new PostponeEntryAgendaUI().run();
    }

    @FXML
    private void handleCancelEntry(ActionEvent event) {
        new CancelEntryAgendaUI().run();
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
