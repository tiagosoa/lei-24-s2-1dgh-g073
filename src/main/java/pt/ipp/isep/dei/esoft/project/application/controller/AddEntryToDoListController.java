package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Controller class responsible for adding entries to a To-Do List.
 */
public class AddEntryToDoListController {
    private ToDoListRepository toDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public AddEntryToDoListController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows injecting repositories for testing purposes.
     *
     * @param toDoListRepository       the ToDoListRepository to use
     * @param greenSpaceRepository     the GreenSpaceRepository to use
     * @param authenticationRepository the AuthenticationRepository to use
     */
    public AddEntryToDoListController(ToDoListRepository toDoListRepository,
                                      GreenSpaceRepository greenSpaceRepository,
                                      AuthenticationRepository authenticationRepository) {
        this.toDoListRepository = toDoListRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.authenticationRepository = authenticationRepository;
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
     * Retrieves the AuthenticationRepository instance, initializing it if necessary.
     *
     * @return the AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Adds a new entry to the To-Do List.
     *
     * @param title          the title of the entry
     * @param taskDescription the description of the task
     * @param urgency        the urgency level of the task
     * @param duration       the estimated duration of the task
     * @return the created TDLEntry
     */
    public TDLEntry addEntry(String title, String taskDescription, String urgency, String duration) {
        TDLEntry entry = new TDLEntry(title, taskDescription, urgency, duration);
        ToDoList toDoList = toDoListRepository.getToDoListByGSM(getGSMFromSession());
        toDoList.addEntry(entry);
        return entry;
    }

    /**
     * Retrieves the GreenSpaceManager (GSM) associated with the current user session.
     *
     * @return the GSM associated with the current user session
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }
}