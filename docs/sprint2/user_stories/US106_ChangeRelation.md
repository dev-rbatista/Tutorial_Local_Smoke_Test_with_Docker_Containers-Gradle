# US106 Change a relation between two family members

# 1. Requirements

## 1.1. Client Notes

As administrator, I want to change a relation between two family members:

**Demonstration 1** As administrator, I want to...

- 1.1. Change a relation of two family members to have a different designation.

- 1.2. Change a relation of two family members to have a different parental
  permission.

The interpretation made of this requirement is that the family administrator can
create relationships between two family members, themselves included, and these
relationships are not restricted to normal family relationships.

## 1.2. System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US106

actor "Family Administrator" as familyAdministrator
participant ": System" as system

activate familyAdministrator
familyAdministrator -> system : Change relation between memebers
activate system
familyAdministrator -> system : Inputs required data

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
- US011_Add Family Administrator: to add an Administrator, that he is allowed to
  add a Relation;
- US101_Add Family Members: to add a Relation.
- US105_Create a Relation between members: to have relations in the family to
  change them.

# 2. Analysis

For the fulfillment of the raised requirements, we analyze that for the
accomplishment of the US we need, at this moment, the input by the family
administrator of the following data:

- AdminCC (User that tries to add relation to other Family Member);
- FamilyMemberACC (ID from the first Family Member to whom will be added a
  relationship);
- FamilyMemberBCC (ID from the second Family Member to whom will be added a
  relationship);
