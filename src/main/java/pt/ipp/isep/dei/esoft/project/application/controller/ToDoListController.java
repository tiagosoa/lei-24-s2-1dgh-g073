package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ToDoListController {

    private final ToDoListRepository toDoListRepository;

    public ToDoListController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
    }

    public ToDoListController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public ToDoEntry addToDoEntry(String description, GreenSpace greenSpace) {
        ToDoEntry entry = new ToDoEntry(description, greenSpace);
        toDoListRepository.addToDoEntry(entry);
        return entry;
    }

    public List<ToDoEntry> getToDoEntries() {
        return toDoListRepository.getToDoEntries();
    }
}
