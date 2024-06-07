# USER MANUAL

## 1. Introduction

Welcome to the User Manual for Futuros Desempregados' Green Space Management Application! This manual is designed to provide guidance on using our application effectively, serving as your go-to resource for understanding the functionality and features of our application.

The objective of this manual is to assist users in understanding how to navigate and utilize the features of the application by providing detailed instructions and explanations.

This app is a software solution designed to streamline the management of green spaces, including personnel, vehicles, and facilities. It is a Java-based application accessible through desktop devices only.

This manual is addressed to five primary user groups:

>Human Resources Managers (HRMs): responsible for managing personnel, assigning tasks, and overseeing the skills and qualifications of collaborators within green spaces.

>Vehicle and Equipment Fleet Managers (VFMs): responsible for managing vehicles, equipment, and other facilities necessary for the maintenance and operation of green spaces.

>Collaborators: people who are employees in the organization and carry out design, construction and/or maintenance tasks for green areas, depending on their skills.

>Green Spaces Managers (GSMs): responsible for managing the green spaces in charge of the organization.

>Software Quality Assessment Team Manager (QAM) - responsible for managing the Software Quality Assessment Team and its process.

Whether you are an experienced user or new to the application, this manual will provide step-by-step instructions and best practices to help you make the most out of our application.


## 2. System Overview

The primary objective of this app is to facilitate the management of resources within green spaces, ensuring smooth operations and optimal utilization of human and material resources. Key goals include:

> - Centralizing Information: Provide a centralized platform for storing and accessing information related to collaborators, teams, their skills, assigned jobs, and vehicles. GSMs also get access to Agendas and To-Do Lists, so as to manage tasks and assure the proper functioning of the parks.

> - Streamlining Processes: Automate processes such as team proposal generation, scheduling vehicle maintenance/check-ups to enhance efficiency and reduce manual effort.

### Structure of the Application

The application is organized into five main modules: _Human Resources Management (for HRMs)_, _Vehicle and Fleet Management (for VFMs)_, _Green Space Management_ (for GSMs), _Quality Assessment Management_ (for QAMs), and _Collaborator Utilities_ (for Collaborators). Each module caters to specific functionalities and dependencies:

> HRM Module: This module focuses on managing human resources involved in green space projects. It includes features such as registering skills and jobs, assigning skills to collaborators, and generating team proposals.

> VFM Module: The VFM module is dedicated to managing facilities and equipment associated with green spaces. It allows users to register vehicles, schedule and record vehicle check-ups, and maintain a list of vehicles needing attention.
> 
> GSM Module: The Green Space Management module is designed for managing green spaces. It includes functionalities for registering green spaces, managing To-Do lists and Agendas, assigning teams and vehicles to tasks, and handling emergency plans with evacuation routes and assembly points.

> QAM Module: The Quality Assessment Management module is focused on evaluating and ensuring the quality of software used in green space management. It includes features for running tests, analyzing algorithm performance, and assessing the complexity and efficiency of developed procedures.

> Collaborator Utilities: This module provides tools for collaborators to manage their tasks and assignments. It includes functionalities for consulting assigned tasks and recording task completion.



### Main Features

> Registering and managing collaborator information, including personal details, skills, and assigned jobs.

> Generating team proposals automatically based on specified criteria, saving them if they are accepted.

> Assigning skills to collaborators to ensure appropriate staffing for projects.

> Registering vehicles and maintaining details such as brand, model, and maintenance frequency.

> Scheduling and recording vehicle check-ups to ensure proper maintenance and safety compliance.


Application Structure Diagram

[Domain Model](02.analysis/svg/project-domain-model.svg)

## 3. System Requirements

### Operating System:

The app is compatible with any OS that is capable of running the Java language. Internet connection is required for initial setup and updates.

It does not require any additional plugins for basic functionality. However, depending on your organization's needs, integration with other software systems may be necessary. Please consult with your IT department for specific plugin requirements.

### Installation Procedures:

>- Download our application’s repository from Github;
>
>- Run the .jar file within the downloaded repository, by using a terminal window;
>
>- Read the [[Readme.md]](Readme.md) for more information.



### Technical Specifications:

> Database: The app utilizes a relational database management system (RDBMS) to store and manage user data.

> Programming Languages and Frameworks: The app is developed using Java, Python and Maven.

> Security: The app implements industry-standard security practices to safeguard user data.

> Scalability: The architecture of the app's GUI is simple and designed to be scalable, allowing it to accommodate growing user bases and expanding functionality with ease.

> Support: For technical support and assistance, please contact our support team at g073@isep.ipp.pt.

## 4. Features


### Skill Registration:

US Number: 1

Title: Create a Skill

User/Role: HRM

>Instructions:
>
>- Login as an HRM
>
>- Click on "Create Skill".
>
>- Enter the skill name.
>
>- Click on "Submit" to save the skill.



