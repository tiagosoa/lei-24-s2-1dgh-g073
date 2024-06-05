package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for assigning skills to collaborators.
 */
public class AssignTeamController {

    private TeamRepository teamRepository;
    private AgendaRepository agendaRepository;
    private TaskRepository taskRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public AssignTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.taskRepository = repositories.getTaskRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows passing repositories for testing purposes.
     *
     * @param teamRepository            Team repository
     * @param agendaRepository     Agenda repository
     * @param taskRepository     Task repository
     * @param authenticationRepository    Authentication repository
     */
    public AssignTeamController(TeamRepository teamRepository,
                                AgendaRepository agendaRepository,
                                TaskRepository taskRepository,
                                AuthenticationRepository authenticationRepository) {
        this.teamRepository = teamRepository;
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

    public Optional<AgendaEntry> assignTeam(AgendaEntry entry, Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team not found.");
        }
        entry.addTeam(team);
        taskRepository.assignTeam(entry, team);
        return Optional.of(entry);
    }

    /**
     * Retrieves a list of teams.
     *
     * @return List of teams
     */
    public List<Team> getTeamList() {
        return teamRepository.getTeamList();
    }

    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }
}