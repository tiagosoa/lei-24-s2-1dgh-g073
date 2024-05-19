package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class RegisterMaintenanceController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;

    public RegisterMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public RegisterMaintenanceController(OrganizationRepository organizationRepository,
                                         VehicleRepository vehicleRepository,
                                         AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the Vehicle Repository instance.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository == null ? Repositories.getInstance().getVehicleRepository() : vehicleRepository;
    }

    /**
     * Retrieves the Organization Repository instance.
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository == null ? Repositories.getInstance().getOrganizationRepository() : organizationRepository;
    }

    /**
     * Retrieves the Authentication Repository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository == null ? Repositories.getInstance().getAuthenticationRepository() : authenticationRepository;
    }

    public VFM getVFMFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}