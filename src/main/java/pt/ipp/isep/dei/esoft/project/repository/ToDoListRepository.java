package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {

    private final List<ToDoEntry> toDoEntries;

    public ToDoListRepository() {
        this.toDoEntries = new ArrayList<>();
    }

    public boolean addToDoEntry(ToDoEntry entry) {
        return toDoEntries.add(entry);
    }

    public List<ToDoEntry> getToDoEntries() {
        return new ArrayList<>(toDoEntries);
    }
}

