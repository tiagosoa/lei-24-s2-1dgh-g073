[# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

### (i):
* Authentication and authorization that will ensure that only HRMs and FMs can access their respective functionalities.

* Database operations that make possible the Creation, Update or Deletion of skills, collaborators, roles, vehicles.

### (ii):

* Ability to generate team proposals, vehicle maintenance schedules, and collaborator skills.

* Database that saves changes made to skills, job roles, collaborators, and vehicles, to ease the trace of information.

* Security features to ensure the protection of sensitive information like collaborator contact details.

## Usability

_The usability of the system is paramount to ensuring that the HRM and FM can effectively use the software.
This topic addresses several key aspects of usability, including error prevention, interface aesthetics and design, help and documentation, and consistency and standards.

### Error Prevention
* Implement form validations across all user inputs to prevent errors before they occur.
* Utilize confirmations for irreversible actions (for example, deleting a collaborator or skill) to prevent accidental data loss.

### Interface Aesthetics and Design
* Modern UI design that focuses on an easy off the application.
    * Using whitespace effectively
    * Choosing readable fonts and utilizing a color scheme that is appealing and are friendly to users with visual impairments.
* Design an intuitive navigation menu that allow users to quickly access different parts of the application.

### Help and Documentation
* Provide help texts and tips for  the more complex fields or functionalities to guide the users.
* Provide a  help center that details how to perform common tasks within the system or troubleshoot issues.

### Consistency and Standards
* UI consistency so that it eases the new users experience on the application.
* Offer always a good quality standard that meets the users expectations.

## Reliability
(fill this)

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._



## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

(fill in here )

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

(fill in here )

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

* The system will be implemented in Java.
* The system saves the registered skills, collaborators, and jobs in a database.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

* The system shall integrate with third-party APIs for functionalities such as irrigation systems and geolocation services.

### Physical Constraints
_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

* The system hardware shall meet minimum requirements such as CPU speed, RAM capacity, and disk space as specified in the system documentation.