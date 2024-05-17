# US006 - Register a Vehicle

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | AddVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | AddVehicleController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Vehicle?             | VehicleRepository       | Creator (Rule 1): in the DM VehicleRepository saves Vehicle.                                                  |
| 			  		        | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization          | IE: knows/has its own Employees and VFMs.                                                                     |
| 			  		        | 							                                       | HRM                   | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 	... requesting the name of the skill?						  | AddVehicleUI         | Pure Fabrication: user inputs the skill name in the UI.                                                       |
| Step 3  		     | 	...saving the inputted data?                 | Vehicle                 | IE: object created in step 1 has its own data.                                                                |	|                      |                                                                                                               |              
| Step 4 		      | 	... showing the typed data?                  | AddVehicleUI         | Pure Fabrication: UI shows the typed name.                                                                    | 
| Step 5		       | 	... validating all data (local validation)?  | Vehicle                 | IE: Constructor validates its own data.                                                                       | 
| 			  		        | 	... validating all data (global validation)? | VehicleRepository       | IE: knows all its skills and their traits.                                                                    | 
| 			  		        | 	... saving the created skill?                | VehicleRepository       | IE: saves all its skills.                                                                                     | 
| Step 6 		      | 	... informing operation success?             | AddVehicleUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* VehicleRepository
* Vehicle

Other software classes (i.e. Pure Fabrication) identified:

* AddVehicleUI
* AddVehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

[Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

[Sequence Diagram - Create Vehicle](svg/us006-sequence-diagram-partial-create-vehicle.svg)
[Sequence Diagram - Get VFM](svg/us006-sequence-diagram-partial-get-VFM.svg)

## 3.3. Class Diagram (CD)

[Class Diagram](svg/us006-class-diagram.svg)