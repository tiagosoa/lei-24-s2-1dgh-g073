# US008 - List Vehicles that need Maintenance

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                           | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------------------------------|:---------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 | 	... interacting with the actor?            | ProduceMaintenanceListUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 | 	... coordinating the US?                   | ProduceMaintenanceListController | Controller                                                                                                    |
| 			  		 | 	... creating a new List?                   | VehicleRepository                | Creator (Rule 1): in the DM Organization has a List                                                           |
| 			  		 | ... knowing the user using the system?      | UserSession                      | IE: cf. A&A component documentation.                                                                          |
| 			  		 | 							                                     | Organization                     | IE: knows/has its own VFMs                                                                                    |
| 			  		 | 							                                     | VFM                              | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		 | ... getting the vehicle parts?							       | VehicleRepository                |                                                                                                               |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* List

Other software classes (i.e. Pure Fabrication) identified: 

* ProduceMaintenanceListUI  
* ProduceMaintenanceListController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us008-sequence-diagram-split.svg)

**Get Vehicle Parts**

![Sequence Diagram - Partial - Get Vehicle Parts](svg/us008-sequence-diagram-partial-get-vehicle-parts.svg)

**Create List**

![Sequence Diagram - Partial - Create List](svg/us008-sequence-diagram-partial-create-list.svg)

**Get VFM**

![Sequence Diagram - Partial - Get VFM](svg/us008-sequence-diagram-partial-get-vfm.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)