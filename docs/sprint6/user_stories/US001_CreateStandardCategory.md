# US001 Create a Standard Category
=======================================


# 1. Requirements

### 1.1 Client Notes

*As a system manager, I want to create a new Standard Category*


**1** As a system manager, I want to create...

- 1.1. The new standard category "Habitação", at the root level;

- 1.2. The already existing category "Habitação";

- 1.3. The new standard category "Renda", as a sub-category of Habitação; 

We interpreted this requirement as the function of a system manager to create a new standard category that can be accessed by all users. 
The name of the category must not be empty, and it can't exist in the current list of standard categories.
A standard category can be a sub-category of an existing standard category.
The name of a standard category must be case-insensitive.

### 1.2 Dependencies

### 1.2.1 Pre-conditions



### 1.2.2 Other User Stories



## 1.3 Acceptance Criteria

### 1.3.1 Success Cases


### 1.3.2 Failure Cases



## 1.4 SSD

```` puml

    autonumber
    title createStandardCategory SSD
    actor "System Manager" as systemManager
    participant "System" as system

    activate systemManager
    systemManager -> system: create a Standard Category
    activate system
    systemManager -> system: input required data
    system --> systemManager: inform success
    deactivate system

@endpuml
````

# 2. Analysis

##2.1 Summary

## 2.2. Domain Model Excerpt


# 3. Design

## 3.1. Functionality Use
The AddStandardCategoryController will invoke the Application object, which stores a CategoryService object.
The Application will return the CategoryService, which contains a list of all StandardCategories.
The CategoryService then creates a new StandardCategory Object and adds it to the existing list.


## 3.2 Class Diagram

![class diagram]()

## 3.3. Applied Patterns

# 3.4 Sequence Diagram

```` puml

   autonumber
   title createStandardCategory

   participant ": ICategoryRESTController" as controller
   participant ": ICreateStandardCategoryService" as service
   participant "aStandardCategory: \n StandardCategory" as standardCat
   
   participant "aStandardCategory: \n StandardCategory" as standardCat
   participant ": ICategoryRepository" as repo
   participant ": ICategoryRepositoryJPA" as repoJPA
  
   -> controller : createCategoryDTO
   activate controller
   
   ref over controller
        InternalExternalAssembler.toInternal(createCategoryDTO)
   end
   
   controller -> service : createStandardCategory( \n inputStandardCategoryDTO)
   activate service
   
   ref over service 
        create Value Objects
   end
   
   service -> standardCat** : create(categoryName, parentID)
   service -> repo: add(aStandardCategory)
   activate repo
   
   ref over repo
        CategoryAssembler.toData(aStandardCategory)
   end
   
   repo -> repoJPA: save(aStandardCategoryJPA)
   activate repoJPA
   repoJPA -> repo: savedStandardCategoryJPA
   deactivate repoJPA
   
   ref over repo 
        CategoryAssembler.toDomain(savedStandardCategoryJPA)
   end
   
   repo -> service : savedStandardCategory
   deactivate repo
   
   ref over service
        InternalExternalAssembler.toOutputDTO(savedStandardCategory)
   end
   
   service -> controller : aOutputStandardCategoryDTO 
   deactivate service
   
   ref over controller
        addSelfLink to aOutputStandardCategoryDTO
   end
   <- controller : ResponseEntity( \n aOutputStandardCategoryDTO, HttpStatus.Created)
  
   deactivate controller

@endpuml
````

```` puml

    autonumber
    title CategoryInternalExternalAssembler.toInputDTO()
    
    participant ": ICategoryInternalExternalAssembler" as assembler
    participant "aInputStandardCategoryDTO: \n InputStandardCategoryDTO" as inputDTO
    
    -> assembler: createCategoryDTO
    activate assembler
    assembler -> inputDTO** : create() 
    activate inputDTO
    assembler -> inputDTO: setCategoryName()
    assembler -> inputDTO: setParentID()
    deactivate inputDTO
    <- assembler : aInputStandardCategoryDTO
    deactivate assembler

@endpuml
````


```` puml

    autonumber
    title create ValueObjects 
    
    participant ": ICategoryDTODomainAssembler" as assembler
    participant "aCategoryName : \n CategoryName" as name
    
    
    -> assembler: inputStandardCategoryDTO
    activate assembler
    assembler -> name** : create( inputStandardCategoryDTO.unpackCategoryName)
   
    <- assembler : aCategoryName
    deactivate assembler

@endpuml
````


