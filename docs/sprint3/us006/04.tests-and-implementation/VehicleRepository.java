package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing vehicles.
 */
public class VehicleRepository {

    private List<Vehicle> vehicles;

    /**
     * Constructor that initializes the repository with vehicles from the organization.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
        List<Vehicle> orgVehicles = Organization.getVehicleList();
        if (orgVehicles != null) {
            this.vehicles.addAll(orgVehicles);
        }
    }

    /**
     * Retrieves a vehicle by its plate number.
     *
     * @param plateNumber the plate number of the vehicle to retrieve
     * @return the vehicle with the specified plate number
     * @throws IllegalArgumentException if the vehicle does not exist
     */
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return vehicles.stream()
                .filter(v -> v.getPlateNumber().equals(plateNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vehicle does not exist."));
    }

    /**
     * Adds a new vehicle to the repository.
     *
     * @param vehicle the vehicle to add
     * @return an Optional containing the added vehicle if successful, empty otherwise
     */
    public Optional<Vehicle> add(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            vehicles.add(vehicle.clone());
            return Optional.of(vehicle.clone());
        }
        return Optional.empty();
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return vehicles.stream().noneMatch(v -> v.getPlateNumber().equals(vehicle.getPlateNumber()));
    }

    /**
     * Retrieves all vehicles in the repository.
     *
     * @return a list of all vehicles in the repository
     */
    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }
}