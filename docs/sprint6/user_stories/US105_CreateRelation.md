# US105 Create a Relation between two Family Members
=======================================

# 1. Requirements

## 1.1 Client Notes
*As a family administrator, I want to add family members*

We interpreted this requirement as the function of a family administrator adding a new Person to their family.

This Person's email account must not exist in the Application since it is used as a unique ID.

- A Person needs to have:
    - ID (email)
    - Name
    - Vat number
    - Address
    - Birthdate
    - Phone (none or one)
    - Family ID


## 1.2 Dependencies

### 1.2.1 Pre-conditions

### 1.2.2 Other User Stories

## 1.3 Acceptance Criteria

### 1.3.1 Success Cases

### 1.3.2 Failure Cases

## 1.4 SSD

```plantuml
@startuml

header SSD
title Add a Family Member
autonumber
actor "Family Administrator" as Actor
participant "System" as System
activate Actor
Actor -> System : Add a family member
activate System
System --> Actor : Request Person Data (Name, Birthdate, \nemail (ID), Vat Number, Phone Number, Address and CC number)  
Actor -> System : Input Person Data (Name, Birthdate, \nemail (ID), Vat Number, Phone Number, Address and CC number)
alt failure
System --> Actor : Inform Failure
else success 
System --> Actor : Inform Success
end
deactivate System
deactivate Actor
@enduml
```

# 2. Analysis

## 2.1 Summary

Each Relation will have 3 mandatory attributes. Two PersonID's and the Relation Designation.
The Relation ID will be created in the Persistence.

Both persons have to be from the same family.
This will be validated either in the FrontEnd and Backend.
Frontend will have a drop-down with all the Family Members inside the Family resource.

BackEnd will have it's own validation in order to protect itself from Frontend changes that could lead to errors.

Service will have to be linked to Person Repository in order to obtain both persons and verify if they belong to the same family, before creating the Relation.

# Decide how to implement the Config File in order to have predefined Relations.

## Service Validations:

