# US151 Add family administrator
=======================================


# 1. Requirements

## 1.1. Client Notes

As a system manager, I want to add a family administrator:

**Demonstratim 1** As a system manager, I want to add...

- 1.1. A new administrator.

**Extracted from communications with the Product Owner**

- *"A Family Administrator is a Family Member too."*
- *"Is there only one administrator per family"*

The interpretation made of this requirement is that the system administrator can add a new administrator to a family that dont have an assigned administrator.

## 1.2 System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US011

actor "System Manager" as manager
participant ": System" as system

activate manager

activate system
manager -> system : choose family with no administrator
manager -> system : input required data

alt failure
system -> manager : Inform Failure

else sucess
system -> manager : Inform Sucess

end

deactivate system

deactivate manager
```

## 1.3. Dependencies from other User Stories

This user story is dependent on the following:

- US010_Add Family: to create a family without members and administrator.

# 2. Analysis

For the fulfillment of the raised requirements, we analyze that for the accomplishment of the US we need, at this moment, the impute of the system administrator of the following data:

- The Family he wants to add an administrator;
- And the data necessary to create a new administrator (id, name, vatNumber, address, birthDate, phoneNumber and emailAddress).

From analysis done to requirements gathering, a new administrator will be created to a family that has no administrator.

##2.1. Domain Model Diagram

```puml
title Domain Model Diagram US011

class Family {
- Name
- UniqueID
- RegistrationDate
}

class FamilyMember {
- Name
- BirthDate

}

class CCnumber {

}

class PhoneNumber {

}

class EmailAddress {

}

class VatNumber {

}

class Address {
- Street
- Postal Code
- Local
- City

}

Family -down-> FamilyMember : has administrator
Family -down-> FamilyMember : \n has members
FamilyMember --> CCnumber : has
FamilyMember --> PhoneNumber : has
FamilyMember --> EmailAddress : has
FamilyMember --> VatNumber : has
FamilyMember --> Address : has
```

# 3. Design

The main process to fulfill this requirement, would require the actor to choose a family without administrator and input the data needed to create a family administrator.

## 3.1 Functionality Use

**addFamilyAdministrator( ).1**
```puml

autonumber
title addFamilyAdministrator( ).1
actor "System Manager" as systemManager
participant ": UI" as UI
participant ": AddFamilyAdministratorController" as controller

activate systemManager
systemManager -> UI: add a family administrator
activate UI
UI -> controller: getFamiliesWithoutAdministrator()
activate controller

ref over controller
addFamilyAdministrator( ).2
end ref

autonumber 7
controller --> UI: listOfFamilies
deactivate controller
UI --> systemManager: show list of families without an administrator and ask to select one
deactivate UI

systemManager -> UI: select chosen family
activate UI
UI --> systemManager: ask family administrator's data (id, name, vatNumber, address, \nbirthDate, phoneNumber, emailAddress)
deactivate UI

systemManager -> UI: input data
activate UI
UI -> controller: addFamilyAdministrator(id, name, vatNumber, address, \nbirthDate, phoneNumber, emailAddress, familyID)
activate controller

ref over controller : addFamilyAdministrator( ).3

autonumber 26
alt 
controller --> UI: inform failure
UI --> systemManager: inform failure

else 

autonumber 26
controller --> UI: inform sucess
deactivate controller
UI --> systemManager: inform success
deactivate UI

end

```

**addFamilyAdministrator( ).2**
```puml
autonumber 2
title addFamilyAdministrator( ).2

participant ": AddFamilyAdministratorController" as controller
participant ": Appplication" as app
participant "familyService : FamilyService" as familyService

-> controller : getFamiliesWithoutAdministrator
activate controller
controller -> app : getFamilyService( )
activate app
app -> controller : familyService
deactivate app
controller -> familyService: getFamiliesWithoutAdministrator( )
activate familyService
familyService -> controller: listOfFamilies
deactivate familyService
<- controller : ListOfFamilies
deactivate controller 
```

**addFamilyAdministrator( ).3**
```puml
autonumber 12
title addFamilyAdministrator( ).3

participant ": AddFamilyAdministratorController" as controller
participant ": Application" as app
participant "familyService : FamilyService" as familyService
participant "aFamily : Family" as family

-> controller : addFamilyAdministrator(id, name, \nvatNumber, address, birthDate, phoneNumber, \nemailAddress, familyID, administrator)
activate controller
controller -> app: getFamilyService()
activate app
app --> controller: FamilyService
deactivate app
controller -> familyService: addFamilyAdministrator(id, name, vatNumber, address, \nbirthDate, phoneNumber, emailAddress, familyID, administrator)
activate familyService

familyService -> familyService: aFamily = getFamily(familyID)

