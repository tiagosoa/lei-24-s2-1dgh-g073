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
            this.greenSpaces = new ArrayList<>();
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
     * Registers a new green space and adds it to the organization.
     *
     * @param type the type of the green space
     * @return an optional containing the registered green space, or empty if registration fails
     */
    public Optional<GreenSpace> registerGreenSpace(String type, double area) {
        GreenSpace greenSpace = new GreenSpace(type, area);
        if (addGreenSpace(greenSpace)) {
            return Optional.of(greenSpace);
        }
        return Optional.empty();
    }

    // Private helper methods for adding entities


    public boolean addGreenSpace(GreenSpace greenSpace) {
        if (validateGreenSpace(greenSpace)) {
            return greenSpaces.add(greenSpace.clone());
        }
        return false;
    }

    private boolean validateGreenSpace(GreenSpace greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    /**
     * Returns a copy of the list of green spaces.
     *
     * @return a list of green spaces
     */
    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaces;
    }
}