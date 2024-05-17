# US004 - Assign a Skill 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...    | Answer                         | Justification (with patterns)                                                                                  |
|:---------------|:-----------------------------------------------|:-------------------------------|:---------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                | AssignSkillUI                  | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.  |
|                | ... coordinating the US?                       | AssignSkillController          | Controller (UI Pattern): The Controller class orchestrates the user story and interacts with other components. |
| 			  		        | 	... instantiating a new Collaborator?         | CollaboratorRepository         | Creator (Rule 1): in the DM CollaboratorRepository has a Collaborator.                                         |
|                | ... knowing the user using the system?         | UserSession                    | IE: cf. A&A component documentation.                                                                           |
|                |                                                |                                |                                                                                                                |
| Step 2         | ... showing the collaborator list?             | AssignSkillUI                  | IE: is responsible for user interactions.                                                                      |
| Step 3         | ... saving the selected collaborators?         | AssignSkillUI                  | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.  |
| Step 4         | ... showing the skill list?                    | AssignSkillUI                  | IE: is responsible for user interactions.                                                                      |
| Step 5         | ... saving the selected skills?                | AssignSkillUI                  | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.  |
| Step 6 		      | 	... showing the data before confirmation?     | AssignSkillUI                  | IE: is responsible for user interactions.                                                                      |
| Step 7 		      | 	... validating all data (local validation)?   | Collaborator                   | IE: object created in US03 has its own data.                                                                   |
| 			  		        | 	... validating all data (global validation)?  | CollaboratorRepository         | IE: knows all its collaborators.                                                                               | 
|                | ... knowing the user using the system?         | UserSession                    | IE: cf. A&A component documentation.                                                                           |
| Step 8 		      | 	... informing operation success?              | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                      |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* CollaboratorRepository
* Collaborator

Other software classes (i.e. Pure Fabrication) identified: 

* AssignSkillUI  
* AssignSkillController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

[Sequence Diagram - Full](svg/us004-sequence-diagram-full.svg)

### Split Diagrams

[Sequence Diagram - Split](svg/us004-sequence-diagram-split.svg)


### Partial Diagrams

[Sequence Diagram - Assign Skill](svg/us004-sequence-diagram-partial-assign-skill.svg)
[Sequence Diagram - Get Skill List](svg/us004-sequence-diagram-partial-get-skill-list.svg)
[Sequence Diagram - Get Selected Skills](svg/us004-sequence-diagram-partial-get-selected-skills.svg)
[Sequence Diagram - Get Collaborator](svg/us004-sequence-diagram-partial-get-collaborator.svg)
[Sequence Diagram - Get Collaborator List](svg/us004-sequence-diagram-partial-get-collaborator-list.svg)
[Sequence Diagram - Get HRM](svg/us004-sequence-diagram-partial-get-hrm.svg)

## 3.3. Class Diagram (CD)

[Class Diagram](svg/us004-class-diagram.svg)