package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class RegisterCollaboratorController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;


    //Repository instances are obtained from the Repositories class
    public RegisterCollaboratorController() {
        getOrganizationRepository();
        getCollaboratorRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterCollaboratorController(OrganizationRepository organizationRepository,
                                          CollaboratorRepository collaboratorRepository,
                                          AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
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

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, String doctype, int IDnumber, HRM hrm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByHRM(hrm);

        Optional<Collaborator> newCollaborator = Optional.empty();

        if (organization.isPresent()) {
            newCollaborator = organization.get()
                    .registerCollaborator(name, birthdate, admissiondate, address, mobile, email, doctype, IDnumber, hrm);
        }
        return newCollaborator;
    }

    public HRM getHRMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new HRM(email.getEmail());
    }

}