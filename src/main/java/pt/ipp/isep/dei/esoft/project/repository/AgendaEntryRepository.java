package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntryRepository {
    private List<TDLEntry> agendaEntries;

    public AgendaEntryRepository() {
        this.agendaEntries = new ArrayList<>();
    }

    public void addEntry(TDLEntry entry) {
        agendaEntries.add(entry);
    }

    public List<TDLEntry> getAgendaEntries() {
        return new ArrayList<>(agendaEntries);
    }
}



