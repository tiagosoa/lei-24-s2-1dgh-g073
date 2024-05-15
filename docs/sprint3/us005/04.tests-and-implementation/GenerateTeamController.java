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
        this.organizationRepository = getOrganizationRepository();
        this.collaboratorRepository = getCollaboratorRepository();
        this.authenticationRepository = getAuthenticationRepository();
        this.skillRepository = getSkillRepository();
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
    private CollaboratorRepository getCollaboratorRepository() {
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
    private SkillRepository getSkillRepository() {
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

        List<Collaborator> allCollaborators = collaboratorRepository.getCollaboratorList();
        List<Collaborator> qualifiedCollaborators = filterCollaboratorsBySkills(allCollaborators, requiredSkills);

        if (qualifiedCollaborators.size() < minTeamSize) {
            throw new IllegalArgumentException("Not enough qualified collaborators available.");
        }

        List<Collaborator> team = new ArrayList<>();
        for (int i = 0; i < maxTeamSize && i < qualifiedCollaborators.size(); i++) {
            team.add(qualifiedCollaborators.get(i));
        }

        return team;
    }

    /**
     * Filters a list of collaborators based on required skills.
     *
     * @param collaborators     the list of collaborators to filter
     * @param requiredSkills    the list of required skills
     * @return a list of collaborators with all required skills
     */
    private List<Collaborator> filterCollaboratorsBySkills(List<Collaborator> collaborators, List<Skill> requiredSkills) {
        List<Collaborator> qualifiedCollaborators = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            List<Skill> collaboratorSkills = collaborator.getSkills();
            boolean hasAllRequiredSkills = true;
            for (Skill requiredSkill : requiredSkills) {
                if (!collaboratorSkills.contains(requiredSkill)) {
                    hasAllRequiredSkills = false;
                    break;
                }
            }
            if (hasAllRequiredSkills) {
                qualifiedCollaborators.add(collaborator);
            }
        }
        return qualifiedCollaborators;
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