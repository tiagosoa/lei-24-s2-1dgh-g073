package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TDLEntryTest {
    private GSM gsm;
    private TDLEntry entry;
    private GreenSpace greenSpace;

    @BeforeEach
    public void setUp() {
        gsm = new GSM("gsm@gmail.pt");
        entry = new TDLEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks");
        greenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
    }

    @Test
    public void ensureAddGreenSpaceWorks() {
        assertTrue(entry.addGreenSpace(greenSpace));
        assertEquals(greenSpace, entry.getAssociatedGreenSpace());

        GreenSpace anotherGreenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
        assertFalse(entry.addGreenSpace(anotherGreenSpace));
        assertEquals(greenSpace, entry.getAssociatedGreenSpace());
    }

    @Test
    public void ensureGetAssociatedGreenSpaceWorks() {
        assertNull(entry.getAssociatedGreenSpace());
        entry.addGreenSpace(greenSpace);
        assertEquals(greenSpace, entry.getAssociatedGreenSpace());
    }

    @Test
    public void ensureGetTitleWorks() {
        assertEquals("Podar árvores", entry.getTitle());
    }

    @Test
    public void ensureGetTaskDescriptionWorks() {
        assertEquals("Podar todas as árvores no parque.", entry.getTaskDescription());
    }

    @Test
    public void ensureGetUrgencyWorks() {
        assertEquals("Medium", entry.getUrgency());
    }

    @Test
    public void ensureGetDurationWorks() {
        assertEquals("3 weeks", entry.getDuration());
    }

    @Test
    public void ensureEqualsWorks() {
        TDLEntry sameEntry = new TDLEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks");
        TDLEntry differentEntry = new TDLEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks");

        assertEquals(entry, sameEntry);
        assertNotEquals(entry, differentEntry);
        assertNotEquals(entry, null);
        assertNotEquals(entry, new Object());
    }

    @Test
    public void ensureHashCodeWorks() {
        TDLEntry sameEntry = new TDLEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks");
        assertEquals(entry.hashCode(), sameEntry.hashCode());

        TDLEntry differentEntry = new TDLEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks");
        assertNotEquals(entry.hashCode(), differentEntry.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        TDLEntry clonedEntry = entry.clone();
        assertEquals(entry, clonedEntry);
        assertNotSame(entry, clonedEntry);
    }
}