package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

public class AddEntryToDoListController {
    private ToDoListRepository toDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    public AddEntryToDoListController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public AddEntryToDoListController(ToDoListRepository toDoListRepository,
                                      GreenSpaceRepository greenSpaceRepository,
                                      AuthenticationRepository authenticationRepository) {
        this.toDoListRepository = toDoListRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.authenticationRepository = authenticationRepository;
    }

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

    public TDLEntry addEntry(String title, String taskDescription, String urgency, String duration) {
        TDLEntry entry = new TDLEntry(title, taskDescription, urgency, duration);
        ToDoList toDoList = toDoListRepository.getToDoListByGSM(getGSMFromSession());
        toDoList.addEntry(entry);
        return entry;
    }

    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }
}
