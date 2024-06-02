package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GSM;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {
    private final List<Agenda> agendas;

    public AgendaRepository() {
        this.agendas = new ArrayList<>();
    }

    public Agenda getAgendaByGSM(GSM gsm) {
        for (Agenda existingAgenda : agendas) {
            if (existingAgenda.getGSM().equals(gsm)) {
                return existingAgenda;
            }
        }
        // If agenda does not exist, create and add a new one with an empty list of AgendaEntry
        Agenda newAgenda = new Agenda(new ArrayList<AgendaEntry>(), gsm);
        agendas.add(newAgenda);
        return newAgenda;
    }

    public boolean addAgenda(Agenda agenda) {
        return agendas.add(agenda);
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    // Method to update an Agenda
    public void updateAgenda(Agenda updatedAgenda) {
        for (int i = 0; i < agendas.size(); i++) {
            if (agendas.get(i).getGSM().equals(updatedAgenda.getGSM())) {
                agendas.set(i, updatedAgenda);
                return;
            }
        }
    }

    // Method to update an AgendaEntry
    public void updateAgendaEntryDate(AgendaEntry updatedEntry) {
        for (Agenda agenda : agendas) {
            for (AgendaEntry agendaEntry : agenda.getEntries()) {
                if (agendaEntry.equals(updatedEntry)) {
                    agendaEntry.setDeadline(updatedEntry.getStartDate());
                    return;
                }
            }
        }
    }

    public void cancelEntry(AgendaEntry updatedEntry) {
        for (Agenda agenda : agendas) {
            for (AgendaEntry agendaEntry: agenda.getEntries()){
                if (agendaEntry.equals(updatedEntry)) {
                    agendaEntry.cancel();  // Ensuring status update if required
                }
            }
        }
    }
}





