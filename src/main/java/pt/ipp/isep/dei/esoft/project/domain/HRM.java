package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class HRM {
    private final String email;
    private String name;
    private String position;
    private String phone;

    public HRM(String email) {
        this.email = email;
    }

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
    public HRM clone() {
        return new HRM(this.email);
    }
}