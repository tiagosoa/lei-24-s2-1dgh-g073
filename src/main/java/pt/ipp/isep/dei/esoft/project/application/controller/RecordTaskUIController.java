package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.RecordTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.List;

public class RecordTaskUIController {

    @FXML
    private ListView<String> tasksListView;

    private final RecordTaskController controller;
    private List<Task> collaboratorTasks;
    private Task selectedTask;

    public RecordTaskUIController() {
        controller = new RecordTaskController();
    }

    @FXML
    private void initialize() {
        requestData();
        displayTasks();
    }

    private void requestData() {
        Collaborator collaborator = controller.getCollaboratorFromSession();
        Team team = controller.getCollaboratorTeam(collaborator);
        collaboratorTasks = controller.getTaskRepository().getTasksByTeam(team);
    }

    private void displayTasks() {
        if (collaboratorTasks == null || collaboratorTasks.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Tasks");
            alert.setHeaderText(null);
            alert.setContentText("There are no tasks assigned to you with the 'Planned' status.");
            alert.showAndWait();
            return;
        }
        for (Task task : collaboratorTasks) {
            tasksListView.getItems().add(task.getTitle() + " - " + task.getStatus());
        }
        tasksListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void handleMarkAsDone() {
        int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < collaboratorTasks.size()) {
            selectedTask = collaboratorTasks.get(selectedIndex);
            controller.markTaskAsDone(selectedTask);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Task Updated");
            alert.setHeaderText(null);
            alert.setContentText("Task marked as done successfully.");
            alert.showAndWait();
            tasksListView.getItems().set(selectedIndex, selectedTask.getTitle() + " - " + selectedTask.getStatus());
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a valid task.");
            alert.showAndWait();
        }
    }
}
