package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntryRepository {
    private List<AgendaEntry> agendaEntries;

    public AgendaEntryRepository() {
        this.agendaEntries = new ArrayList<>();
    }

    public void addEntry(AgendaEntry agendaEntry) {
        agendaEntries.add(agendaEntry);
    }

    public List<AgendaEntry> getAgendaEntries() {
        return new ArrayList<>(agendaEntries);
    }
}



