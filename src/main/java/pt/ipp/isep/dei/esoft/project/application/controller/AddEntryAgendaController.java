package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Date;
import java.util.List;

public class AddEntryAgendaController {
    private ToDoList toDoList;

    private ToDoListRepository toDoListRepository;
    private OrganizationRepository organizationRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    public AddEntryAgendaController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public AddEntryAgendaController(OrganizationRepository organizationRepository,
                                      ToDoListRepository toDoListRepository,
                                      GreenSpaceRepository greenSpaceRepository,
                                      AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
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


    public List<TDLEntry> getToDoListEntries() {
        return toDoList.getEntries();
    }

    public void addEntryToAgenda(TDLEntry entry, GreenSpace greenSpace) {
        if (entry.addGreenSpace(greenSpace)) {
            toDoList.addEntry(entry);
        } else {
            throw new IllegalStateException("Entry already has an associated green space");
        }
    }

    public List<GreenSpace> getManagedGreenSpaces() {
        return greenSpaceRepository.getManagedGreenSpaces(toDoList.getGSM());
    }
}
