package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.ui.gui.App;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;

public class AddEntryAgendaUIController {

    @FXML
    private ComboBox<TDLEntry> toDoListEntryComboBox;

    @FXML
    private DatePicker entryStartDatePicker;

    private final AddEntryAgendaController controller;

    public AddEntryAgendaUIController() {
        this.controller = new AddEntryAgendaController();
    }

    @FXML
    public void initialize() {
        List<TDLEntry> toDoListEntries = controller.getToDoListEntries();
        toDoListEntryComboBox.setItems(FXCollections.observableArrayList(toDoListEntries));
        toDoListEntryComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<TDLEntry> call(ListView<TDLEntry> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(TDLEntry item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getTitle()); // Assuming TDLEntry has a getTitle() method
                        }
                    }
                };
            }
        });

        toDoListEntryComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(TDLEntry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle()); // Assuming TDLEntry has a getTitle() method
                }
            }
        });

        // Disable past dates
        entryStartDatePicker.setDayCellFactory(getDayCellFactory());

        // Ensure manually entered dates are validated
        entryStartDatePicker.setOnAction(event -> validateDate());
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EEEEEE;");
                        }
                    }
                };
            }
        };
    }

    private void validateDate() {
        LocalDate selectedDate = entryStartDatePicker.getValue();
        if (selectedDate != null && selectedDate.isBefore(LocalDate.now())) {
            entryStartDatePicker.setValue(null);
            showAlert("Input Error", "Start date cannot be in the past.");
        }
    }

    @FXML
    private void handleSubmit() {
        TDLEntry toDoListEntry = toDoListEntryComboBox.getValue();
        LocalDate entryStartDate = entryStartDatePicker.getValue();

        if (toDoListEntry == null || entryStartDate == null) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        if (entryStartDate.isBefore(LocalDate.now())) {
            showAlert("Input Error", "Start date cannot be in the past.");
            return;
        }

        String entryStatus = "Planned";

        if (controller.addEntry(toDoListEntry, entryStatus, entryStartDate).isPresent()) {
            showAlert("Success", "Entry added to agenda successfully!");
        } else {
            showAlert("Failure", "Failed to add entry to the agenda.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        App.showGSMUI();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
