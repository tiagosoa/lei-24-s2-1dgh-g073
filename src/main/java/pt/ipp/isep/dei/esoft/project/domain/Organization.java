package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The Organization class represents an organization within the project domain.
 * It manages HRMs (Human Resource Managers), VFMs (Vehicle Fleet Managers), skills, jobs,
 * collaborators, vehicles, and provides methods for creating and managing them.
 */

public class Organization {
    private final String vatNumber;
    private final List<HRM> hrms;
    private final List<VFM> vfms;
    private static List<Skill> skills = new ArrayList<>();
    private static List<Job> jobs = new ArrayList<>();
    private static List<Collaborator> collaborators = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private String name;
    private String website;
    private String phone;
    private String email;

    /**
     * This method is the constructor of the organization.
     *
     * @param vatNumber The vat number of the organization. This is the identity of the organization, therefore it
     *                  cannot be changed.
     */
    public Organization(String vatNumber) {
        this.vatNumber = vatNumber;
        hrms = new ArrayList<>();
        vfms = new ArrayList<>();
    }

    /**
     * These methods return a list of skills, jobs, collaborators, and vehicles each.
     *
     * @return a list of objects
     */

    public static List<Collaborator> getCollaboratorList() {
        return collaborators;
    }

    public static List<Skill> getSkillList() {
        return skills;
    }

    public static List<Job> getJobList() {
        return jobs;
    }

    public static List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Checks if an HRM works for the organization.
     *
     * @param hrm The HRM to be checked.
     * @return True if the HRM works for the organization, false otherwise.
     */
    public boolean employs(HRM hrm) {
        return hrms.contains(hrm);
    }

    /**
     * Checks if a VFM works for the organization.
     *
     * @param vfm The VFM to be checked.
     * @return True if the VFM works for the organization, false otherwise.
     */

    public boolean employs(VFM vfm) {
        return vfms.contains(vfm);
    }

    /**
     * This method creates a new skill.
     *
     * @param name The name of the skill to be created.
     * @return The optional value, indicating if it was created successfully or not.
     */
    public Optional<Skill> createSkill(String name) {
        Optional<Skill> optionalValue = Optional.empty();

        Skill skill = new Skill(name);

        if (addSkill(skill)) {
            optionalValue = Optional.of(skill);
        }
        return optionalValue;
    }

    /**
     * This method creates a new vehicle.
     *
     * @param model                  The model of the vehicle.
     * @param brand                  The brand of the vehicle.
     * @param type                   The type of the vehicle.
     * @param tareWeight             The tare weight of the vehicle.
     * @param grossWeight            The gross weight of the vehicle.
     * @param currentKm              The current kilometers of the vehicle.
     * @param registerDate           The date when the vehicle was registered.
     * @param acquisitionDate        The date when the vehicle was acquired.
     * @param maintenanceFrequencyKm The maintenance frequency in kilometers for the vehicle.
     * @param plateNumber            The plate number (ID) of the vehicle.
     * @param lastMaintenanceDate    The date when the vehicle last went through maintenance.
     * @return An optional containing the created vehicle if the operation is successful, or an empty optional otherwise.
     */


    public Optional<Vehicle> createVehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                                           double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        Optional<Vehicle> optionalValue = Optional.empty();

        Vehicle vehicle = new Vehicle(model, brand, type, tareWeight, grossWeight, currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate);

