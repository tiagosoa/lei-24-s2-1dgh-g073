package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    private final List<Agenda> agendas;

    public AgendaRepository() {
        this.agendas = new ArrayList<>();
    }

    public Agenda getAgendaByGSM(GSM gsm) {
        for (Agenda existingTDList : agendas) {
            if (existingTDList.getGSM().equals(gsm)) {
                return existingTDList;
            }
        }
        throw new IllegalArgumentException("Agenda does not exist.");
    }

    public boolean addAgenda(Agenda agenda) {
        return agendas.add(agenda);
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }
}

