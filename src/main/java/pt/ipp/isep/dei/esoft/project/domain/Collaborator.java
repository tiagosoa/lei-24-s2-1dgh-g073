package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Collaborator in the system.
 */
public class Collaborator {

    private final String name;
    private final String birthdate;
    private final String admissiondate;
    private final String address;
    private final int mobile;
    private final String email;
    private final int taxpayer;
    private final String doctype;
    private final int IDnumber;

    private HRM hrm;
    private List<Job> jobs = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    /**
     * Constructor for Collaborator class.
     *
     * @param name          the name of the collaborator
     * @param birthdate     the birth date of the collaborator
     * @param admissiondate the admission date of the collaborator
     * @param address       the address of the collaborator
     * @param mobile        the mobile number of the collaborator
     * @param email         the email of the collaborator
     * @param taxpayer      the taxpayer number of the collaborator
     * @param doctype       the documentation type of the collaborator
     * @param IDnumber      the ID number of the collaborator
     */
    public Collaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        validateCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber);
        this.name = name;
        this.birthdate = birthdate;
        this.admissiondate = admissiondate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayer = taxpayer;
        this.doctype = doctype;
        this.IDnumber = IDnumber;
        this.hrm = hrm;
    }

    /**
     * Validates the input parameters for creating a collaborator.
     *
     * @param name         the name of the collaborator
     * @param birthdate    the birth date of the collaborator
     * @param admissiondate the admission date of the collaborator
     * @param address      the address of the collaborator
     * @param mobile       the mobile number of the collaborator
     * @param email        the email of the collaborator
     * @param taxpayer     the taxpayer number of the collaborator
     * @param doctype      the documentation type of the collaborator
     * @param IDnumber     the ID number of the collaborator
     */
    private void validateCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        if (name == null || name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Collaborator name cannot be null, empty, or contain special characters or digits.");
        }
        if (birthdate == null || birthdate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator birth date cannot be null or empty.");
        }
        if (admissiondate == null || admissiondate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator admission date cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Collaborator address cannot be null or empty.");
        }
        if (mobile <= 0) {
            throw new IllegalArgumentException("Collaborator mobile number must be a positive number.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Collaborator email cannot be empty.");
        }
        if (taxpayer <= 0) {
            throw new IllegalArgumentException("Collaborator taxpayer number must be a positive number.");
        }
        if (doctype == null || doctype.isEmpty()) {
            throw new IllegalArgumentException("Collaborator documentation type cannot be empty.");
        }
        if (IDnumber <= 0) {
            throw new IllegalArgumentException("Collaborator ID number must be a positive number.");
        }
    }

    /**
     * Adds a skill to the collaborator.
     *
     * @param skill the skill to be added
     * @return true if the skill was added successfully, false otherwise
     */
    public boolean addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            skill.addCollaborator(this);
            return true;
        }
        return false;
    }

    /**
     * Adds a job to the collaborator.
     *
     * @param job the job to be added
     */
    public void addJob(Job job) {
        if (!jobs.contains(job)) {
            jobs.add(job);
            job.addCollaborator(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collaborator)) return false;
        Collaborator that = (Collaborator) o;
        return mobile == that.mobile && taxpayer == that.taxpayer && IDnumber == that.IDnumber && name.equals(that.name) && birthdate.equals(that.birthdate) && admissiondate.equals(that.admissiondate) && address.equals(that.address) && email.equals(that.email) && doctype.equals(that.doctype) && Objects.equals(hrm, that.hrm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);
    }

    public Collaborator clone() {
        return new Collaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber);
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthdate;
    }

    public String getAdmissionDate() {
        return admissiondate;
    }

    public String getAddress() {
        return address;
    }

    public int getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getTaxpayer() {
        return taxpayer;
    }

    public String getDocType() {
        return doctype;
    }

    public int getIDNumber() {
        return IDnumber;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}