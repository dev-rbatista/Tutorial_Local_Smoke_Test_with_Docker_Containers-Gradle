
# US104 Get List of Family Members and their Relation
=======================================


# 1. Requirements

*As a family administrator, I want to get the list of family members and their relation*


**Demo1** As a family administrator, I want to get... 

- Demo1.1. The list of my family members and their relation towards me.

We interpreted this requirement as the function of obtaining the list of all the family members from the family administrator's family
and the family members' relation towards him/her.   

# 2. Analysis

In order to fulfill this requirement, we need two data pieces:
- *familyID* of the Family Administrator's family
- *CCnumber* of the Family Administrator 

At a later iteration, the *familyID* and the *CCnumber* that validate the user permission, would be acquired through the Login information of the Family Administrator.

For this sprint, the *familyID* and the Family Administrator *CCnumber* will have to be inserted as a parameter.


# 3. Design

The main process to achieve this requirement would need the actor to select that he wants to see
the list of family members and their relation towards him. Since we don't have an
UI at the moment, the *familyID* and the *CCnumber* will have to be manually inserted.

````puml
@startuml

' startuml é syntax para iniciar esquema em PlantUML

' autonumber é auto-explicativo
' actor é syntax de ator. participant é syntax de uma Lifeline
' "as" define alias que queremos dar ao participant ou actor

autonumber
title getFamilyMembersAndRelation List.1
actor "Family Administrator" as actor
participant " : UI" as UI
participant " : GetFamilyMembersListController" as controller
participant " : Application" as application
participant " aFamilyService : FamilyService" as service
participant " aFamily : Family" as family

/' Comentário: activate é a syntax para ativar lifeline "preenchida" (Não tracejada). 
    Activate só deve ser inserido no momento em que se quer ativar a lifeline preenchida
'/

activate actor
actor -> UI : I want to see the list of family members and their relation
activate UI
UI -> controller : getFamilyMembersAndRelation(familyID, familyAdministratorID)
activate controller
controller -> application : getFamilyService()
activate application
application --> controller : aFamilyService
deactivate application
controller -> service :  getFamilyMembersAndRelation(familyID, familyAdministratorID)
activate service
loop for each Family in List<Family>
service -> service : getFamily(familyID)
activate family
end


service -> family : getFamilyMembersAndRelation(familyID, familyAdministratorID)


alt failure : Actor is not the Family Administrator. Returned List is empty.
family --> service : (failure) - Empty List
service --> controller : (failure) - Empty List
controller --> UI : Failure (false)
UI --> actor  : No family members and relationship found. User must be Family Administrator
ref over family 
getFamilyMembersAndRelation List.2
end ref

else sucess
family --> service : FamilyMemberAndRelationDTO List
service --> controller : FamilyMemberAndRelationDTO List
controller --> UI : Success (True)
UI --> actor : Show list of family members and their relation


end

@enduml
````


````puml

@startuml


 participant " aFamily : Family" as family
 participant " aFamilyMember : FamilyMember" as familyMember
 participant " oneFamilyMemberRelationDTO : FamilyMemberRelationDTO" as DTO
 participant " aFamilyMembersAndRelationDTOList : List <FamilyMemberRelationDTO> " as DTOlist

autonumber
title getFamilyMembersAndRelation List.2
-> family : getFamilyMembersAndRelation(familyID, familyAdministratorID)
activate family
family -> family : verifyAdministratorPermission(familyAdministratorID)
alt failure : User doesn't have Administrator privileges
 <-- family : return Empty FamilyMembersAndRelation List
else sucess : User has Administrator privileges

    
    loop for each Family Member in List<FamilyMember> convertToFamilyMemberRelationDTO() 
    family -> familyMember  : createFamilyMemberRelationDTO()  
    activate familyMember     
    family -> DTOlist ** : create
    activate DTOlist
    familyMember -> DTO ** : create(name, Relation)
    deactivate familyMember
    activate DTO
    DTO -> DTOlist : add()
    deactivate DTO

     end

family <-- DTOlist : aFamilyMembersAndRelationList
<-- family : aFamilyMembersAndRelationList
deactivate DTOlist
deactivate family
end



@enduml
````


## 3.1. Functionality Use

The GetFamilyMembersAndRelationController will invoke the Application object, that contains a Family Service.
This FamilyService contains a List of Families (Family type objects). The Application will return the FamilyService.

The FamilyService will find the family within the list of families, using the FamilyID previously inputed along with the user CCnumber (Unique citizen ID).

