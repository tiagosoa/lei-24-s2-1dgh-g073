package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

public class JobRepository {

    private final List<Job> jobs;
    public JobRepository() {
        jobs = new ArrayList<>();
    }

    /**
     * This method returns an exsiting Job by its name.
     *
     * @param name The name of the job category to be created.
     * @return The job name
     * @throws IllegalArgumentException if the job does not exist, which should never happen.
     */
    public Job getJobByName(String name, HRM hrm) {
        Job newJob = new Job(name, hrm);
        Job job = null;
        if (jobs.contains(newJob)) {
            job = jobs.get(jobs.indexOf(newJob));
        }
        if (job == null) {
            throw new IllegalArgumentException(
                    "Job name requested for [" + name + "] does not exist.");
        }
        return job;
    }

    public Optional<Job> add(Job job) {

        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobs.add(newJob.get());
        }

        if (!operationSuccess) {
            newJob = Optional.empty();
        }

        return newJob;
    }

    private boolean validateSkill(Job job) {
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