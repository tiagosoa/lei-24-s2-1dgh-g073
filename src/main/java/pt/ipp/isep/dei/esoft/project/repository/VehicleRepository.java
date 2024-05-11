package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    private List<Vehicle> vehicles;
    public VehicleRepository() {
        this.vehicles = Organization.getVehicles();
        if (this.vehicles == null) {
            // Initialize the vehicles list if it's null
            this.vehicles = new ArrayList<>();
        }
    }

    /**
     * This method returns an existing Vehicle by its plate number.
     *
     * @param plateNumber The name of the job category to be created.
     * @return The vehicle.
     * @throws IllegalArgumentException if the vehicle does not exist, which should never happen.
     */
    public Vehicle getVehiclebyPlateNumber(String plateNumber) {
        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getPlateNumber().equals(plateNumber)) {
                return existingVehicle;
            }
        }
        return null;
    }
    public Optional<Vehicle> add(Vehicle vehicle) {

        Optional<Vehicle> newVehicle = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicle(vehicle)) {
            newVehicle = Optional.of(vehicle.clone());
            operationSuccess = vehicles.add(newVehicle.get());
        }

        if (!operationSuccess) {
            newVehicle = Optional.empty();
        }

        return newVehicle;
    }

    private boolean validateVehicle(Vehicle vehicle) {
        boolean isValid = !vehicles.contains(vehicle);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of vehicles.
     *
     * @return The list of vehicles.
     */
    public List<Vehicle> getVehicles() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(vehicles);
    }
}