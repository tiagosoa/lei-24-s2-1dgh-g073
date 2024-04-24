# US002 - Create a Job

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...    | Answer              | Justification (with patterns)                                                                                 |
|:---------------|:-----------------------------------------------|:--------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?               | CreateJobUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                      | CreateJobController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Job?                  | Organization        | Creator (Rule 1): in the DM Organization has a Job.                                                           |
| 			  		        | ... knowing the user using the system?         | UserSession         | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                        | Organization        | IE: knows/has its own Employees and HRMs                                                                      |
| 			  		        | 							                                        | HRM                 | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 							                                        |                     |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                  | Job                 | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... validating all data (local validation)?   | Job                 | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)?  | Organization        | IE: knows all its jobs.                                                                                       | 
| 			  		        | 	... saving the created job?                   | Organization        | IE: owns all its jobs.                                                                                        | 
| Step 5 		      | 	... informing operation success?              | CreateJobUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Organization
* Job

Other software classes (i.e. Pure Fabrication) identified:

* CreateJobUI
* CreateJobController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

[Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)

### Split Diagram

[Sequence Diagram - Split](svg/us002-sequence-diagram-split.svg)

### Partial Diagrams

[Sequence Diagram - Create Job](svg/us002-sequence-diagram-partial-create-job.svg)
[Sequence Diagram - Get HRM](svg/us002-sequence-diagram-partial-get-employee.svg)

## 3.3. Class Diagram (CD)

[Class Diagram](svg/us002-class-diagram.svg)