If the familyID does not correspond to any family on the families List, it will throw an Exception which will be caught by the Controller.

If the familyID matches any of the familyID's in the List and once in the Family instance, it executes a validation in order to verify if
the user who is asking for the information has permission to access it.

If the user is not that Family Administrator, the method wont iterate and will return an empty List back to the Service.

Otherwise, it will succeed and will call, for each FamilyMember of the Family, the method CreateFamilyMemberRelationDTO.
This method creates a DTO copy of the specific FamilyMember containing the member name and the relation towards the Family Administrator.

Each DTO created is stored in a List, and said list is returned upwards, where it will reach the Controller.


## 3.2. Class Diagram
The main Classes involved are:
- GetFamilyMembersAndRelationController
- Application
- FamilyService
- Family
- FamilyMember
- FamilyMemberRelationDTO

````puml

title Class Diagram - US104

class GetFamilyMembersAndRelationController{
- Application ffmApp
+ getFamilyMembersAndRelation(int familyID, String adminCCNumber)
}

class Application {
 - FamilyService familyService
  + getFamilyService()
}

class FamilyService {
- List<Family> families
+ getFamily(familyID)
+ getFamilyMembersRelationDTOList(int familyID, String adminCCNumber)
}

class Family {
- int familyID
- List<FamilyMember> familyMembers
+ isAdmin()
+ getFamilyMembersRelationDTOList()
}

class FamilyMember {
- private String name
- Relation relation
+ getName()
+ getRelation()
+ createFamilyMemberRelationDTO()
}

class FamilyMemberRelationDTO {
- String name
- String relationDesignation
+ getName()
+ getrelationDesignation()
}

GetFamilyMembersAndRelationController --> Application
Application --> FamilyService
FamilyService --> Family
Family --> FamilyMember
FamilyMember --> FamilyMemberRelationDTO

````

## 3.3. Applied Patterns
We applied the principles of Controller, Information Expert, Creator e PureFabrication from the GRASP pattern.
We also used the SOLID SRP principle.

## 3.4. Tests 

**FamilyMemberRelationDTO Class Tests:** 


 **Tests Setup:** 
 
    FamilyMemberRelationDTO test = new FamilyMemberRelationDTO("josé", "filho");


**Tests 1 & 2:** Verify correct return of name and relationDesignation attributes

    @Test
    void getName() {
        String expected = "josé";
        String result = test.getName();
        assertEquals(expected, result);
    }

    @Test
    void getRelationDesignation() {
        String expected = "filho";
        String result = test.getRelationDesignation();
        assertEquals(expected, result);
    }
 
**FamilyMember Class Tests:**

**Tests Setup:**
    
    String cc = "000000000ZZ4";
    String name = "Diogo";
    Date date = new Date(1990, 8, 26);
    Integer numero = 919999999;
    String email = "josediogoccbr@gmail.com";
    int nif = 212122233;
    String rua = "Rua Nossa";
    String codPostal = "4444-555";
    String local = "Zinde";
    String city = "Porto";
    String relacao = "filho";
    Relation relation = new Relation(relacao);
    boolean admin = false;
    
    String id2 = "166699209ZY8";
    String name2 = "Tony";
    Date date2 = new Date(1954, 8, 26);
    int numero2 = 919999998;
    String email2 = "tony@gmail.com";
    int nif2 = 212122000;
    String rua2 = "Rua";
    String codPostal2 = "4444-556";
    String local2 = "Gaia";
    String city2 = "Porto";
    String relacao2 = "primo";
    Relation relation2 = new Relation(relacao2);
    boolean admin2 = false;
    
    FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    FamilyMember jorge = new FamilyMember(id2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, admin2);
    FamilyMember newMember = new FamilyMember(cc, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2);



**Test 3:** Verify correct creation of FamilyMemberRelationDTO inside FamilyMemberClass 

 
    @Test
    void createFamilyMemberRelationDTO_VerifyCorrectConversionOfAttributes() {
        FamilyMember José = new FamilyMember(cc,name,date,numero,email,nif,rua,codPostal,local,city);
        FamilyMemberRelationDTO expected = new FamilyMemberRelationDTO("Diogo", "Undefined Relation");
        FamilyMemberRelationDTO result = José.createFamilyMemberRelationDTO();
        assertEquals(expected, result);
    }

