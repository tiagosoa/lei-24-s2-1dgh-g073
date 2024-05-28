package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * Repository class for handling authentication operations.
 */
public class AuthenticationRepository {
    private final AuthFacade authenticationFacade;

    /**
     * Constructor that initializes the authentication facade.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs a login operation using the provided email and password.
     * @param email the user's email
     * @param pwd the user's password
     * @return true if the login is successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Logs out the current user session.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Retrieves the current user session.
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a new user role with the specified id and description.
     * @param id the role id
     * @param description the role description
     * @return true if the role is successfully added, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with the specified name, email, password, and role id.
     * @param name the user's name
     * @param email the user's email
     * @param pwd the user's password
     * @param roleId the role id for the user
     * @return true if the user is successfully added with the role, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}