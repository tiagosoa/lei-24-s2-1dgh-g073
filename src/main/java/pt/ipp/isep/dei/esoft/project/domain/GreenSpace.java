package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class GreenSpace {
    private final String name;
    private final String type;
    private final double area;
    private final GSM gsm;

    public GreenSpace(String name, String type, double area, GSM gsm) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.gsm = gsm;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public GSM getGSM() {
        return gsm;
    }

    public boolean isOfName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GreenSpace)) return false;
        GreenSpace that = (GreenSpace) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public GreenSpace clone() {
        return new GreenSpace(name, type, area, gsm);
    }
}
