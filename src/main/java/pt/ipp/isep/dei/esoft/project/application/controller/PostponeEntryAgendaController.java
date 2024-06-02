package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;

public class PostponeEntryAgendaController {
    private final AgendaRepository agendaRepository;
    private final AuthenticationRepository authenticationRepository;

    public PostponeEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public PostponeEntryAgendaController(AgendaRepository agendaRepository,
                                         AuthenticationRepository authenticationRepository) {
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }


    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
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

    public boolean postponeEntry(AgendaEntry agendaEntry, LocalDate postponeDate) {
        if (postponeDate.isAfter(agendaEntry.getStartDate())) {
            agendaEntry.setDeadline(postponeDate);
            agendaRepository.updateAgendaEntryDate(agendaEntry);
            return true;
        }
        return false;
    }
}



