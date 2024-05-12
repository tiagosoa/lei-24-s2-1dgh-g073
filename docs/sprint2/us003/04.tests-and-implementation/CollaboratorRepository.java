package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

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
        this.collaborators = Organization.getCollaboratorList();
        if (this.collaborators == null) {
            // Initialize the collaborators list if it's null
            this.collaborators = new ArrayList<>();
        }
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