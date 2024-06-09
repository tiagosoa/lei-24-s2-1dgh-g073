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

/**
 * Controller class for consulting tasks.
 */
public class ConsultTasksController {
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;
    private TaskRepository taskRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes repositories.
     */
    public ConsultTasksController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.taskRepository = repositories.getTaskRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows injecting repositories for testing purposes.
     *
     * @param teamRepository          Team repository
     * @param collaboratorRepository  Collaborator repository
     * @param taskRepository          Task repository
     * @param authenticationRepository Authentication repository
     */
    public ConsultTasksController(TeamRepository teamRepository, CollaboratorRepository collaboratorRepository, TaskRepository taskRepository, AuthenticationRepository authenticationRepository) {
        this.teamRepository = teamRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.taskRepository = taskRepository;
        this.authenticationRepository = authenticationRepository;
    }

    // Getters for repositories

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
     * Retrieves the TaskRepository instance.
     *
     * @return TaskRepository instance
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

    /**
     * Retrieves the collaborator from the current user session.
     *
     * @return Collaborator object
     */
    public Collaborator getCollaboratorFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return collaboratorRepository.getCollaboratorByEmail(String.valueOf(email));
    }

    /**
     * Retrieves the team of a given collaborator.
     *
     * @param collaborator Collaborator object
     * @return Team object
     */
    public Team getCollaboratorTeam(Collaborator collaborator) {
        return teamRepository.getTeamByCollaborator(collaborator);
    }

    /**
     * Retrieves tasks for a specific team within a date range and optional status filter.
     *
     * @param team       Team object
     * @param firstDate  Start date of the range
     * @param secondDate End date of the range
     * @param status     Status filter (can be null)
     * @return List of Task objects
     */
    public List<Task> getTasksForCollaborator(Team team, LocalDate firstDate, LocalDate secondDate, String status) {
        List<Task> tasks = taskRepository.getTasksByTeamAndDateRange(team, firstDate, secondDate);
        if (status != null) {
            tasks = taskRepository.filterTasksByStatus(tasks, status);
        }
        return tasks;
    }

}