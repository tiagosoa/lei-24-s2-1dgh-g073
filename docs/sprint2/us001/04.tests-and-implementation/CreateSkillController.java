package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class CreateSkillController {

    private OrganizationRepository organizationRepository;
    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;


    //Repository instances are obtained from the Repositories class
    public CreateSkillController() {
        getOrganizationRepository();
        getSkillRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateSkillController(OrganizationRepository organizationRepository,
                                 SkillRepository skillRepository,
                                 AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the SkillRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
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

    public Optional<Skill> createSkill(String name, HRM hrm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByHRM(hrm);

        Optional<Skill> newSkill = Optional.empty();

        if (organization.isPresent()) {
            newSkill = organization.get()
                    .createSkill(name, hrm);
        }
        return newSkill;
    }

    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

}