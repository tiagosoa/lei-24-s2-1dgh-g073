package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ConsultTasksController {
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;
    private TaskRepository taskRepository;
    private AuthenticationRepository authenticationRepository;

    public ConsultTasksController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.taskRepository = repositories.getTaskRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public ConsultTasksController(TeamRepository teamRepository, CollaboratorRepository collaboratorRepository, TaskRepository taskRepository, AuthenticationRepository authenticationRepository) {
        this.teamRepository = teamRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.taskRepository = taskRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     *
     * @return CollaboratorRepository instance
     */
    public CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the TeamRepository instance.
     *
     * @return TeamRepository instance
     */
    public TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Retrieves the TeamRepository instance.
     *
     * @return TeamRepository instance
     */
    public TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();
            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Collaborator getCollaboratorFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return collaboratorRepository.getCollaboratorByEmail(String.valueOf(email));
    }

    public Team getCollaboratorTeam(Collaborator collaborator) {
        return teamRepository.getTeamByCollaborator(collaborator);
    }

    public List<Task> getTasksForCollaborator(Team team, LocalDate firstDate, LocalDate secondDate, String status) {
        List<Task> tasks = taskRepository.getTasksByTeamAndDateRange(team, firstDate, secondDate);
        if (status != null) {
            tasks = taskRepository.filterTasksByStatus(tasks, status);
        }
        return tasks;
    }

}