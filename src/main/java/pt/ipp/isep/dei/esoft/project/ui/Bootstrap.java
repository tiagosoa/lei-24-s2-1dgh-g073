package pt.ipp.isep.dei.esoft.project.ui;


import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.GSM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addCollaborators();
        addVehicles();
        addOrganization();
        addUsers();
        addEmptyToDoList();
        addEmptyAgenda();
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

        organization.addCollaborator(new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789));
        organization.addCollaborator(new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799));
        organization.addCollaborator(new Collaborator("Jonas", "25-09-1994", "08-07-2023", "Rio Tinto", 93761984, "jonas@gmail.pt", 275315949,"CC", 734691829));

        organizationRepository.add(organization);
    }
    private void addEmptyToDoList() {
        // Get the ToDoList repository
        ToDoListRepository toDoListRepository = Repositories.getInstance().getToDoListRepository();

        // Check if the repository already contains a ToDoList (optional)
        if (toDoListRepository.getToDoLists().isEmpty()) {
            // Create an empty ToDoList
            ToDoList toDoList = new ToDoList(new ArrayList<>(), new GSM("gsm@this.app"));

            // Add the empty ToDoList to the repository
            toDoListRepository.addToDoList(toDoList);
        }
    }

    private void addEmptyAgenda() {
        // Get the Agenda repository
        AgendaRepository agendaRepository = Repositories.getInstance().getAgendaRepository();

        // Check if the repository already contains a Agenda (optional)
        if (agendaRepository.getAgendas().isEmpty()) {
            // Create an empty Agenda
            Agenda agenda = new Agenda(new ArrayList<>(), new GSM("gsm@this.app"));

            // Add the empty Agenda to the repository
            agendaRepository.addAgenda(agenda);
        }
    }


    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        skillRepository.addSkill(new Skill("Pichelar"));
        skillRepository.addSkill(new Skill("Pintar"));
        skillRepository.addSkill(new Skill("Cortar relva"));

    }

    private void addJobs() {

        //get job repository
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        jobRepository.addJob(new Job("Picheleiro"));
        jobRepository.addJob(new Job("Carpinteiro"));
        jobRepository.addJob(new Job("Jardineiro" ));
    }

    private void addCollaborators() {

        //get job repository
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();

        collaboratorRepository.addCollaborator(new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789));
        collaboratorRepository.addCollaborator(new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799));
        collaboratorRepository.addCollaborator(new Collaborator("Jonas", "25-09-1994", "08-07-2023", "Rio Tinto", 93761984, "jonas@gmail.pt", 275315949,"CC", 734691829));

        Collaborator collaborator1 = collaboratorRepository.getCollaboratorByID(123456789);
        Collaborator collaborator2 = collaboratorRepository.getCollaboratorByID(234516799);
        Collaborator collaborator3 = collaboratorRepository.getCollaboratorByID(734691829);

        Skill skill1 = new Skill("Pichelar");
        Skill skill2 = new Skill("Pintar");
        Skill skill3 = new Skill("Cortar relva");

        collaborator1.addSkill(skill1);
        collaborator2.addSkill(skill2);
        collaborator3.addSkill(skill3);

        List<Collaborator> list1 = new ArrayList<>();
        list1.add(collaborator1);
        list1.add(collaborator2);
        List<Collaborator> list2 = new ArrayList<>();
        list2.add(collaborator2);
        list2.add(collaborator3);
        List<Collaborator> list3 = new ArrayList<>();
        list3.add(collaborator1);
        list3.add(collaborator3);

        teamRepository.createTeam(list1, 1);
        teamRepository.createTeam(list2, 2);
        teamRepository.createTeam(list3, 3);
    }

    private void addVehicles() {

        //get job repository
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);

        vehicleRepository.addVehicle(new Vehicle("Porsche", "Turbo", "Car", 1275, 1820, 30000, date1, date2, 10000, "00-AA-00", date3));
        vehicleRepository.addVehicle(new Vehicle("Ford", "Focus", "Car", 1325, 1950, 30500, date1, date2, 15000, "01-AB-01", date3));
        vehicleRepository.addVehicle(new Vehicle("Fiat", "Punto", "Car", 1005, 1020, 25000, date1, date2, 7000, "22-AC-01", date3));



    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);

        authenticationRepository.addUserWithRole("Tiago Soares", "1231246@isep.ipp.pt", "TIAgo46",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Diogo Cabral", "1230603@isep.ipp.pt", "DIOgo03",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Diogo Vilela", "1230804@isep.ipp.pt", "DIOgo04",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Guilherme Miranda", "1230582@isep.ipp.pt", "GUImi82",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Hugo Ramos", "1231219@isep.ipp.pt", "HUGor19",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Tiago Soares", "1231246@isep.ipp.pt", "isep",
                AuthenticationController.ROLE_VFM);



        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "HRMpw33",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "VFMpw44",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("GSM", "gsm@this.app", "GSMpw55",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Jonas", "jonas@gmail.pt", "JONpw78",
                AuthenticationController.ROLE_COLLABORATOR);
                authenticationRepository.addUserWithRole("Alfredo", "alfredo64@gmail.pt", "ALFpw12",
                AuthenticationController.ROLE_COLLABORATOR);
        authenticationRepository.addUserWithRole("Alberto", "albertoogrande@gmail.pt", "ALBpw45",
                AuthenticationController.ROLE_COLLABORATOR);
    }

}