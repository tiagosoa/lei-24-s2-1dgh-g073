package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Collaborator objects.
 */
public class CollaboratorRepository {

    private List<Collaborator> collaborators;

    /**
     * Constructor for CollaboratorRepository.
     * Initializes the list of collaborators.
     */
    public CollaboratorRepository() {
        this.collaborators = new ArrayList<>();
    }

    /**
     * Retrieves a Collaborator by its ID number.
     *
     * @param IDnumber The ID number of the collaborator to retrieve.
     * @return The Collaborator with the specified ID.
     * @throws IllegalArgumentException if the collaborator does not exist.
     */
    public Collaborator getCollaboratorByID(int IDnumber) {
        for (Collaborator existingCollaborator : collaborators) {
            if (existingCollaborator.getIDNumber() == IDnumber) {
                return existingCollaborator;
            }
        }
        throw new IllegalArgumentException("Collaborator does not exist.");
    }

    /**
     * Registers a new collaborator with the organization.
     *
     * @param name          the name of the collaborator
     * @param birthdate     the birthdate of the collaborator
     * @param admissiondate the admission date of the collaborator
     * @param address       the address of the collaborator
     * @param mobile        the mobile number of the collaborator
     * @param email         the email of the collaborator
     * @param taxpayer      the taxpayer number of the collaborator
     * @param doctype       the document type of the collaborator
     * @param IDnumber      the ID number of the collaborator
     * @return an optional containing the registered collaborator, or empty if registration fails
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        Collaborator collaborator = new Collaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber);
        if (addCollaborator(collaborator)) {
            return Optional.of(collaborator);
        }
        return Optional.empty();
    }

    public boolean addCollaborator(Collaborator collaborator) {
        if (validateCollaborator(collaborator)) {
            return collaborators.add(collaborator.clone());
        }
        return false;
    }

    /**
     * Adds a new collaborator to the list.
     *
     * @param collaborator The collaborator to add.
     * @return An optional containing the added collaborator if successful, empty optional otherwise.
     */
    public Optional<Collaborator> add(Collaborator collaborator) {
        Optional<Collaborator> newCollaborator = Optional.empty();
        boolean operationSuccess = false;

        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            operationSuccess = collaborators.add(newCollaborator.get());
        }

        if (!operationSuccess) {
            newCollaborator = Optional.empty();
        }

        return newCollaborator;
    }

    /**
     * Validates a collaborator to avoid duplicates.
     *
     * @param collaborator The collaborator to validate.
     * @return True if the collaborator is valid (not a duplicate).
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Updates a collaborator when assigned with a job or skill.
     *
     * @param collaboratorToUpdate The collaborator to update.
     */
    public void updateCollaborator(Collaborator collaboratorToUpdate) {
        for (int i = 0; i < collaborators.size(); i++) {
            Collaborator collaborator = collaborators.get(i);
            if (collaborator.getEmail().equals(collaboratorToUpdate.getEmail())) {
                collaborators.set(i, collaboratorToUpdate);
                return; // Found and updated collaborator, exit the loop
            }
        }
        throw new IllegalArgumentException("Collaborator not found.");
    }

    /**
     * Returns a defensive (immutable) copy of the list of collaborators.
     *
     * @return The list of collaborators.
     */
    public List<Collaborator> getCollaboratorList() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }
}