package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

public class AssignTeamUIController {

    @FXML
    private ComboBox<AgendaEntry> agendaEntryComboBox;

    @FXML
    private ComboBox<Team> teamComboBox;

    @FXML
    private TextArea teamDetailsTextArea;

    private final AssignTeamController controller;

    public AssignTeamUIController() {
        this.controller = new AssignTeamController();
    }

    @FXML
    public void initialize() {
        populateAgendaEntries();
        populateTeams();

        teamComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Team> call(javafx.scene.control.ListView<Team> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Team item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getTeamID() + " - " + item.listMembers());
                        }
                    }
                };
            }
        });

        teamComboBox.setOnAction(event -> {
            Team selectedTeam = teamComboBox.getValue();
            if (selectedTeam != null) {
                StringBuilder teamDetails = new StringBuilder("Team ID: " + selectedTeam.getTeamID() + "\n");
                teamDetails.append("Collaborators:\n");
                for (Collaborator collaborator : selectedTeam.getTeam()) {
                    teamDetails.append(collaborator.getName()).append("\n");
                }
                teamDetailsTextArea.setText(teamDetails.toString());
            } else {
                teamDetailsTextArea.clear();
            }
        });
    }


    private void populateAgendaEntries() {
        agendaEntryComboBox.setItems(FXCollections.observableArrayList(controller.getAgendaEntries()));
    }

    private void populateTeams() {
        TeamRepository teamRepository = controller.getTeamRepository();
        List<Team> teams = teamRepository.getTeamList();
        teamComboBox.setItems(FXCollections.observableArrayList(teams));
    }

    @FXML
    private void handleSubmit() {
        AgendaEntry agendaEntry = agendaEntryComboBox.getValue();
        Team team = teamComboBox.getValue();

        if (agendaEntry == null || team == null) {
            showAlert("Input Error", "Please select both an agenda entry and a team.");
            return;
        }

        Optional<AgendaEntry> check = controller.assignTeam(agendaEntry, team);
        if (check.isPresent()) {
            showAlert("Success", "Team assigned successfully! Sending notifications...");
            sendNotifications(team);
        } else {
            showAlert("Failure", "Failed to assign team to the agenda entry.");
        }
    }

    private void sendNotifications(Team team) {
        // Implement sending notifications
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