1. Extract PersonID's from both persons to validate if they are not the same person.
2. Obtain Persons (Implied Verification of persons' existence)
3. Verify if they have the same FamilyID (Service has to obtain both persons from Repo, extract ID and compare them).




---
---
---

[COMMENT]: # (The first one seems better practice than the second one. Validate both)


The following Domain Model is only referring to this user story. The complete model can be found in the diagrams folder.


Each Person will have two types of attributes. The attributes *name*, *CCNumber*, *birthDate*, *address* and *vatNumber*
will have a **single value** but *EmailAddress* and *PhoneNumber* will behave differently. Both *EmailAddress* and *
PhoneNumber* are attributes that a Person can have more than one. A *Person* **must have at least one email**, but it's
possible that has **none or multiple** *PhoneNumbers*.

The **Person** must have the following characteristics with the following rules:

| **_Value Objects_**         | **_Business Rules_**                                                                   |
| :-------------------------- | :------------------------------------------------------------------------------------- |
| **CCNumber**                | Required, unique, CCNumber must have 8 numeric digits and 4 alphanumeric.              |
| **Name**                    | Required, string                                                                       |
| **BirthDate**               | Required, date(year-month-day)                                                         |
| **Address**                 | Required, string                                                                       |
| **VatNumber**               | Required, unique, Vat must have 9 numeric digits                                       |
| **EmailAddress**            | Required, unique, Email must follow a pattern                                          |
| **PhoneNumber**             | Non-Required, PhoneNumber must have 9 digits                                           |


## 2.2. Domain Model Excerpt

```plantuml
@startuml
header Domain Model
hide methods
hide circle
skinparam linetype ortho

class Family {
 - Name
 - Registration Date
}

class Person {
 - Name
 - Vat number
 - Birthdate
 - Address
 }

class EmailAddress {
 - Email
}

class PhoneNumber {
 - Phone Number
}

class FamilyID {
 - FamilyID
}

Family "1" -> "0..*" Person: has non-administrator members
Family "1" -> "1" Person: has admin 
Person "1" --> "1..*" EmailAddress: has
Family "1" --> "1" FamilyID: has
Person "1 " --> "0..*" PhoneNumber: has

@enduml
```

# 3. Design

## 3.1. Design decisions

The process to fulfill this requirement requires the actor to select they want to add a new person to their family, which would
prompt the input of the person's data.

The main user's FamilyID will be automatically retrieved by checking who is logged into the application. It will also verify if the main user is the admin of their own family.

Given the current absence of an UI layer the required data will be passed directly into the AddPersonController.

We chose to verify the uniqueness of the Email Address after instancing the email. This way we could minimize the possibility of duplicate emails being added since the verification would occur at the moment of addition to the family repository.


## 3.2. Class Diagram

```puml
@startuml

title US010 Create a Family and set the Family administrator

skinparam linetype polyline
hide empty members

class CreateFamilyController {
  + createFamilyAndAdmin()
}

class Application {
  + getPersonRepository()
+ getFamilyRepository()
}

class CreateFamilyService {
+ createFamilyAndAddAdmin()
}

class CreateFamilyDTO {
}

class AddPersonDTO {
}

class FamilyID <<ValueObject>> <<ID>> {
}

class Email <<ValueObject>> <<ID>> {
}

class FamilyRepository <<Repository>> {
+ generateAndGetFamilyID()
+ createAndAddFamily()
+ removeFamily()
}

class PersonRepository <<Repository>> {
+ createAndAddPerson()

}

class Person <<Entity>> <<Root>> {
}

class Address <<ValueObject>> {
}

class BirthDate <<ValueObject>> {
}

class PhoneNumber <<ValueObject>> {
}

class Name <<ValueObject>> {
}

class VATNumber <<ValueObject>> {
}

class Family <<Entity>> <<Root>> {
}

class RegistrationDate <<ValueObject>> {
}


CreateFamilyController -d-> Application : application
CreateFamilyController .r> CreateFamilyDTO
CreateFamilyController .r> AddPersonDTO
CreateFamilyController -d---.> CreateFamilyService

CreateFamilyService -u-> Application : application
CreateFamilyService .u> CreateFamilyDTO
CreateFamilyService .u> AddPersonDTO
CreateFamilyService --.l--> FamilyRepository
CreateFamilyService .r> PersonRepository
CreateFamilyService -d-.> FamilyID
CreateFamilyService -down-.> Email
CreateFamilyService -down-.> Address
CreateFamilyService -d-.> BirthDate
CreateFamilyService -d-.> PhoneNumber
CreateFamilyService -d-.> Name
CreateFamilyService -d-.> VATNumber
CreateFamilyService -d-.> RegistrationDate

FamilyRepository *--l-- "0..*" Family
PersonRepository *--d--- "0..*" Person

Family -down-> "1" Email : admin
Family -down-> "1" RegistrationDate : registrationDate
Family -down-> "1" FamilyID : id

Person -u--> "1" FamilyID : family
Person -u--> "1" Email : id
Person -u--> "1" Address : address
Person -u--> "1" BirthDate : birthDate
Person -up--> "0..1" PhoneNumber : phoneNumber
Person -up--> "1" Name : name
Person -up--> "1" VATNumber : vatNumber
@enduml
```

## 3.3. Functionality Use

The CreateFamilyController creates a new CreateFamilyService object using a inputFamilyDTO, a inputPersonDTO and the
application.
The CreateFamilyService will create all the necessary value objects to create the family and administrator.
The CreateFamilyService will invoke the Application to retrieve the PersonRepository and FamilyRepository.
The CreateFamilyService will invoke the FamilyRepository to create a familyID and then a Family.
The CreateFamilyService will invoke the PersonRepository to create the Person object for the administrator,
providing the email from the admin is unique. If it isn't, the previously created Family will be deleted.
The CreateFamilyController will then return a true or false response depending on the sucess or insuccess
of creating the Family and administrator.


## 3.4. Sequence Diagram

````puml
@startuml
title US105 Create Relation Between Two Family Members

autonumber

participant ": FamilyRESTController" as controller <<interface>>
participant ": RelationInputDTOAssembler" as inputDto
participant ": CreateRelationService" as service <<interface>>
participant ": RelationDTODomainAssembler" as dtoDomain
participant "aRelation\n : Relation" as relation
participant ": FamilyRepository" as repository <<interface>>
participant ": RelationDataDomainAssembler" as dataDomain
participant ": FamilyRepositoryJPA" as repositoryJPA <<interface>>
participant "savedFamily\n : Family" as savedFamily


-> controller : POST /{FamilyID}/relations \ncreateRelation(CreateRelationDTO)
activate controller

ref over controller, inputDto
Assemble ExternalDTO to InputDTO
end

controller -> service : createRelation(inputRelationDTO)
activate service

ref over service, dtoDomain
Assemble InputDTO to Value Objects
end

ref over service
Validations
end
service -> relation ** : create(aPersonID, anotherPersonID, relationDesignation)

ref over service
Fazer um getByID da Family ao familyRepository, obter a família e fazer add relation.
A Family deve ter este método add relation que corre todas as relations existentes para garantir que não há uma igual.
end

service -> repository : add(aFamily)
activate repository

ref over repository, dataDomain
Assemble Family to FamilyJPA
end

repository -> repositoryJPA : save(aFamilyJPA)
activate repositoryJPA
return savedFamilyJPA

ref over repository, dataDomain
Assemble FamilyJPA to Value Objects
end

repository -> savedFamily ** : create(VOs)

return savedFamily

ref over service, dtoDomain
Get Relation from savedFamily
Assemble Relation to OutputRelationDTO
end

return outputRelationDTO

ref over controller, inputDto
Add Links to OutputDTO
end

return ResponseRelation\n(outputRelationDTO, HttpStatus)

@enduml
````

````puml
@startuml
title Validations



participant ": CreateRelationService" as service << Interface >>
participant ": PersonRepository" as repository << Interface >>
participant ": PersonDataDomainAssembler" as assembler
participant ": PersonRepositoryJPA" as JPA << Interface >>

activate service

alt if aPersonID equals anotherPersonID
<-- service : Throw IllegalArgumentException
end
service -> repository : getByID(aPersonID)
activate repository
repository -> repository : retrievePersonFromRepository(aPersonID)
activate repository
ref over repository, assembler
Assemble PersonID to PersonIDJPA
end
repository -> JPA : findByID(aPersonIDJPA)
activate JPA
return Optional<PersonJPA> optional
alt if optional.isEmpty()
<-- repository :  Throw EmailNotRegisteredException
else optional.isPresent()
repository -> repository : createPerson(optional.get())
activate repository

ref over repository, assembler
Assemble PersonJPA to Person
end

return aPerson

end

return aPerson

return aPerson

service -> repository : getByID(anotherPersonID)
activate repository
repository -> repository: retrievePersonFromRepository(anotherPersonID)
activate repository
ref over repository, assembler
Assemble PersonID to PersonIDJPA
end
repository -> JPA : findByID(anotherPersonIDJPA)
activate JPA
return Optional<PersonJPA> optional
alt if optional.isEmpty()
<-- repository :  Throw EmailNotRegisteredException
else optional.isPresent()
repository -> repository : createPerson(optional.get())
activate repository

ref over repository, assembler
Assemble PersonJPA to Person
end

return anotherPerson

end

return anotherPerson

return anotherPerson



alt if aPerson.getFamilyID() \n!= anotherPerson.getFamilyID()
<-- service: Throw IllegalArgumentException
end

@enduml
````

## 3.5. Applied Patterns

We applied the principles of Controller, Information Expert, Creator and PureFabrication from the GRASP pattern. We also
used the SOLID Single Responsibility Principle.

We applied the following principles:

- GRASP:
    - Information expert:
        - This pattern was used in classes that implemented the Account interface, like in this case CashAccount class, for returning a DTO with the account id and description without removing information outside the class;

    - Controller:
        - To deal with the responsibility of receiving input from outside the system (first layer after the UI) we use a case controller.

    - Pure Fabrication:
        - In this user story the Application and AccountService class was used, which does not represent a business domain concept. It was created to be responsible for all operations regarding Account type Classes.

    - High cohesion and Low Coupling:
        - The creation of the AccountService class provided low Coupling and high Cohesion, keeping one Class as the Information Expert.

    - Protected Variation:
        - An Account interface was used in which the polymorphism was used to be implemented in several classes, each representative of a type of Account.

- SOLID:
    - Single-responsibility principle:
        - this pattern was used in the AccountService class, in which it the only responsibility is manage account operations.



## 3.6. Tests

### 3.6.1. XXXX

#### 3.6.1.1. Success

#### 3.6.1.2. Failure


### 3.6.2. YYYY

#### 3.6.2.1. Success

#### 3.6.2.2. Failure


### 3.6.3. ZZZZ

#### 3.6.3.1. Success

#### 3.6.3.2. Failure


Several cases where analyzed in order to test the creation of a new Family

**Test 1:** Test that it is possible to create a new instance of Family with a valid Admin

**Test 2:** Test that it is not possible to create a new instance of Family if admin email is already registered

**Test 3:** Test that it is not possible to create a new instance of Family receiving a **familyName** that is null

**Test 4:** Test that it is not possible to create a new instance of Family receiving a **familyName** that is empty

**Test 5:** Test that it is not possible to create a new instance of Family receiving a **familyName** that is blank

**Additional Tests** Test that its not possible to create a new instance of Family if any attribure is empty, blank or
null The whole user story was tested for the case of success and for failure

**Test 5:** Success

```` 
@DisplayName("Test if a family can be successfully created")  
@Test
 void shouldBeTrueCreateFamily() {
        Application application = new Application();
        Create Family Controller controller = new Create Family Controller(application);
        CreateFamilyDTO inputFamilyDTO = new CreateFamilyDTO("tonyze@hotmail.com", "Silva", "Tony", "12/12/1990", 999999999, 919999999, "Rua das Flores", "Porto", 69, "4400-000", "139861572ZW2");
        
        assertTrue(controller.createFamilyAndAdmin(inputFamilyDTO));    
    }
````

**Test 6:** Failure

````
@DisplayName ("Test if a family isnt created if the admin email is already registered in the app")  
@Test
    void shouldBeFalseCreateFamilyEmailAlreadyregistered() {
        Application application = new Application();
        Create Family Controller controller = new Create Family Controller(application);
        CreateFamilyDTO createFamilyDTO1 = new CreateFamilyDTO("tonyze@hotmail.com", "Silva", "Tony", "12/12/1990", 999999999, 919999999, "Rua das Flores", "Porto", 69, "4400-000", "139861572ZW2");
        CreateFamilyDTO inputFamilyDTO2 = new CreateFamilyDTO("tonyze@hotmail.com", "Pereira", "Rita", "12/12/1990", 999999999, 919999999, "Rua das Flores", "Porto", 69, "4400-000", "139861572ZW2");
        controller.createFamilyAndAdmin(createFamilyDTO1);
        assertFalse(controller.createFamilyAndAdmin(inputFamilyDTO2));    
    }
    }
