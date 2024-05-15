package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Job entities.
 */
public class JobRepository {

    private List<Job> jobs;

    /**
     * Constructor for the JobRepository class.
     * Initializes the list of jobs.
     */
    public JobRepository() {
            this.jobs = new ArrayList<>();
        }

    /**
     * Retrieves an existing Job by its name.
     *
     * @param name The name of the job to retrieve.
     * @return The Job with the specified name.
     * @throws IllegalArgumentException if the job does not exist.
     */
    public Job getJobByName(String name) {
        for (Job existingJob : jobs) {
            if (existingJob.getName().equals(name)) {
                return existingJob;
            } else {
                throw new IllegalArgumentException("Job does not exist.");
            }

        }
        return null;
    }


    /**
     * Creates a new job and adds it to the organization.
     *
     * @param name the name of the job
     * @return an optional containing the created job, or empty if creation fails
     */
    public Optional<Job> createJob(String name) {
        Job job = new Job(name);
        if (addJob(job)) {
            return Optional.of(job);
        }
        return Optional.empty();
    }

    /**
     * Adds a new job to the repository.
     *
     * @param job The job to add.
     * @return An optional containing the added job if successful, empty optional otherwise.
     */

    private boolean addJob(Job job) {
        if (validateJob(job)) {
            return jobs.add(job.clone());
        }
        return false;
    }
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

    /**
     * Validates a job to check for duplicates.
     *
     * @param job The job to validate.
     * @return True if the job is valid (not a duplicate).
     */
    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    /**
     * Returns a defensive (immutable) copy of the list of jobs.
     *
     * @return A copy of the list of jobs.
     */
    public List<Job> getJobs() {
        // Return a defensive copy to prevent modification from outside
        return List.copyOf(jobs);
    }

}