package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

public class Agenda {
    private List<AgendaEntry> entries;
    private GSM gsm;

    public Agenda(List<AgendaEntry> entries, GSM gsm) {
        this.entries = entries;
        this.gsm = gsm;
    }

    public List<AgendaEntry> getEntries() {
        return entries;
    }

    public boolean addEntry(AgendaEntry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
            return true;
        }
        return false;
    }

    public boolean cancelEntry(AgendaEntry entry) {
        for (AgendaEntry agendaEntry : entries) {
            if (agendaEntry.equals(entry)) {
                agendaEntry.cancel();
                return true;
            }
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

    public GSM getGSM() {
        return gsm;
    }
}
