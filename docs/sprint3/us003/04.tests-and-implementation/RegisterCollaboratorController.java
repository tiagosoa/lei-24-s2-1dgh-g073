package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterCollaboratorController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;

    public RegisterCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        this.organizationRepository = repositories.getOrganizationRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.jobRepository = repositories.getJobRepository();
    }

    public RegisterCollaboratorController(OrganizationRepository organizationRepository,
                                          CollaboratorRepository collaboratorRepository,
                                          AuthenticationRepository authenticationRepository,
                                          JobRepository jobRepository) {
        this.organizationRepository = organizationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
        this.jobRepository = jobRepository;
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate,
                                                       String address, int mobile, String email, int taxpayer, String doctype,
                                                       int IDnumber, HRM hrm) {
        Optional<Organization> organization = organizationRepository.getOrganizationByHRM(hrm);

        Optional<Collaborator> newCollaborator = Optional.empty();

        if (organization.isPresent()) {
            newCollaborator = organization.get()
                    .registerCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);
            if (newCollaborator.isPresent()) {
                newCollaborator.get().setJobs(new ArrayList<>());
            }
        }

        if (organization.isPresent()) {
            newCollaborator.ifPresent(collaboratorRepository::add);
            return newCollaborator;
        }

        return Optional.empty();
    }

    public void assignJobToCollaborator(int collaboratorID, String jobname) {
        Collaborator collaborator = collaboratorRepository.getCollaboratorByID(collaboratorID);

        if (collaborator == null) {
            throw new IllegalArgumentException("Collaborator not found.");
        }

        Job job = jobRepository.getJobByName(jobname);
        if (job != null) {
            collaborator.addJob(job);
        }

        collaboratorRepository.updateCollaborator(collaborator);
    }

    public HRM getHRMFromSession() {
        Email email = this.authenticationRepository.getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }
}