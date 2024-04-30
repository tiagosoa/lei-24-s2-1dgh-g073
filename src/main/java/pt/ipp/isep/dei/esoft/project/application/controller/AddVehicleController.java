package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class AddVehicleController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;


    //Repository instances are obtained from the Repositories class
    public AddVehicleController() {
        getOrganizationRepository();
        getVehicleRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public AddVehicleController(OrganizationRepository organizationRepository, VehicleRepository vehicleRepository, AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the JobRepository
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Vehicle> addVehicle(String model,String brand,String type, VFM vfm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByVFM(vfm);

        Optional<Vehicle> newVehicle = Optional.empty();

        if (organization.isPresent()) {
            newVehicle = organization.get().createVehicle(model,brand,type,vfm);
        }
        return newVehicle;
    }

    public VFM getVFMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new VFM(email.getEmail());
    }

}
