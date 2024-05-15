package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a Green Spaces Manager (GSM) in the system.
 */
public class GSM {
    private final String email; // unique identifier for the GSM
    private String name; // name of the GSM
    private String position; // position held by the GSM
    private int phone; // phone number of the GSM

    /**
     * Constructor for creating a GSM with the specified email.
     *
     * @param email the email of the GSM
     */
    public GSM(String email) {
        this.email = email;
    }

    /**
     * Checks if this GSM is equal to another object based on email.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GSM)) {
            return false;
        }
        GSM gsm = (GSM) o;
        return email.equals(gsm.email);
    }

    /**
     * Generates a hash code for the GSM based on email.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Checks if the GSM has the specified email.
     *
     * @param email the email to check
     * @return true if the GSM has the specified email, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Creates a clone of the GSM.
     *
     * @return a clone of the current instance
     */
    public GSM clone() {
        return new GSM(this.email);
    }
}