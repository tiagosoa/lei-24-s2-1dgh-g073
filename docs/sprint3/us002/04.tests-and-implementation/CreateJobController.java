package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class CreateJobController {

    private OrganizationRepository organizationRepository;
    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;

    // Default constructor that initializes the repositories
    public CreateJobController() {
        this.organizationRepository = getOrganizationRepository();
        this.jobRepository = getJobRepository();
        this.authenticationRepository = getAuthenticationRepository();
    }

    // Constructor that allows passing repositories for testing purposes
    public CreateJobController(OrganizationRepository organizationRepository,
                               JobRepository jobRepository,
                               AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.jobRepository = jobRepository;
        this.authenticationRepository = authenticationRepository;
    }

    // Method to get the JobRepository instance
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    // Method to get the OrganizationRepository instance
    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;
    }

    // Method to get the AuthenticationRepository instance
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    // Method to create a new job for a given organization
    public Optional<Job> createJob(String name, HRM hrm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByHRM(hrm);
        Optional<Job> newJob = Optional.empty();

        if (organization.isPresent()) {
            newJob = organization.get().createJob(name);
        }
        return newJob;
    }

    // Method to retrieve the HRM from the current user session
    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

}