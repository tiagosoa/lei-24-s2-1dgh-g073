package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Objects;

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

    public Vehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                   double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        validateTrait(model);
        validateTrait(brand);
        validateTrait(type);
        validateTrait(tareWeight);
        validateTrait(grossWeight);
        validateTrait(currentKm);
        validateTrait(registerDate);
        validateTrait(acquisitionDate);
        validateTrait(maintenanceFrequencyKm);
        validateTrait(plateNumber);
        validateTrait(lastMaintenanceDate);

        this.model = model;
        this.brand = brand;
        this.type = type;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequencyKm = maintenanceFrequencyKm;
        this.plateNumber = plateNumber;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public Vehicle(String plateNumber) {
        validateTrait(plateNumber);

        this.model = "";
        this.brand = "";
        this.type = "";
        this.tareWeight = 0;
        this.grossWeight = 0;
        this.currentKm = 0;
        this.registerDate = LocalDate.now();
        this.acquisitionDate = LocalDate.now();
        this.maintenanceFrequencyKm = 0;
        this.plateNumber = plateNumber;
        this.lastMaintenanceDate = LocalDate.now();
    }

    private void validateTrait(Object trait) {
        if (trait == null || (trait instanceof String && ((String) trait).isEmpty()) || (trait instanceof Number && ((Number) trait).doubleValue() <= 0)) {
            throw new IllegalArgumentException("Trait cannot be null, empty, or less than or equal to 0.");
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
        return Objects.equals(plateNumber, vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }

    public Vehicle clone() {
        return new Vehicle(model, brand, type, tareWeight, grossWeight, currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);
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

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setCurrentKm(double currentKm) {
        this.currentKm = currentKm;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", Brand: " + brand + ", Type: " + type + ", Tare Weight: " + tareWeight + ", Gross Weight: " + grossWeight + ", Current Km: " + currentKm + ", Register Date: " + registerDate + ", Acquisition Date: " + acquisitionDate + ", Maintenance Frequency Km: " + maintenanceFrequencyKm + ", Plate Number: " + plateNumber + ", Last Maintenance Date: " + lastMaintenanceDate;
    }
}
