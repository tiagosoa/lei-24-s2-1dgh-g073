package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addCollaborators();
        addOrganization();
        addUsers();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addHRM(new HRM("admin@this.app"));
        organization.addHRM(new HRM("1231246@isep.ipp.pt"));
        organization.addHRM(new HRM("hrm@this.app"));
        organizationRepository.add(organization);
    }

    private void addSkills() {
        //TODO: add bootstrap Task Categories here

        //get skill repository
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        //skillRepository.add(new TaskCategory("Analysis"));
        //skillRepository.add(new TaskCategory("Design"));
        //skillRepository.add(new TaskCategory("Implementation"));
        //skillRepository.add(new TaskCategory("Development"));
        //skillRepository.add(new TaskCategory("Testing"));
        //skillRepository.add(new TaskCategory("Deployment"));
        //skillRepository.add(new TaskCategory("Maintenance"));
    }

    private void addJobs() {
        //TODO: add bootstrap Task Categories here

        //get job repository
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        //skillRepository.add(new TaskCategory("Analysis"));
        //skillRepository.add(new TaskCategory("Design"));
        //skillRepository.add(new TaskCategory("Implementation"));
        //skillRepository.add(new TaskCategory("Development"));
        //skillRepository.add(new TaskCategory("Testing"));
        //skillRepository.add(new TaskCategory("Deployment"));
        //skillRepository.add(new TaskCategory("Maintenance"));
    }

    private void addCollaborators() {
        //TODO: add bootstrap Task Categories here

        //get job repository
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        //skillRepository.add(new TaskCategory("Analysis"));
        //skillRepository.add(new TaskCategory("Design"));
        //skillRepository.add(new TaskCategory("Implementation"));
        //skillRepository.add(new TaskCategory("Development"));
        //skillRepository.add(new TaskCategory("Testing"));
        //skillRepository.add(new TaskCategory("Deployment"));
        //skillRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM,
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Tiago Soares", "1231246@isep.ipp.pt", "1231246",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Diogo Cabral", "1230603@isep.app", "1230603",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Diogo Vilela", "1230804@isep.app", "1230804",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Guilherme Miranda", "1230582@isep.app", "1230582",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Hugo Ramos", "1231219@isep.app", "1231219",
                AuthenticationController.ROLE_HRM);


        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "pwd",
                AuthenticationController.ROLE_HRM);
    }
}