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

public class CancelEntryAgendaController {
    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;

    public CancelEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public CancelEntryAgendaController(AgendaRepository agendaRepository,
                                       AuthenticationRepository authenticationRepository) {
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }


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



    public void cancelEntry(AgendaEntry entry) {
        Agenda agenda = getAgenda(getGSMFromSession());
        if (!agenda.cancelEntry(entry)) {
            throw new IllegalStateException("Failed to cancel entry. Entry may not exist.");
        }
        agendaRepository.cancelEntry(entry);
    }
}



