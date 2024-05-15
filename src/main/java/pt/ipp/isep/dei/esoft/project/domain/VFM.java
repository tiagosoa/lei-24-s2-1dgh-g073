package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a Vehicle and Equipment Fleet Manager (VFM) in the system.
 */
public class VFM {
    private final String email; // unique identifier for the VFM
    private String name; // name of the VFM
    private String position; // position held by the VFM
    private int phone; // phone number of the VFM

    /**
     * Constructor for creating a VFM with the specified email.
     *
     * @param email the email of the VFM
     */
    public VFM(String email) {
        this.email = email;
    }

    /**
     * Checks if this VFM is equal to another object based on email.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VFM)) {
            return false;
        }
        VFM vfm = (VFM) o;
        return email.equals(vfm.email);
    }

    /**
     * Generates a hash code for the VFM based on email.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Checks if the VFM has the specified email.
     *
     * @param email the email to check
     * @return true if the VFM has the specified email, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Creates a clone of the VFM.
     *
     * @return a clone of the current instance
     */
    public VFM clone() {
        return new VFM(this.email);
    }
}