# US026 - Assign vehicles to an entry in the Agenda.


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to assign one or more vehicles to an entry in the Agenda

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	The Agenda is made up of entries that relate to a task (which was previously in the To-Do List), the team that will carry out the task, the vehicles/equipment assigned to the task, expected duration, and the status (Planned, Postponed, Canceled, Done).


**From the client clarifications:**

> **Question:** Is the user able to assign only unassigned vehicles?
>
> **Answer:** Yes, the vehicle needs to be available in the period.

> **Question:** Is it possible to add any kind of vehicle?
>
> **Answer:** Yes, any kind of vehicle can be assigned.


### 1.3. Acceptance Criteria

* n/a

### 1.4. Found out Dependencies

* There is a dependency on "US022 - Add an entry to the Agenda", as there must be at least one entry in the Agenda to assign vehicles to it.
* There is a dependency on "US006 - Register a Vehicle", as there must be at least one vehicle registered to assign it to an entry.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
    * the vehicle(s) to assign to the entry

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us026-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* n/a