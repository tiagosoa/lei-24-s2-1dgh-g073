# Supplementary Specification (FURPS+)

### Functionality

_Specifies functionalities that:  
(i) are common across several US/UC;  
(ii) are not related to US/UC, namely: Audit, Reporting and Security._

### (i):
* Authentication and authorization that will ensure that only HRMs and VFMs can access their respective functionalities.

* Database operations that make possible the Creation, Update or Deletion of skills, collaborators, roles, vehicles.
### (ii):

* Ability to generate team proposals, vehicle maintenance schedules, and collaborator skills.

* Database that saves changes made to skills, job roles, collaborators, and vehicles, to ease the trace of information.

* Security features to ensure the protection of sensitive information like collaborator contact details.

### Usability

_The usability of the system is paramount to ensuring that the HRM and VFM can effectively use the software.
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
* UI consistency so that it eases the new users experience on the application
* Offer always a good quality standard that meets the users expectations

## Reliability
* The reliability of the software system is key to ensure trust and efficiency. 
* The following topics outline the specific reliability requirements for the software:

### Frequency and Severity of Failures

* The system must be designed to minimize failures.

* Severity of failures should be categorized and prioritized based on their impact on the system's operation and user experience [Most Important Comes First].

### Possibility of Recovery

* The software must include mechanisms for automatic recovery from failures whenever possible.

* For failures that require manual intervention, the system should provide the according error message contribute to a quick diagnosis and resolution.

### Possibility of Prediction

* Implement monitoring tools that can identify potential issues before they cause system failures.

### Accuracy

* Consistent checks that allow for an accurate data integrity, to prevent any data loss.

* The system must go through extensive testing to check his accuracy.
### Average Time Between Failures (MTBF)
* Aim for a high MTBF, wich will lead to an efficient and bugless application.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

_Performance requirements of the software:_ 
* _(i) response time:_  
* _(ii) CPU usage:_
* _(iii) memory consumption:_

### (i)
_Our application's performance is optimized to achieve swift response times, 
ensuring seamless task allocation among workers and preventing any delays in 
maintenance procedures._

### (ii)
_With a minimal CPU footprint, the application can run 
efficiently on any device, catering to a diverse user base._

### (iii)
_Those managing the database will require ample memory capacity to store extensive 
information, while regular users can seamlessly utilize the application without 
significant memory demands._

## Supportability

Supportability is crucial for ensuring the software system is maintainable, adaptable, and scalable, addressing the needs of Human Resources Managers (HRM) and Vehicle and Equipment Fleet Managers (VFM) both now and in the future. This section outlines the key supportability requirements to achieve these goals:

### Testability
* The system must be designed with testability in mind and allow for automated and manual testing.
### Adaptability
* It must adapt a customization by each one of the organization necessities.
### Maintainability
* All code should be documented, follow coding standards, and be structured for an easy readability to facilitate maintenance and updates.
* Use a control system to manage changes.
### Compatibility
* Ensure compatibility with most devices, operating systems, and browsers.
### Configurability
* Allow users to configure their own settings, and options without needing a code change or technical support(For example UI visualization changes).
### Installability
* Simple installation process with clear instructions and automated installers.

## +
### Design Constraints
Guidelines that shape the development/design off the software.
### Programming Language
 * The app is mainly coded using Java

### Development Tools
* The app was developed on IntelliJ IDEA, with the help of GitHub and Bitbucket

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