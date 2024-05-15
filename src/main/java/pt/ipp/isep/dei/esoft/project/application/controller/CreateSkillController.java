package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

/**
 * Controller class responsible for creating a new Skill.
 */
public class CreateSkillController {

    private OrganizationRepository organizationRepository;
    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories by obtaining them from the Repositories class.
     */
    public CreateSkillController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.skillRepository = repositories.getSkillRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows receiving repositories as parameters for testing purposes.
     *
     * @param organizationRepository     the OrganizationRepository to use
     * @param skillRepository            the SkillRepository to use
     * @param authenticationRepository   the AuthenticationRepository to use
     */
    public CreateSkillController(OrganizationRepository organizationRepository,
                                 SkillRepository skillRepository,
                                 AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the SkillRepository instance, initializing it if necessary.
     *
     * @return the SkillRepository instance
     */
    public SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }


    /**
     * Retrieves the OrganizationRepository instance, initializing it if necessary.
     *
     * @return the OrganizationRepository instance
     */
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;
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
     * Creates a new Skill for a given name and HRM.
     *
     * @param name the name of the Skill
     * @return an Optional containing the newly created Skill, or empty if the Organization is not found
     */
    public Optional<Skill> createSkill(String name) {

        Optional<Skill> newSkill = Optional.empty();
        newSkill = skillRepository.createSkill(name);
        
        return newSkill;
    }
    /**
     * Retrieves the HRM associated with the current user session.
     *
     * @return the HRM associated with the current user session
     */
    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

}