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
    private static final List<Skill> skills = new ArrayList<>();
    private static final List<Job> jobs = new ArrayList<>();
    private static final List<Collaborator> collaborators = new ArrayList<>();
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final List<GreenSpace> greenSpaces = new ArrayList<>();
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

    // Getters for lists of entities

    /**
     * Returns a copy of the list of collaborators.
     *
     * @return a list of collaborators
     */
    public static List<Collaborator> getCollaboratorList() {
        return new ArrayList<>(collaborators);
    }

    /**
     * Returns a copy of the list of skills.
     *
     * @return a list of skills
     */
    public static List<Skill> getSkillList() {
        return new ArrayList<>(skills);
    }

    /**
     * Returns a copy of the list of jobs.
     *
     * @return a list of jobs
     */
    public static List<Job> getJobList() {
        return new ArrayList<>(jobs);
    }

    /**
     * Returns a copy of the list of vehicles.
     *
     * @return a list of vehicles
     */
    public static List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Returns a copy of the list of green spaces.
     *
     * @return a list of green spaces
     */
    public static List<GreenSpace> getGreenSpaceList() {
        return new ArrayList<>(greenSpaces);
    }

    // Methods for entity management

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
     * Registers a new green space and adds it to the organization.
     *
     * @param type the type of the green space
     * @return an optional containing the registered green space, or empty if registration fails
     */
    public Optional<GreenSpace> registerGreenSpace(String type, double area) {
        GreenSpace greenSpace = new GreenSpace(type, area);
        if (addGreenSpace(greenSpace)) {
            return Optional.of(greenSpace);
        }
        return Optional.empty();
    }

    // Private helper methods for adding entities


    private boolean addGreenSpace(GreenSpace greenSpace) {
        if (validateGreenSpace(greenSpace)) {
            return greenSpaces.add(greenSpace.clone());
        }
        return false;
    }

    private boolean validateGreenSpace(GreenSpace greenSpace) {
        return !greenSpaces.contains(greenSpace);
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

    private boolean validateHRM(HRM hrm) {
        return !hrms.contains(hrm);
    }

    private boolean validateVFM(VFM vfm) {
        return !vfms.contains(vfm);
    }

    private boolean validateGSM(GSM gsm) {
        return !gsms.contains(gsm);
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

        for (Skill skill : skills) {
            skills.add(skill.clone());
        }

        for (Job job : jobs) {
            jobs.add(job.clone());
        }

        for (Collaborator collaborator : collaborators) {
            collaborators.add(collaborator.clone());
        }

        for (Vehicle vehicle : vehicles) {
            vehicles.add(vehicle.clone());
        }

        return clone;
    }
}