````

# 4. Implementation

[comment]: # (NOTE: Only critical methods for the US implementation)

1. All the Value Objects are initially instanced (instantiated), with respective validations.


      public boolean createFamilyAndAddAdmin() {
      boolean result;
      EmailAddress adminEmail = new EmailAddress(inputPersonDTO.unpackEmail());
      FamilyName familyName = new FamilyName(inputFamilyDTO.unpackFamilyName());
      Name name = new Name(inputPersonDTO.unpackName());
      BirthDate birthdate = new BirthDate(inputPersonDTO.unpackBirthDate());
      VATNumber vat = new VATNumber(inputPersonDTO.unpackVAT());
      PhoneNumber phone = new PhoneNumber(inputPersonDTO.unpackPhone());
      Address address = new Address(inputPersonDTO.unpackStreet(), inputPersonDTO.unpackCity(), inputPersonDTO.unpackZipCode(), inputPersonDTO.unpackHouseNumber());
      CCnumber cc = new CCnumber(inputPersonDTO.unpackCCNumber());
      RegistrationDate registrationDate = new RegistrationDate(inputFamilyDTO.unpackLocalDate());

2. Family ID is automatically generated by the Family Repository (Information Expert)


      public FamilyID generateAndGetFamilyID() {
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        if (checkIfFamilyIDExists(familyID)) {
            familyID = generateAndGetFamilyID();
        }
        return familyID;
      }

3. AdminEmail is added to the Family upon its instantiation. The Family is immediately added to the FamilyRepository (
   The administrator email validation will come later.)


      public void createAndAddFamily(FamilyName familyName, FamilyID familyID, RegistrationDate registrationDate, EmailAddress adminEmail) {
        Family family = new Family(familyID, familyName, registrationDate, adminEmail);
        this.families.add(family);
      }

4. Before creating the Administrator, the email is validated in the Person Repository in order to guarantee that it is
   Unique


      private boolean isEmailAlreadyRegistered(EmailAddress email) {
        boolean emailIsRegistered = false;
        for (Person person : people) {
            if (person.isSameEmail(email)) {
                emailIsRegistered = true;
            }
        }
        return emailIsRegistered;
      }

5. If the Email fails verification, the Family is removed from the FamilyRepository and the process fails.


      try {
        personRepository.createAndAddPerson(name, birthdate, adminEmail, vat, phone, address, cc, familyID);
        result = true;
      } catch (EmailAlreadyRegisteredException e) {
        familyRepository.removeFamily(familyID);
        result = false;
      }
      return result;


# 5. Integration

[comment]: # (All other US's/features that this one will be added on !!!!!!)

The development of this user story was the basis for the family structure where the FamilyMembers are stored and was
thus crucial for the development of the other User Stories

# 6. Observations

[comment]: # (Tudo o que nao encaixe em lado nenhum vai para aqui AKA LIXOOOOOOO !!!!!!!)

As with the Standard Category the family ID will probably need to be reworked in a future sprint to allow for more
complex ID information if needed (probably using a UUID)