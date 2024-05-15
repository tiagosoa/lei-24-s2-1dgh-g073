package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing greenSpaces.
 */
public class GreenSpaceRepository {

    private List<GreenSpace> greenSpaces;

    /**
     * Constructs a new GreenSpaceRepository.
     * Initializes the greenSpaces list with the existing greenSpaces from the Organization.
     * If the greenSpaces list is null, it creates a new empty list.
     */
    public GreenSpaceRepository() {
        this.greenSpaces = Organization.getGreenSpaceList();
        if (this.greenSpaces == null) {
            this.greenSpaces = new ArrayList<>();
        }
    }

    /**
     * Retrieves an existing GreenSpace by its type and area.
     *
     * @param type The type of the greenSpace to retrieve.
     * @param area The area of the greenSpace to retrieve.
     * @return The GreenSpace with the specified type and area.
     * @throws IllegalArgumentException if the greenSpace does not exist.
     */
    public GreenSpace getGreenSpaceByName(String type, double area) {
        for (GreenSpace existingGreenSpace : greenSpaces) {
            if (existingGreenSpace.isOfTypeAndArea(type, area)) {
                return existingGreenSpace;
            }
        }
        throw new IllegalArgumentException("GreenSpace does not exist.");
    }

    /**
     * Adds a new GreenSpace to the repository.
     *
     * @param greenSpace The GreenSpace to add.
     * @return An Optional containing the added GreenSpace if successful, empty otherwise.
     */
    public Optional<GreenSpace> add(GreenSpace greenSpace) {
        Optional<GreenSpace> newGreenSpace = Optional.empty();
        boolean operationSuccess = false;

        if (validateGreenSpace(greenSpace)) {
            newGreenSpace = Optional.of(greenSpace.clone());
            operationSuccess = greenSpaces.add(newGreenSpace.get());
        }

        if (!operationSuccess) {
            newGreenSpace = Optional.empty();
        }

        return newGreenSpace;
    }

    /**
     * Validates if a GreenSpace can be added to the repository.
     *
     * @param greenSpace The GreenSpace to validate.
     * @return true if the GreenSpace is valid and can be added, false otherwise.
     */
    private boolean validateGreenSpace(GreenSpace greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    /**
     * Returns a defensive (immutable) copy of the list of greenSpaces.
     *
     * @return A copy of the list of greenSpaces.
     */
    public List<GreenSpace> getGreenSpaceList() {
        return List.copyOf(greenSpaces);
    }
}