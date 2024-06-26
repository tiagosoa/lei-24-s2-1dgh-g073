package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an organization with various entities such as HRMs, VFMs, skills, jobs, collaborators, and vehicles.
 */
public class Organization {
    private final String vatNumber;
    private final List<HRM> hrms = new ArrayList<>();
    private final List<VFM> vfms = new ArrayList<>();
    private static final List<Skill> skills = new ArrayList<>();
    private static final List<Job> jobs = new ArrayList<>();
    private static final List<Collaborator> collaborators = new ArrayList<>();
    private static final List<Vehicle> vehicles = new ArrayList<>();
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
     * Creates a new skill and adds it to the organization.
     *
     * @param name the name of the skill
     * @return an optional containing the created skill, or empty if creation fails
     */
    public Optional<Skill> createSkill(String name) {
        Skill skill = new Skill(name);
        if (addSkill(skill)) {
            return Optional.of(skill);
        }
        return Optional.empty();
    }

    /**
     * Creates a new vehicle and adds it to the organization.
     *
     * @param model                the model of the vehicle
     * @param brand                the brand of the vehicle
     * @param type                 the type of the vehicle
     * @param tareWeight           the tare weight of the vehicle
     * @param grossWeight          the gross weight of the vehicle
     * @param currentKm            the current kilometers of the vehicle
     * @param registerDate         the registration date of the vehicle
     * @param acquisitionDate      the acquisition date of the vehicle
     * @param maintenanceFrequencyKm the maintenance frequency in kilometers
     * @param plateNumber          the plate number of the vehicle
     * @param lastMaintenanceDate  the date of the last maintenance
     * @return an optional containing the created vehicle, or empty if creation fails
     */
    public Optional<Vehicle> createVehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                                           double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        Vehicle vehicle = new Vehicle(model, brand, type, tareWeight, grossWeight, currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);
        if (addVehicle(vehicle)) {
            return Optional.of(vehicle);
        }
        return Optional.empty();
    }

    /**
     * Creates a new job and adds it to the organization.
     *
     * @param name the name of the job
     * @return an optional containing the created job, or empty if creation fails
     */
    public Optional<Job> createJob(String name) {
        Job job = new Job(name);
        if (addJob(job)) {
            return Optional.of(job);
        }
        return Optional.empty();
    }

    /**
     * Registers a new collaborator with the organization.
     *
     * @param name         the name of the collaborator
     * @param birthdate    the birthdate of the collaborator
     * @param admissiondate the admission date of the collaborator
     * @param address      the address of the collaborator
     * @param mobile       the mobile number of the collaborator
     * @param email        the email of the collaborator
     * @param taxpayer     the taxpayer number of the collaborator
     * @param doctype      the document type of the collaborator
     * @param IDnumber     the ID number of the collaborator
     * @param hrm          the HRM associated with the collaborator
     * @return an optional containing the registered collaborator, or empty if registration fails
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber, HRM hrm) {
        Collaborator collaborator = new Collaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);
        if (addCollaborator(collaborator)) {
            return Optional.of(collaborator);
        }
        return Optional.empty();
    }

    // Private helper methods for adding entities

    private boolean addSkill(Skill skill) {
        if (validateSkill(skill)) {
            return skills.add(skill.clone());
        }
        return false;
    }

    private boolean addJob(Job job) {
        if (validateJob(job)) {
            return jobs.add(job.clone());
        }
        return false;
    }

    private boolean addCollaborator(Collaborator collaborator) {
        if (validateCollaborator(collaborator)) {
            return collaborators.add(collaborator.clone());
        }
        return false;
    }

    private boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return vehicles.add(vehicle.clone());
        }
        return false;
    }

    // Private helper methods for entity validation

    private boolean validateSkill(Skill skill) {
        return !skills.contains(skill);
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    private boolean validateJob(Job job) {
        return !jobs.contains(job);
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    // Check if any HRM or VFM has a specific email

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

    private boolean validateHRM(HRM hrm) {
        return !hrms.contains(hrm);
    }

    private boolean validateVFM(VFM vfm) {
        return !vfms.contains(vfm);
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