**Test 4:** Conversion of FamilyMember with no relation attribute, expecting "Undefined relation" on
            FMR DTO's relationDesignation attribute.

    @Test
    void createFamilyMemberRelationDTO_VerifyIfNoRelationReturnsUndefinedRelationString() {
        FamilyMemberRelationDTO test = new FamilyMemberRelationDTO("José", "Undefined relation");
        String expected = "UndEfIned relatiOn";
        String result = test.getRelationDesignation();
        assertTrue(expected.equalsIgnoreCase(result));
    }
    
    
**Test 5:** Verify correct change of relationDesignation to "Self", when converting a Family Member
,which is Family Administrator, to an FMR DTO

    @Test
    void TestConversionToFamilyMemberDTO_VerifyIfAdministratorDesignationReturns_Self() {
        newMember.makeAdmin();
        FamilyMemberRelationDTO expected = new FamilyMemberRelationDTO("Tony", "Self");
        FamilyMemberRelationDTO result = newMember.createFamilyMemberRelationDTO();
        assertEquals(expected.getRelationDesignation(), result.getRelationDesignation());
    }
    
    
**Test 6:** Verify conversion of a existing FamilyMember relationDesignation

	 @Test
        void TestConversionToFamilyMemberDTO_ExpectingSuccess() {
            Relation relation = new Relation("primo");
            jorge.addRelation(relation);
            FamilyMemberRelationDTO expected = new FamilyMemberRelationDTO("Tony", "primo");
            FamilyMemberRelationDTO result = jorge.createFamilyMemberRelationDTO();
            assertEquals(expected.getRelationDesignation(), result.getRelationDesignation());
        }
        
        
**Family Class Tests**

**Tests Setup:**

        String cc = "135149126ZW9";
        String name = "Diogo";
        Date date = new Date(1990, 8, 26);
        int numero = 919999999;
        String email = "diogo@gmail.com";
        int nif = 212122230;
        String rua = "Rua Nossa";
        String codPostal = "4444-555";
        String local = "Zinde";
        String city = "Porto";
        String relacao = "filho";
        Relation relation = new Relation(relacao);
        boolean admin = false;
    
        String cc2 = "166699209ZY8";
        String name2 = "Tony";
        Date date2 = new Date(1954, 8, 26);
        int numero2 = 919999998;
        String email2 = "tony@gmail.com";
        int nif2 = 212122000;
        String rua2 = "Rua";
        String codPostal2 = "4444-556";
        String local2 = "Gaia";
        String city2 = "Porto";
        String relacao2 = "primo";
        Relation relation2 = new Relation(relacao2);
        boolean admin2 = false;
    
        //Setup for getFamilyMemberRelationDTO List
        FamilyMember diogo = new FamilyMember(cc, name, date,numero,email,nif,rua,codPostal,local, city);
        FamilyMember jorge = new FamilyMember(cc, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2);
        FamilyMember newMember = new FamilyMember(cc, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2);
        FamilyMemberRelationDTO diogoDTO = new FamilyMemberRelationDTO(diogo.getName(), "Undefined Relation");
        FamilyMemberRelationDTO jorgeDTO = new FamilyMemberRelationDTO(jorge.getName(), "Undefined Relation");
        FamilyMemberRelationDTO newMemberUndefinedRelation = new FamilyMemberRelationDTO(newMember.getName(), "Undefined Relation");
        List<FamilyMemberRelationDTO> expectedDTOList = new ArrayList<>();
        ArrayList<FamilyMember> expectedFamilyMembers = new ArrayList<>();
        int familyOneID = 123;
        String familyOneName = "Simpson";
        Family family = new Family(familyOneName, familyOneID);


**Test 7:** Verify correct conversion of the List of FamilyMembers of a Family, to FamilyMembersRelationDTO,
            and said List.

	@Test
        void getFamilyMembersRelationDTOList() {
            expectedDTOList.add(diogoDTO);
            expectedDTOList.add(jorgeDTO);
            expectedDTOList.add(newMemberUndefinedRelation);
            expectedFamilyMembers.add(diogo);
            expectedFamilyMembers.add(jorge);
            expectedFamilyMembers.add(newMember);
            family.addFamilyMemberArray(expectedFamilyMembers);
            List<FamilyMemberRelationDTO> result = family.getFamilyMembersRelationDTOList();
            assertEquals(expectedDTOList, result);
            assertNotSame(expectedDTOList, result);
        }
	
	
