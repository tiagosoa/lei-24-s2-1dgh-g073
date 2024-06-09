package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class responsible for handling the cancellation of agenda entries.
 */
public class CancelEntryAgendaController {
    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public CancelEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows injection of repositories for testing purposes.
     *
     * @param agendaRepository         Agenda repository
     * @param authenticationRepository Authentication repository
     */
    public CancelEntryAgendaController(AgendaRepository agendaRepository,
                                       AuthenticationRepository authenticationRepository) {
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the AgendaRepository instance.
     *
     * @return AgendaRepository instance
     */
    public AgendaRepository getAgendaRepository() {
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

    /**
     * Retrieves the agenda associated with a given GSM.
     *
     * @param gsm GSM identifier
     * @return Agenda associated with the GSM
     */
    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    /**
     * Retrieves the GSM identifier from the current user session.
     *
     * @return GSM identifier
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    /**
     * Retrieves the list of agenda entries associated with the current user session.
     *
     * @return List of agenda entries
     */
    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }

    /**
     * Cancels a specific agenda entry.
     *
     * @param entry Agenda entry to be canceled
     */
    public void cancelEntry(AgendaEntry entry) {
        Agenda agenda = getAgenda(getGSMFromSession());
        if (!agenda.cancelEntry(entry)) {
            throw new IllegalStateException("Failed to cancel entry. Entry may not exist.");
        }
        agendaRepository.cancelEntry(entry);
    }
}