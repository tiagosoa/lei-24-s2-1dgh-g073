package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AddEntryAgendaController {
    private final GreenSpaceRepository greenSpaceRepository;
    private final ToDoListRepository toDoListRepository;
    private final AgendaRepository agendaRepository;
    private final AuthenticationRepository authenticationRepository;

    public AddEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public AddEntryAgendaController(GreenSpaceRepository greenSpaceRepository,
                                    ToDoListRepository toDoListRepository,
                                    AgendaRepository agendaRepository,
                                    AuthenticationRepository authenticationRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.toDoListRepository = toDoListRepository;
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository;
    }

    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public Optional<AgendaEntry> addEntry(TDLEntry entry, String status, LocalDate startDate) {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        AgendaEntry agendaEntry = new AgendaEntry(
                entry.getTitle(),
                entry.getTaskDescription(),
                entry.getUrgency(),
                entry.getDuration(),
                status,
                startDate
        );
        agendaEntry.addGreenSpace(entry.getAssociatedGreenSpace());
        agenda.addEntry(agendaEntry);
        agendaRepository.addAgenda(agenda);
        return Optional.of(agendaEntry);
    }

    public ToDoList getToDoList(GSM gsm) {
        return toDoListRepository.getToDoListByGSM(gsm);
    }

    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    public List<TDLEntry> getToDoListEntries() {
        GSM gsm = getGSMFromSession();
        ToDoList toDoList = getToDoList(gsm);
        return toDoList.getEntries();
    }

    public List<GreenSpace> getManagedGreenSpaces() {
        return greenSpaceRepository.getGreenSpaceList();
    }

    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }

    public boolean postponeEntry(AgendaEntry entry, LocalDate newDeadline) {
        if (newDeadline.isAfter(entry.getDeadline())) {
            entry.setDeadline(newDeadline);
            agendaRepository.updateAgendaEntry(entry);
            return true;
        }
        return false;
    }

    public void cancelEntry(AgendaEntry entry) {
        Agenda agenda = getAgenda(getGSMFromSession());
        if (!agenda.cancelEntry(entry)) {
            throw new IllegalStateException("Failed to cancel entry. Entry may not exist.");
        }
        agendaRepository.updateAgenda(agenda);
    }

    public void assignVehicleToEntry(AgendaEntry entry, Vehicle vehicle) {
        entry.assignVehicle(vehicle);
        agendaRepository.updateAgendaEntry(entry);
    }
}



