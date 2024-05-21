package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addCollaborators();
        addVehicles();
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

        skillRepository.addSkill(new Skill("Skill Name"));
        skillRepository.addSkill(new Skill("Skill Name One"));
    }

    private void addJobs() {

        //get job repository
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        jobRepository.addJob(new Job("Job Name"));
        jobRepository.addJob(new Job("Job Name One"));
    }

    private void addCollaborators() {

        //get job repository
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        collaboratorRepository.addCollaborator(new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789));
        collaboratorRepository.addCollaborator(new Collaborator("nemlei", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456799,"CC", 123456789));

    }

    private void addVehicles() {

        //get job repository
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        vehicleRepository.addVehicle(new Vehicle("Porsche", "Turbo", "Car", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3));
        vehicleRepository.addVehicle(new Vehicle("Ford", "Focus", "Car", 1275, 1820, 30000, date1, date2, 1, "01-AB-01", date3));

    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);

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