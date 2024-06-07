package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList toDoList;
    private TDLEntry entry1;
    private TDLEntry entry2;
    private GSM gsm;

    @BeforeEach
    public void setUp() {
        entry1 = new TDLEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks");
        entry2 = new TDLEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks");
        List<TDLEntry> entries = new ArrayList<>();
        entries.add(entry1);
        gsm = new GSM("gsm@gmail.pt");
        toDoList = new ToDoList(entries, gsm);
    }

    @Test
    public void ensureAddEntryWorks() {
        assertTrue(toDoList.addEntry(entry2));
        assertEquals(2, toDoList.getEntries().size());
        assertTrue(toDoList.getEntries().contains(entry2));
        assertFalse(toDoList.addEntry(entry2));
        assertEquals(2, toDoList.getEntries().size());
    }

    @Test
    public void ensureEqualsWorks() {
        List<TDLEntry> entries = new ArrayList<>();
        entries.add(entry1);
        ToDoList sameToDoList = new ToDoList(entries, gsm);

        List<TDLEntry> differentEntries = new ArrayList<>();
        differentEntries.add(entry2);
        ToDoList differentToDoList = new ToDoList(differentEntries, gsm);

        assertEquals(toDoList, sameToDoList);
        assertNotEquals(toDoList, differentToDoList);
        assertNotEquals(toDoList, null);
        assertNotEquals(toDoList, new Object());
    }

    @Test
    public void ensureHashCodeWorks() {
        List<TDLEntry> entries = new ArrayList<>();
        entries.add(entry1);
        ToDoList sameToDoList = new ToDoList(entries, gsm);

        assertEquals(toDoList.hashCode(), sameToDoList.hashCode());

        List<TDLEntry> differentEntries = new ArrayList<>();
        differentEntries.add(entry2);
        ToDoList differentToDoList = new ToDoList(differentEntries, gsm);

        assertNotEquals(toDoList.hashCode(), differentToDoList.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        ToDoList clonedToDoList = toDoList.clone();
        assertEquals(toDoList, clonedToDoList);
        assertNotSame(toDoList, clonedToDoList);
    }

    @Test
    public void ensureGetEntriesWorks() {
        List<TDLEntry> entries = toDoList.getEntries();
        assertEquals(1, entries.size());
        assertTrue(entries.contains(entry1));
    }

    @Test
    public void ensureGetGSMWorks() {
        assertEquals(gsm, toDoList.getGSM());
    }
}
