package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterCollaboratorController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;


    //Repository instances are obtained from the Repositories class
    public RegisterCollaboratorController() {
        getOrganizationRepository();
        getCollaboratorRepository();
        getAuthenticationRepository();
        getJobRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterCollaboratorController(OrganizationRepository organizationRepository,
                                          CollaboratorRepository collaboratorRepository,
                                          AuthenticationRepository authenticationRepository,
                                          JobRepository jobRepository) {
        this.organizationRepository = organizationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
        this.jobRepository = jobRepository;
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

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the JobRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate,
                                                       String address, int mobile, String email, String doctype,
                                                       int IDnumber, HRM hrm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByHRM(hrm);

        Optional<Collaborator> newCollaborator = Optional.empty();

        if (organization.isPresent()) {
            newCollaborator = organization.get()
                    .registerCollaborator(name, birthdate, admissiondate, address, mobile, email, doctype, IDnumber, hrm);
            if (newCollaborator.isPresent()) {
                // Initialize job list for the collaborator
                newCollaborator.get().setJobs(new ArrayList<>());
            }
        }
        if (organization.isPresent()) {

            newCollaborator.ifPresent(collaboratorRepository::add); // Add collaborator to repository

            return newCollaborator;
        }

        return Optional.empty(); // Return empty if organization is not present
    }
    public void assignJobToCollaborator(int collaboratorID, String jobName, HRM hrm) {
        Optional<Collaborator> optCollaborator = Optional.of(collaboratorRepository.getCollaboratorByID(collaboratorID));
        Optional<Job> optJob = Optional.of(jobRepository.getJobByName(jobName, hrm));

        if (optCollaborator.isPresent() && optJob.isPresent()) {
            Collaborator collaborator = optCollaborator.get();
            Job job = optJob.get();

            // Add job to the collaborator's job list
            collaborator.getJobs().add(job);

            // Set collaborator for the job
            job.setCollaborator(collaborator);
        } else {
            throw new IllegalArgumentException("Collaborator or Job not found.");
        }
    }

    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

}