```` puml

   autonumber
   title CategoryDataDomainAssembler.toData()

   participant ": ICategoryDataDomainAssembler" as DataDomainAssembler
   participant "aCategoryNameJPA : CategoryNameJPA" as catNameJPA
   participant "aParentIDJPA: CategoryIDJPA" as parentIDJPA
   participant "aStandardCategoryJPA: \n StandardCategoryJPA" as standardCatJPA
   
   -> DataDomainAssembler : aStandardCategory
   activate DataDomainAssembler
   DataDomainAssembler -> catNameJPA**: create(StandardCategory.getName())
   DataDomainAssembler -> parentIDJPA**: create(StandardCategory.getParentID())
   DataDomainAssembler -> standardCatJPA**: create()
   activate standardCatJPA
   DataDomainAssembler -> standardCatJPA: setName(aCategoryNameJPA)
   DataDomainAssembler -> standardCatJPA: setParentID(aParentIDJPA)
   deactivate standardCatJPA
   <- DataDomainAssembler: aStandardCategoryJPA
   deactivate DataDomainAssembler

@endpuml
````


```` puml

   autonumber
   title CategoryDataDomainAssembler.toDomain()

   participant ": ICategoryDataDomainAssembler" as DataDomainAssembler
   participant "aCategoryName : CategoryName" as catName
   participant "aParentID: CategoryID" as parentID
   participant "aCategoryID: CategoryID" as catID
   participant "aStandardCategory: \n StandardCategory" as standardCat
   
   -> DataDomainAssembler : aStandardCategoryJPA
   activate DataDomainAssembler
   DataDomainAssembler -> catName**: create(aStandardCategoryJPA.getName())
   DataDomainAssembler -> parentID**: create(aStandardCategoryJPA.getParentID())
   DataDomainAssembler -> catID**: create(aStandardCategoryJPA.getCatgegoryID())
   DataDomainAssembler -> standardCat**: create()
   activate standardCat
   DataDomainAssembler -> standardCat: setName(aCategoryName)
   DataDomainAssembler -> standardCat: setParentID(aParentID)
   DataDomainAssembler -> standardCat: setCategoryID(aCategoryID)
   deactivate standardCat
   <- DataDomainAssembler: aStandardCategory
   deactivate DataDomainAssembler

@endpuml
````

```` puml
    
   autonumber
   title CategoryInternalExternalAssembler.toOutputDTO()
   
   participant ": ICategoryInternalExternalAssembler" as assembler
   participant "aOutputStandardCategoryDTO :\n OutputStandardCategoryDTO" as outputDTO
   
   -> assembler : aStandardCategory
   activate assembler
   assembler -> outputDTO**: create()
   assembler -> outputDTO: setName(aStandardCategory.getName())
   activate outputDTO
   assembler -> outputDTO: setParentID(aStandardCategory.getParentID())
   assembler -> outputDTO: setCategoryID(aStandardCategory.getCategoryID())
   deactivate outputDTO
   <- assembler: aOutputStandardCategoryDTO
   deactivate assembler
    

@endpuml
````



## 3.5. Applied Patterns

We applied the principles of Controller, Information Expert, Creator and
PureFabrication from the GRASP pattern. We also used the SOLID Single
Responsibility Principle.

We applied the following principles:

- GRASP:
    - Information expert:
        - This pattern was used in classes such as the Person Repository, in
          order to apply the "Tell Don't Ask" Principle.

    - Controller:
        - To deal with the responsibility of receiving input from outside the
          system (first layer after the UI) we use a case controller.

    - Pure Fabrication:
        - In this user story the AddFamilyMemberService class
          was used, which does not represent a business domain concept. It was
          created to be responsible for all operations regarding the creation of
          a Family Member.

    - High cohesion and Low Coupling:
        - The creation of the Repository Interface will provide low Coupling and
          high Cohesion.

    - Protected Variation:
        - An Interface will be used in which Polymorphism is going to be applied
          in order to protect the existing classes from future variations.

- SOLID:
    - Single-responsibility principle:
        - This pattern was used in the AddFamilyMemberService, in which the only
          responsibility is to add a Family Member.

## 3.6. Tests

### 3.6.1. Creation of a Person/Addition to a Family

#### 3.6.1.1. Success

# 4. Implementation


# 5. Integration
 
The development of this user story was the basis for the financial categories of the application and was thus crucial for the development of other User Stories
Both [US002](US002_GetStandardCategoriesTree.md) and [US110](US110_GetCategoryTree.md) used the implementation of this US

#6. Observations

The way to generate an ID for a Standard Category will probably need to be reworked in a future sprint to allow for more complex ID information if needed