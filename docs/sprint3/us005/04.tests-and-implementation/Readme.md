# US005 - Generate a Team

## 4. Construction (Implementation)

* Class GenerateTeamController


    public class GenerateTeamController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;

    public List<Collaborator> generateTeam(int maxTeamSize, int minTeamSize, List<Skill> requiredSkills) {
    if (minTeamSize <= 0 || maxTeamSize < minTeamSize || requiredSkills.isEmpty()) {
    throw new IllegalArgumentException("Invalid input parameters.");
    }  

        List<Collaborator> allCollaborators = collaboratorRepository.getCollaboratorList();
        List<Collaborator> qualifiedCollaborators = filterCollaboratorsBySkills(allCollaborators, requiredSkills);

        if (qualifiedCollaborators.size() < minTeamSize) {
            throw new IllegalArgumentException("Not enough qualified collaborators available.");
        }

        List<Collaborator> team = new ArrayList<>();
        for (int i = 0; i < maxTeamSize && i < qualifiedCollaborators.size(); i++) {
            team.add(qualifiedCollaborators.get(i));
        }

        return team;
    }

* Class GenerateTeamUI


    public class GenerateTeamUI implements Runnable {

    private final GenerateTeamController controller;
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private final Scanner scanner;

    // Constructors and methods omitted for brevity...

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the minimum team size:");
        int minTeamSize = scanner.nextInt();

        System.out.println("Enter the maximum team size:");
        int maxTeamSize = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        if (minTeamSize >= maxTeamSize || minTeamSize <= 0 || maxTeamSize <= 0) {
            throw new IllegalArgumentException("Invalid team size inputs.");
        }
        System.out.println("Enter the required skills separated by semicolons:");
        String inputSkills = scanner.nextLine();
        List<Skill> requiredSkills = parseSkills(inputSkills);

        try {
            List<Collaborator> team = getController().generateTeam(maxTeamSize, minTeamSize, requiredSkills);
            displayTeam(team);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Other methods omitted for brevity...
    }


## 5. Integration and Demo

* The Team Generation functionality has been integrated into the application.
* Demo purposes: Team Generation can be accessed via the UI to generate teams.

## 6. Observations

n/a