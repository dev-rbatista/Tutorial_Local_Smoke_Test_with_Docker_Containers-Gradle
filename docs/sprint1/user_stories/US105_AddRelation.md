# US105 Create a relation between two family members

# 1. Requirements

## 1.1. Client Notes

As administrator, I want to create a relation between two family members:

**Demonstration 1** As administrator, I want to...

- 1.1. Create a new relation and set that relation to a given member.

**Extracted from communications with the Product Owner**

- >*"All relations use the level of the main user of the family as a reference. Be aware that SWS wants its application to be as inclusive as possible, in order to wider its market base"*;
- >*"When creating relationships, they are relative to the "main user level", not at all restricted to the main user"*.
- >*"Relationships are created by the Family Administrator. Eventually he can make some mistakes, but it is not expected that this will have a major impact on the operation of the application. If this is relevant in the future then this problem is addressed."*

The interpretation made of this requirement is that the family administrator can create relationships between family members and him, and these relationships are not restricted to normal family relationships.

## 1.2. System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US105

actor "Family Administrator" as familyAdministrator
participant ": System" as system

activate familyAdministrator
familyAdministrator -> system : add relation to member
activate system
familyAdministrator -> system : inputs required data

alt failure
system -> familyAdministrator : Inform Failure

else sucess
system -> familyAdministrator : Inform Sucess

end

deactivate system

deactivate familyAdministrator
```

## 1.3. Dependencies from other User Stories

This user story is dependent on the following:

- US010_Add Family: to create a family;
- US011_Add Family Administrator: to add an Administrator, that he is allowed to add a Relation;
- US101_Add Family Members: to add a Relation.

# 2. Analysis

For the fulfillment of the raised requirements, we analyze that for the accomplishment of the US we need, at this moment, the input of the family administrator of the following data:

- Self ID (User that tries to add relation to other Family Member);
- Other ID (ID from the Family Member to whom will be added a relationship);
- Relation Designation (Relationship name);
- Family ID (User's family ID).

From analysis done to requirements gathering, if the user is the family administrator, he/she will be able to add a relationship to the family member in question, regardless of whether the relationship designation exists or does not exist in the list of designations present in the Family. 

As we did not get a clear answer to the question about the previous existence of a Relation attributed to a Family Member, it was assumed that an error is thrown (catched and returns false), with no changes in the existing Relation.

##2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram US105

class Family {
- Name
- UniqueID
- RegistrationDate

}

class FamilyMember {
- Name
- BirthDate

}

class Relation {
- Designation

}

Family -down-> FamilyMember : has administrator
Family -down-> FamilyMember : \n has members
FamilyMember -down-> Relation : has relation
FamilyMember --> FamilyMember : administrator can add relation to family members
(FamilyMember, FamilyMember) <.. Relation 
```

# 3. Design

The process to fulfill the requirement we need the input of data from a UI to create a Relation object and add it to a specific FamilyMember(familyMemberID), in a given Family(familyID). to assure that que actor is an administrator, we verify if a Family
Member(selfID) is in a specific Family(familyID) and have the attribute (boolean) administrator in true state.

The controller will return:
- True, if a Relation has been assign;
- False, if the actor is not an administrator in a given family.
- False, if catches one of the following throws ("Family don't exist", "Family Member don't exist", "Empty or Null relation designation", "This family member already has an assigned relation").

## 3.1. Functionality Use

**createRelation( ).1**
```puml
autonumber

title createRelation( ).1

actor "Family Administrator" as familyAdministrator
participant ": UI" as ui
participant ": ControllerAddRelation" as controler
participant ": Application" as app
participant "familyService : FamilyService" as service


activate familyAdministrator
familyAdministrator -> ui : add realation to member
activate ui
ui -> familyAdministrator : ask:selfID, otherID, relationDesignation, familyID
deactivate ui 
familyAdministrator -> ui : imputs required data
activate ui
ui -> controler : createRelation(sefID, otherID, relationDesignation, familyID)
activate controler

controler -> app : getFamilyService()
activate app
app -> controler : familyService
deactivate app

controler -> service : createRelation(sefID, otherID, relationDesignation, familyID)
activate service

alt failure: Actor is not Admin or a exception was thrown

service -> controler : failure
controler -> ui : failure
ui -> familyAdministrator : Inform Failure

ref over service

createRelation( ).2 

end ref

else sucess

service -> controler : ok

deactivate service
controler -> ui : ok
deactivate controler
ui -> familyAdministrator : Sucess
deactivate ui
deactivate familyAdministrator

end

```
**createRelation( ).2**
```puml
autonumber
title createRelation( ).2

participant ": FamilyService" as service
participant "fam : Family" as fam
participant "person : Person" as person

activate service
-> service : createRelation(sefID, otherID, relationDesignation, familyID)
service -> service : fam =  1. getFamily(familyID)

service -> fam : isAdmin(selfID)
activate fam

alt !isDdmin
fam -> service : !isAdmin()
<- service : failure

else isAdmin

fam -> service: isAdmin()
deactivate fam
service -> fam : hasDesignation(relationDesignation)
activate fam

alt !hasDesignation()
fam -> service : !hasDesignation()


service -> fam : addToRelationDesignationList(relationDesignation)

else hasDesignation()

fam -> service : hasDesignation
deactivate fam

end

service -> fam : addRelation(otherID, relation)
activate fam
fam -> fam : person = getPerson(otherID)

fam -> person : addRelation(relation)
activate person
person -> "relation : Relation"** : createRelation(relationDesignation)
person -> person : addRelation(relation)
person -> fam : ok
deactivate person
fam -> service : ok
deactivate fam
<- service : ok

end

deactivate service

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

}

class Relation {
- designation

}

FFMapp -down-> FamilyService : has 
FamilyService -down-> Family : has list of
Family -down-> FamilyMember : has list of 
FamilyMember -down-> Relation : has 
```

