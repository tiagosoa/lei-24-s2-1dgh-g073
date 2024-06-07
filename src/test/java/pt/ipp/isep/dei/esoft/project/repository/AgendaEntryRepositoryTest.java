package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaEntryRepositoryTest {

    private AgendaEntryRepository agendaEntryRepository;
    private AgendaEntry entry1;
    private AgendaEntry entry2;

    @BeforeEach
    public void setUp() {
        agendaEntryRepository = new AgendaEntryRepository();
        entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
    }

    @Test
    public void ensureAddEntryWorks() {
        agendaEntryRepository.addEntry(entry1);
        List<AgendaEntry> entries = agendaEntryRepository.getAgendaEntries();
        assertEquals(1, entries.size());
        assertTrue(entries.contains(entry1));

        agendaEntryRepository.addEntry(entry2);
        entries = agendaEntryRepository.getAgendaEntries();
        assertEquals(2, entries.size());
        assertTrue(entries.contains(entry2));
    }

    @Test
    public void ensureGetAgendaEntriesWorks() {
        agendaEntryRepository.addEntry(entry1);
        agendaEntryRepository.addEntry(entry2);
        List<AgendaEntry> entries = agendaEntryRepository.getAgendaEntries();

        assertEquals(2, entries.size());
        assertTrue(entries.contains(entry1));
        assertTrue(entries.contains(entry2));
    }

    @Test
    public void ensureGetAgendaEntriesReturnsNewList() {
        agendaEntryRepository.addEntry(entry1);
        List<AgendaEntry> entries = agendaEntryRepository.getAgendaEntries();

        entries.clear();

        List<AgendaEntry> entriesAfterClear = agendaEntryRepository.getAgendaEntries();
        assertEquals(1, entriesAfterClear.size());
    }
}
