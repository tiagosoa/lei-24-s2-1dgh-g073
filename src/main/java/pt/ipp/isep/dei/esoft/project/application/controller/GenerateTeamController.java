package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;

public class GenerateTeamController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;

    public GenerateTeamController() {
        getOrganizationRepository();
        getCollaboratorRepository();
        getAuthenticationRepository();
        getSkillRepository();
    }

    public GenerateTeamController(OrganizationRepository organizationRepository,
                                          CollaboratorRepository collaboratorRepository,
                                          AuthenticationRepository authenticationRepository,
                                          SkillRepository skillRepository) {
        this.organizationRepository = organizationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
        this.skillRepository = skillRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the CollaboratorRepository
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
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

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the SkillRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    public List<Collaborator> generateTeam(int maxTeamSize, int minTeamSize, List<Skill> requiredSkills) {
        // Validate input parameters
        if (minTeamSize <= 0 || maxTeamSize < minTeamSize || requiredSkills.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        // Get all collaborators from the repository
        List<Collaborator> allCollaborators = collaboratorRepository.getCollaboratorList();

        // Filter collaborators based on required skills
        List<Collaborator> qualifiedCollaborators = filterCollaboratorsBySkills(allCollaborators, requiredSkills);

        // Check if there are enough qualified collaborators
        if (qualifiedCollaborators.size() < minTeamSize) {
            throw new IllegalArgumentException("Not enough qualified collaborators available.");
        }

        // Generate team from qualified collaborators
        List<Collaborator> team = new ArrayList<>();
        for (int i = 0; i < maxTeamSize && i < qualifiedCollaborators.size(); i++) {
            team.add(qualifiedCollaborators.get(i));
        }

        return team;
    }



    private List<Collaborator> filterCollaboratorsBySkills(List<Collaborator> collaborators, List<Skill> requiredSkills) {
        List<Collaborator> qualifiedCollaborators = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            List<Skill> collaboratorSkills = collaborator.getSkills();
            boolean hasAllRequiredSkills = true;
            for (Skill requiredSkill : requiredSkills) {
                if (!collaboratorSkills.contains(skillRepository.getSkillByName(requiredSkill.getName()))) {
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

public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }
}
