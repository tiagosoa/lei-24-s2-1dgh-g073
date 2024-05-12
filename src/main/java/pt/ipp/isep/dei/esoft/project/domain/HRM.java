package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a Human Resource Manager (HRM) in the system.
 */
public class HRM {
    private final String email;
    private String name;
    private String position;
    private String phone;

    /**
     * Constructs an HRM object with the specified email.
     *
     * @param email the email of the HRM
     */
    public HRM(String email) {
        this.email = email;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HRM)) {
            return false;
        }
        HRM hrm = (HRM) o;
        return email.equals(hrm.email);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value based on the email
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Checks if the HRM has the specified email.
     *
     * @param email the email to check
     * @return true if the HRM has the specified email, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Creates a clone of the HRM object.
     *
     * @return a clone of the current instance
     */
    public HRM clone() {
        return new HRM(this.email);
    }
}