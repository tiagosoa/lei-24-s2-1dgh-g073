package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

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
        Organization organization = new Organization("MusgoSublime");
        organization.addHRM(new HRM("admin@this.app"));
        organization.addHRM(new HRM("1231246@isep.ipp.pt"));
        organization.addHRM(new HRM("1230804@isep.ipp.pt"));
        organization.addHRM(new HRM("1230603@isep.ipp.pt"));
        organization.addHRM(new HRM("1230582@isep.ipp.pt"));
        organization.addHRM(new HRM("1231219@isep.ipp.pt"));
        organization.addHRM(new HRM("hrm@this.app"));

        organization.addVFM(new VFM("1231246@isep.ipp.pt"));
        organization.addVFM(new VFM("1230804@isep.ipp.pt"));
        organization.addVFM(new VFM("1230603@isep.ipp.pt"));
        organization.addVFM(new VFM("1230582@isep.ipp.pt"));
        organization.addVFM(new VFM("1231219@isep.ipp.pt"));
        organization.addVFM(new VFM("vfm@this.app"));

        organization.addGSM(new GSM("1231246@isep.ipp.pt"));
        organization.addGSM(new GSM("1230804@isep.ipp.pt"));
        organization.addGSM(new GSM("1230603@isep.ipp.pt"));
        organization.addGSM(new GSM("1230582@isep.ipp.pt"));
        organization.addGSM(new GSM("1231219@isep.ipp.pt"));
        organization.addGSM(new GSM("gsm@this.app"));

        organizationRepository.add(organization);
    }

    private void addSkills() {
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
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Tiago Soares", "1231246@isep.ipp.pt", "1231246",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Diogo Cabral", "1230603@isep.ipp.pt", "1230603",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Diogo Vilela", "1230804@isep.ipp.pt", "1230804",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Guilherme Miranda", "1230582@isep.ipp.pt", "1230582",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Hugo Ramos", "1231219@isep.ipp.pt", "1231219",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Tiago Soares", "1231246@isep.ipp.pt", "isep",
                AuthenticationController.ROLE_VFM);


        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "pwd",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "pwd",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("GSM", "gsm@this.app", "pwd",
                AuthenticationController.ROLE_GSM);


    }
}