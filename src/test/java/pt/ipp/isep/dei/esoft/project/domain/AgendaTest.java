package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {
    private Agenda agenda;
    private GSM gsm;
    private AgendaEntry entry1;
    private AgendaEntry entry2;

    @BeforeEach
    public void setUp() {
        gsm = new GSM("gsm@gmail.pt");
        entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        List<AgendaEntry> entries = new ArrayList<>();
        entries.add(entry1);
        agenda = new Agenda(entries, gsm);
    }

    @Test
    public void ensureGetEntriesReturnsAnEntry() {
        List<AgendaEntry> entries = agenda.getEntries();
        assertNotNull(entries);
        assertEquals(1, entries.size());
        assertTrue(entries.contains(entry1));
    }

    @Test
    public void ensureAddEntryWorksForNonExistingEntry() {
        boolean added = agenda.addEntry(entry2);
        assertTrue(added);
        assertEquals(2, agenda.getEntries().size());
        assertTrue(agenda.getEntries().contains(entry2));

        added = agenda.addEntry(entry1);
        assertFalse(added);
        assertEquals(2, agenda.getEntries().size());
    }

    @Test
    public void ensureCancelEntryWorksForExistingDateAndFailsForANonExistingEntry() {
        boolean cancelled = agenda.cancelEntry(entry1);
        assertTrue(cancelled);
        assertEquals("Cancelled", entry1.getStatus());

        AgendaEntry entry3 = new AgendaEntry("Limpar Parque", "Limpar os passeios do parque", "High", "1 month", "Planned", LocalDate.now().plusDays(2));
        cancelled = agenda.cancelEntry(entry3);
        assertFalse(cancelled);
    }

    @Test
    public void ensureEqualsWorks() {
        List<AgendaEntry> entries = new ArrayList<>();
        entries.add(entry1);
        Agenda anotherAgenda = new Agenda(entries, gsm);

        assertEquals(agenda, anotherAgenda);

        anotherAgenda.addEntry(entry2);
        assertNotEquals(agenda, anotherAgenda);
    }

    @Test
    public void ensureHashCodeWorks() {
        List<AgendaEntry> entries = new ArrayList<>();
        entries.add(entry1);
        Agenda anotherAgenda = new Agenda(entries, gsm);

        assertEquals(agenda.hashCode(), anotherAgenda.hashCode());

        anotherAgenda.addEntry(entry2);
        assertNotEquals(agenda.hashCode(), anotherAgenda.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        Agenda clonedAgenda = agenda.clone();

        assertEquals(agenda, clonedAgenda);
        assertNotSame(agenda, clonedAgenda);
    }

    @Test
    public void ensureGetGSMWorks() {
        assertEquals(gsm, agenda.getGSM());
    }
}