        if (addVehicle(vehicle)) {
            optionalValue = Optional.of(vehicle);
        }
        return optionalValue;
    }

    /**
     * This method creates a new job.
     *
     * @param name The name of the job to be created.
     * @return
     */
    public Optional<Job> createJob(String name) {
        Optional<Job> optionalValue = Optional.empty();

        Job job = new Job(name);

        if (addJob(job)) {
            optionalValue = Optional.of(job);
        }
        return optionalValue;
    }

    /**
     * This method registers a new collaborator.
     *
     * @param name        The name of the collaborator to be registered.
     * @param birthdate   The date of birth of the collaborator to be registered.
     * @param admissiondate The date of admission of the collaborator to be registered.
     * @param address     The address where the collaborator to be registered resides.
     * @param mobile      The mobile phone number of the collaborator to be registered.
     * @param email       The email of the collaborator to be registered.
     * @param taxpayer    The taxpayer number of the collaborator to be registered.
     * @param doctype     The type of documentation the collaborator to be registered has.
     * @param IDnumber    The ID number of the collaborator to be registered.
     * @param hrm         The hrm that registers the collaborator.
     * @return
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber, HRM hrm) {
        Optional<Collaborator> optionalValue = Optional.empty();

        Collaborator collaborator = new Collaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);

        if (addCollaborator(collaborator)) {
            optionalValue = Optional.of(collaborator);
        }
        return optionalValue;
    }

    /**
     * This method adds a skill to the list of skills.
     *
     * @param skill The skill to be added.
     * @return True if the skill was added successfully.
     */
    private boolean addSkill(Skill skill) {
        boolean success = false;
        if (validateSkill(skill)) {
            success = skills.add(skill.clone());
        }
        return success;

    }

    /**
     * This method adds a skill to the list of jobs.
     *
     * @param job The job to be added.
     * @return True if the skill was added successfully.
     */
    private boolean addJob(Job job) {
        boolean success = false;
        if (validateJob(job)) {
            success = jobs.add(job.clone());
        }
        return success;

    }

    /**
     * This method adds a collaborator to the list of collaborators.
     *
     * @param collaborator The collaborator to be added.
     * @return True if the collaborator was added successfully.
     */
    private boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validateCollaborator(collaborator)) {
            success = collaborators.add(collaborator.clone());
        }
        return success;

    }

    /**
     * This method adds a vehicle to the list of vehicles.
     *
     * @param vehicle The vehicle to be added.
     * @return True if the vehicle was added successfully.
     */
    private boolean addVehicle(Vehicle vehicle) {
        boolean success = false;
        if (validateVehicle(vehicle)) {
            success = vehicles.add(vehicle.clone());
        }
        return success;

    }

    /**
     * This method validates the skill, checking for duplicates.
     *
     * @param skill The skill to be validated.
     * @return True if the skill is valid.
     */
    private boolean validateSkill(Skill skill) {
        return skillsDoNotContain(skill);
    }

    /**
     * This method validates the vehicle, checking for duplicates.
     *
     * @param vehicle The vehicle to be validated.
     * @return True if the vehicle is valid.
     */

    private boolean validateVehicle(Vehicle vehicle) {
        return vehiclesDoNotContain(vehicle);
    }

    /**
     * This method validates the job, checking for duplicates.
     *
     * @param job The job to be validated.
     * @return True if the job is valid.
     */
    private boolean validateJob(Job job) {
        return jobsDoNotContain(job);
    }

    /**
     * This method validates the collaborator, checking for duplicates.
     *
     * @param collaborator The collaborator to be validated.
     * @return True if the collaborator is valid.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return collaboratorsDoNotContain(collaborator);
    }


    /**
     * This method checks if the skill is already in the list of skills.
     *
     * @param skill The skill to be checked.
     * @return True if the skill is not in the list of skills.
     */
    private boolean skillsDoNotContain(Skill skill) {
        return !skills.contains(skill);
    }

    /**
     * This method checks if the vehicle is already in the list of vehicles.
     *
     * @param vehicle The vehicle to be checked.
     * @return True if the vehicle is not in the list of vehicles.
     */
    private boolean vehiclesDoNotContain(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    /**
     * This method checks if the job is already in the list of jobs.
     *
     * @param job The job to be checked.
     * @return True if the job is not in the list of jobs.
     */
    private boolean jobsDoNotContain(Job job) {
        return !jobs.contains(job);
    }

    /**
     * This method checks if the collaborator is already in the list of collaborators.
     *
     * @param collaborator The collaborator to be checked.
     * @return True if the collaborator is not in the list of collaborators.
     */
    private boolean collaboratorsDoNotContain(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * These methods check if the organization has an HRM or VFM with the given email.
     *
     * @param email The email to be checked.
     * @return True if the organization has an HRM or VFM with the given email.
     */
    public boolean anyHRMHasEmail(String email) {
        boolean result = false;
        for (HRM hrm : hrms) {
            if (hrm.hasEmail(email)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean anyVFMHasEmail(String email) {
        boolean result = false;
        for (VFM vfm : vfms) {
            if (vfm.hasEmail(email)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if this organization is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Computes the hash code of this organization.
     *
     * @return The hash code of the organization.
     */
    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    //add hrm to organization
    public boolean addHRM(HRM hrm) {
        boolean success = false;
        if (validateHRM(hrm)) {
            success = hrms.add(hrm);
        }
        return success;
    }

    public boolean addVFM(VFM vfm) {
        boolean success = false;
        if (validateVFM(vfm)) {
            success = vfms.add(vfm);
        }
        return success;
    }

    private boolean validateHRM(HRM hrm) {
        return hrmsDoNotContain(hrm);
    }

    private boolean validateVFM(VFM vfm) {
        return vfmsDoNotContain(vfm);
    }

    private boolean hrmsDoNotContain(HRM hrm) {
        return !hrms.contains(hrm);
    }

    private boolean vfmsDoNotContain(VFM vfm) {
        return !vfms.contains(vfm);
    }

    /**
     * Creates a clone of this organization.
     *
     * @return A clone of this organization.
     */
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = this.name;
        clone.website = this.website;
        clone.phone = this.phone;
        clone.email = this.email;

        for (HRM in : this.hrms) {
            clone.hrms.add(in.clone());
        }

        for (VFM in : this.vfms) {
            clone.vfms.add(in.clone());
        }

        for (Skill in : skills) {
            clone.skills.add(in.clone());
        }

        for (Job in : jobs) {
            clone.jobs.add(in.clone());
        }

        for (Collaborator in : collaborators) {
            clone.collaborators.add(in.clone());
        }

        for (Vehicle in : vehicles) {
            clone.vehicles.add(in.clone());
        }

        return clone;
    }
}