- Relation Designation (Relationship name);
- Family ID (User's family ID).
- ParentBoolean (true if A has permission to see B's balance)

From analysis done to requirements gathering, if the user is the family
administrator, he/she will be able to change a relationship to the family
members in question.

## 2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram US106

class Family {
+ List<Relations>
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
-MemberA
-MemberB
-isAParentOfB

}

Family -down-> FamilyMember : has administrator
Family -down-> FamilyMember : \n has members
Family -down-> Relation : has relation
Relation -down-> FamilyMember: has familyMembers
FamilyMember --> Family : administrator can add relations to family
(FamilyMember, FamilyMember) <.. Relation 
```

# 3. Design

The process to fulfill the requirement we need the input of data from a UI to
create a Relation object and add to it specific FamilyMembers, in a given
Family(familyID). to assure that que actor is an administrator, we verify if a
Family Member(selfID) is in a specific Family(familyID) and have the attribute (
boolean) administrator in true state. For that, the implementation of US105 was
used, only now it checks for the existence of a previous Relation object between
the two members, and deletes it before adding the new one.

The controller will return:

- True, if a Relation has been changed;
- False, if the actor is not an administrator in a given family.
- False, if catches one of the following throws ("Family don't exist", "Family
  Member don't exist", "Empty or Null relation designation").

## 3.1. Functionality Use

**createRelation( ).1**

```puml
autonumber

title createRelation( ).1

actor "Family Administrator" as familyAdministrator
participant ": UI" as ui
participant ": AddOrChangeRelationController" as controler
participant ": Application" as app
participant ": FamilyService" as service
participant ": Family" as family
participant "Relation Service" as relServ
participant "newRelation : Relation" as relation

activate familyAdministrator
familyAdministrator -> ui : change realation of two members
activate ui
ui -> familyAdministrator : ask:selfID, ID A, ID B, relationDesignation, familyID, parentalBoolean
deactivate ui 
familyAdministrator -> ui : imputs required data
activate ui
ui -> controler : createRelation(selfID, ID A, ID B, relationDesignation, familyID, parentalBoolean)
activate controler

controler -> app : getFamilyService()
activate app
app --> controler : familyService
controler -> app : getRelationService()
app --> controler : relationService
deactivate app

controler -> service : getTargetFamily(familyID)
activate service
service-->controler: targetFamily
deactivate service

controler->family: verifyAdminPermission(selfID)
activate family

alt failure: Actor is not Admin or a exception was thrown

family --> controler : failure
controler -> ui : failure
ui -> familyAdministrator : Inform Failure

else sucess
family-->controler: ok
deactivate family
controler->relServ: addRelation(targetFamily, ID A, ID B, relationDesignation, parentalBoolean)
activate relServ
relServ->relation**: create(ID A, ID B, relationDesignation, parentalBoolean)
activate relation
relation->relation: validateDesignation
relation-->relServ: ok
deactivate relation
relServ->family: addRelation(newRelation)
activate family
family->family: find and delete previous relation between A and B
family->family: add newRelation
family->relServ: ok
deactivate family
relServ->controler:ok
deactivate relServ 
controler -> ui : ok
deactivate controler
ui -> familyAdministrator : Sucess
deactivate ui
deactivate familyAdministrator

end

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
- Relations List
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
Family -down-> Relation : has 
Relation -down-> FamilyMember: contains
```

## 3.3. Applied Patterns

We applied the principles of Controller, Information Expert, Creator and
PureFabrication from the GRASP pattern. We also used the SOLID SRP principle.

## 3.4. Tests

Several cases where analyzed in order to test the creation and change of a
Relation instance:

**Test 1:** Verification of the controller flow

    @Test
    void ChangeRelationTrueNewDesignation() {
        String relationDesignation = "Girlfriend";
        String newRelationDesignation = "Wife";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddOrChangeRelationController addOrChangeRelationController = new AddOrChangeRelationController(application);
        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, relationDesignation, false);
        assertTrue(addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, newRelationDesignation, false));
    }

**Test 2:** Verification that the new Relation object is not the same as the old
one, when changing Designations

    @Test
    void ChangeRelationTrueCheckRelationChangedDesignation() {
        String relationDesignation = "Girlfriend";
        String newRelationDesignation = "Wife";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddOrChangeRelationController addOrChangeRelationController = new AddOrChangeRelationController(application);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, relationDesignation, false);
        FamilyMemberRelationDTO oldDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, newRelationDesignation, false);
        FamilyMemberRelationDTO newDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        assertNotEquals(oldDTO, newDTO);
        assertNotSame(oldDTO, newDTO);
    }

**Test 3:** Verification that the new Relation object is not the same as the old
one, when chaning parental permission

    @Test
    void ChangeRelationTrueCheckRelationChangedPermissions() {
        String relationDesignation = "Girlfriend";


        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddOrChangeRelationController addOrChangeRelationController = new AddOrChangeRelationController(application);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, relationDesignation, false);
        FamilyMemberRelationDTO oldDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, relationDesignation, true);
        FamilyMemberRelationDTO newDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        assertNotEquals(oldDTO, newDTO);
        assertNotSame(oldDTO, newDTO);
    }

**Test 4:** Verification that the old Relation does not get changed in case the
new Relation fails verification @Test void
ChangeRelationFalseCheckRelationDoesntChangeifInvalidName() { String
relationDesignation = "Girlfriend"; String newRelationDesignation = "";

        Application application = new Application();

        FamilyService familyService = application.getFamilyService();
        familyService.addFamily(familyOneName);
        familyService.addFamilyAdministrator(cc, name, date, numero, email, nif, rua, codPostal, local, city, familyOneIDGenerated);
        familyService.addFamilyMember(cc, cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, familyOneIDGenerated);

        AddOrChangeRelationController addOrChangeRelationController = new AddOrChangeRelationController(application);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, relationDesignation, false);
        FamilyMemberRelationDTO oldDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        addOrChangeRelationController.createRelation(cc, cc, cc2, familyOneIDGenerated, newRelationDesignation, false);
        FamilyMemberRelationDTO newDTO = familyService.getFamilyMembersRelationDTOList(familyOneIDGenerated, cc).get(0);

        assertEquals(oldDTO, newDTO);
        assertNotSame(oldDTO, newDTO);
    }

# 5. Integration/Demonstration

In order to fulfill this requirement, major changes to the Relation class were
made. In order to accomodate these changes, the FamilyMemberDTO and RelationDTO
classes were re-worked. This US is also closely related to US105, using the same
controller and functionalities.
The [US104](US104_GetFamilyMembersAndRelation.md) uses the implementation of
this US.

# 6. Observations

The relation designation is always from A to B. So a relation with designation
Father will mean that A is B's Father. 2 family members can therefore have 2
relations between them (A->B and B->A).