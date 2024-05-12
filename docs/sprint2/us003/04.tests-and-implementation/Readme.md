# US003 - Create a Job

## 4. Tests

# Test 1: Job Creation

Ensure that it is possible to create a new Job with a valid name.

    @Test
    void ensureJobIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
    }

# Test 2: Job Name Validation

Check that an IllegalArgumentException is thrown when attempting to create a Job with a null or empty name.

    @Test
    void ensureJobNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Job(null));
    }

## 5. Construction (Implementation)
* Class CreateJobController


    public class CreateJobController {

    private OrganizationRepository organizationRepository;
    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;

    // Constructors and methods omitted for brevity...

    public Optional<Job> createJob(String name, HRM hrm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByHRM(hrm);
        Optional<Job> newJob = Optional.empty();

        if (organization.isPresent()) {
            newJob = organization.get().createJob(name);
        }
        return newJob;
    }

    // Other methods omitted for brevity
    }

* Class CreateJobUI


    public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private String jobName;
    private JobRepository jobRepository;

    // Constructors and methods omitted for brevity...

    private void submitData() {
        HRM hrm = controller.getHRMFromSession();
        Optional<Job> job = controller.createJob(jobName, hrm);

        if (job.isPresent()) {
            jobRepository.add(job.get());
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    // Other methods omitted for brevity...
    }

* Class Organization



    public class Organization {

    // Constructors, getters, and other methods omitted for brevity...

    public Optional<Job> createJob(String name) {
        Job job = new Job(name);
        if (addJob(job)) {
            return Optional.of(job);
        }
        return Optional.empty();
    }

    private boolean addJob(Job job) {
        if (validateJob(job)) {
            return jobs.add(job.clone());
        }
        return false;
    }

    private boolean validateJob(Job job) {
        return !jobs.contains(job);
    }

    // Other methods omitted for brevity...
    }

* Class Job


    public class Job {

    // Constructors, getters, and other methods omitted for brevity...

    public Job(String name) {
        validateJob(name);
        this.name = name;
    }

    private void validateJob(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty.");
        } else if (!name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Job name cannot contain special characters or digits.");
        }
    }

    // Other methods omitted for brevity...
    }

* Class JobRepository


    public class JobRepository {

    // Constructors and other methods omitted for brevity...

    public Optional<Job> add(Job job) {
        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobs.add(newJob.get());
        }

        if (!operationSuccess) {
            newJob = Optional.empty();
        }

        return newJob;
    }

    private boolean validateJob(Job job) {
        return !jobs.contains(job);
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Job creation functionality has been integrated into the application.
* Demo purposes: Job creation can be accessed via the UI to create new jobs.

## 7. Observations

n/a