package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Controller class responsible for assigning skills to collaborators.
 */
public class AssignSkillController {

    private OrganizationRepository organizationRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public AssignSkillController() {
        this.organizationRepository = getOrganizationRepository();
        this.skillRepository = getSkillRepository();
        this.collaboratorRepository = getCollaboratorRepository();
        this.authenticationRepository = getAuthenticationRepository();
    }

    /**
     * Constructor that allows passing repositories for testing purposes.
     *
     * @param organizationRepository     Organization repository
     * @param skillRepository            Skill repository
     * @param collaboratorRepository     Collaborator repository
     * @param authenticationRepository    Authentication repository
     */
    public AssignSkillController(OrganizationRepository organizationRepository,
                                 SkillRepository skillRepository,
                                 CollaboratorRepository collaboratorRepository,
                                 AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the SkillRepository instance.
     *
     * @return SkillRepository instance
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     *
     * @return CollaboratorRepository instance
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the OrganizationRepository instance.
     *
     * @return OrganizationRepository instance
     */
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Assigns skills to a collaborator.
     *
     * @param collaboratorID       ID of the collaborator
     * @param collaboratorskills   List of skills to assign
     */
    public void assignSkillsToCollaborator(int collaboratorID, List<String> collaboratorskills) {
        Collaborator collaborator = collaboratorRepository.getCollaboratorByID(collaboratorID);

        if (collaborator == null) {
            throw new IllegalArgumentException("Collaborator not found.");
        }

        for (String skillname : collaboratorskills) {
            Skill skill = skillRepository.getSkillByName(skillname);
            if (skill != null) {
                collaborator.addSkill(skill);
            }
        }

        collaboratorRepository.updateCollaborator(collaborator);
    }

    /**
     * Retrieves the HRM (Human Resource Manager) from the current session.
     *
     * @return HRM instance
     */
    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

    /**
     * Retrieves a list of collaborators.
     *
     * @return List of collaborators
     */
    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Retrieves a list of skills.
     *
     * @return List of skills
     */
    public List<Skill> getSkillList() {
        return skillRepository.getSkillList();
    }
}