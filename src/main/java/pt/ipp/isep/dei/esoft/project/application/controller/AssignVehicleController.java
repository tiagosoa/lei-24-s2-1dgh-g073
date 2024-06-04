package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

public class AssignVehicleController {
    private final VehicleRepository vehicleRepository;
    private final AgendaRepository agendaRepository;
    private final AuthenticationRepository authenticationRepository;

    public AssignVehicleController() {
        Repositories repositories = Repositories.getInstance();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public AssignVehicleController(VehicleRepository toDoListRepository,
                                   AgendaRepository agendaRepository,
                                   AuthenticationRepository authenticationRepository) {
        this.vehicleRepository = toDoListRepository;
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the Vehicle Repository instance.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository == null ? Repositories.getInstance().getVehicleRepository() : vehicleRepository;
    }


    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }
    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }
    public boolean assignVehicleToEntry(AgendaEntry entry, List<Vehicle> selectedVehicles) {
        if (selectedVehicles != null || entry != null) {
            assert selectedVehicles != null;
            entry.assignVehicle(selectedVehicles);
            return true;
        }
        return false;
    }
}



