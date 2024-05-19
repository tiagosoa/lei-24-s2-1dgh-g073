# US004 - Assign a Skill

## 4. Tests

# Test 1: Skill Assignment

Ensure that it is possible to assign an existing skill to an existing Collaborator.

    @Test
    void ensureSkillCanBeAssignedToCollaborator() {
        Skill skill = new Skill("Java");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, new HRM("john.doe@this.company.com"));

        assertTrue(collaborator.addSkill(skill));
    }


## 5. Construction (Implementation)
* Class AssignSkillController


    public class AssignSkillController {

    private OrganizationRepository organizationRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;

    // Constructors and methods omitted for brevity...

    public void assignSkillsToCollaborator(int collaboratorID, List<String> collaboratorskills) {
        Collaborator collaborator = collaboratorRepository.getCollaboratorByID(collaboratorID);

        if (collaborator == null) {
            throw new IllegalArgumentException("Collaborator not found.");
        }

        for (String skillname : collaboratorskills) {
            Skill skill = skillRepository.getSkillByName(skillname);
            if (skill != null) {
                collaborator.addSkill(skill);
            }
        }

        collaboratorRepository.updateCollaborator(collaborator);
    }

    // Other methods omitted for brevity
    }

* Class AssignSkillUI


    public class AssignSkillUI implements Runnable {

    private AssignSkillController controller;
    private CollaboratorRepository collaboratorRepository;

    private SkillRepository skillRepository;
    private Scanner scanner;

    // Constructors and methods omitted for brevity...

    public void run() {
        System.out.println("\n\n--- Assign Skill ------------------------");
        List<Collaborator> collaborators = getController().getCollaboratorList();
        // Show list of registered collaborators
        if (collaborators.isEmpty()) {
            System.out.println("No collaborators registered.");
            return;
        }
        System.out.println("Collaborator List:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
        int collaboratorIndex = readInput(1, collaborators.size()) - 1;
        Collaborator selectedCollaborator = collaborators.get(collaboratorIndex);

        // Show list of skills
        List<Skill> skills = skillRepository.getSkillList();
        if (skills.isEmpty()) {
            System.out.println("No skills registered.");
            return;
        }
        System.out.println("Select skill(s) to assign to " + selectedCollaborator.getName() + ":");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getName());
        }
        System.out.println("Enter skill number(s) separated by commas (e.g., 1,2,3):");
        String skillIndexInput = scanner.nextLine();
        String[] skillIndices = skillIndexInput.split(",");
        List<String> selectedSkillNames = new ArrayList<>();
        for (String index : skillIndices) {
            int skillIndex = Integer.parseInt(index.trim()) - 1;
            selectedSkillNames.add(skills.get(skillIndex).getName().trim());
        }
        HRM hrm = getController().getHRMFromSession();

        // Assign selected skills to collaborator
        getController().assignSkillsToCollaborator(selectedCollaborator.getIDNumber(), selectedSkillNames);
        System.out.println("Skills assigned successfully to " + selectedCollaborator.getName());
    }

    // Other methods omitted for brevity...
    }

* Class Collaborator


    public class Collaborator {

    // Constructors, getters, and other methods omitted for brevity...

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
    }

    public boolean addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            skill.addCollaborator(this);
            return true;
        }
        return false;
    }

    // Other methods omitted for brevity...
    }

* Class Skill


    public Skill(String name) {
        validateSkill(name);
        this.name = name;
    }

    // Constructors and other methods omitted for brevity...

    public void addCollaborator(Collaborator collaborator) {
        collaborator.addSkill(this);
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Skill assignment functionality has been integrated into the application.
* Demo purposes: Skill assignment can be accessed via the UI to assign skills to collaborators.

## 7. Observations

n/a