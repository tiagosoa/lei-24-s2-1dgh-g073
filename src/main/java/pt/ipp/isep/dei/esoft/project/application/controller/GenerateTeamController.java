package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;

/**
 * Controller class responsible for generating a team of collaborators based on required skills.
 */
public class GenerateTeamController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;

    /**
     * Default constructor that initializes repositories.
     */
    public GenerateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.skillRepository = repositories.getSkillRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Parameterized constructor that sets repositories based on input.
     *
     * @param organizationRepository     the organization repository
     * @param collaboratorRepository      the collaborator repository
     * @param authenticationRepository    the authentication repository
     * @param skillRepository             the skill repository
     */
    public GenerateTeamController(OrganizationRepository organizationRepository,
                                  CollaboratorRepository collaboratorRepository,
                                  AuthenticationRepository authenticationRepository,
                                  SkillRepository skillRepository) {
        this.organizationRepository = organizationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
        this.skillRepository = skillRepository;
    }

    // Methods for retrieving repositories if not set

    /**
     * Retrieves the collaborator repository.
     *
     * @return the collaborator repository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository == null ? Repositories.getInstance().getCollaboratorRepository() : collaboratorRepository;
    }

    /**
     * Retrieves the organization repository.
     *
     * @return the organization repository
     */
    private OrganizationRepository getOrganizationRepository() {
        return organizationRepository == null ? Repositories.getInstance().getOrganizationRepository() : organizationRepository;
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
     * Retrieves the skill repository.
     *
     * @return the skill repository
     */
    public SkillRepository getSkillRepository() {
        return skillRepository == null ? Repositories.getInstance().getSkillRepository() : skillRepository;
    }

    /**
     * Generates a team of collaborators based on input parameters.
     *
     * @param maxTeamSize     the maximum team size
     * @param minTeamSize     the minimum team size
     * @param requiredSkills  the list of required skills
     * @return a list of collaborators for the team
     * @throws IllegalArgumentException if input parameters are invalid or not enough qualified collaborators are available
     */
    public List<Collaborator> generateTeam(int maxTeamSize, int minTeamSize, List<Skill> requiredSkills) {
        if (minTeamSize <= 0 || maxTeamSize < minTeamSize || requiredSkills.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        return collaboratorRepository.generateTeam(maxTeamSize, minTeamSize, requiredSkills);
    }

    /**
     * Retrieves the HRM (Human Resource Manager) from the current user session.
     *
     * @return the HRM object associated with the current user session
     */
    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }
}