package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Controller class responsible for adding a new Vehicle to the system.
 */
public class AddVehicleController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes repositories.
     */
    public AddVehicleController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Parameterized constructor that allows injecting repositories.
     */
    public AddVehicleController(OrganizationRepository organizationRepository,
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

    /**
     * Adds a new Vehicle to the system based on the provided information.
     *
     * @param model                  the model of the vehicle
     * @param brand                  the brand of the vehicle
     * @param type                   the type of the vehicle
     * @param tareWeight             the tare weight of the vehicle
     * @param grossWeight            the gross weight of the vehicle
     * @param currentKm              the current kilometers of the vehicle
     * @param registerDate           the registration date of the vehicle
     * @param acquisitionDate        the acquisition date of the vehicle
     * @param maintenanceFrequencyKm the maintenance frequency in kilometers
     * @param plateNumber            the plate number of the vehicle
     * @param lastMaintenanceDate    the date of the last maintenance
     * @return an Optional containing the newly created Vehicle
     */
    public Optional<Vehicle> addVehicle(String model, String brand, String type, double tareWeight,
                                        double grossWeight, double currentKm, LocalDate registerDate,
                                        LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber,
                                        LocalDate lastMaintenanceDate) {
        Optional<Vehicle> newVehicle;
        newVehicle = vehicleRepository.createVehicle(model, brand, type, tareWeight, grossWeight, currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);

        return newVehicle;
    }

    /**
     * Retrieves the VFM associated with the current user session.
     *
     * @return the VFM object representing the current user session
     */
    public VFM getVFMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}