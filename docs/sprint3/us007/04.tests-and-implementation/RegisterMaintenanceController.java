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

    public Optional<Boolean> registerMaintenance(String plateNumber, int currentKm, LocalDate maintenanceDate) {
        try {
            Vehicle vehicle = vehicleRepository.getVehicleByPlateNumber(plateNumber);

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
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}