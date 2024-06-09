package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.GSM;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing Agendas.
 */
public class AgendaRepository {
    private final List<Agenda> agendas;

    /**
     * Constructor for AgendaRepository.
     */
    public AgendaRepository() {
        this.agendas = new ArrayList<>();
    }

    /**
     * Get an Agenda by GSM.
     *
     * @param gsm the GSM to search for
     * @return the Agenda associated with the given GSM
     */
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

    /**
     * Add an Agenda to the repository.
     *
     * @param agenda the Agenda to add
     * @return true if the Agenda was added successfully
     */
    public boolean addAgenda(Agenda agenda) {
        return agendas.add(agenda);
    }

    /**
     * Get all Agendas in the repository.
     *
     * @return a list of all Agendas in the repository
     */
    public List<Agenda> getAgendas() {
        return agendas;
    }

    /**
     * Update an existing Agenda with new information.
     *
     * @param updatedAgenda the updated Agenda
     */
    public void updateAgenda(Agenda updatedAgenda) {
        for (int i = 0; i < agendas.size(); i++) {
            if (agendas.get(i).getGSM().equals(updatedAgenda.getGSM())) {
                agendas.set(i, updatedAgenda);
                return;
            }
        }
    }

    /**
     * Update the date of an AgendaEntry and set its status to "Postponed".
     *
     * @param updatedEntry the AgendaEntry to update
     */
    public void updateAgendaEntryDate(AgendaEntry updatedEntry) {
        for (Agenda agenda : agendas) {
            for (AgendaEntry agendaEntry : agenda.getEntries()) {
                if (agendaEntry.equals(updatedEntry)) {
                    agendaEntry.setDeadline(updatedEntry.getStartDate());
                    agendaEntry.setStatus("Postponed");
                    return;
                }
            }
        }
    }

    /**
     * Cancel an AgendaEntry by setting its status to "Cancelled".
     *
     * @param updatedEntry the AgendaEntry to cancel
     */
    public void cancelEntry(AgendaEntry updatedEntry) {
        for (Agenda agenda : agendas) {
            for (AgendaEntry agendaEntry : agenda.getEntries()) {
                if (agendaEntry.equals(updatedEntry)) {
                    agendaEntry.cancel();  // Ensuring status update if required
                }
            }
        }
    }
}