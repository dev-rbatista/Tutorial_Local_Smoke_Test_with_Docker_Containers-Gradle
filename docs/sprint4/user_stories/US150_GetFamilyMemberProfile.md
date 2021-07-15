# US150 Get Profile Information

# 1. Requirements

## 1.1. Client Notes

**As a family member, I want to get my profile’s information.**

We interpreted this requirement as the function of a user to receive their personal profile information.

- A MemberProfile needs to have the following information:
   - Name;
   - Email (ID)
   - Birth Date;
   - Email (other emails);
   - VAT Number;
   - Phone Number(none or more);
   - Address;


## 1.2. Dependencies

### 1.2.1. Pre-conditions

To be able to **get family member's profile information**, some user stories need to be implemented in first place. 
Before everything else, the *Application* needs to have a family with family members. So, **the system manager needs to create a family and assign a family administrator** because the administrator will have the responsibility of adding family members to his family.
After the **family administrator adds the family members** it will be possible to execute this US by each member. 

### 1.2.2. Other User Stories

The following users stories need to be executed before US150:

- US010 As system manager, I want to create a family and set the family administrator

- US101 As a family administrator, I want to add family members;

## 1.3. Acceptance Criteria

### 1.3.1. Success Cases

This user story is always executed correctly since it doesn't need any data entry neither validation.
It just gets family member stored information and shows it in the UI.

### 1.3.2. Failure Cases

In this US there aren't any direct failure scenarios because it just retrives already validated information that belongs to a family member.
The possible failure cases are related with wrong execution of the dependent user stories from the section **1.2.2.**.

## 1.4. System Sequence Diagram

*As a Family Member, I want to get my profile´s information*

```plantuml
@startuml

header SSD
title Get my profile information
autonumber
actor "FamilyMember" as Actor
participant "System" as System
activate Actor
Actor -> System : Get my profile information
activate System
System --> Actor : Return Family Member Data (Name, Email (ID), Birthdate, \nOther Emails, Vat Number, Phone Numbers and Address)  
deactivate System
deactivate Actor

@enduml
```


# 2. Analysis

## 2.1 Summary

The profile information we need to obtain in this user story contains the personal information of the user.
This includes _Name_, _BirthDate_, _Address_, _VATNumber_ and _PrimaryEmail_, as well as a list of any other _Emails_ and a list of any _PhoneNumbers_ registered in the application.


## 2.2. Domain Model Excerpt

```plantuml
@startuml
header Domain Model US150
hide methods
hide circle
skinparam linetype ortho

class Person{
- name
- birthDate
- vatNumber
- address
}

class Email{
}

class PhoneNumber{
}

Person "1" -> "0..*" PhoneNumber: has 
Person "1" --> "1..*" Email : has 
@enduml
```

# 3. Design

## 3.1. Design decisions

To fulfill this requirement it is required for the actor to select they want to get their profile information.

The user's primary email (it's identification) will be automatically retrieved by checking who is logged into the application.

To allow the output of all the data required we opted to create a ProfileDTO gathering all the relevant profile information.

[COMMENT]: # (A criação do DTO de saida será feita na classe person. O DTO é criado com um builder que lhe passa os dados.
Esses dados são passados a primitivos directamente ou convertidos quando desempacotados?)

## 3.2. Class Diagram

```puml
@startuml
header Class Diagram
title US150 Get Family Member Profile Information

skinparam linetype polyline
skinparam class {
BackgroundColor LightBlue
ArrowColor Black
BorderColor DarkBlue
}
hide empty members

class GetFamilyMemberProfileController {
  + getFamilyMemberProfile()
}

class Application {
  + getPersonRepository()
  + getLoggedPerson()
}

class GetFamilyMemberProfileService {
  + getFamilyMemberProfile()
}

class ProfileDTO {
}

class PersonRepository <<Repository>> {
  + getPersonByID()
  + getFamilyMemberProfile()
}

class Person <<Entity>> <<Root>> {
  + getFamilyMemberProfile()
}

class Address <<ValueObject>> {
}

class Email <<ValueObject>> <<ID>> {
}

class BirthDate <<ValueObject>> {
}

class PhoneNumber <<ValueObject>> {
}

class Name <<ValueObject>> {
}

class VATNumber <<ValueObject>> {
}


GetFamilyMemberProfileController -r--> Application : application
GetFamilyMemberProfileController ..> GetFamilyMemberProfileService
GetFamilyMemberProfileController ..> ProfileDTO

GetFamilyMemberProfileService --> Application : application
GetFamilyMemberProfileService ..> PersonRepository

PersonRepository *-- "1..*" Person

'Ligação de ProfileDTO para PersonRepo e GetCenasService?!

Person ..> ProfileDTO

Person --> "1" Email : id
Person --> "0..*" Email : otherEmails
Person --> "1" Address : address
Person --> "1" BirthDate : birthDate
Person --> "0..*" PhoneNumber : phoneNumber
Person --> "1" Name : name
Person --> "1" VATNumber : vatNumber

ProfileDTO -u-> Email
ProfileDTO -u-> Address
ProfileDTO -u-> BirthDate
ProfileDTO -u-> PhoneNumber
ProfileDTO -u-> Name
ProfileDTO -u-> VATNumber

@enduml
```

[comment]: # (Ligação de ProfileDTO para PersonRepo e GetCenasService?!)

