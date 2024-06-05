package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class RecordTaskController {
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;
    private AgendaRepository agendaRepository;
    private  TaskRepository taskRepository;
    private  AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public RecordTaskController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.taskRepository = repositories.getTaskRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }


    public RecordTaskController(TeamRepository teamRepository, CollaboratorRepository collaboratorRepository, AgendaRepository agendaRepository, TaskRepository taskRepository, AuthenticationRepository authenticationRepository) {
        this.teamRepository = teamRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.agendaRepository = agendaRepository;
        this.taskRepository = taskRepository;
        this.authenticationRepository = authenticationRepository;
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
     * Retrieves the AgendaRepository instance.
     *
     * @return AgendaRepository instance
     */
    public AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
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

    public void markTaskAsDone(Task task) {
        if ("Planned".equals(task.getStatus())) {
            task.setStatus("Done");
            taskRepository.updateTask(task);
        } else {
            throw new IllegalArgumentException("Only planned tasks can be marked as done.");
        }
    }

    public Collaborator getCollaboratorFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return collaboratorRepository.getCollaboratorByEmail(String.valueOf(email));
    }

    public Team getCollaboratorTeam(Collaborator collaborator) {
        return teamRepository.getTeamByCollaborator(collaborator);
    }
}
