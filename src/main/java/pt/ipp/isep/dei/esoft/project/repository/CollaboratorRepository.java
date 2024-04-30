package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private final List<Collaborator> collaborators;
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    /**
     * This method returns an exsiting Collaborator by its ID number.
     *
     * @param IDnumber        The ID number of the collaborator to be registered.
     * @return The collaborator name
     * @throws IllegalArgumentException if the collaborator does not exist, which should never happen.
     */
    public Collaborator getCollaboratorByID(int IDnumber) {
        Collaborator collaborator = null;
        for (Collaborator existingCollaborator : collaborators) {
            if (existingCollaborator.getIDNumber() == IDnumber){
                collaborator = existingCollaborator;
                break;
            }
        }
        if (collaborator == null) {
            throw new IllegalArgumentException(
                    "Collaborator ID number requested for [" + IDnumber + "] does not exist.");
        }
        return collaborator;
    }

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

    private boolean validateCollaborator(Collaborator collaborator) {
        boolean isValid = !collaborators.contains(collaborator);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of collaborators.
     *
     * @return The list of collaborators.
     */
    public List<Collaborator> getCollaborators() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }
}