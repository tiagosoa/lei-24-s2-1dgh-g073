# US004 - Assign a Skill 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | AssignSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | AssignSkillController | Controller                                                                                                    |
| 			  		        | 	... assigning the Skills?                    | Organization          | Creator (Rule 1): in the DM Organization has Collaborators and Skills.                                        |
| 			  		        | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization          | IE: knows/has its own Collaborators/Skills                                                                    |
| 			  		        | 							                                       | HRM                   | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	...assigning the Skill?						                | AssignSkillController | Controller                                                                                                    |
| Step 3  		     | 	...saving the inputted data?                 | AssignedCollaborator  | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... validating all data (local validation)?  | AssignedCollaborator  | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | Organization          | IE: knows all its skills and collaborators.                                                                   | 
| 			  		        | 	... saving the assigned collaborator?        | Organization          | IE: owns all its skills and collaborators.                                                                    | 
| Step 5 		      | 	... informing operation success?             | AssignSkillUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Collaborator
* Skill

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