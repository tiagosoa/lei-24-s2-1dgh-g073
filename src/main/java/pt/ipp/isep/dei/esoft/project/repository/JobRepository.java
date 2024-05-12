package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private List<Job> jobs;
    public JobRepository() {
        this.jobs = Organization.getJobList();
        if (this.jobs == null) {
            // Initialize the collaborators list if it's null
            this.jobs = new ArrayList<>();
        }
    }

    /**
     * This method returns an exsiting Job by its name.
     *
     * @param name The name of the job to be created.
     * @return The job name
     * @throws IllegalArgumentException if the job does not exist, which should never happen.
     */
    public Job getJobByName(String name) {
        for (Job existingJob : jobs) {
            if (existingJob.getName().equals(name)) {
                return existingJob;
            }
        }
        throw new IllegalArgumentException("Job does not exist.");
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

    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Job> getJobs() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }


}