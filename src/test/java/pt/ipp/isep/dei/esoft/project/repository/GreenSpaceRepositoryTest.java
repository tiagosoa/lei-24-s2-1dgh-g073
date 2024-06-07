package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSpaceRepositoryTest {

    private GreenSpaceRepository greenSpaceRepository;
    private GSM gsm1;
    private GSM gsm2;
    private GreenSpace greenSpace1;
    private GreenSpace greenSpace2;

    @BeforeEach
    public void setUp() {
        greenSpaceRepository = new GreenSpaceRepository();
        gsm1 = new GSM("gsm@gmail.pt");
        gsm2 = new GSM("gsm2@gmail.pt");

        greenSpace1 = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm1);
        greenSpace2 = new GreenSpace("Arca d'Água", "Garden", 512.0, gsm2);

        greenSpaceRepository.addGreenSpace(greenSpace1);
        greenSpaceRepository.addGreenSpace(greenSpace2);
    }

    @Test
    public void ensureGetGreenSpaceByNameWorks() {
        GreenSpace retrievedGreenSpace = greenSpaceRepository.getGreenSpaceByName("Covelo");
        assertEquals(greenSpace1, retrievedGreenSpace);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            greenSpaceRepository.getGreenSpaceByName(" ");
        });
        assertEquals("GreenSpace does not exist.", exception.getMessage());
    }

    @Test
    public void ensureRegisterGreenSpaceWorks() {
        Optional<GreenSpace> newGreenSpace = greenSpaceRepository.registerGreenSpace("São Roque", "Large-sized Park", 1300.0, gsm1);
        assertTrue(newGreenSpace.isPresent());
        assertEquals("São Roque", newGreenSpace.get().getName());

        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        assertEquals(3, greenSpaces.size());
    }

    @Test
    public void ensureAddGreenSpaceWorks() {
        GreenSpace newGreenSpace = new GreenSpace("Palácio Cristal", "Garden", 1456.0, gsm1);
        boolean added = greenSpaceRepository.addGreenSpace(newGreenSpace);
        assertTrue(added);

        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        assertEquals(3, greenSpaces.size());
    }

    @Test
    public void ensureAddDuplicateGreenSpaceFails() {
        GreenSpace duplicateGreenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm1);
        boolean added = greenSpaceRepository.addGreenSpace(duplicateGreenSpace);
        assertFalse(added);

        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        assertEquals(2, greenSpaces.size());
    }

    @Test
    public void ensureGetGreenSpaceListWorks() {
        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        assertEquals(2, greenSpaces.size());
        assertTrue(greenSpaces.contains(greenSpace1));
        assertTrue(greenSpaces.contains(greenSpace2));
    }

    @Test
    public void ensureGetManagedGreenSpacesWorks() {
        List<GreenSpace> managedByGSM1 = greenSpaceRepository.getManagedGreenSpaces(gsm1);
        assertEquals(1, managedByGSM1.size());
        assertTrue(managedByGSM1.contains(greenSpace1));

        List<GreenSpace> managedByGSM2 = greenSpaceRepository.getManagedGreenSpaces(gsm2);
        assertEquals(1, managedByGSM2.size());
        assertTrue(managedByGSM2.contains(greenSpace2));
    }

    @Test
    public void ensureGetManagedGreenSpacesSortedWorks() {
        Properties config = new Properties();
        config.setProperty("sortingAlgorithm", "bubbleSort");
        greenSpaceRepository.config = config;

        List<GreenSpace> sortedGreenSpaces = greenSpaceRepository.getManagedGreenSpacesSorted(gsm1);
        assertEquals(1, sortedGreenSpaces.size());
        assertEquals(greenSpace1, sortedGreenSpaces.get(0));
    }
}
