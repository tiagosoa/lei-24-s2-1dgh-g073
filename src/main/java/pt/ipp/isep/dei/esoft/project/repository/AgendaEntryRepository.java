package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing AgendaEntry objects.
 */
public class AgendaEntryRepository {
    private List<AgendaEntry> agendaEntries;

    /**
     * Constructor for AgendaEntryRepository class.
     * Initializes the list of agenda entries.
     */
    public AgendaEntryRepository() {
        this.agendaEntries = new ArrayList<>();
    }

    /**
     * Adds a new AgendaEntry to the repository.
     *
     * @param agendaEntry the AgendaEntry to be added
     */
    public void addEntry(AgendaEntry agendaEntry) {
        agendaEntries.add(agendaEntry);
    }

    /**
     * Retrieves a copy of the list of agenda entries.
     *
     * @return a new list containing all agenda entries
     */
    public List<AgendaEntry> getAgendaEntries() {
        return new ArrayList<>(agendaEntries);
    }
}