## 3.3. Applied Patterns

We applied the following principles:

- GRASP:
    - Information expert:
        - This pattern was used for the Relation class, where the only responsibility is to store an attribute referring to its name. The methods contained in this class are also concerned only with itself;
    
    - Creator:
        - This pattern was used in the Family Member class. It is the responsibility of the Family Member class to create a Relation instance to be assigned to it self;
        
    - Controller:
        - To deal with the responsibility of receiving input from outside the system (first layer after the UI) we use a case controller;
        
    - Pure Fabrication:
        - In this user story the Application class was used, which does not represent a business domain concept. It was created to be able to store Families; 
    
    - High cohesion:
        - The creation of the Relation class to store the information of a relationship designation, allows this class to be changed, maintained and reused without compromising the other classes.
        
- SOLID:
    - Single-responsibility principle:
        - this pattern was used in the Relation class, in which it only presents a responsibility.

## 3.4. Tests

Several cases where analyzed in order to test the creation of a Relation instance:

**Test 1:** Verification of an object with a valid state

    @Test
    void creatingRelation() {
        String relationDesignation = "Mother";

        Relation relation = new Relation(relationDesignation);

        assertNotNull(relation);
    }
    
**Test 2:** Verification attribute passed for instantiation of a Relation

    @Test
    void getRelationDesisgnation() {
         String relationDesignation = "Father";
         Relation relation = new Relation(relationDesignation);
    
         String expected = "Father";
    
         String result = relation.getRelationDesignation();
    
         assertEquals(result, expected);
        }
   
    
**Test 3:** Verification of an instantiation of an Relation objetct with invalid arguments (Null argument)

    @Test
    void instationOfARelationObjectWithInvalidArgumentsNull() {
         String relationDesignation = null;
    
         assertThrows(Exception.class, () -> new Relation(relationDesignation));
    }

**Test 4:** Verification of an instantiation of an Relation objetct with invalid arguments (Empty argument)

    @Test
    void instationOfARelationObjectWithInvalidArgumentsEmpty() {
         String relationDesignation = "";
    
         assertThrows(Exception.class, () -> new Relation(relationDesignation));
    }

To test the new functionality was use the following tests:

**Test 5:** Test at controller to assert a success Case

    @Test
    void AddRelationTrue() {
        String relationDesignation = "Father";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddRelationController addOrChangeRelationController = new AddRelationController(application);

        assertTrue(addOrChangeRelationController.createRelation(cc, cc2, relationDesignation, familyOneIDGenerated));
    }
    
**Test 6:** Test at controller to assert an insuccess case (Is not Administrator)

    @Test
    void AddRelationFalseNoAdministrator() {
        String relationDesignation = "Father";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);

        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc3, name3, date3, numero3, email3, nif3, rua3, codPostal3, local3, city3, familyOneIDGenerated);

        AddRelationController addOrChangeRelationController = new AddRelationController(application);

        assertFalse(addOrChangeRelationController.createRelation(cc2, cc3, relationDesignation, familyOneIDGenerated));
    }
    
**Test 7:** Test at controller to assert an insuccess case (Empty Relation Designation)
    
    @Test
    void AddRelationFalseCatchEmptyRelationDesignationThrow() {
        String relationDesignation = "";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddRelationController addOrChangeRelationController = new AddRelationController(application);

        assertFalse(addOrChangeRelationController.createRelation(cc, cc2, relationDesignation, familyOneIDGenerated));
    }

# 4. Implementation

**Verification if actor is a administrator in a given family**

    public boolean verifyAdministrator(String ccNumber) {
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.compareID(ccNumber))
                return familyMember.isAdministrator();
        }
        return false;
    }

**Verification if a relation designation is already present in family list of assigned relations**

    public boolean hasDesignation(String relationDesignation) {
        for (String relationDesigantion : relationDesignations) {
            if (relationDesigantion.toLowerCase().equals(relationDesignation.toLowerCase()))
                return true;
        }
        return false;
    }

**Selecting the specific family member to be attributed a Relation**

    public FamilyMember getFamilyMemberByID(String ccNumber) {
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.compareID(ccNumber))
                return familyMember;
        }
        // If given ID is not present, a exception is thrown
        throw new IllegalArgumentException("No family member with such ID");
    }

**Creating and adding a Relation to a given Family Member**

    protected void addRelation(String relationDesignation) {
        if (this.relation != null)
            throw new IllegalArgumentException("This family member already has an assigned relation");

        Relation relation = new Relation(relationDesignation);

        this.relation = relation;
    }

**Validating the arguments to create a Relation**

    private void isValid(String relationDesignation) {
        if (relationDesignation == null || relationDesignation.trim().length()==0 || relationDesignation.isEmpty()) {
            // If is null or empty, a exception is throw
            throw new IllegalArgumentException("Empty or Null relation designation");
        }
    }

# 5. Integration/Demonstration

The development of this US will have an impact on the development of the US104 (Get Family Members And Relation), as it was returning a list with the names of family members and their Relation. So, the [US104](US104_GetFamilyMembersAndRelation.md) uses the implementation of this US.

# 6. Observations

Was not asked to fulfill the US, but, for future use, a list has been created that stores the relation designations not repeated in the specific family, and in the future a feature can be developed that allows the family administrator to choose between creating a relationship with a new designation or using one already assigned.
