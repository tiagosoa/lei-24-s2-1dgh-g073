package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
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


    // Repository instances are obtained from the Repositories class
    public RegisterMaintenanceController() {
        getOrganizationRepository();
        getVehicleRepository();
        getAuthenticationRepository();
    }

    // Allows receiving the repositories as parameters for testing purposes
    public RegisterMaintenanceController(OrganizationRepository organizationRepository,
                                         VehicleRepository vehicleRepository,
                                         AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the VehicleRepository
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Boolean> registerMaintenance(String plateNumber, int currentKm, LocalDate maintenanceDate) {
        try {
            Vehicle vehicle = vehicleRepository.getVehiclebyPlateNumber(plateNumber);

            // Check if the maintenance date is valid (not in the future)
            if (maintenanceDate.isAfter(LocalDate.now())) {
                System.out.println("Maintenance date cannot be in the future.");
                return Optional.empty();
            } else {
                vehicle.setCurrentKilometers(currentKm);
                vehicle.setMaintenanceDate(maintenanceDate);
                return Optional.of(true);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }
    public VFM getVFMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}
