package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.time.LocalDate;
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
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with plate number " + plateNumber + " does not exist."));
    }


    /**
     * Registers maintenance for a vehicle with the given plate number, current kilometers, and maintenance date.
     *
     * @param plateNumber The plate number of the vehicle.
     * @param currentKm The current kilometers of the vehicle.
     * @param maintenanceDate The date of the maintenance.
     * @return true if maintenance registration is successful, false otherwise.
     */
    public boolean registerMaintenance(String plateNumber, int currentKm, LocalDate maintenanceDate) {
        try {
            Vehicle vehicle = getVehicleByPlateNumber(plateNumber);

            if (maintenanceDate.isAfter(LocalDate.now())) {
                System.out.println("Maintenance date cannot be in the future.");
                return false;
            }

            vehicle.setCurrentKm(currentKm);
            vehicle.setLastMaintenanceDate(maintenanceDate);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * Creates a new vehicle and adds it to the organization.
     *
     * @param model                the model of the vehicle
     * @param brand                the brand of the vehicle
     * @param type                 the type of the vehicle
     * @param tareWeight           the tare weight of the vehicle
     * @param grossWeight          the gross weight of the vehicle
     * @param currentKm            the current kilometers of the vehicle
     * @param registerDate         the registration date of the vehicle
     * @param acquisitionDate      the acquisition date of the vehicle
     * @param maintenanceFrequencyKm the maintenance frequency in kilometers
     * @param plateNumber          the plate number of the vehicle
     * @param lastMaintenanceDate  the date of the last maintenance
     * @return an optional containing the created vehicle, or empty if creation fails
     */
    public Optional<Vehicle> createVehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                                           double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        Vehicle vehicle = new Vehicle(model, brand, type, tareWeight, grossWeight, currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);
        if (addVehicle(vehicle)) {
            return Optional.of(vehicle);
        }
        return Optional.empty();
    }
    public boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return vehicles.add(vehicle.clone());
        }
        return false;
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

    public List<String> produceMaintenanceList() {
        // Retrieve vehicles needing maintenance
        List<String> maintenanceList = new ArrayList<>();
        List<Vehicle> vehicles = getVehicles();

        // Iterate over vehicles and add data to the maintenance list
        for (Vehicle vehicle : vehicles) {
            String plate = vehicle.getPlateNumber();
            String brand = vehicle.getBrand();
            String model = vehicle.getModel();
            double currentKms = vehicle.getCurrentKm();
            LocalDate lastMaintenanceDate = vehicle.getLastMaintenanceDate();
            int maintenanceFrequencyKm = vehicle.getMaintenanceFrequencyKm();
            LocalDate nextMaintenanceDate = lastMaintenanceDate.plusDays(maintenanceFrequencyKm);
            double nextMaintenanceKms = lastMaintenanceDate.plusDays(maintenanceFrequencyKm).until(LocalDate.now(), java.time.temporal.ChronoUnit.DAYS);

            if (currentKms >= nextMaintenanceKms) {
                // Format data and add to the maintenance list
                String maintenanceData = String.format("%s %s %s %.0f %s %s",
                        plate, brand, model, currentKms, lastMaintenanceDate, nextMaintenanceDate);
                maintenanceList.add(maintenanceData);
            }
        }

        return maintenanceList;
    }
}