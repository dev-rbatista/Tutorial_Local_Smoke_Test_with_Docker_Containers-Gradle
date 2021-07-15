# US002 US002 Get the standard categories tree
==============================================

# 1. Requirements

*As a system manager, I want to get the standard categories tree.*

**Demo1** As a system manager, I want to ...

- Demo1.1. show standard categories tree from new application

- Demo1.2. show standard categories tree from categories list that has new added categories

We interpreted this requirement as the function of the system manager to get the standard categories. The standard
categories tree must show the "built-in" categories of the application.

# 2. Analysis

In order to fulfill this requirement, we need two data pieces:

- categoryName

For this sprint, the system manager only gets the standard categories from the categories list.

# 3. Design

The main process to fulfill this requirement is to request(infer) in the UI for the standard categories.
This is achieved through the UI, asking the controller to the application for the standard categories.
The application then returns a list object containing the categories identified as standard

### Sequence diagram
````puml
@startuml
autonumber
title GetStandardCategoryTree
actor "System Manager" as actor
participant ": UI" as UI
participant ": getStandardCategoryTreeController" as controller
participant ": FFMApplication" as application
participant ": categoryService" as catservice
participant "CategoryTreeDTO" as tree
participant "aTree: CategoryTree" as atree

activate actor
actor -> UI: Get Standard Category Tree(family ID, familyMemberID)
activate UI
UI -> controller: getStandardCategoryTree()
activate controller
activate application
controller -> application: getCategoryService
application -> controller: return CategoryService
deactivate application
controller -> catservice: getCategoryTree( familyID, familyService)
activate catservice
catservice -> tree: newTree(this.CategoryServise)
activate tree
tree -> catservice: getStandardCategories
catservice->tree: return StandarCategories
tree->atree: build tree (standardCategories, customCategories)
activate atree
atree->tree: Ok
deactivate atree
tree->catservice:Ok
deactivate tree
catservice->controller:Ok
deactivate catservice
controller->UI: Ok
deactivate controller
UI->actor: Success
deactivate UI
deactivate actor

@enduml
````

## 3.1. Functionality Use

The getStandardCategoryTreeController will invoke the Application object, which stores
the CategoryService object. The Application will return that
service, and the getStandardCategoryTree method will be called from the CategoryService. 
The CategoryTree DTO will be instantiated, and will fetch the StandardCategory
objects stored in the CategoryService. It will store and present that List back to the UI.

## 3.2. Class Diagram

The main Classes involved are:

- getCategoryTreeController
- Application
- CategoryService
- CategoryTreeDTO

```puml
title Class Diagram

class Controller {
+getStandardCatergoriesTree()
}

class Application {
  - CategoryService categoryService
  + getCategoryService()
 }

class CategoryService {
- StandardCategory List
  + getStandardCategories()
}

class CategoryTreeDTO {
- StandardCategory List
}


Controller --> Application
Controller --> CategoryService : use
Application --> CategoryService
CategoryService --> CategoryTreeDTO : use

```

## 3.3. Applied Patterns

We applied some GRASP principles as Controller, Creator and Information Expert.
We also applied the SOLID SRP (Single Responsabity Principle) in each of the classes.

## 3.4. Tests



# 4. Implementation

From a request the Category Service class creates a new List<StandardCategoryDTO> object that holds each StandardCategoryDTO object that has its own List<StandardCategory> of childs object.
Then after is created a Tree representation that is a List object of the above.

# 5. Integration/Demonstration

The design and development of this user story is important for the application transactions because client requirements want to have the possibility to analyse
monthly and annual spending per category and/or class of categories.
[US110](US110_GetCategoryTree.md) use an implementation of this user story.

# 6. Observations

In future User Stories we need to change something in order to group transactions per category and/or class(group) of categories.
Also the client requirements state "class of categories" as a group or a specific class and that may be only clarified by the product owner in future sprint reviews.





