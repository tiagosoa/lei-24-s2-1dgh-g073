package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private List<Collaborator> collaborators;

    /**
     * This method is the constructor of the repository.
     */
    public CollaboratorRepository() {
        this.collaborators = Organization.getCollaboratorList();
        if (this.collaborators == null) {
            // Initialize the collaborators list if it's null
            this.collaborators = new ArrayList<>();
        }
    }

    /**
     * This method returns an existing Collaborator by its ID number.
     *
     * @param IDnumber        The ID number of the collaborator to be registered.
     * @return The collaborator name
     * @throws IllegalArgumentException if the collaborator does not exist, which should never happen.
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
     * This method adds a new collaborator to the list.
     *
     * @param collaborator The collaborator to be added.
     * @return An optional signaling if the operation is successful, or an empty optional otherwise.
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
     * This method validates the collaborator, checking for duplicates.
     *
     * @param collaborator The collaborator to be validated.
     * @return True if the collaborator is valid.
     */

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * This method updates the collaborator, when assigned with a job or skill.
     *
     * @param collaboratorToUpdate The collaborator to be updated.
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
         * This method returns a defensive (immutable) copy of the list of collaborators.
         *
         * @return The list of collaborators.
         */
    public List<Collaborator> getCollaboratorList() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }
}