package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Controller class responsible for assigning skills to collaborators.
 */
public class AssignTeamController {

    private OrganizationRepository organizationRepository;
    private TeamRepository teamRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Default constructor that initializes the repositories.
     */
    public AssignTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.teamRepository = repositories.getTeamRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    /**
     * Constructor that allows passing repositories for testing purposes.
     *
     * @param organizationRepository     Organization repository
     * @param teamRepository            Skill repository
     * @param collaboratorRepository     Collaborator repository
     * @param authenticationRepository    Authentication repository
     */
    public AssignTeamController(OrganizationRepository organizationRepository,
                                TeamRepository teamRepository,
                                CollaboratorRepository collaboratorRepository,
                                AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.teamRepository = teamRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Retrieves the TeamRepository instance.
     *
     * @return TeamRepository instance
     */
    public TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     *
     * @return CollaboratorRepository instance
     */
    public CollaboratorRepository getCollaboratorRepository() {
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
            Skill skill = teamRepository.getSkillByName(skillname);
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
        return teamRepository.getSkillList();
    }
}