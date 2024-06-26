# US008 - List Vehicles that need Maintenance


## 1. Requirements Engineering

### 1.1. User Story Description

As a VFM, I want the system to produce a list (report) of vehicles needing maintenance.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	The system automatically creates the list based on the current KMs and the check-up frequency of the registered vehicles.

**From the client clarifications:**

> **Question:** Can the vehicles get placed automatically on a list or the one listing has to be the FM?
>
> **Answer:** The list of vehicles is automatically created but the creation is triggered by the FM.

> **Question:** What data of a vehicle is shown on the list?
>
> **Answer:** Data that allows identification of the vehicle like the Plate, brand and model, as well as the data that allows to select/insert the vehicle in the list, which is the number of kms, frequency of checkup and the last checkup.

### 1.3. Acceptance Criteria

* **AC1:** The list must clearly identify the vehicles through: plate number, brand, model and the date that justified the maintenance need
* **AC2:** The report should have the data concerning the vehicle description (Plate, Brand, Model and Current Kms) and the Checkup related data

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a Vehicle" as there must be at least one vehicle already registered, as well as "US007 - Register a Vehicle's check-up", as there must be at least one vehicle with a check-up date registered.

### 1.5 Input and Output Data

**Input Data:**
n/a

**Output Data:**
* List of the vehicles that need maintenance

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

n/a