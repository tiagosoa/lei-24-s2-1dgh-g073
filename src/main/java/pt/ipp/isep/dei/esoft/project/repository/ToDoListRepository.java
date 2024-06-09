package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing ToDoList objects.
 */
public class ToDoListRepository {

    private final List<ToDoList> toDoLists;

    /**
     * Constructor for ToDoListRepository.
     */
    public ToDoListRepository() {
        this.toDoLists = new ArrayList<>();
    }

    /**
     * Retrieves a ToDoList based on the associated GSM.
     *
     * @param gsm the GSM associated with the ToDoList
     * @return the ToDoList with the specified GSM
     * @throws IllegalArgumentException if no ToDoList is found with the given GSM
     */
    public ToDoList getToDoListByGSM(GSM gsm) {
        for (ToDoList existingTDList : toDoLists) {
            if (existingTDList.getGSM().equals(gsm)) {
                return existingTDList;
            }
        }
        throw new IllegalArgumentException("Skill does not exist.");
    }

    /**
     * Adds a new ToDoList to the repository.
     *
     * @param toDoList the ToDoList to add
     * @return true if the ToDoList was successfully added, false otherwise
     */
    public boolean addToDoList(ToDoList toDoList) {
        return toDoLists.add(toDoList);
    }

    /**
     * Retrieves all ToDoLists stored in the repository.
     *
     * @return a list of all ToDoLists in the repository
     */
    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }
}