**Test 8:** Verify if an empty Family (i.e. with no Family Members) returns an empty List
            of FamilyMemberRelationDTO

	 @Test
        void getFamilyMembersRelationDTOList_FamilyWithNoMembersReturningEmptyList() {
            List<FamilyMemberRelationDTO> expected = expectedDTOList;
            List<FamilyMemberRelationDTO> result = family.getFamilyMembersRelationDTOList();
            assertEquals(expectedDTOList, result);
            assertNotSame(expectedDTOList, result);
        }
        
**FamilyService Class Tests**

**Tests Setup:**
        
            String cc = "143896040ZV3";
            String name = "Diogo";
            Date date = new Date(1990, 8, 26);
            int numero = 919999999;
            String email = "abc@gmail.com";
            int nif = 212122233;
            String rua = "Rua Nossa";
            String codPostal = "4444-555";
            String local = "Zinde";
            String city = "Porto";
            String relacao = "filho";
            Relation relation = new Relation(relacao);
            boolean admin = false;
        
        
            //Added 2nd FamilyMember to test
            String cc2 = "166699209ZY8";
            String name2 = "Tony";
            Date date2 = new Date(1954, 8, 26);
            int numero2 = 919999998;
            String email2 = "tony@gmail.com";
            int nif2 = 212122000;
            String rua2 = "Rua";
            String codPostal2 = "4444-556";
            String local2 = "Gaia";
            String city2 = "Porto";
            String relacao2 = "pai";
            Relation relation2 = new Relation(relacao2);
            boolean admin2 = false;
        
            //Added 3rd FamilyMember to test
            String id3 = "137476450ZX0";
            String name3 = "TonyZe";
            Date date3 = new Date(1900, 8, 26);
            int numero3 = 919939998;
            String email3 = "tonyze@gmail.com";
            int nif3 = 212122000;
            String rua3 = "Rua";
            String codPostal3 = "4444-556";
            String local3 = "Gaia";
            String city3 = "Porto";
            boolean admin3 = true;
        
            
            String id4 = "000000000ZZ4";
            String name4 = "TonyZe";
            Date date4 = new Date(1920, 8, 26);
            int numero4 = 919939998;
            String email4 = "tonyze@gmail.com";
            int nif4 = 212122000;
            String rua4 = "Rua";
            String codPostal4 = "4444-556";
            String local4 = "Gaia";
            String city4 = "Porto";
        
            //DTO Test Setup
            FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
            FamilyMember jorge = new FamilyMember(cc2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, admin2);
            FamilyMember manuelAdmin = new FamilyMember(id3, name3, date3, numero3, email3, nif3, rua3, codPostal3, local3, city3, admin3);
            FamilyMember noRelationMember = new FamilyMember(id4, name4, date4, numero4, email4, nif4, rua4, codPostal4, local4, city4);
            int familyOneID = 123;
            String familyOneName = "Simpson";
            int familyTwoID = 456;
            String familyTwoName = "Simpson";
            int familyThreeID = 789;
            String familyThreeName = "Simpson";
            Family family = new Family(familyOneName, familyOneID);
            Family familyTwo = new Family(familyTwoName, familyTwoID);
            Family familyThree = new Family(familyThreeName, familyThreeID);
            ArrayList<FamilyMember> familyMembers = new ArrayList<>();
            FamilyMemberRelationDTO diogoDTO = new FamilyMemberRelationDTO(diogo.getName(), "Undefined Relation");
            FamilyMemberRelationDTO jorgeDTO = new FamilyMemberRelationDTO(jorge.getName(), "Undefined Relation");
            FamilyMemberRelationDTO manuelAdminDTO = new FamilyMemberRelationDTO(manuelAdmin.getName(), "Self");
            List<FamilyMemberRelationDTO> expected = new ArrayList<FamilyMemberRelationDTO>();
        
