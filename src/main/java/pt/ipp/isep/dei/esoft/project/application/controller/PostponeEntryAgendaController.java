package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class responsible for handling the postponement of agenda entries.
 */
public class PostponeEntryAgendaController {
    private final AgendaRepository agendaRepository;
    private final TaskRepository taskRepository;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public PostponeEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.taskRepository = repositories.getTaskRepository();
    }

    /**
     * Constructor that allows injecting repositories for testing purposes.
     *
     * @param agendaRepository         the agenda repository
     * @param authenticationRepository the authentication repository
     */
    public PostponeEntryAgendaController(AgendaRepository agendaRepository,
                                         TaskRepository taskRepository,
                                         AuthenticationRepository authenticationRepository) {
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Getter for the agenda repository.
     *
     * @return the agenda repository
     */
    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    /**
     * Getter for the authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Retrieves the agenda associated with a given GSM.
     *
     * @param gsm the GSM identifier
     * @return the agenda
     */
    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    /**
     * Retrieves the GSM identifier from the current user session.
     *
     * @return the GSM identifier
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    /**
     * Retrieves the list of agenda entries associated with the current user session.
     *
     * @return the list of agenda entries
     */
    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }

    /**
     * Postpones the deadline of a given agenda entry to a new date.
     *
     * @param agendaEntry   the agenda entry to be postponed
     * @param postponeDate  the new date for the deadline
     * @return true if the postponement was successful, false otherwise
     */
    public boolean postponeEntry(AgendaEntry agendaEntry, LocalDate postponeDate) {
        if (postponeDate.isAfter(agendaEntry.getStartDate())) {
            agendaEntry.setDeadline(postponeDate);
            agendaRepository.updateAgendaEntryDate(agendaEntry);
            taskRepository.updateTaskDate(agendaEntry);
            return true;
        }
        return false;
    }
}