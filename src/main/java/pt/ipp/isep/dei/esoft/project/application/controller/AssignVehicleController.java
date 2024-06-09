package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Controller class responsible for assigning vehicles to agenda entries.
 */
public class AssignVehicleController {
    private final VehicleRepository vehicleRepository;
    private final AgendaRepository agendaRepository;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public AssignVehicleController() {
        Repositories repositories = Repositories.getInstance();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.agendaRepository = repositories.getAgendaRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Parameterized constructor that allows injecting repositories for testing purposes.
     */
    public AssignVehicleController(VehicleRepository vehicleRepository,
                                   AgendaRepository agendaRepository,
                                   AuthenticationRepository authenticationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.agendaRepository = agendaRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the Vehicle Repository instance.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository == null ? Repositories.getInstance().getVehicleRepository() : vehicleRepository;
    }

    /**
     * Retrieves the Agenda Repository instance.
     */
    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    /**
     * Retrieves the Authentication Repository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Retrieves the agenda associated with a given GSM.
     *
     * @param gsm the GSM identifier
     * @return the agenda associated with the given GSM
     */
    public Agenda getAgenda(GSM gsm) {
        return agendaRepository.getAgendaByGSM(gsm);
    }

    /**
     * Retrieves the GSM from the current user session.
     *
     * @return the GSM associated with the current user session
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

    /**
     * Retrieves the agenda entries associated with the current user session.
     *
     * @return the list of agenda entries associated with the current user session
     */
    public List<AgendaEntry> getAgendaEntries() {
        GSM gsm = getGSMFromSession();
        Agenda agenda = getAgenda(gsm);
        return agenda.getEntries();
    }

    /**
     * Assigns selected vehicles to a given agenda entry.
     *
     * @param entry           the agenda entry to assign vehicles to
     * @param selectedVehicles the list of vehicles to assign
     * @return true if the vehicles were successfully assigned, false otherwise
     */
    public boolean assignVehicleToEntry(AgendaEntry entry, List<Vehicle> selectedVehicles) {
        if (selectedVehicles != null && entry != null) {
            entry.assignVehicle(selectedVehicles);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the list of vehicles available in the repository.
     *
     * @return the list of vehicles available in the repository
     */
    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }
}