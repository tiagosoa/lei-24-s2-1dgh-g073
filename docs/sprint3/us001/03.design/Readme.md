# US001 - Create a Skill 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | CreateSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | CreateSkillController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?               | SkillRepository       | Creator (Rule 1): in the DM SkillRepository saves Skill.                                                      |
| 			  		        | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization          | IE: knows/has its own Employees and HRMs.                                                                     |
| 			  		        | 							                                       | HRM                   | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	... requesting the name of the skill?						  | CreateSkillUI         | Pure Fabrication: user inputs the skill name in the UI.                                                       |
| Step 3  		     | 	...saving the inputted data?                 | Skill                 | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... showing the typed name?                  | CreateSkillUI         | Pure Fabrication: UI shows the typed name.                                                                    | 
| Step 5		       | 	... validating all data (local validation)?  | Skill                 | IE: Constructor validates its own data.                                                                       | 
| 			  		        | 	... validating all data (global validation)? | SkillRepository       | IE: knows all its skills and their traits.                                                                    | 
| 			  		        | 	... saving the created skill?                | SkillRepository       | IE: saves all its skills.                                                                                     | 
| Step 6 		      | 	... informing operation success?             | CreateSkillUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* SkillRepository
* Skill

Other software classes (i.e. Pure Fabrication) identified: 

* CreateSkillUI  
* CreateSkillController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

[Sequence Diagram - Full](svg/us001-sequence-diagram-full.svg)

### Split Diagrams

[Sequence Diagram - Create Skill](svg/us001-sequence-diagram-partial-create-skill.svg)
[Sequence Diagram - Get HRM](svg/us001-sequence-diagram-partial-get-hrm.svg)

## 3.3. Class Diagram (CD)

[Class Diagram](svg/us001-class-diagram.svg)