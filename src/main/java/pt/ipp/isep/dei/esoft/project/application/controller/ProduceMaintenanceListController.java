package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class ProduceMaintenanceListController {

    private VehicleRepository vehicleRepository;

    public ProduceMaintenanceListController() {
        this.vehicleRepository = getVehicleRepository();
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository == null ? Repositories.getInstance().getVehicleRepository() : vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        // Retrieve vehicles from the repository
        return vehicleRepository.getVehicles();
    }
}
