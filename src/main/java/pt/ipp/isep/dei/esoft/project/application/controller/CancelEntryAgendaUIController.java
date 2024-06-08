package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

import java.io.IOException;
import java.util.List;

public class CancelEntryAgendaUIController {
    @FXML
    private ComboBox<AgendaEntry> agendaEntryComboBox;

    private final CancelEntryAgendaController controller = new CancelEntryAgendaController();

    @FXML
    public void initialize() {
        populateAgendaEntryComboBox();
    }

    private void populateAgendaEntryComboBox() {
        List<AgendaEntry> entries = controller.getAgendaEntries();
        agendaEntryComboBox.getItems().addAll(entries);
    }

    @FXML
    public void handleCancelEntry() {
        AgendaEntry agendaEntry = agendaEntryComboBox.getValue();
        if (agendaEntry != null) {
            try {
                controller.cancelEntry(agendaEntry);
                showAlert("Success", "Entry cancelled successfully!");
            } catch (IllegalStateException e) {
                showAlert("Error", e.getMessage());
            }
        } else {
            showAlert("Error", "Please select an agenda entry to cancel.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        App.showGSMUI();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
