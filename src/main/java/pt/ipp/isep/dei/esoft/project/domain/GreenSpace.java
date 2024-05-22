package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a greenSpace.
 */
public class GreenSpace {
    private final String name, type;

    private final double area;

    /**
     * Creates a new GreenSpace with the given type and area.
     *
     * @param type the type of the greenSpace.
     * @param area the area of the greenSpace.
     */
    public GreenSpace(String name, String type, double area) {
        this.name = name;
        validateGreenSpaceName(name);
        this.type = type;
        validateGreenSpaceType(type);
        this.area = area;
        validateGreenSpaceArea(area);
    }

    private void validateGreenSpaceName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("GreenSpace name is null or empty.");
        }
    }


    private void validateGreenSpaceType(String type) {
        if (type == null || type.isEmpty() || !(type.equals("Garden") || type.equals("Medium-sized Park") || type.equals("Large-sized Park"))){
            throw new IllegalArgumentException("GreenSpace type is invalid, or null or empty.");
        }
    }

    private void validateGreenSpaceArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("GreenSpace area value is invalid.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GreenSpace)) {
            return false;
        }
        GreenSpace greenSpace = (GreenSpace) o;
        return Objects.equals(type, greenSpace.type) && Objects.equals(area, greenSpace.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, area);
    }

    /**
     * Creates a clone of the current GreenSpace instance.
     *
     * @return A clone of the current instance.
     */
    public GreenSpace clone() {
        return new GreenSpace(this.name, this.type, this.area);
    }

    /**
     * Gets the name of the greenSpace.
     *
     * @return the name of the greenSpace
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the greenSpace.
     *
     * @return the type of the greenSpace
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the area of the greenSpace.
     *
     * @return the area of the greenSpace
     */
    public double getArea() {
        return area;
    }

    public boolean isOfNameTypeAndArea(String name, String type, double area) {
        return this.name.equals(name) && this.type.equals(type) && this.area == area;
    }
}