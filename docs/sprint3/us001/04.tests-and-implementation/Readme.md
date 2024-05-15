# US001 - Create a Skill 

## 4. Tests

# Test 1: Skill Creation

Ensure that it is possible to create a new Skill with a valid name.

    @Test
    void ensureSkillIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
    }

# Test 2: Skill Name Validation

Check that an IllegalArgumentException is thrown when attempting to create a Skill with a null or empty name.


    @Test
    void ensureSkillNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");

        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(null));
    }

## 5. Construction (Implementation)
* Class CreateSkillController


    public class CreateSkillController {

    private OrganizationRepository organizationRepository;
    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;

    // Constructors and methods omitted for brevity...

    public Optional<Skill> createSkill(String name, HRM hrm) {
        Optional<Organization> organization = organizationRepository.getOrganizationByHRM(hrm);

        Optional<Skill> newSkill = Optional.empty();

        if (organization.isPresent()) {
            newSkill = organization.get().createSkill(name);
        }
        return newSkill;
    }

    // Other methods omitted for brevity
    }

 * Class CreateSkillUI


    public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private SkillRepository skillRepository;

    // Constructors and methods omitted for brevity...

    private void submitData() {
        HRM hrm = controller.getHRMFromSession();
        Optional<Skill> skill = controller.createSkill(skillName, hrm);

        if (skill.isPresent()) {
            skillRepository.add(skill.get());
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    // Other methods omitted for brevity...
    }

* Class Organization



    public class Organization {

    // Constructors, getters, and other methods omitted for brevity...

    public Optional<Skill> createSkill(String name) {
        Skill skill = new Skill(name);
        if (addSkill(skill)) {
            return Optional.of(skill);
        }
        return Optional.empty();
    }

    private boolean addSkill(Skill skill) {
        if (validateSkill(skill)) {
            return skills.add(skill.clone());
        }
        return false;
    }

    private boolean validateSkill(Skill skill) {
        return !skills.contains(skill);
    }

    // Other methods omitted for brevity...
    }

* Class Skill


    public class Skill {

    // Constructors, getters, and other methods omitted for brevity...

    public Skill(String name) {
        validateSkill(name);
        this.name = name;
    }

    private void validateSkill(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        } else if (!name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Skill name cannot contain special characters or digits.");
        }
    }

    // Other methods omitted for brevity...
    }

* Class SkillRepository


    public class SkillRepository {

    // Constructors and other methods omitted for brevity...

    public Optional<Skill> add(Skill skill) {
        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    private boolean validateSkill(Skill skill) {
        return !skills.contains(skill);
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Skill creation functionality has been integrated into the application.
* Demo purposes: Skill creation can be accessed via the UI to create new skills.

## 7. Observations

n/a