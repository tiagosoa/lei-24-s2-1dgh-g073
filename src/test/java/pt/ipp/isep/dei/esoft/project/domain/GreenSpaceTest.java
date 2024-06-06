package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSpaceTest {
    private GreenSpace greenSpace;
    private GSM gsm;

    @BeforeEach
    public void setUp() {
        gsm = new GSM("gsm@gmail.pt");
        greenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
    }

    @Test
    public void ensureGetNameWorks() {
        assertEquals("Covelo", greenSpace.getName());
    }

    @Test
    public void ensureGetTypeWorks() {
        assertEquals("Medium-sized Park", greenSpace.getType());
    }

    @Test
    public void ensureGetAreaWorks() {
        assertEquals(1230.0, greenSpace.getArea());
    }

    @Test
    public void ensureGetGSMWorks() {
        assertEquals(gsm, greenSpace.getGSM());
    }

    @Test
    public void ensureIsOfNameWorks() {
        assertTrue(greenSpace.isOfName("Covelo"));
        assertFalse(greenSpace.isOfName("Arca d'Água"));
    }

    @Test
    public void ensureEqualsWorks() {
        GreenSpace sameGreenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
        GreenSpace differentGreenSpace = new GreenSpace("Arca d'Água", "Garden", 512.0, gsm);

        assertEquals(greenSpace, sameGreenSpace);
        assertNotEquals(greenSpace, differentGreenSpace);
    }

    @Test
    public void ensureHashCodeWorks() {
        GreenSpace sameGreenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
        assertEquals(greenSpace.hashCode(), sameGreenSpace.hashCode());

        GreenSpace differentGreenSpace = new GreenSpace("Arca d'Água", "Garden", 512.0, gsm);
        assertNotEquals(greenSpace.hashCode(), differentGreenSpace.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        GreenSpace clonedGreenSpace = greenSpace.clone();
        assertEquals(greenSpace, clonedGreenSpace);
        assertNotSame(greenSpace, clonedGreenSpace);
    }
}