familyService -> family : addFamilyAdministrator(id, name, vatNumber, address, \nbirthDate, phoneNumber, emailAddress, administrator)
activate family

ref over family : addFamilyAdministrator( ).4

autonumber 24
family -> familyService : inform sucess
deactivate family

familyService -> controller : inform sucess
deactivate familyService

<- controller : inform sucess
deactivate controller

```

**addFamilyAdministrator( ).4**
```puml
autonumber 17
title addFamilyAdministrator( ).4

participant "aFamily : Family" as family
participant "familyAdministrator \n: FamilyMember" as administrator
participant "aVATNumber \n: VATNumber" as vatNumber
participant "anAddress \n: Address" as address
participant "aPhoneNumber \n: PhoneNumber" as phoneNumber
participant "anEmail : Email" as email

-> family : addFamilyAdministrator(id, name, \nvatNumber, address, birthDate, phoneNumber, \nemailAddress, administrator)
activate family

activate administrator
family -> administrator**
administrator -> vatNumber**
administrator -> address**
administrator -> phoneNumber**
administrator -> email**
deactivate administrator

family -> family : addFamilyMember\n(familyAdministrator)

<- family : inform sucess
deactivate family

```

## 3.2. Class Diagram

```puml
title Class Diagram

class FFMapp {
- Category list

}

class FamilyService {
- Families List
}

class Family {
- familyID
- registrationDate
- familyID
- relationDesignationsList

}

class FamilyMember {
- personID
- name
- birthDate
- boolean: administrtor

}


