package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for adding entries to the agenda.
 */
public class AddEntryAgendaController {
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoListRepository toDoListRepository;
    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;
    private TaskRepository taskRepository;

    /**
     * Default constructor that initializes repositories.
     */
    public AddEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.taskRepository = repositories.getTaskRepository();
    }

    /**
     * Parameterized constructor to set repositories.
     */
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

    /**
     * Retrieves the TaskRepository instance, initializing it if necessary.
     *
     * @return the TaskRepository instance
     */
    public TaskRepository getTaskRepository() {
        return taskRepository == null ? Repositories.getInstance().getTaskRepository() : taskRepository;
    }

    /**
     * Retrieves the ToDoListRepository instance, initializing it if necessary.
     *
     * @return the ToDoListRepository instance
     */
    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository == null ? Repositories.getInstance().getToDoListRepository() : toDoListRepository;
    }

    /**
     * Retrieves the AgendaRepository instance, initializing it if necessary.
     *
     * @return the AgendaRepository instance
     */
    public AgendaRepository getAgendaRepository() {
        return agendaRepository == null ? Repositories.getInstance().getAgendaRepository() : agendaRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance, initializing it if necessary.
     *
     * @return the AuthenticationRepository instance
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Adds a new entry to the agenda based on a ToDoList entry.
     *
     * @param entry     the ToDoList entry to add to the agenda
     * @param status    the status of the entry
     * @param startDate the start date of the entry
     * @return an Optional containing the added AgendaEntry
     */
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
        taskRepository.addTask(agendaEntry);
        return Optional.of(agendaEntry);
    }

    /**
     * Retrieves the ToDoList associated with a Green Space Manager.
     *
     * @param gsm the Green Space Manager
     * @return the ToDoList associated with the Green Space Manager
     */
    public ToDoList getToDoList(GSM gsm) {
        return toDoListRepository.getToDoListByGSM(gsm);
    }

    /**
     * Retrieves the Agenda associated with a Green Space Manager.
     *
     * @param gsm the Green Space Manager
     * @return the Agenda associated with the Green Space Manager
     */
    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    /**
     * Retrieves the Green Space Manager from the current session.
     *
     * @return the Green Space Manager from the current session
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    /**
     * Retrieves the list of ToDoList entries associated with the Green Space Manager.
     *
     * @return the list of ToDoList entries
     */
    public List<TDLEntry> getToDoListEntries() {
        GSM gsm = getGSMFromSession();
        ToDoList toDoList = getToDoList(gsm);
        return toDoList.getEntries();
    }

    /**
     * Retrieves the list of Agenda entries associated with the Green Space Manager.
     *
     * @return the list of Agenda entries
     */
    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }
}