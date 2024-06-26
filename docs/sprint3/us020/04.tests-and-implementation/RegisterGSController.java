package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

/**
 * Controller class responsible for creating a new GreenSpace.
 */
public class RegisterGSController {
    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories by obtaining them from the Repositories class.
     */
    public RegisterGSController() {
        Repositories repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows receiving repositories as parameters for testing purposes.
     *
     * @param greenSpaceRepository      the GreenSpaceRepository to use
     * @param authenticationRepository  the AuthenticationRepository to use
     */
    public RegisterGSController(GreenSpaceRepository greenSpaceRepository,
                                AuthenticationRepository authenticationRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the GreenSpaceRepository instance, initializing it if necessary.
     *
     * @return the GreenSpaceRepository instance
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance, initializing it if necessary.
     *
     * @return the AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Creates a new GreenSpace for a given type, area and GSM.
     *
     * @param name the name of the GreenSpace
     * @param type the type of the GreenSpace
     * @param area the area of the GreenSpace
     * @param gsm  the GSM associated with the GreenSpace
     * @return an Optional containing the newly created GreenSpace, or empty if the Organization is not found
     */
    public Optional<GreenSpace> registerGreenSpace(String name, String type, double area, GSM gsm) {
        Optional<GreenSpace> newGreenSpace;
        newGreenSpace = greenSpaceRepository.registerGreenSpace(name, type, area, gsm);
        return newGreenSpace;
    }

    /**
     * Retrieves the GSM associated with the current user session.
     *
     * @return the GSM associated with the current user session
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }

}