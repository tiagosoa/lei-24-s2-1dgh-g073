package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.esoft.project.application.controller.ConsultTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class ConsultTasksUIController {

    @FXML
    private DatePicker firstDatePicker;

    @FXML
    private DatePicker secondDatePicker;

    @FXML
    private RadioButton statusAll;

    @FXML
    private RadioButton statusPlanned;

    @FXML
    private RadioButton statusPostponed;

    @FXML
    private RadioButton statusCancelled;

    @FXML
    private RadioButton statusDone;

    @FXML
    private ListView<String> tasksListView;

    private final ConsultTasksController controller;

    public ConsultTasksUIController() {
        controller = new ConsultTasksController();
    }

    @FXML
    private void initialize() {
        ToggleGroup statusGroup = new ToggleGroup();
        statusAll.setToggleGroup(statusGroup);
        statusPlanned.setToggleGroup(statusGroup);
        statusPostponed.setToggleGroup(statusGroup);
        statusCancelled.setToggleGroup(statusGroup);
        statusDone.setToggleGroup(statusGroup);
        statusAll.setSelected(true);
    }

    @FXML
    private void handleConsultTasks() {
        Collaborator collaborator = controller.getCollaboratorFromSession();
        Team team = controller.getCollaboratorTeam(collaborator);
        LocalDate firstDate = firstDatePicker.getValue();
        LocalDate secondDate = secondDatePicker.getValue();
        String status = getStatus();

        List<Task> tasks = controller.getTasksForCollaborator(team, firstDate, secondDate, status);
        tasksListView.getItems().clear();
        if (tasks == null || tasks.isEmpty()) {
            tasksListView.getItems().add("There are currently no tasks assigned to you in the inputted time frame.");
        } else {
            for (Task task : tasks) {
                tasksListView.getItems().add(task.getTitle() + ": " + task.getTaskDescription() + " - " + task.getStartDate() + " - Status: " + task.getStatus());
            }
        }
    }

    private String getStatus() {
        if (statusPlanned.isSelected()) {
            return "Planned";
        } else if (statusPostponed.isSelected()) {
            return "Postponed";
        } else if (statusCancelled.isSelected()) {
            return "Cancelled";
        } else if (statusDone.isSelected()) {
            return "Done";
        } else {
            return null;
        }
    }
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        App.showCollaboratorUI();
    }
}

