package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListRepositoryTest {

    private ToDoListRepository toDoListRepository;
    private ToDoList toDoList;
    private TDLEntry entry1;
    private TDLEntry entry2;
    private GSM gsm;

    @BeforeEach
    void setUp() {
        toDoListRepository = new ToDoListRepository();

        entry1 = new TDLEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks");
        entry2 = new TDLEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks");

        List<TDLEntry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);

        gsm = new GSM("gsm@gmail.pt");
        toDoList = new ToDoList(entries, gsm);
    }

    @Test
    void ensureAddToDoListWorks() {
        assertTrue(toDoListRepository.addToDoList(toDoList));
        assertEquals(1, toDoListRepository.getToDoLists().size());
    }

    @Test
    void ensureGetToDoListByGSMWorks() {
        toDoListRepository.addToDoList(toDoList);
        ToDoList retrievedToDoList = toDoListRepository.getToDoListByGSM(gsm);
        assertEquals(toDoList, retrievedToDoList);
    }

    @Test
    void ensureGetToDoListByGSMFailsIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> toDoListRepository.getToDoListByGSM(gsm));
    }

    @Test
    void ensureGetToDoListsWorks() {
        toDoListRepository.addToDoList(toDoList);
        List<ToDoList> toDoLists = toDoListRepository.getToDoLists();
        assertNotNull(toDoLists);
        assertEquals(1, toDoLists.size());
        assertEquals(toDoList, toDoLists.get(0));
    }
}
