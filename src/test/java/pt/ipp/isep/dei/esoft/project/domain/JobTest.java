package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void ensureJobIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
    }

    @Test
    void ensureJobNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Job(null));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
        assertEquals(job, job);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");

        assertNotEquals(job, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");

        assertNotEquals(job, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
        Job job1 = new Job("namelei");

        assertNotEquals(job, job1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
        assertEquals(job.hashCode(), job.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Job job = new Job("name");
        Job job1 = new Job("namelei");

        assertNotEquals(job.hashCode(), job1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Job job = new Job("name");
        Job clone = job.clone();
        assertEquals(job, clone);
    }
}