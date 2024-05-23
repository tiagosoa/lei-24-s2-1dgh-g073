package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a To-Do List in the system.
 */
public class ToDoList {

    private List<Entry> entries;
    private GSM gsm;

    /**
     * Constructor for creating a ToDoList object with a list of entries and a GSM object.
     *
     * @param entries the list of entries to be added to the ToDoList
     * @param gsm the GSM object associated with the ToDoList
     */
    public ToDoList(List<Entry> entries, GSM gsm) {
        this.entries = entries;
        this.gsm = gsm;
    }

    /**
     * Validates the input parameters for creating a collaborator.
     *
     * @param name         the name of the collaborator
     * @param birthdate    the birthdate of the collaborator
     * @param admissiondate the admission date of the collaborator
     * @param address      the address of the collaborator
     * @param mobile       the mobile number of the collaborator
     * @param email        the email of the collaborator
     * @param taxpayer     the taxpayer number of the collaborator
     * @param doctype      the documentation type of the collaborator
     * @param IDnumber     the ID number of the collaborator
     */
    private void validateCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        if (name == null || name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Collaborator name cannot be null, empty, or contain special characters or digits.");
        }
        if (birthdate == null || birthdate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator birth date cannot be null or empty.");
        }
        if (admissiondate == null || admissiondate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator admission date cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Collaborator address cannot be null or empty.");
        }
        if (mobile <= 0) {
            throw new IllegalArgumentException("Collaborator mobile number must be a positive number.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Collaborator email cannot be empty.");
        }
        if (taxpayer <= 0) {
            throw new IllegalArgumentException("Collaborator taxpayer number must be a positive number.");
        }
        if (doctype == null || doctype.isEmpty()) {
            throw new IllegalArgumentException("Collaborator documentation type cannot be empty.");
        }
        if (IDnumber <= 0) {
            throw new IllegalArgumentException("Collaborator ID number must be a positive number.");
        }
    }

    private void validateName(String name){
        if (name == null || name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Collaborator name cannot be null, empty, or contain special characters or digits.");
        }
    }

    /**
     * Adds an entry to the to-do list
     *
     * @param entry the entry to be added
     * @return true if the entry was added successfully, false otherwise
     */

    public boolean addEntry(Entry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDoList)) return false;
        ToDoList that = (ToDoList) o;
        return entries.equals(that.entries) && gsm.equals(that.gsm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, gsm);
    }

    public ToDoList clone() {
        return new ToDoList(entries, gsm);
    }

    public GSM getGSM() {return gsm;}
}