**Test 9:** Verify Administrator Validation before obtaining FamilyMemberRelationDTOList expecting true

	@Test
        void verifyAdministratorPermissionBeforeInvokingGetDTOList_TestWithAdministratorExpectingTrue() {
            FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
            int familyID = 1;
            String familyName = "Ribeiro";
            Family ribeiro = new Family(familyName, familyID);
            ribeiro.addFamilyMember(diogo);
            FamilyService family = new FamilyService(ribeiro);
            diogo.makeAdmin();
            boolean expected = true;
            boolean result = family.verifyAdministratorPermission(ribeiro.getFamilyID(), diogo.getID());
            assertTrue(result);
	
	
**Test 10:** Verify Administrator Validation before obtaining FamilyMemberRelationDTOList expecting false

    @Test
    void verifyAdministratorPermissionBeforeInvokingGetDTOList_TestWithNoAdministratorExpectingFalse() {
        FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city, admin);
        int familyID = 1;
        String familyName = "Ribeiro";
        Family ribeiro = new Family(familyName, familyID);
        ribeiro.addFamilyMember(diogo);
        FamilyService family = new FamilyService(ribeiro);
        boolean expected = false;
        boolean result = family.verifyAdministratorPermission(ribeiro.getFamilyID(), "000000000ZZ4");
        assertFalse(result);
    }
    
 **Test 11:** Verify correct list since member has administrator privileges.   
    
    @Test
        void getDTOList_ExpectingToHaveEqualListsBecauseMemberIsAdminAndMethodWillReturnFilledList() {
            //Arrange
            familyMembers.add(diogo);
            familyMembers.add(jorge);
            familyMembers.add(manuelAdmin);
            family.addFamilyMemberArray(familyMembers);
            familyTwo.addFamilyMember(diogo);
            familyTwo.addFamilyMember(jorge);
            expected.add(diogoDTO);
            expected.add(jorgeDTO);
            expected.add(manuelAdminDTO);
            FamilyService familyService = new FamilyService(family);
            //Act
            List<FamilyMemberRelationDTO> result = familyService.getFamilyMembersRelationDTOList(family.getFamilyID(), manuelAdmin.getID());
            //Assert
            assertEquals(expected, result);
            assertNotSame(expected, result);
        }
        
 **Test 12:** Verify empty list because the FamilyMember asking for the list
              does not have Administrator privileges.
 
    @Test
     void getDTOList_TestWithNoAdministratorIDExpectingToBeNotEquals_ReturnIsEmptyList() {
         //Arrange
         familyMembers.add(diogo);
         familyMembers.add(jorge);
         familyMembers.add(manuelAdmin);
         family.addFamilyMemberArray(familyMembers);
         familyTwo.addFamilyMember(diogo);
         familyTwo.addFamilyMember(jorge);
         expected.add(diogoDTO);
         expected.add(jorgeDTO);
         expected.add(manuelAdminDTO);
         FamilyService familyService = new FamilyService(family);
         //Act
         List<FamilyMemberRelationDTO> result = familyService.getFamilyMembersRelationDTOList(family.getFamilyID(), jorge.getID());
         //Assert
         assertNotEquals(expected, result);
         assertNotSame(expected, result);
     }
 
  **Test 13:** Verify throw of Exception because given family ID does not exists
  
    @Test
      void getDTOList_TestExceptionThrowNoFamilyWithGivenID() {
          //Arrange
          FamilyService familyService = new FamilyService(family);
          //Assert
          assertThrows(IllegalArgumentException.class, () -> familyService.getFamilyMembersRelationDTOList(8898, manuelAdmin.getID()));
      }
      
 **Test 14:** Verify correct return of List
 
    @Test
     void getDTOList_TestWithFamilyMemberWithAdminPrivilegesButNoRelation() {
         //Arrange
         familyMembers.add(diogo);
         familyMembers.add(jorge);
         familyMembers.add(noRelationMember);
         family.addFamilyMemberArray(familyMembers);
         familyTwo.addFamilyMember(diogo);
         familyTwo.addFamilyMember(jorge);
         expected.add(diogoDTO);
         expected.add(jorgeDTO);
         expected.add(manuelAdminDTO);
         FamilyService familyService = new FamilyService(family);
         //Act
         List<FamilyMemberRelationDTO> result = familyService.getFamilyMembersRelationDTOList(family.getFamilyID(), noRelationMember.getID());
         //Assert
         assertNotEquals(expected, result);
         assertNotSame(expected, result);
     }
     
**GetFamilyMembersAndRelationController Class Tests**

**Tests Setup: Same Setup used on FamilyService Class Tests**

