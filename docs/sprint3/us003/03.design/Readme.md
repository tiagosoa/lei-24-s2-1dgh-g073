# US003 - Register a Collaborator 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                         | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterCollaboratorUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController | Controller                                                                                                    |
| 			  		        | 	... registering a new Collaborator?          | Organization                   | Creator (Rule 1): in the DM Organization has a Collaborator.                                                  |
| 			  		        | ... knowing the user using the system?        | UserSession                    | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization                   | IE: knows/has its own Employees/Collaborators                                                                 |
| 			  		        | 							                                       | HRM                            | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	... assigning the job?						                 | pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController | Controller                                                                                                    |
| Step 3  		     | 	...saving the inputted data?                 | Collaborator                   | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... validating all data (local validation)?  | Collaborator                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | Organization                   | IE: knows all its collaborators.                                                                              | 
| 			  		        | 	... saving the registered collaborator?      | Organization                   | IE: owns all its collaborators.                                                                               | 
| Step 5 		      | 	... informing operation success?             | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Collaborator

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterCollaboratorUI  
* pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

[Sequence Diagram - Full](svg/us003-sequence-diagram-full.svg)

### Split Diagram

[Sequence Diagram - Split](svg/us003-sequence-diagram-split.svg)

### Partial Diagrams

[Sequence Diagram - Register Collaborator](svg/us003-sequence-diagram-partial-register-collaborator.svg)
[Sequence Diagram - Assign Job](svg/us003-sequence-diagram-partial-assign-job.svg)
[Sequence Diagram - Get Employee](svg/us003-sequence-diagram-partial-get-employee.svg)

## 3.3. Class Diagram (CD)

[Class Diagram](svg/us003-class-diagram.svg)