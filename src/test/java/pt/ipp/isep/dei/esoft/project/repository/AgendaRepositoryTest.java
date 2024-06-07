package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaRepositoryTest {

    private AgendaRepository agendaRepository;
    private GSM gsm1;
    private GSM gsm2;
    private Agenda agenda1;
    private Agenda agenda2;
    private AgendaEntry entry1;
    private AgendaEntry entry2;

    @BeforeEach
    public void setUp() {
        agendaRepository = new AgendaRepository();
        gsm1 = new GSM("gsm@gmail.pt");
        gsm2 = new GSM("gsm2@gmail.pt");

        entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));

        List<AgendaEntry> entries1 = new ArrayList<>();
        entries1.add(entry1);
        List<AgendaEntry> entries2 = new ArrayList<>();
        entries2.add(entry2);

        agenda1 = new Agenda(entries1, gsm1);
        agenda2 = new Agenda(entries2, gsm2);
    }

    @Test
    public void ensureGetAgendaByGSMWorks() {
        agendaRepository.addAgenda(agenda1);
        agendaRepository.addAgenda(agenda2);

        Agenda retrievedAgenda1 = agendaRepository.getAgendaByGSM(gsm1);
        assertEquals(agenda1, retrievedAgenda1);

        Agenda retrievedAgenda2 = agendaRepository.getAgendaByGSM(gsm2);
        assertEquals(agenda2, retrievedAgenda2);

        GSM gsm3 = new GSM("gsm3@gmail.pt");
        Agenda newAgenda = agendaRepository.getAgendaByGSM(gsm3);
        assertNotNull(newAgenda);
        assertEquals(gsm3, newAgenda.getGSM());
        assertTrue(newAgenda.getEntries().isEmpty());
    }

    @Test
    public void ensureAddAgendaWorks() {
        boolean added = agendaRepository.addAgenda(agenda1);
        assertTrue(added);

        List<Agenda> agendas = agendaRepository.getAgendas();
        assertEquals(1, agendas.size());
        assertTrue(agendas.contains(agenda1));
    }

    @Test
    public void ensureGetAgendasWorks() {
        agendaRepository.addAgenda(agenda1);
        agendaRepository.addAgenda(agenda2);

        List<Agenda> agendas = agendaRepository.getAgendas();
        assertEquals(2, agendas.size());
        assertTrue(agendas.contains(agenda1));
        assertTrue(agendas.contains(agenda2));
    }

    @Test
    public void ensureUpdateAgendaWorks() {
        agendaRepository.addAgenda(agenda1);
        agendaRepository.addAgenda(agenda2);

        List<AgendaEntry> updatedEntries = new ArrayList<>(agenda1.getEntries());
        AgendaEntry newEntry = new AgendaEntry("Limpar passeios", "Limpar os passeios do parque", "High", "1 week", "Planned", LocalDate.now().plusDays(5));
        updatedEntries.add(newEntry);

        Agenda updatedAgenda = new Agenda(updatedEntries, gsm1);
        agendaRepository.updateAgenda(updatedAgenda);

        Agenda retrievedAgenda = agendaRepository.getAgendaByGSM(gsm1);
        assertEquals(updatedAgenda, retrievedAgenda);
    }

    @Test
    public void ensureUpdateAgendaEntryDateWorks() {
        agendaRepository.addAgenda(agenda1);

        LocalDate newDeadline = LocalDate.now().plusDays(7);
        AgendaEntry updatedEntry = new AgendaEntry(entry1.getTitle(), entry1.getTaskDescription(), entry1.getUrgency(), entry1.getDuration(), entry1.getStatus(), newDeadline);
        agendaRepository.updateAgendaEntryDate(updatedEntry);

        Agenda retrievedAgenda = agendaRepository.getAgendaByGSM(gsm1);
        List<AgendaEntry> entries = retrievedAgenda.getEntries();

        assertEquals(1, entries.size());
        assertEquals(newDeadline, entries.get(0).getStartDate());
        assertEquals("Postponed", entries.get(0).getStatus());
    }

    @Test
    public void ensureCancelEntryWorks() {
        agendaRepository.addAgenda(agenda1);

        AgendaEntry cancelEntry = new AgendaEntry(entry1.getTitle(), entry1.getTaskDescription(), entry1.getUrgency(), entry1.getDuration(), entry1.getStatus(), entry1.getStartDate());
        agendaRepository.cancelEntry(cancelEntry);

        Agenda retrievedAgenda = agendaRepository.getAgendaByGSM(gsm1);
        List<AgendaEntry> entries = retrievedAgenda.getEntries();

        assertEquals(1, entries.size());
        assertEquals("Cancelled", entries.get(0).getStatus());
    }
}
