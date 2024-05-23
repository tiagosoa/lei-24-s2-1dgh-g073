package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {

    private final List<ToDoList> toDoLists;

    public ToDoListRepository() {
        this.toDoLists = new ArrayList<>();
    }

    public ToDoList getToDoListByGSM(GSM gsm) {
        for (ToDoList existingTDList : toDoLists) {
            if (existingTDList.getGSM().equals(gsm)) {
                return existingTDList;
            }
        }
        throw new IllegalArgumentException("Skill does not exist.");
    }

    public boolean addToDoList(ToDoList toDoList) {
        return toDoLists.add(toDoList);
    }

    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }
}

