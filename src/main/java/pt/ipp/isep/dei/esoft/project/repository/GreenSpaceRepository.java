package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
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
     * Constructs a new GreenSpaceRepository and initializes the greenSpaces list.
     */
    public GreenSpaceRepository() {
        // Initialize the greenSpaces list with a new ArrayList
        this.greenSpaces = new ArrayList<>();
    }

    /**
     * Retrieves an existing GreenSpace by its name.
     *
     * @param name The name of the greenSpace to retrieve.
     * @return The GreenSpace with the specified name.
     * @throws IllegalArgumentException if the greenSpace does not exist.
     */
    public GreenSpace getGreenSpaceByName(String name) {
        // Iterate through the greenSpaces list to find a matching greenSpace
        for (GreenSpace existingGreenSpace : greenSpaces) {
            if (existingGreenSpace.isOfName(name)) {
                return existingGreenSpace;
            }
        }
        throw new IllegalArgumentException("GreenSpace does not exist.");
    }

    public GreenSpace findByName(String name) {
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }

    /**
     * Registers a new green space and adds it to the organization.
     *
     * @param name The name of the green space.
     * @param type The type of the green space.
     * @param area The area of the green space.
     * @param gsm The GSM associated with the green space.
     * @return an optional containing the registered green space, or empty if registration fails
     */
    public Optional<GreenSpace> registerGreenSpace(String name, String type, double area, GSM gsm) {
        // Create a new GreenSpace object
        GreenSpace greenSpace = new GreenSpace(name, type, area, gsm);
        // Add the green space to the list if validation passes
        if (addGreenSpace(greenSpace)) {
            return Optional.of(greenSpace);
        }
        return Optional.empty();
    }

    // Private helper methods for adding entities

    /**
     * Adds a green space to the list of green spaces.
     *
     * @param greenSpace The green space to add.
     * @return true if the green space is added successfully, false otherwise
     */
    public boolean addGreenSpace(GreenSpace greenSpace) {
        // Validate the green space before adding it to the list
        if (validateGreenSpace(greenSpace)) {
            // Add a clone of the green space to the list
            return greenSpaces.add(greenSpace.clone());
        }
        return false;
    }

    /**
     * Validates if a green space is not already in the list.
     *
     * @param greenSpace The green space to validate.
     * @return true if the green space is valid, false if it already exists in the list
     */
    private boolean validateGreenSpace(GreenSpace greenSpace) {
        // Check if the green space is not already in the list
        return !greenSpaces.contains(greenSpace);
    }

    /**
     * Returns a copy of the list of green spaces.
     *
     * @return a list of green spaces
     */
    public List<GreenSpace> getGreenSpaceList() {
        // Return a copy of the list of green spaces
        return new ArrayList<>(greenSpaces);
    }

    /**
     * Returns the list of green spaces managed by the given GSM.
     *
     * @param gsm The GSM to retrieve managed green spaces for.
     * @return a list of green spaces managed by the given GSM
     */
    public List<GreenSpace> getManagedGreenSpaces(GSM gsm) {
        List<GreenSpace> managedGreenSpaces = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getGSM().equals(gsm)) {
                managedGreenSpaces.add(greenSpace);
            }
        }
        return managedGreenSpaces;
    }
}

