package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    private final List<Vehicle> vehicles;
    public VehicleRepository() {
        vehicles = new ArrayList<>();
    }

    /**
     * This method returns an exsiting Job by its name.
     *
     * @param model The name of the job category to be created.
     * @return The job name
     * @throws IllegalArgumentException if the job does not exist, which should never happen.
     */
    public Vehicle getVehiclebyModel(String model) {
        Vehicle vehicle = null;
        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getModel().equals(model)){
                vehicle = existingVehicle;
                break;
            }
        }
        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle model requested for [" + model + "] does not exist.");
        }
        return vehicle;
    }

    public Vehicle getVehiclebyBrand(String brand) {
        Vehicle vehicle = null;
        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getModel().equals(brand)){
                vehicle = existingVehicle;
                break;
            }
        }
        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle brand requested for [" + brand + "] does not exist.");
        }
        return vehicle;
    }

    public Vehicle getVehiclebyType(String type) {
        Vehicle vehicle = null;
        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getModel().equals(type)){
                vehicle = existingVehicle;
                break;
            }
        }
        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle type requested for [" + type + "] does not exist.");
        }
        return vehicle;
    }

    public Optional<Vehicle> add(Vehicle vehicle) {

        Optional<Vehicle> newVehicle = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(vehicle)) {
            newVehicle = Optional.of(vehicle.clone());
            operationSuccess = vehicles.add(newVehicle.get());
        }

        if (!operationSuccess) {
            newVehicle = Optional.empty();
        }

        return newVehicle;
    }

    private boolean validateSkill(Vehicle vehicle) {
        boolean isValid = !vehicles.contains(vehicle);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Vehicle> getVehicles() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(vehicles);
    }
}