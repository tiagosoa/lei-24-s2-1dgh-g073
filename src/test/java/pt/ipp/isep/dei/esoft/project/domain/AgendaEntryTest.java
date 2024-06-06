package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaEntryTest {

    private GSM gsm;
    private AgendaEntry entry;
    private GreenSpace greenSpace;
    private Team team;
    private Vehicle vehicle1;
    private Vehicle vehicle2;

    private Vehicle vehicle3;

    @BeforeEach
    public void setUp() {
        gsm = new GSM("gsm@gmail.pt");
        entry = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        greenSpace = new GreenSpace("Covelo", "Medium-sized Park", 1230.0, gsm);
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789));
        collaborators.add(new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799));
        team = new Team(collaborators, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        vehicle1 = new Vehicle("Porsche", "Turbo", "Car", 1275, 1820, 30000, date1, date2, 10000, "00-AA-00", date3);
        vehicle2 = new Vehicle("Ford", "Focus", "Car", 1325, 1950, 30500, date1, date2, 15000, "01-AB-01", date3);
        vehicle3 = new Vehicle("Fiat", "Punto", "Car", 1005, 1020, 25000, date1, date2, 7000, "22-AC-01", date3);    }

    @Test
    public void ensureAddGreenSpaceWorks() {
        boolean added = entry.addGreenSpace(greenSpace);
        assertTrue(added);
        assertEquals(greenSpace, entry.getAssociatedGreenSpace());

        GreenSpace greenSpace2 = new GreenSpace("Arca d'Água", "Garden", 512.0, gsm);
        added = entry.addGreenSpace(greenSpace2);
        assertFalse(added);
        assertEquals(greenSpace, entry.getAssociatedGreenSpace());
    }

    @Test
    public void ensureAddTeamWorks() {
        boolean added = entry.addTeam(team);
        assertTrue(added);
        assertEquals(team, entry.getAssociatedTeam());

        List<Collaborator> collaborators2 = new ArrayList<>();
        collaborators2.add(new Collaborator("Jonas", "25-09-1994", "08-07-2023", "Rio Tinto", 93761984, "jonas@gmail.pt", 275315949,"CC", 734691829));
        Team team2 = new Team(collaborators2, 2);
        added = entry.addTeam(team2);
        assertFalse(added);
        assertEquals(team, entry.getAssociatedTeam());
    }

    @Test
    public void ensureSetDeadlineWorks() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        entry.setDeadline(newDate);
        assertEquals(newDate, entry.getStartDate());
    }

    @Test
    public void ensureSetStatusWorks() {
        entry.setStatus("Done");
        assertEquals("Done", entry.getStatus());
    }

    @Test
    public void ensureEntryCancelWorks() {
        entry.cancel();
        assertEquals("Cancelled", entry.getStatus());
    }

    @Test
    public void ensureAssignVehicleWorks() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        entry.assignVehicle(vehicles);
        assertEquals(2, entry.getAssociatedVehicles().size());
        assertTrue(entry.getAssociatedVehicles().contains(vehicle1));
        assertTrue(entry.getAssociatedVehicles().contains(vehicle2));

        entry.assignVehicle(vehicles);
        assertEquals(2, entry.getAssociatedVehicles().size());
    }

    @Test
    public void ensureEqualsWorks() {
        AgendaEntry sameEntry = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        AgendaEntry differentEntry = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));

        assertEquals(entry, sameEntry);
        assertNotEquals(entry, differentEntry);
    }

    @Test
    public void ensureHashCodeWorks() {
        AgendaEntry sameEntry = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        assertEquals(entry.hashCode(), sameEntry.hashCode());

        AgendaEntry differentEntry = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        assertNotEquals(entry.hashCode(), differentEntry.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        AgendaEntry clonedEntry = entry.clone();
        assertEquals(entry, clonedEntry);
        assertNotSame(entry, clonedEntry);
    }

    @Test
    public void ensureGettersWork() {
        assertEquals("Podar árvores",  entry.getTitle());
        assertEquals("Podar todas as árvores no parque.",  entry.getTaskDescription());
        assertEquals("Medium",  entry.getUrgency());
        assertEquals("3 weeks",  entry.getDuration());
        assertEquals("Planned", entry.getStatus());
        assertEquals( LocalDate.now().plusDays(3), entry.getStartDate());
    }
}
