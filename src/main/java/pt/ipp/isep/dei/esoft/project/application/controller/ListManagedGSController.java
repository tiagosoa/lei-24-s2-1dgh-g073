package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Controller class responsible for managing the list of managed GreenSpaces.
 */
public class ListManagedGSController {

    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the GreenSpaceRepository and AuthenticationRepository.
     */
    public ListManagedGSController() {
        Repositories repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows injecting GreenSpaceRepository and AuthenticationRepository dependencies.
     *
     * @param greenSpaceRepository     the GreenSpaceRepository instance
     * @param authenticationRepository  the AuthenticationRepository instance
     */
    public ListManagedGSController(GreenSpaceRepository greenSpaceRepository,
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
     * Retrieves the authentication repository.
     *
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository == null ? Repositories.getInstance().getAuthenticationRepository() : authenticationRepository;
    }

    /**
     * Retrieves a list of managed GreenSpaces associated with a specific GSM.
     *
     * @param gsm the GSM (Green Space Manager) to retrieve managed GreenSpaces for
     * @return a list of managed GreenSpaces sorted by a specific criteria
     */
    public List<GreenSpace> getManagedGreenSpaces(GSM gsm) {
        return greenSpaceRepository.getManagedGreenSpacesSorted(gsm);
    }

    /**
     * Retrieves the GSM (Green Space Manager) associated with the current user session.
     *
     * @return the GSM associated with the current user session
     */
    public GSM getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GSM(email.getEmail());
    }
}