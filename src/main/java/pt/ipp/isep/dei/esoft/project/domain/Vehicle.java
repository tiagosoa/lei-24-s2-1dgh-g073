package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;
import java.time.LocalDate;

public class Vehicle {
    private final String model;
    private final String brand;
    private final String type;
    private final double tareWeight;
    private final double grossWeight;
    private double currentKm;
    private final LocalDate registerDate;
    private final LocalDate acquisitionDate;
    private final int maintenanceFrequencyKm;
    private final String plateNumber;
    private LocalDate lastMaintenanceDate;

    // Constructor including all characteristics
    public Vehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                   double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        this.model = model;
        validateVehicle(model);
        this.brand = brand;
        validateVehicle(brand);
        this.type = type;
        validateVehicle(type);
        this.tareWeight = tareWeight;
        validateVehicle(tareWeight);
        this.grossWeight = grossWeight;
        validateVehicle(grossWeight);
        this.currentKm = currentKm;
        validateVehicle(currentKm);
        this.registerDate = registerDate;
        validateVehicle(registerDate);
        this.acquisitionDate = acquisitionDate;
        validateVehicle(acquisitionDate);
        this.maintenanceFrequencyKm = maintenanceFrequencyKm;
        validateVehicle(maintenanceFrequencyKm);
        this.plateNumber = plateNumber;
        validateVehicle(plateNumber);
        this.lastMaintenanceDate = lastMaintenanceDate;
        validateVehicle(lastMaintenanceDate);
    }

    private void validateVehicle(String trait) {
        //TODO: missing from the diagrams
        if (trait == null || trait.isEmpty()) {
            throw new IllegalArgumentException("This trait cannot be null or empty.");
        }
    }

    private void validateVehicle(Double trait) {
        //TODO: missing from the diagrams
        if (trait == null) {
            throw new IllegalArgumentException("This trait cannot be null or empty.");
        }
    }

    private void validateVehicle(int trait) {
        //TODO: missing from the diagrams
        if (trait <= 0) {
            throw new IllegalArgumentException("This trait cannot be null or empty.");
        }
    }

    private void validateVehicle(LocalDate trait) {
        //TODO: missing from the diagrams
        if (trait == null) {
            throw new IllegalArgumentException("This trait cannot be null or empty.");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return brand.equals(vehicle.brand) && model.equals(vehicle.model) && type.equals(this.type) && tareWeight == this.tareWeight && grossWeight == this.grossWeight && currentKm == this.currentKm && registerDate.equals(this.registerDate) && acquisitionDate.equals(this.acquisitionDate) && maintenanceFrequencyKm == this.maintenanceFrequencyKm && plateNumber.equals(vehicle.plateNumber) && lastMaintenanceDate.equals(this.lastMaintenanceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model,brand,type,tareWeight,grossWeight,currentKm,registerDate,acquisitionDate,maintenanceFrequencyKm,plateNumber,lastMaintenanceDate);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Vehicle clone() {
        return new Vehicle(this.model,this.brand,this.type,this.tareWeight,this.grossWeight,this.currentKm,this.registerDate,this.acquisitionDate,this.maintenanceFrequencyKm, this.plateNumber, this.lastMaintenanceDate);
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getCurrentKm() {
        return currentKm;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public int getMaintenanceFrequencyKm() {
        return maintenanceFrequencyKm;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDate getLastMaintenanceDate() {return lastMaintenanceDate;}

    public void setCurrentKilometers(int currentKm) {
        this.currentKm = currentKm;
    }

    public void setMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
}