package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an organization with various entities such as HRMs, VFMs, GSMs, skills, jobs, collaborators, vehicles, and green spaces.
 */
public class Organization {
    private final String vatNumber;
    private final List<HRM> hrms = new ArrayList<>();
    private final List<VFM> vfms = new ArrayList<>();
    private final List<GSM> gsms = new ArrayList<>();
    private final List<Collaborator> collaborators = new ArrayList<>();
    private String name;
    private String website;
    private String phone;
    private String email;

    /**
     * Constructs an organization with a VAT number.
     *
     * @param vatNumber the VAT number of the organization
     */
    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * Checks if the organization employs a specific HRM.
     *
     * @param hrm the HRM to check
     * @return true if the organization employs the HRM, false otherwise
     */
    public boolean employs(HRM hrm) {
        return hrms.contains(hrm);
    }

    /**
     * Checks if the organization employs a specific VFM.
     *
     * @param vfm the VFM to check
     * @return true if the organization employs the VFM, false otherwise
     */
    public boolean employs(VFM vfm) {
        return vfms.contains(vfm);
    }

    /**
     * Checks if the organization employs a specific GSM.
     *
     * @param gsm the GSM to check
     * @return true if the organization employs the GSM, false otherwise
     */
    public boolean employs(GSM gsm) {
        return gsms.contains(gsm);
    }


    /**
     * Checks if the organization employs a specific collaborator
     *
     * @param collaborator the Collaborator to check
     * @return true if the organization employs the Collaborator, false otherwise
     */
    public boolean employs(Collaborator collaborator) {
        return collaborators.contains(collaborator);
    }

    // Check if any HRM, VFM or GSM has a specific email

    /**
     * Checks if any HRM has a specific email.
     *
     * @param email the email to check
     * @return true if any HRM has the email, false otherwise
     */
    public boolean anyHRMHasEmail(String email) {
        return hrms.stream().anyMatch(hrm -> hrm.hasEmail(email));
    }

    /**
     * Checks if any VFM has a specific email.
     *
     * @param email the email to check
     * @return true if any VFM has the email, false otherwise
     */
    public boolean anyVFMHasEmail(String email) {
        return vfms.stream().anyMatch(vfm -> vfm.hasEmail(email));
    }

    /**
     * Checks if any GSM has a specific email.
     *
     * @param email the email to check
     * @return true if any GSM has the email, false otherwise
     */
    public boolean anyGSMHasEmail(String email) {
        return gsms.stream().anyMatch(gsm -> gsm.hasEmail(email));
    }

    /**
     * Checks if any Collaborator has a specific email.
     *
     * @param email the email to check
     * @return true if any GSM has the email, false otherwise
     */
    public boolean anyCollaboratorHasEmail(String email) {
        return collaborators.stream().anyMatch(collaborator -> collaborator.hasEmail(email));
    }

    // Equals, hashCode, and cloning

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    /**
     * Adds an HRM to the organization.
     *
     * @param hrm the HRM to add
     * @return true if the HRM is added, false otherwise
     */
    public boolean addHRM(HRM hrm) {
        if (validateHRM(hrm)) {
            return hrms.add(hrm);
        }
        return false;
    }

    /**
     * Adds a VFM to the organization.
     *
     * @param vfm the VFM to add
     * @return true if the VFM is added, false otherwise
     */
    public boolean addVFM(VFM vfm) {
        if (validateVFM(vfm)) {
            return vfms.add(vfm);
        }
        return false;
    }

    /**
     * Adds a GSM to the organization.
     *
     * @param gsm the GSM to add
     * @return true if the GSM is added, false otherwise
     */
    public boolean addGSM(GSM gsm) {
        if (validateGSM(gsm)) {
            return gsms.add(gsm);
        }
        return false;
    }

    /**
     * Adds a Collaborator to the organization.
     *
     * @param collaborator the Collaborator to add
     * @return true if the Collaborator is added, false otherwise
     */
    public boolean addCollaborator(Collaborator collaborator) {
        if (validateCollaborator(collaborator)) {
            return collaborators.add(collaborator);
        }
        return false;
    }

    private boolean validateHRM(HRM hrm) {
        return !hrms.contains(hrm);
    }

    private boolean validateVFM(VFM vfm) {
        return !vfms.contains(vfm);
    }

    private boolean validateGSM(GSM gsm) {
        return !gsms.contains(gsm);
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Creates a deep copy of the organization.
     *
     * @return a new instance of Organization with copied attributes and entities
     */
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = this.name;
        clone.website = this.website;
        clone.phone = this.phone;
        clone.email = this.email;

        for (HRM hrm : this.hrms) {
            clone.hrms.add(hrm.clone());
        }

        for (VFM vfm : this.vfms) {
            clone.vfms.add(vfm.clone());
        }

        for (GSM gsm : this.gsms) {
            clone.gsms.add(gsm.clone());
        }

        for (Collaborator collaborator : this.collaborators) {
            clone.collaborators.add(collaborator.clone());
        }
        return clone;
    }
}