### Job Registration:

US Number: 2

Title: Create a Job

User/Role: HRM

>Instructions:
>
>- Login as an HRM
>
>- Click on "Create Job".
>
>- Enter the job name.
>
>- Click on the "Submit" button to save the job.


### Collaborator Registration:

US Number: 3

Title: Register a Collaborator

User/Role: HRM

>Instructions:
>
>- Login as an HRM
>
>- Ensure you have at least one job registered within the system.
>
>- Click on "Register Collaborator".
>
> - Enter the required data (name, birthdate, admission date, address, contact info (mobile and email), ID doc type and a respective number).
>
> - Click on the "Submit" button to save the collaborator.

### Skill Assignment:

US Number: 4

Title: Assign a Skill

User/Role: HRM

>Instructions:
>
>- Login as an HRM
>
>- Ensure you have at least one Collaborator and one Skill registered within the system.
>
>- Click on "Assign Skill to Collaborator".
>
>- Select one or more skills from the list.
>
>- Select a Collaborator from the list.
>
>- Click on the "Save" button to assign the skill(s).



### Generating Team Proposal:

US Number: 5

Title: Generating a Team

User/Role: HRM

>Instructions:
>
>- Login as an HRM
>
>- Ensure you have at least one collaborator registered within the system, with a skill assigned to them.
>
>- Click on "Generate Team".
>
>- Specify the maximum and minimum team size and required skills.
>
>- Click on the "Submit" button to automatically generate a team proposal.
>
>- Click "Yes" if you accept the team proposal and wish to save it, otherwise click "No".





### Vehicle Registration:

US Number: 6

Title: Register a Vehicle

User/Role: VFM

>Instructions:
>
>- Login as a VFM.
>
>- Click on "Register Vehicle".
>
>- Enter the vehicle details including brand, model, type, etc.
>
>- Click on the "Submit" button to add the vehicle to the system.



### Vehicle Check-up Registration:

US Number: 7

Title: Register a Vehicle’s Maintenance

User/Role: VFM

>Instructions:
>
>- Login as a VFM.
>
>- Ensure you have at least one vehicle registered within the system.
>
>- Click on "Register Maintenance"
>
>- Select the vehicle from the list needing maintenance.
>
>- Enter the check-up details such as date, maintenance performed, etc.
>
>- Click on the "Save" button to record the maintenance date.



### Vehicle Check-up List:

US Number: 8

Title: List Vehicles that need Check-up

User/Role: VFM

>Instructions:
>
>- Login as a VFM.
>
>- Ensure you have at least one vehicle registered with a maintenance date.
>
>- Click on "List Maintenances"
>
>- The system lists the vehicles that need maintenance.

## 5. Troubleshooting

### Issue 1. Application Doesn't Open

Possible Cause: Corrupted installation, system error, or conflicting software.

Solution: Reboot the system and try opening the application again. If the issue persists, reinstall the application.

### Issue 2. Error Message During Registration

Possible Cause: Incorrect input data

Solution: Double-check the input data and ensure the requested data is inputted correctly.

### Issue 3. Unable to Assign Skills to Collaborator

Possible Cause: Incorrect permissions, system error, or database issue.

Solution: Check if you have the necessary permissions to use the functions you have problems with. If the issue persists, try rebooting the .jar or logging out and logging back in.

### Issue 4. Information Not Saving

Possible Cause: Database error, input validation failure.

Solution: Ensure all required fields are filled out correctly and try saving the information again.

### Issue 5: Team Proposal Generation Fails

Possible Cause: Insufficient data.

Solution: Double-check the input data including maximum team size and required skills. If the issue persists, assure you have skills registered successfully.

### Issue 6: Maintenance Reminder Not Received

Possible Cause: Notification settings, server error, or database issue.

Solution: Check the notification settings to ensure reminders are enabled. If the issue persists, try rebooting the .jar the page or logging out and logging back in.

### Issue 7: Data Loss

Possible Cause: Server error, cache issue, or accidental deletion.

Solution: Reboot the .jar and check if the data reappears. If not, try logging out and logging back in.


If any of these problems persist, contact the Helpdesk or Support Service (g073@isep.ipp.pt) for assistance.



## 6. FAQs

>Q1: Can I access the application from my mobile device?
>
>A1: Currently, the application is only accessible through desktop or laptop computers.

>Q2: Can I export data from the application for reporting purposes?
>
>A2: Yes, you can export data in various formats such as CSV.

>Q3: Is there a support team available in case I encounter any issues?
>
>A3: Yes, you can contact either the Helpdesk or our team at g073@isep.ipp.pt to answer any questions or technical issues you may encounter.

>Q4: Is there any further documentation I can consult?
> 
>A4: There is the [Glossary](glossary.md), the [Supplementary Specifications](supplementary-specification.md), and the [Use Case Diagram](use-case-diagram.md) files, all of which are located within the project repository. 