package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GSMTest {
    private GSM gsm;

    @BeforeEach
    public void setUp() {
        gsm = new GSM("gsm@gmail.pt");
    }

    @Test
    public void ensureGetEmailWorks() {
        assertEquals("gsm@gmail.pt", gsm.getEmail());
    }

    @Test
    public void ensureHasEmailWorks() {
        assertTrue(gsm.hasEmail("gsm@gmail.pt"));
        assertFalse(gsm.hasEmail("gsm2@gmail.pt"));
    }

    @Test
    public void ensureEqualsWorks() {
        GSM sameGsm = new GSM("gsm@gmail.pt");
        GSM differentGsm = new GSM("gsm2@gmail.pt");

        assertEquals(gsm, sameGsm);
        assertNotEquals(gsm, differentGsm);
        assertNotEquals(gsm, null);
        assertNotEquals(gsm, new Object());
    }

    @Test
    public void ensureHashCodeWorks() {
        GSM sameGsm = new GSM("gsm@gmail.pt");
        assertEquals(gsm.hashCode(), sameGsm.hashCode());

        GSM differentGsm = new GSM("gsm2@gmail.pt");
        assertNotEquals(gsm.hashCode(), differentGsm.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        GSM clonedGsm = gsm.clone();
        assertEquals(gsm, clonedGsm);
        assertNotSame(gsm, clonedGsm);
    }
}