## 3.3. Functionality Use

The GetFamilyMemberProfileController creates a new GetFamilyMemberProfileService using the Application.
The GetFamilyMemberProfileService will invoke the Application to retrieve the PersonRepository.
The PersonRepository will use the GetPersonByID method to find the person whose profile we want to return.
The Person will then create a ProfileDTO containing all the data required for the user story (Name, Address,
BirthDate, VATNumber, PrimaryEmail and any other Emails or PhoneNumbers)
It will then return the ProfileDTO all the way back to the  GetFamilyMemberProfileController.

## 3.4. Sequence Diagram

The process to fulfill this requirement requires the actor to select they want to create a new family, which would
prompt the input of the name for that family as well as the administrator email, and the other necessary data stated in
2.1.  
Given the current absence of an UI layer the required data will be passed directly into the CreateFamilyController.
ation would occur at the moment of addition to the repository.

````puml
@startuml

autonumber
header Sequence Diagram - part 1
title US150 Get my profile information


participant ": IGetProfile\nInfoService" as service
participant ": IPersonRepository" as repository
'participant "aPerson \n: Person" as person
participant ": PersonToDTO" as mapper

-> service : getProfileInfo(getProfileInfoDTO)
activate service

service -> service : personID = getProfileInfoDTO.unpackPersonID()
service -> repository : getById(personID)
activate repository
return aPerson

service -> mapper : createPersonDTO(aPerson)
activate mapper
ref over mapper 
Person DTO creation
end
return aPersonDTO

return aPersonDTO
@enduml
````

````puml
@startuml

autonumber 6
header Sequence Diagram - part 2
title US150 Person DTO creation

participant ":PersonToDTO" as mapper
participant "aPerson \n: Person" as person

participant "aProfileDTO \n: ProfileOutputDTO" as profiledto

-> mapper : createPersonDTO(aPerson)
activate mapper
mapper -> person : getID()
activate person
return id

mapper -> person : getName()
activate person
return name

mapper -> person : getBirthDate()
activate person
return birthDate

mapper -> person : getOtherEmail()
activate person
return emails

mapper -> person : getVat()
activate person
return vat

mapper -> person : getPhoneNumbers()
activate person
return phoneNumbers

mapper -> person : getAddress()
activate person
return address

mapper -> profiledto** : create 


activate profiledto
mapper -> profiledto : setID(id)



mapper -> profiledto : setName(name)



mapper -> profiledto : setBirthDate(birthDate)



mapper -> profiledto : setEmails(emails)



mapper -> profiledto : setVat(vat)


mapper -> profiledto : setPhoneNumbers(phoneNumbers)



mapper -> profiledto : setAddress(address)

deactivate profiledto
<-- mapper : aProfielDTO



@enduml
````

## 3.5. Applied Patterns

We applied the following principles:

- GRASP:
   - Information expert:
      - This pattern is used in Person class because it stores all the Person attributes associated with it and creates its own DTO. PersonRepository just stores all the Person´s objects.
      
   - Controller:
      - To deal with the responsibility of receiving input from outside the system (first layer after the UI) we use a case controller.

   - Pure Fabrication:
      - In this user story the Application and GetProfileInfoService class were used, which does not represent business domain concepts. They were created to be responsible for obtaining profile information.

[comment]: # (- Protected Variation:
      - O uso do DTO é PV?! Se houver uma alteração nos atributos da classe Person, a sua informação estando encapsulada num DTO só vai sofrer alterações na construção e na "entrega".)

- SOLID:
   - Single-responsibility principle:
      - this pattern was used in the GetPersonInfoService class, in which is the only responsibility is manage person profile operations;
      - also used in ProfileDTO, its only responsibility is to store and deliver Person Data.
   
   - Open-Closed Principle:
      - Any adicional field will not impact the methods. Any change within Person attributes will have impact only on ProfileDTO creation and UI representation.

## 3.6. Tests

### 3.6.1. Person

#### 3.6.1.1. Success

**Test 1:** Test ProfileDTO creation

#### 3.6.1.2 Failure

No failure tests 

_____

### 3.6.2. PersonRepository

#### 3.6.2.1. Success

**Test 2:** Test if the PersonRepository gets the Person by his email (ID)

**Test 3:** Test if the PersonRepository gets the ProfileDTO from Person using his email (ID)

#### 3.6.2.2 Failure

No failure tests

_____

### 3.6.3. GetProfileInfoService

#### 3.6.3.1. Success

**Test 4:** Test if the Service gets the ProfileDTO from it the PersonRepository

#### 3.6.3.2. Failure

No failure tests

_____

### 3.6.4. GetFamilyMemberProfileController

#### 3.6.4.1. Success

**Test 5:** Test if the Controller gets the ProfileDTO from the GetProfileInfoService

#### 3.6.4.2. Failure

No failure tests

_____


# 4. Implementation


# 5. Integration

[comment]: # (All other US's/features that this one will be added on !!!!!!)

The development of this user story was the basis for the family structure where the FamilyMembers are stored and was
thus crucial for the development of the other User Stories


# 6. Observations

[comment]: # (Tudo o que nao encaixe em lado nenhum vai para aqui AKA LIXOOOOOOO !!!!!!!)

As with the Standard Category the family ID will probably need to be reworked in a future sprint to allow for more
complex ID information if needed (probably using a UUID)