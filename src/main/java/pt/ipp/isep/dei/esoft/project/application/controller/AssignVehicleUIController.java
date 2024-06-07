package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class AssignVehicleUIController {
    @FXML
    private ComboBox<AgendaEntry> agendaComboBox;
    @FXML
    private ListView<Vehicle> vehicleListView;
    @FXML
    private Button assignButton;
    @FXML
    private Label resultLabel;

    private final AssignVehicleController controller;

    public AssignVehicleUIController() {
        controller = new AssignVehicleController();
    }

    @FXML
    public void initialize() {
        loadAgendaEntries();
        loadVehicles();
    }

    private void loadAgendaEntries() {
        List<AgendaEntry> entries = controller.getAgendaEntries();
        ObservableList<AgendaEntry> observableEntries = FXCollections.observableArrayList(entries);
        agendaComboBox.setItems(observableEntries);
    }

    private void loadVehicles() {
        List<Vehicle> vehicles = controller.getVehicleRepository().getVehicles();
        ObservableList<Vehicle> observableVehicles = FXCollections.observableArrayList(vehicles);
        vehicleListView.setItems(observableVehicles);
        vehicleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleAssignVehicles() {
        AgendaEntry selectedEntry = agendaComboBox.getValue();
        List<Vehicle> selectedVehicles = vehicleListView.getSelectionModel().getSelectedItems();

        if (selectedEntry != null && !selectedVehicles.isEmpty()) {
            boolean success = controller.assignVehicleToEntry(selectedEntry, selectedVehicles);
            if (success) {
                resultLabel.setText("Vehicles assigned successfully.");
            } else {
                resultLabel.setText("Operation unsuccessful.");
            }
        } else {
            resultLabel.setText("Please select an agenda entry and vehicles.");
        }
    }
}
