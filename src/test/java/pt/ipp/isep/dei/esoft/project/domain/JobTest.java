package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void ensureJobIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);
    }

    @Test
    void ensureJobNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Job(null, hrm));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);
        assertEquals(job, job);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);

        assertNotEquals(job, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);

        assertNotEquals(job, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);
        Job job1 = new Job("namelei", hrm);

        assertNotEquals(job, job1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);
        assertEquals(job.hashCode(), job.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name", hrm);
        Job job1 = new Job("namelei", hrm);

        assertNotEquals(job.hashCode(), job1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Job job = new Job("name", hrm);
        Job clone = job.clone();
        assertEquals(job, clone);
    }
}