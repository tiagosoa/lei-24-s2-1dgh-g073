package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VFMTest {

    @Test
    void ensureTwoVFMsWithSameEmailEquals() {
        VFM vfm1 = new VFM("john.doe@this.company.com");
        VFM vfm2 = new VFM("john.doe@this.company.com");
        assertEquals(vfm1, vfm2);
    }

    @Test
    void ensureVFMWithDifferentEmailNotEquals() {
        VFM vfm1 = new VFM("john.doe@this.company.com");
        VFM vfm2 = new VFM("jane.doe@this.company.com");
        assertNotEquals(vfm1, vfm2);
    }

    @Test
    void ensureVFMDoesNotEqualNull() {
        VFM vfm1 = new VFM("john.doe@this.company.com");
        assertNotEquals(vfm1, null);
    }

    @Test
    void ensureVFMDoesNotEqualOtherObject() {
        VFM vfm1 = new VFM("john.doe@this.company.com");
        assertNotEquals(vfm1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        VFM vfm1 = new VFM("john.doe@this.company.com");
        assertEquals(vfm1, vfm1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "john.doe@this.company.com";
        VFM vfm1 = new VFM(email);
        VFM vfm2 = new VFM(email);
        assertEquals(vfm1.hashCode(), vfm2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        VFM vfm1 = new VFM("john.doe@this.company.com");
        VFM vfm2 = new VFM("jane.doe@this.company.com");
        assertNotEquals(vfm1.hashCode(), vfm2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.compay.org";
        VFM vfm = new VFM(email);
        assertTrue(vfm.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        VFM vfm = new VFM(email);
        assertFalse(vfm.hasEmail("jane.doe@this.company.com"));

    }

    @Test
    void ensureCloneWorks() {
        String email = "john.doe@this.company.com";
        VFM vfm = new VFM(email);
        VFM clone = vfm.clone();
        assertEquals(vfm, clone);
    }
}