# US003 - Register a Collaborator 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                         | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterCollaboratorUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterCollaboratorController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Collaborator?        | CollaboratorRepository         | Creator (Rule 1): in the DM CollaboratorRepository has a Collaborator.                                        |
| 			  		        | ... knowing the user using the system?        | UserSession                    | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | CollaboratorRepository         | IE: knows/has its own HRMs/Collaborators                                                                      |
| 			  		        | 							                                       | HRM                            | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	... requesting the data						                | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     |
| Step 3  		     | 	...saving the inputted data?                 | Collaborator                   | IE: object created in step 1 has its own data.                                                                ||              
| Step 4 		      | 	... showing the job list?                    | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     | 
| Step 5 		      | 	... validating the selected data?            | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     |
| Step 6 		      | 	... showing the data before confirmation?    | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     |
| Step 7 		      | 	... validating all data (local validation)?  | Collaborator                   | IE: object created in step 1 has its own data.                                                                              |
| 			  		        | 	... validating all data (global validation)? | CollaboratorRepository         | IE: knows all its collaborators.                                                                              | 
| 			  		        | 	... saving the registered collaborator?      | CollaboratorRepository         | IE: stores all collaborators.                                                                                 | 
| Step 8 		      | 	... informing operation success?             | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* CollaboratorRepository
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