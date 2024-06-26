# US001 - Create a Skill 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | CreateSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | CreateSkillController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?               | Organization          | Creator (Rule 1): in the DM Organization has a Skill.                                                         |
| 			  		        | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization          | IE: knows/has its own Employees                                                                               |
| 			  		        | 							                                       | HRM                   | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 							                                       |                       |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Skill                 | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... validating all data (local validation)?  | Skill                 | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | Organization          | IE: knows all its tasks.                                                                                      | 
| 			  		        | 	... saving the created task?                 | Organization          | IE: owns all its tasks.                                                                                       | 
| Step 5 		      | 	... informing operation success?             | CreateSkillUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Skill

Other software classes (i.e. Pure Fabrication) identified: 

* CreateSkillUI  
* CreateSkillController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us001-sequence-diagram-full.svg)

### Split Diagrams

n/a

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us001-class-diagram.svg)