FFMapp -down-> FamilyService : has list of 
FamilyService -down-> Family : has list of
Family -down-> FamilyMember : has list of 
```

## 3.3. Applied Patterns

We applied the principles of Controller, Information Expert, Creator e PureFabrication from the GRASP pattern.
We also used the SOLID SRP principle.

#####Test 1: Verify that a vatNumber is accepted -> Class VatNumber
- **1.1.** VatNumber is not created, and an error is thrown because **vatNumber** is null
  
- **1.2.** VatNumber is not created, and an error is thrown because **vatNumber** is incorrect
  
- **1.3.** VatNumber is created because **vatNumber** is correct

#####Test 2: Verify that an address is accepted -> Class Address
- **2.1.** Address is not created, and an error is thrown because **street** is null
  
- **2.2.** Address is not created, and an error is thrown because **street** is empty
  
- **2.3.** Address is not created, and an error is thrown because **street** is blank
  
- **2.4.** Address is created because **street** is correct

- **2.5.** Address is not created, and an error is thrown because **postalCode** is null

- **2.6.** Address is not created, and an error is thrown because **postalCode** is empty

- **2.7.** Address is not created, and an error is thrown because **postalCode** is blank

- **2.8.** Address is not created, and an error is thrown because **postalCode** is incorrect

- **2.9.** Address is created because **postalCode** is correct

- **2.10.** Address is not created, and an error is thrown because **local** is null

- **2.11.** Address is not created, and an error is thrown because **local** is empty

- **2.12.** Address is not created, and an error is thrown because **local** is blank

- **2.13.** Address is created because **local** is correct

- **2.14.** Address is not created, and an error is thrown because **city** is null

- **2.15.** Address is not created, and an error is thrown because **city** is empty

- **2.16.** Address is not created, and an error is thrown because **city** is blank

- **2.17.** Address is created because **city** is correct

#####Test 3: Verify that a phone is accepted -> Class PhoneNumber and FamilyMember
- **3.1.** Phone is not created, and an error is thrown because **phoneNumber** is null

- **3.2.** Phone is not created, and an error is thrown because **phoneNumber** is incorrect

- **3.3.** Phone is created because **vatNumber** is correct

#####Test 4: Verify that an email is accepted -> Class Email and FamilyMember
- **4.1.** All email tests are in **US151**

#####Test 5: Verify that a birthDate is accepted -> Class FamilyMember
- **5.1.** With FamilyAdministrator constructor from **SystemManager**, BirthDate is not created, and an error is thrown because **birthDate** is null
  
- **5.2.** With FamilyAdministrator constructor from **SystemManager**, BirthDate is created because **birthDate** is correct

#####Test 6: Verify that a Name is accepted -> Class FamilyMember
- **6.1.** With FamilyAdministrator constructor from **SystemManager**, name is not created, and an error is thrown because **name** is null
- **6.3.** With FamilyAdministrator constructor from **SystemManager**, name is not created, and an error is thrown because **name** is empty
- **6.5.** With FamilyAdministrator constructor from **SystemManager**, name is not created, and an error is thrown because **name** is blank
- **6.7.** With FamilyAdministrator constructor from **SystemManager**, name is created because **name** is correct

#####Test 7: Verify if the VatNumber already belongs to a FamilyAdministrator from his family -> Class Family
- **7.1** FamilyAdministrator is not created and not added to the family, and an error is thrown because the **vatNumber** already exists in this family
````
@Test
void NotAddFamilyAdministrator_VatExists() {
    FamilyMember pessoa1 = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    String familyName = "Moreira";
    int familyID = 1;
    Family familia = new Family(familyName, familyID);
    familia.addFamilyMember(pessoa1);
    assertThrows(IllegalArgumentException.class, () -> familia.addFamilyAdministrator(cc2, name2, date2, numero2, email2, nif, rua2, codPostal2, local2, city2));
}
````
- **7.2** FamilyAdministrator is created and added to the family because the **vatNumber** does not exists in this family
````
@Test
void addFamilyAdministrator_VatNotExists() {
    FamilyMember pessoa1 = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    String familyName = "Moreira";
    int familyID = 1;
    Family familia = new Family(familyName, familyID);
    familia.addFamilyMember(pessoa1);
    assertTrue(familia.addFamilyAdministrator(cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2));
}
````
###Test 8: Verify if the ccNumber already belongs to a FamilyAdministrator from user's family -> Class Family
- **8.1** FamilyAdministrator is not created and not added to the family, and an error is thrown because the **ccNumber** already exists in this family
````
@Test
void NotAddFamilyAdministrator_CCExists() {
    FamilyMember pessoa1 = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    String familyName = "Moreira";
    int familyID = 1;
    Family familia = new Family(familyName, familyID);
    familia.addFamilyMember(pessoa1);
    assertThrows(IllegalArgumentException.class, () -> familia.addFamilyMember(cc, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2));
}
````

- **8.2** FamilyAdministrator is created and added to the family because the **ccNumber** does not exists in this family
````
@Test
void addFamilyAdministrator_CCNotExists() {
    FamilyMember pessoa1 = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    String familyName = "Moreira";
    int familyID = 1;
    Family familia = new Family(familyName, familyID);
    familia.addFamilyMember(pessoa1);
    assertTrue(familia.addFamilyMember(cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2));
}
````

#####Test 9: Verify if the email already exists in the system -> Class FamilyService
- **9.1** FamilyAdministrator is not created and not added to the family, and an error is thrown because the **email** already exists in the Application
````
@Test
void NotAddFamilyAdministrator_EmailPresent() { // Not able to create a family member object
    Family ribeiros = new Family("Ribeiro",1);
    ribeiros.addFamilyMember(cc, name, date, numero, "abc@gmail.com", nif, rua, codPostal, local, city);
    FamilyService familyService = new FamilyService(ribeiros);
    assertThrows(IllegalArgumentException.class,()-> familyService.addFamilyAdministrator(cc2, name2, date2, numero2, "abc@gmail.com", nif2, rua2, codPostal2, local2, city2,1));
}
````
- **9.2** FamilyAdministrator is created and added to the family because the **email** does not exists in the Application
````
@Test
void addFamilyAdministrator_EmailNotPresent() { // Not able to create a family member object
    Family ribeiros = new Family("Ribeiro",1);
    ribeiros.addFamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
    FamilyService familyService = new FamilyService(ribeiros);
    assertTrue(familyService.addFamilyAdministrator(cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2,1));
}
````

###Test 10: Verify if the Family exists in the system -> Class FamilyService
- **10.1** FamilyAdministrator is not created and not added to the family, and an error is thrown because the **Family** does not exist in the Application
````
@Test
void NotAddFamilyAdministrator_FamilyNotExists() {
    Family ribeiros = new Family("Ribeiro",1);
    FamilyService familyService = new FamilyService(ribeiros);
    assertThrows(IllegalArgumentException.class,()-> familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city,2));
}
````
- **10.2** FamilyAdministrator is created and added to the family because the **Family** exists in the Application
````
@Test
void addFamilyAdministrator_FamilyExists() {
    Family ribeiros = new Family("Ribeiro",1);
    FamilyService familyService = new FamilyService(ribeiros);
    assertTrue(familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city,1));
}
````

# 4. Implementation

To get around the non-existent UI, we need extra methods to execute the User Story correctly.

- ####Get the FamilyID

With the UI and login layer implemented, it is not necessary to ask which family this user belongs to.

# 5. Integration/Demonstration

As it was said before, this UserStory dependes on **[US010 - Add Family]** to be executed, because there can only be added a Family Administrator to a existing Family

# 6. Observations

In the future, both issues presented in implementation section will be solved when the UI, and login layer are set up.
With the login layer, the user ID will be already available before the UserStory gets executed, avoiding the method *getFamily(familyID)* execution.