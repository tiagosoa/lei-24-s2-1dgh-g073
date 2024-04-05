# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development".


### _Conceptual Class Category List_

**Business Transactions**

* 

---

**Transaction Line Items**

* 

---

**Product/Service related to a Transaction or Transaction Line Item**

* 

---

**Transaction Records**

* 

---  

**Roles of People or Organizations**

* Human Resources Manager (HRM)
* Fleet Manager (FM)
* Collaborator

---

**Places**

* Organization

---

**Noteworthy Events**

* Maintencance/Check-up Date

---

**Physical Objects**

* Vehicle

---

**Descriptions of Things**

* 

---

**Catalogs**

* Skill Catalog

---

**Containers**

* Collaborator

---

**Elements of Containers**

* Job
* Skill

---

**Organizations**

* Organization

---

**Other External/Collaborating Systems**

* Supplier System

---

**Records of finance, work, contracts, legal matters**

* Employment Contract
* Work Order

---

**Financial Instruments**

* Budget

---

**Documents mentioned/used to perform some work/**

* Team Proposal
* List of vehicles that need check-up

---


## Rationale to identify associations between conceptual classes

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_A_** is physically or logically part of **_B_**
- **_A_** is physically or logically contained in/on **_B_**
- **_A_** is a description for **_B_**
- **_A_** known/logged/recorded/reported/captured in **_B_**
- **Human Resources Manager** manages **Collaborator**
- **_A_** is related with a transaction (item) of **_B_**
- etc.


| Concept (A) 		 |          Association   	           |                         Concept (B) |
|----------------|:----------------------------------:|------------------------------------:|
| Organization   |              has		 	               |                                 HRM |
| Organization   |              has		 	               |                                  FM |
| Organization   |              has		 	               |                        Collaborator |
| HRM            |      registers and manages	 	      |                        Collaborator |
| HRM            |             creates 	              |                               Skill |
| HRM            |             creates 	              |                                 Job |
| HRM            |         accepts/refuses 	          |                                Team |
| HRM            |  provides team size and skills to  |                              System |
| Collaborator   |          assigned with 	           |                               Skill |
| Collaborator   |          assigned with 	           |                                 Job |
| FM             |       registers and manages        |                             Vehicle |
| FM             |        requests creation of        | List of vehicles that need check-up |
| Vehicle        |                has                 |                       Check-up date |
| Vehicle        | is physically or logically part of | List of vehicles that need check-up |
| System         |             generates              |                                Team |
| System         |             generates              | List of vehicles that need check-up |







## Domain Model

![Domain Model](svg/project-domain-model.svg)