package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {

    @Test
    void getJobByNameEmptyList() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        assertThrows(IllegalArgumentException.class,
                () -> jobRepository.getJobByName(jobName));
    }

    @Test
    void getJobByNameNullList() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        assertThrows(IllegalArgumentException.class,
                () -> jobRepository.getJobByName(jobName));
    }

    @Test
    void ensureNewJobSuccessfullyAdded() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        Job job = new Job(jobName);
        jobRepository.addJob(job);
    }

    @Test
    void testThatCreateJobWorks() {
        JobRepository jobRepository = new JobRepository();

         

        Job expected = new Job("Job Name");

        Optional<Job> job = jobRepository.createJob("Job Name");

        assertNotNull(job);
        assertTrue(job.isPresent());
        assertEquals(expected, job.get());
    }

    @Test
    void ensureGetJobForExistingJob() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        Job job = new Job(jobName);
        jobRepository.addJob(job);
        Job job1 = jobRepository.getJobByName(jobName);
        assertEquals(job, job1);
    }

    @Test
    void ensureGetJobFailsForNonExistingJob() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        Job job = new Job(jobName);
        jobRepository.addJob(job);
        String jobName1 = "Job Name 1";
        assertThrows(IllegalArgumentException.class,
                () -> jobRepository.getJobByName(jobName1));

    }

    @Test
    void ensureGetJobReturnsAnImmutableList() {
        JobRepository jobRepository = new JobRepository();
        String jobName = "Job Name";
         
        Job job = new Job(jobName);
        jobRepository.addJob(job);

        assertThrows(UnsupportedOperationException.class,
                () -> jobRepository.getJobs().add(new Job("Job Name One")));

    }

    @Test
    void ensureGetJobReturnsTheCorrectList() {
        //Arrange
        JobRepository jobRepository = new JobRepository();
         
        String jobName = "Job Name";
        Job job = new Job(jobName);
        jobRepository.addJob(job);
        int expectedSize = 1;

        //Act
        int size = jobRepository.getJobs().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(job, jobRepository.getJobs().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateJobFails() {
        //Arrange
        JobRepository jobRepository = new JobRepository();
         
        Job job = new Job("Job Name");
        //Add the first job
        jobRepository.addJob(job);

        //Act
        boolean duplicateJob = jobRepository.addJob(job);

        //Assert
        assertFalse(duplicateJob);
    }

    @Test
    void ensureAddingDifferentJobsWorks() {
        //Arrange
        JobRepository jobRepository = new JobRepository();
         
        Job jobOne = new Job("Job Name One");
        Job jobTwo = new Job("Job Name Two");
        //Add the first task
        jobRepository.addJob(jobOne);

        //Act
        boolean expected = jobRepository.addJob(jobTwo);

        //Assert
        assertTrue(expected);
    }
}