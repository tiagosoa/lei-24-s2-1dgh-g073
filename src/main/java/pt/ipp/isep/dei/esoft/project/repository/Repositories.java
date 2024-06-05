package pt.ipp.isep.dei.esoft.project.repository;

/**
 * Singleton class that manages all repositories in the system.
 */
public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final TeamRepository teamRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final ToDoListRepository toDoListRepository;
    private final AgendaRepository agendaRepository;
    private final AuthenticationRepository authenticationRepository;
    private final TaskRepository taskRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes all repository instances.
     */
    private Repositories() {
        organizationRepository = new OrganizationRepository();
        skillRepository = new SkillRepository();
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        teamRepository = new TeamRepository();
        authenticationRepository = new AuthenticationRepository();
        vehicleRepository = new VehicleRepository();
        greenSpaceRepository = new GreenSpaceRepository();
        toDoListRepository = new ToDoListRepository();
        agendaRepository = new AgendaRepository();
        taskRepository = new TaskRepository();
    }

    /**
     * Returns the singleton instance of Repositories.
     *
     * @return The instance of Repositories.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Returns the organization repository.
     *
     * @return The organization repository.
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    /**
     * Returns the skill repository.
     *
     * @return The skill repository.
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Returns the job repository.
     *
     * @return The job repository.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * Returns the collaborator repository.
     *
     * @return The collaborator repository.
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Returns the team repository.
     *
     * @return The team repository.
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    /**
     * Returns the authentication repository.
     *
     * @return The authentication repository.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Returns the vehicle repository.
     *
     * @return The vehicle repository.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Returns the green space repository.
     *
     * @return The green space repository.
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Returns the to-do list repository.
     *
     * @return The to-do list repository.
     */
    public ToDoListRepository getToDoListRepository() {
        return toDoListRepository;
    }

    /**
     * Returns the agenda repository.
     *
     * @return The agenda repository.
     */
    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }
    /**
     * Returns the task repository.
     *
     * @return The task repository.
     */
    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}