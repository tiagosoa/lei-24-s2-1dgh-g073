# US023 - Assign a Team

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer               | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | AssignTeamUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | AssignTeamController | Controller                                                                                                    |
| 			  		        | 	... getting the Agenda for the current GSM?  | AssignTeamController | Controller                                                                                                    |
| 			  		        | 	... getting the Agenda entries?              | AssignTeamController | Controller                                                                                                    |
| 			  		        | ... knowing the user using the system?        | UserSession          | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | GSM                  | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	...showing list of Agenda entries?						     | AssignTeamUI         | IE: is responsible for all user interactions                                                                  |
| Step 3  		     | 	...getting the selected entry?               | AssignTeamUI         | IE: is responsible for all user interactions                                                                  |         
|                | 	... getting the list of Teams?               | AssignTeamController | Controller                                                                                                    |
| Step 4  		     | 	...showing the list of Teams?						          | AssignTeamUI         | IE: is responsible for all user interactions                                                                  |              
| Step 5  		     | 	...getting the selected team?						          | AssignTeamUI         | IE: is responsible for all user interactions                                                                  |   
| Step 6  		     | 	...asking for confirmation?						            | AssignTeamUI         | IE: is responsible for all user interactions                                                                  |   
| Step 7 		      | 	... validating all data (local validation)?  | AgendaEntry          | IE: owns its data.                                                                                            |
| 			  		        | 	... validating all data (global validation)? | Agenda               | IE: stores AgendaEntries.                                                                                     |
| 			  		        | 	... updating the entry?                      | Agenda               | IE: owns AgendaEntries.                                                                                       | 
| Step 8 		      | 	... informing operation success?             | AssignTeamUI         | IE: is responsible for user interactions.                                                                     |
| Step 9 		      | 	... sending the message to the team members? | AssignTeamUI         | Pure Fabrication                                                                                              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* AgendaEntry
* Agenda

Other software classes (i.e. Pure Fabrication) identified: 

* AssignTeamUI  
* AssignTeamController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us023-sequence-diagram-full.svg)

### Split Diagrams

![Sequence Diagram - Assign Team](svg/us023-sequence-diagram-partial-assign-team.svg)
![Sequence Diagram - Get Agenda Entries](svg/us023-sequence-diagram-partial-get-agenda-entries.svg)
![Sequence Diagram - Get Team List](svg/us023-sequence-diagram-partial-get-team-list.svg)
![Sequence Diagram - Get GSM](svg/us023-sequence-diagram-partial-get-gsm.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us023-class-diagram.svg)