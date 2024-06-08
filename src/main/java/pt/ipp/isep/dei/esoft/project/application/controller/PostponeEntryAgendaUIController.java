package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostponeEntryAgendaUIController {
    @FXML
    private ComboBox<AgendaEntry> agendaEntryComboBox;

    @FXML
    private DatePicker newStartDatePicker;

    private final PostponeEntryAgendaController controller = new PostponeEntryAgendaController();

    @FXML
    public void initialize() {
        populateAgendaEntryComboBox();
    }

    private void populateAgendaEntryComboBox() {
        List<AgendaEntry> entries = controller.getAgendaEntries();
        agendaEntryComboBox.getItems().addAll(entries);
    }

    @FXML
    public void handleSubmit() {
        AgendaEntry agendaEntry = agendaEntryComboBox.getValue();
        LocalDate newStartDate = newStartDatePicker.getValue();
        if (agendaEntry != null && newStartDate != null) {
            boolean success = controller.postponeEntry(agendaEntry, newStartDate);
            if (success) {
                showAlert("Success", "Entry postponed successfully!");
            } else {
                showAlert("Error", "New deadline must be later than the current deadline.");
            }
        } else {
            showAlert("Error", "Please select an agenda entry and enter a valid start date.");
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
