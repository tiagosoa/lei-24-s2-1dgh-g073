# US022 - Add an entry to the Agenda

## 2. Analysis

### GSM (Green Spaces Manager):
* Responsible for adding entries to the Agenda
* Attributes include name, vatNumber, phoneNumber, and email.

### AgendaEntry:
* Added to the Agenda by the GSM.
* Attributes include the team and the vehicles/equipment assigned to it, approximate duration, and status.
* Existed previously in the To-Do List.

### TDLEntry:
* Added to the To-Do List by the GSM.
* Moves from the To-Do List to the Agenda.
* Attributes include title/description, degree of urgency, approximate duration, and an associated Green Space.

### Agenda:
* Used by the GSM.
* Includes entries created by the GSM that relate to a task.
* Made up of entries that relate to a task.
* Comprises entries that were previously in the To-Do List.

### ToDoList
* Used by the GSM.
* Includes all pending entries created by the GSM.
* Some entries are moved to the Agenda.

### To-Do List Repository:
* Stores To-Do Lists.

### Agenda Repository:
* Stores Agendas.

### 2.1. Relevant Domain Model Excerpt

![Domain Model](svg/us022-domain-model.svg)

### 2.2. Other Remarks

n/a