package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final SkillRepository skillRepository;

    private final JobRepository jobRepository;
    private final AuthenticationRepository authenticationRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        skillRepository = new SkillRepository();
        jobRepository = new JobRepository();
        authenticationRepository = new AuthenticationRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public SkillRepository getSkillRepository() {return skillRepository;}

    public JobRepository getJobRepository() {return jobRepository;}


    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
}