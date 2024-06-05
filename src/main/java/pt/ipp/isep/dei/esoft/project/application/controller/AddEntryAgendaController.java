package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AddEntryAgendaController {
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoListRepository toDoListRepository;
    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;

    private TaskRepository taskRepository;

    public AddEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.taskRepository = repositories.getTaskRepository();
    }

    public AddEntryAgendaController(GreenSpaceRepository greenSpaceRepository,
                                    ToDoListRepository toDoListRepository,
                                    AgendaRepository agendaRepository,
                                    AuthenticationRepository authenticationRepository,
                                    TaskRepository taskRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.toDoListRepository = toDoListRepository;
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves the GreenSpaceRepository instance, initializing it if necessary.
     *
     * @return the GreenSpaceRepository instance
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }
    public TaskRepository getTaskRepository() {
        return taskRepository == null ? Repositories.getInstance().getTaskRepository() : taskRepository;
    }

    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository == null ? Repositories.getInstance().getToDoListRepository() : toDoListRepository;
    }

    public AgendaRepository getAgendaRepository() {
        return agendaRepository == null ? Repositories.getInstance().getAgendaRepository() : agendaRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return AuthenticationRepository instance
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
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
        //agendaRepository.addAgenda(agenda);
        taskRepository.addTask(agendaEntry);
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

    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }
}



