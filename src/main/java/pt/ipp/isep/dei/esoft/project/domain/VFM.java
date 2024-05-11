package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class VFM {
    private final String email;
    private String name;
    private String position;
    private int phone;

    public VFM(String email) {
        this.email = email;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public VFM clone() {
        return new VFM(this.email);
    }
}