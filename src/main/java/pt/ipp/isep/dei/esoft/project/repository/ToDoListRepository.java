package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {

    private final List<Entry> toDoEntries;

    public ToDoListRepository() {
        this.toDoEntries = new ArrayList<>();
    }

    public boolean addToDoEntry(Entry entry) {
        return toDoEntries.add(entry);
    }

    public List<Entry> getToDoEntries() {
        return new ArrayList<>(toDoEntries);
    }
}

