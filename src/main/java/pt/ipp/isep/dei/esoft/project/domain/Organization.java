package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Organization {
    private final String vatNumber;
    private final List<HRM> hrms;
    private final List<Skill> skills;
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
        skills = new ArrayList<>();
    }

    /**
     * This method checks if an HRM works for the organization.
     *
     * @param hrm The hrm to be checked.
     * @return True if the employee works for the organization.
     */
    public boolean employs(HRM hrm) {
        return hrms.contains(hrm);
    }

    /**
     * This method creates a new skill.
     *
     * @param name            The name of the skill to be created.
     * @param hrm             The hrm of the skill to be created.
     * @return
     */
    public Optional<Skill> createSkill(String name, HRM hrm) {

        //TODO: we could also check if the hrm works for the organization before proceeding
        //checkIfEmployeeWorksForOrganization(hrm);

        // When a Task is added, it should fail if the Task already exists in the list of Tasks.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Skill> optionalValue = Optional.empty();

        Skill skill = new Skill(name, hrm);

        if (addSkill(skill)) {
            optionalValue = Optional.of(skill);
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
            // A clone of the skill is added to the list of skills, to avoid side effects and outside manipulation.
            success = skills.add(skill.clone());
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
     * This method checks if the skill is already in the list of skills.
     *
     * @param skill The skill to be checked.
     * @return True if the skill is not in the list of skills.
     */
    private boolean skillsDoNotContain(Skill skill) {
        return !skills.contains(skill);
    }

    /**
     * This method checks if the organization has an HRM with the given email.
     *
     * @param email The email to be checked.
     * @return True if the organization has an employee with the given email.
     */
    public boolean anyHRMHasEmail(String email) {
        boolean result = false;
        for (HRM hrm : hrms) {
            if (hrm.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

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

    //add hrm to organization
    public boolean addHRM(HRM hrm) {
        boolean success = false;
        if (validateHRM(hrm)) {
            success = hrms.add(hrm);
        }
        return success;
    }

    private boolean validateHRM(HRM hrm) {
        return hrmsDoNotContain(hrm);
    }

    private boolean hrmsDoNotContain(HRM hrm) {
        return !hrms.contains(hrm);
    }

    //Clone organization
    public Organization clone() {
        Organization clone = new Organization(this.vatNumber);
        clone.name = (this.name);
        clone.website = (this.website);
        clone.phone = (this.phone);
        clone.email = (this.email);

        for (HRM in : this.hrms) {
            clone.hrms.add(in.clone());
        }


        for (Skill in : this.skills) {
            clone.skills.add(in.clone());
        }

        return clone;
    }
}