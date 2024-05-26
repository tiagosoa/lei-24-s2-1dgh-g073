# US026 - Assign Vehicles

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer               | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | AssignVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | AssignVehicleController | Controller                                                                                                    |
| 			  		        | 	... getting the Agenda for the current GSM?  | AssignVehicleController | Controller                                                                                                    |
| 			  		        | 	... getting the Agenda entries?              | AssignVehicleController | Controller                                                                                                    |
| 			  		        | ... knowing the user using the system?        | UserSession          | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | GSM                  | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	...showing list of Agenda entries?						     | AssignVehicleUI         | IE: is responsible for all user interactions                                                                  |
| Step 3  		     | 	...getting the selected entry?               | AssignVehicleUI         | IE: is responsible for all user interactions                                                                  |         
|                | 	... getting the list of Vehicles?            | AssignVehicleController | Controller                                                                                                    |
| Step 4  		     | 	...showing the list of Vehicles?						       | AssignVehicleUI         | IE: is responsible for all user interactions                                                                  |              
| Step 5  		     | 	...getting the selected vehicles?						      | AssignVehicleUI         | IE: is responsible for all user interactions                                                                  |   
| Step 6  		     | 	...asking for confirmation?						            | AssignVehicleUI         | IE: is responsible for all user interactions                                                                  |   
| Step 7 		      | 	... validating all data (local validation)?  | AgendaEntry          | IE: owns its data.                                                                                            |
| 			  		        | 	... validating all data (global validation)? | Agenda               | IE: stores AgendaEntries.                                                                                     |
| 			  		        | 	... updating the entry?                      | Agenda               | IE: owns AgendaEntries.                                                                                       | 
| Step 8 		      | 	... informing operation success?             | AssignVehicleUI         | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* AgendaEntry
* Agenda

Other software classes (i.e. Pure Fabrication) identified: 

* AssignVehicleUI  
* AssignVehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us026-sequence-diagram-full.svg)

### Split Diagrams

![Sequence Diagram - Assign Vehicles](svg/us026-sequence-diagram-partial-assign-vehicles.svg)
![Sequence Diagram - Get GSM](svg/us026-sequence-diagram-partial-get-gsm.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us026-class-diagram.svg)