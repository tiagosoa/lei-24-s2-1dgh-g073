package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import java.time.LocalDate;
import java.util.Optional;

public class AddVehicleController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;


    /**
     * Constructor.
     * Repository instances are obtained from the Repositories class.
     */
    public AddVehicleController() {
        getOrganizationRepository();
        getVehicleRepository();
        getAuthenticationRepository();
    }

    /**
     * Constructor that allows receiving the repositories as parameters for testing purposes.
     *
     * @param organizationRepository    Organization repository.
     * @param vehicleRepository         Vehicle repository.
     * @param authenticationRepository Authentication repository.
     */
    public AddVehicleController(OrganizationRepository organizationRepository,
                                VehicleRepository vehicleRepository,
                                AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Gets the vehicle repository.
     *
     * @return The vehicle repository.
     */
    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the VehicleRepository
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets the organization repository.
     *
     * @return The organization repository.
     */
    public OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    /**
     * Gets the authentication repository.
     *
     * @return The authentication repository.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Adds a new vehicle.
     *
     * @param model                 The model of the vehicle.
     * @param brand                 The brand of the vehicle.
     * @param type                  The type of the vehicle.
     * @param tareWeight            The tare weight of the vehicle.
     * @param grossWeight           The gross weight of the vehicle.
     * @param currentKm             The current kilometers of the vehicle.
     * @param registerDate          The date when the vehicle was registered.
     * @param acquisitionDate       The date when the vehicle was acquired.
     * @param maintenanceFrequencyKm The maintenance frequency in kilometers for the vehicle.
     * @param plateNumber           The plate number (ID) of the vehicle.
     * @param lastMaintenanceDate   The date when the vehicle last went through maintenance.
     * @param vfm                   The VFM associated with the registration of the vehicle.
     * @return An optional containing the created vehicle if the operation is successful, or an empty optional otherwise.
     */
    public Optional<Vehicle> addVehicle(String model, String brand, String type, double tareWeight,
                                        double grossWeight, double currentKm, LocalDate registerDate,
                                        LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber,
                                        LocalDate lastMaintenanceDate, VFM vfm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByVFM(vfm);

        Optional<Vehicle> newVehicle = Optional.empty();

        if (organization.isPresent()) {
            newVehicle = organization.get().createVehicle(model, brand, type, tareWeight, grossWeight,
                    currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);
        }
        return newVehicle;
    }

    /**
     * Gets the VFM from the current user session.
     *
     * @return The VFM object.
     */
    public VFM getVFMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}
