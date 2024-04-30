package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Vehicle {
    private final String model;
    private final String brand;
    private final String type;

    private VFM vfm;

    public Vehicle(String model, String brand, String type, VFM vfm) {

        validateVehicle(model);
        this.model = model;
        validateVehicle(brand);
        this.brand = brand;
        validateVehicle(model);
        this.type = type;
        this.vfm = vfm;
    }

    private void validateVehicle(String name) {
        //TODO: missing from the diagrams
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Vehicle name cannot be null or empty.");

        } else if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Vehicle name cannot contain special characters or digits.");
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
        return model.equals(vehicle.model) && vfm.equals(vehicle.vfm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model,brand,type, vfm);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Vehicle clone() {
        return new Vehicle(this.model,this.brand,this.type, this.vfm);
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
}