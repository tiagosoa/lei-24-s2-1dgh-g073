package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collaborator {

    private List<Job> collaboratorjobs;
    private List<Skill> collaboratorskills;
    private final String name;
    private final String birthdate;
    private final String admissiondate;
    private final String address;
    private final int mobile;
    private final String email;
    private final int taxpayer;
    private final String doctype;
    public final int IDnumber;

    private HRM hrm;

    public Collaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber, HRM hrm) {

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
        this.collaboratorjobs = new ArrayList<>();
        this.collaboratorskills = new ArrayList<>();
    }

    private void validateCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        //TODO: missing from the diagrams
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Collaborator name cannot be null or empty.");

        } else if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Collaborator name cannot contain special characters or digits.");
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
            throw new IllegalArgumentException("Collaborator mobile number must be a positive number");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Collaborator email cannot be empty");
        }
        if (taxpayer <= 0) {
            throw new IllegalArgumentException("Collaborator taxpayer number must be a positive number");
        }
        if (doctype == null || doctype.isEmpty()) {
            throw new IllegalArgumentException("Collaborator documentation type cannot be empty");
        }
        if (IDnumber <= 0) {
            throw new IllegalArgumentException("Collaborator ID number must be a positive number");
        }
    }
    public void addSkill(Skill skill) {
        if (!collaboratorskills.contains(skill)) {
            collaboratorskills.add(skill);
            skill.addCollaborator(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collaborator)) {
            return false;
        }
        Collaborator collaborator = (Collaborator) o;
        return name.equals(collaborator.name) && birthdate.equals(collaborator.birthdate) && admissiondate.equals(collaborator.admissiondate) && address.equals(collaborator.address) && mobile == collaborator.mobile && email.equals(collaborator.email) && doctype.equals(collaborator.doctype) && IDnumber == collaborator.IDnumber && hrm.equals(collaborator.hrm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate, admissiondate, address, mobile, email, doctype, IDnumber, hrm);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthdate, this.admissiondate, this.address, this.mobile, this.email, this.taxpayer, this.doctype, this.IDnumber, this.hrm);
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

    public void setJobs(List<Job> collaboratorjobs) {
        this.collaboratorjobs = collaboratorjobs;
    }
    public List<Job> getJobs() {
        return collaboratorjobs;
    }

    public List<Skill> getSkills() {return collaboratorskills;}

    public void setSkills(List<Skill> collaboratorskills) {this.collaboratorskills = collaboratorskills;}
}