package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HRMTest {

    @Test
    void ensureTwoHRMsWithSameEmailEquals() {
        HRM hrm1 = new HRM("john.doe@this.company.com");
        HRM hrm2 = new HRM("john.doe@this.company.com");
        assertEquals(hrm1, hrm2);
    }

    @Test
    void ensureHRMWithDifferentEmailNotEquals() {
        HRM hrm1 = new HRM("john.doe@this.company.com");
        HRM hrm2 = new HRM("jane.doe@this.company.com");
        assertNotEquals(hrm1, hrm2);
    }

    @Test
    void ensureHRMDoesNotEqualNull() {
        HRM hrm1 = new HRM("john.doe@this.company.com");
        assertNotEquals(hrm1, null);
    }

    @Test
    void ensureHRMDoesNotEqualOtherObject() {
        HRM hrm1 = new HRM("john.doe@this.company.com");
        assertNotEquals(hrm1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        HRM hrm1 = new HRM("john.doe@this.company.com");
        assertEquals(hrm1, hrm1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "john.doe@this.company.com";
        HRM hrm1 = new HRM(email);
        HRM hrm2 = new HRM(email);
        assertEquals(hrm1.hashCode(), hrm2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        HRM hrm1 = new HRM("john.doe@this.company.com");
        HRM hrm2 = new HRM("jane.doe@this.company.com");
        assertNotEquals(hrm1.hashCode(), hrm2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.compay.org";
        HRM hrm = new HRM(email);
        assertTrue(hrm.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        HRM hrm = new HRM(email);
        assertFalse(hrm.hasEmail("jane.doe@this.company.com"));

    }

    @Test
    void ensureCloneWorks() {
        String email = "john.doe@this.company.com";
        HRM hrm = new HRM(email);
        HRM clone = hrm.clone();
        assertEquals(hrm, clone);
    }
}