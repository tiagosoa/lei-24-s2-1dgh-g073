package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * Represents a To-Do List in the system.
 */
public class Agenda {

    private List<AgendaEntry> entries;
    private GSM gsm;

    /**
     * Constructor for creating a Agenda object with a list of entries and a GSM object.
     *
     * @param entries the list of entries to be added to the ToDoList
     * @param gsm the GSM object associated with the ToDoList
     */
    public Agenda(List<AgendaEntry> entries, GSM gsm) {
        this.entries = entries;
        this.gsm = gsm;
    }

    public List<AgendaEntry> getEntries() {
        return entries;
    }

    /**
     * Adds an entry to the agenda
     *
     * @param entry the entry to be added
     * @return true if the entry was added successfully, false otherwise
     */

    public boolean addEntry(AgendaEntry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agenda)) return false;
        Agenda that = (Agenda) o;
        return entries.equals(that.entries) && gsm.equals(that.gsm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, gsm);
    }

    public Agenda clone() {
        return new Agenda(entries, gsm);
    }

    public GSM getGSM() {return gsm;}
}
