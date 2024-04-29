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
     * @param name            The name of the collaborator to be registered.
     * @param birthdate       The date of birth of the collaborator to be registered.
     * @param admissiondate   The date of admission of the collaborator to be registered.
     * @param address         The address where the collaborator to be registered resides.
     * @param mobile          The mobile phone number of the collaborator to be registered.
     * @param email           The email of the collaborator to be registered.
     * @param doctype         The type of documentation the collaborator to be registered has.
     * @param IDnumber        The ID number of the collaborator to be registered.
     * @return The collaborator name
     * @throws IllegalArgumentException if the collaborator does not exist, which should never happen.
     */
    public Collaborator getCollaboratorByID(String name, String birthdate, String admissiondate, String address, int mobile, String email, String doctype, int IDnumber, HRM hrm) {
        Collaborator newCollaborator = new Collaborator(name, birthdate, admissiondate, address, mobile, email, doctype, IDnumber, hrm);
        Collaborator collaborator = null;
        if (collaborators.contains(newCollaborator)) {
            collaborator = collaborators.get(collaborators.indexOf(newCollaborator));
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