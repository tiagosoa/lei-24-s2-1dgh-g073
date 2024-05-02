package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            //Get the JobRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    public List<Collaborator> generateTeam(int maxTeamSize, int minTeamSize, List<Skill> requiredSkills) {
        // Get all collaborators from the repository
        List<Collaborator> allCollaborators = collaboratorRepository.getCollaborators();

        // Filter collaborators based on required skills
        List<Collaborator> eligibleCollaborators = filterCollaboratorsBySkills(allCollaborators, requiredSkills);

        // Randomly select collaborators for the team
        List<Collaborator> team = new ArrayList<>();
        int teamSize = getRandomTeamSize(maxTeamSize, minTeamSize);
        if (teamSize > eligibleCollaborators.size()) {
            throw new IllegalArgumentException("Not enough eligible collaborators for the required team size.");
        }
        for (int i = 0; i < teamSize; i++) {
            int randomIndex = new Random().nextInt(eligibleCollaborators.size());
            team.add(eligibleCollaborators.remove(randomIndex));
        }

        return team;
    }

    private List<Collaborator> filterCollaboratorsBySkills(List<Collaborator> collaborators, List<Skill> requiredSkills) {
        List<Collaborator> filteredCollaborators = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            List<Skill> collaboratorSkills = collaborator.getSkills();
            if (collaboratorSkills.containsAll(requiredSkills)) {
                filteredCollaborators.add(collaborator);
            }
        }
        return filteredCollaborators;
    }

    private int getRandomTeamSize(int maxTeamSize, int minTeamSize) {
        return new Random().nextInt(maxTeamSize - minTeamSize + 1) + minTeamSize;
    }
    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }
}