**Test 15:** Verify expected success case

    @Test
    void getFamilyMemberAndRelationDTO_TestSuccessCase() {
            //Arrange
            familyMembers.add(diogo);
            familyMembers.add(jorge);
            familyMembers.add(manuelAdmin);
            family.addFamilyMemberArray(familyMembers);
            familyTwo.addFamilyMember(diogo);
            familyTwo.addFamilyMember(jorge);
            expected.add(diogoDTO);
            expected.add(jorgeDTO);
            expected.add(manuelAdminDTO);
            Application app = new Application();
            GetFamilyMembersListController test = new GetFamilyMembersListController(app);
            app.getFamilyService().addFamily(family);
            //Act
            boolean result = test.getFamilyMembersAndRelation(family.getFamilyID(), manuelAdmin.getID());
            //Assert
            assertTrue(result);
        }
        
 **Tests 16 & 17:** Verify expected failure cases (expecting false) for both possible motives
              Passing a wrong familyID (test 16) and user not being a Family Administrator
 
    @Test
         void getDTOList_TestWithWrongFamilyIDExpectingFalse() {
             //Arrange
             familyMembers.add(diogo);
             familyMembers.add(jorge);
             familyMembers.add(manuelAdmin);
             family.addFamilyMemberArray(familyMembers);
             familyTwo.addFamilyMember(diogo);
             familyTwo.addFamilyMember(jorge);
             Application app = new Application();
             app.getFamilyService().addFamily(family);
             GetFamilyMembersListController controller = new GetFamilyMembersListController(app);
             //Act
             boolean result = controller.getFamilyMembersAndRelation(3, diogo.getID());
             //Assert
             //As nothing has been added to expected both lists are Empty, as predicted
            assertFalse(result);
         } 
         
         @Test
             void getDTOList_TestWithNoAdministratorExpectingFalse() {
                 //Arrange
                 familyMembers.add(diogo);
                 familyMembers.add(jorge);
                 familyMembers.add(manuelAdmin);
                 family.addFamilyMemberArray(familyMembers);
                 familyTwo.addFamilyMember(diogo);
                 familyTwo.addFamilyMember(jorge);
                 Application app = new Application();
                 app.getFamilyService().addFamily(family);
                 expected.add(jorgeDTO);
                 GetFamilyMembersListController controller = new GetFamilyMembersListController(app);
                 //Act
                 boolean result = controller.getFamilyMembersAndRelation(2, diogo.getID());
                 //Assert
                 //As nothing has been added to expected both lists are Empty, as predicted
                 assertFalse(result);
             }     
 

# 4. Implementation

**Finding the correct Family in the Families List**

Two methods were created to find the correct family. One checks the existence of the specific family, through
the familyID and the other one uses this validation to iterate through the list of families and return the correct one.

    private boolean checkIfFamilyExists(int familyID) {
        for (Family family : families) {
            if (familyID == family.getFamilyID()) {
                return true;
            }
        }
        return false;
    }
    
    public Family getFamily(int familyID) {
            if (checkIfFamilyExists(familyID)) {
                for (Family family : families) {
                    if (family.getFamilyID() == familyID)
                        return family;
                }
            } else {
                throw new IllegalArgumentException("No family with such ID");
            }
            return null;
        }
        
**Verify if the user is the Family Administrator**

This method is used to verify the User as a Family Administrator in order to obtain the List

    public boolean verifyAdministrator(String ccNumber) {
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.compareID(ccNumber))
                return familyMember.isAdministrator();
        }
        return false;
    }
    
**Obtain the List of Family Members and their Relation**

Once obtained the correct family and verified the Administrator permission, a method was used to iterate through all the Family Members
of the Family delegating on them the responsibility of creating a DTO copy of themselves.

    public List<FamilyMemberRelationDTO> getFamilyMembersRelationDTOList() {
        List<FamilyMemberRelationDTO> DTOList = new ArrayList<>();
        for (FamilyMember familyMembers : familyMembers) {
            FamilyMemberRelationDTO newMember = familyMembers.createFamilyMemberRelationDTO();
            DTOList.add(newMember);
        }
        return DTOList;
    }    
  
Then, in the FamilyMember Class, every Family Member creates the DTO copy of themselves with the required information
returning it to the Family.

    public FamilyMemberRelationDTO createFamilyMemberRelationDTO() {
            FamilyMemberRelationDTO copyOfFamilyMemberDTO;
            name = this.getName();
            String relation;
            if (this.relation == null) {
                if (this.isAdministrator() == true) {
                    relation = "Self";
                } else {
                    relation = "Undefined Relation";
                }
            } else {
                relation = this.relation.getRelationDesignation();
            }
            copyOfFamilyMemberDTO = new FamilyMemberRelationDTO(name, relation);
            return copyOfFamilyMemberDTO;
        }  


# 5. Integration/Demonstration

As of this sprint, this function has no integration with other functions.

# 6. Observations

In the future, both the FamilyID and the Family Administrator CCnumber would be ideally retrieved by a method
that checks the login info of the current user, instead of being manually inserted.  



