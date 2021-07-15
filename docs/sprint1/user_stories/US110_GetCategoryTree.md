# US110 Get family's category tree
=======================================

# 1. Requirements

* As a family administrator, I want to get the list of the categories on the
  family’s category tree.

**Demo1** As a family admin I want to get:

- Demo1.1. The category tree of the family with ID 10.

- Demo1.2. The category tree of the family with ID 12 (which doesn't exist).

We interpreted this requirement as the function of a family admin to get the
list of categories that their family has.

# 2. Analysis

In order to fulfill this requirement, we need two main data pieces:

- familyID, in order to know which family to retrieve the CategoryTree from
- familyMemberID, in order to verify it's the family Administrator that's
  requesting the tree.

At a later iteration, the family member's ID and familyID would be acquired
through the Log In information. For this sprint, both will need to be inputted.

# 3. Design

The main process to fulfill this requirement would require the actor to select
they want to get the FamilyTree from the UI. In lieu of not having an UI, the
Int *FamilyMemberID* and Int *familyID* will be directly inputed into the
getCategoryTreeController.

````puml
@startuml
autonumber
title GetCategoryTree
actor "Actor" as actor
participant ": UI" as UI
participant ": getCategoryTreeController" as controller
participant ": FFMApplication" as application
participant ": familyService" as famservice
participant ": categoryService" as catservice
participant ": Family" as family
participant "CategoryTreeDTO" as tree
participant "aTree: CategoryTree" as atree

activate actor
actor -> UI: Get Category Tree(family ID, familyMemberID)
activate UI
UI -> controller: getCategoryTree(familyID, familyMemberID)
activate controller
controller -> application: getFamilyService
activate application
application -> controller: return familyService
controller -> application: getCategoryService
application -> controller: return CategoryService
deactivate application
controller -> famservice: verifyAdministratorPermission(familyID, familyMemberID)
activate famservice

alt not the Admin
    
    famservice -> controller: False
    controller-> UI: False
    UI-> actor: Failure

else is the Admin    
    
    famservice -> controller: True
    deactivate famservice
    controller -> catservice: getCategoryTree( familyID, familyService)
    activate catservice
    catservice -> tree: newTree(this.CategoryService, familyService, familyID)
    activate tree
    tree -> catservice: getStandardCategories
    catservice->tree: return StandarCategories
    tree-> famservice: getCustomCategories(familyID)
    activate famservice
    famservice -> family: getCustomCategories
    activate family
    family->famservice: return CustomCategories
    deactivate family
    famservice ->tree: return CustomCategories
    deactivate famservice
    tree->atree**: build tree (standardCategories, customCategories)
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
    end
@enduml
````

## 3.1. Functionality Use

The getCategoryTreeController will invoke the Application object, which stores
the FamilyService and CategoryService objects. The Application will return both
services, and the getCategoryTree method will be called from the CategoryService
once the FamilyAdministrator ID is verified by the FamilyService. The
CategoryTree DTO will be instantiated, and will fetch the StandardCategory
objects stored in the CategoryService and the CustomCategories stored in the
Family. It will store and present both Lists back to the UI.

## 3.2. Class Diagram

The main Classes involved are:

- getCategoryTreeController
- Application
- Family
- FamilyService
- CategoryService
- CategoryTreeDTO

![Class Diagram](https://imgur.com/LoE1lOF.png)


## 3.3. Applied Patterns

We applied the principles of Controller, Information Expert, Creator and
PureFabrication from the GRASP pattern. We also used the SOLID SRP principle.

## 3.4. Tests

The following preparation was made for the execution of the tests:

    int testFamilyID = 10;
    String testFamilyName = "Silva";
    Family testFamily = new Family(testFamilyName, testFamilyID);
    Application app = new Application(testFamily);
    AddFamilyMemberController familyMemberController = new AddFamilyMemberController(app);
    AddFamilyAdministratorController familyAdministratorController = new AddFamilyAdministratorController(app);
    GetCategoryTreeController categoryTreeController = new GetCategoryTreeController(app);
    AddStandardCategoryController addStandardCategoryController = new AddStandardCategoryController(app);
    String[] expected = {"HOUSE", "ELECTRICITY", "WATER", "TRANSPORT", "CAR", "PUBLIC TRANSPORT", "FUEL", "REPAIRS", "PARKING", "TAXES", "INCOME", "SALES", "GAS", "FOOD", "OTHERS", "GROCERIES", "RESTAURANTS", "BUS", "TAXI"};
    Date birthdate = new Date(1954,8,26);
    Relation relation = new Relation("Filho");

    @BeforeEach
    public void setup() {
        familyMemberController.addFamilyMember("Not Admin",birthdate,919999999,"abc@gmail.com",212122233,"Rua Nossa","4444-555","Zinde","Porto",relation,13);
        familyAdministratorController.addFamilyAdministrator(12,"Admin",birthdate,919999999,"lol@gmail.com",212122233,"Rua Nossa","4444-555","Zinde","Porto",relation,10);
        addStandardCategoryController.addStandardCategory("House", 0);//id 1
        addStandardCategoryController.addStandardCategory("Electricity", 1); //id 2
        addStandardCategoryController.addStandardCategory("WatEr", 1);//id 3
        addStandardCategoryController.addStandardCategory("Transport", 0);//id 4
        addStandardCategoryController.addStandardCategory("Car", 4);//id 5
        addStandardCategoryController.addStandardCategory("Public TraNsport", 4);//id 6
        addStandardCategoryController.addStandardCategory("Fuel", 5);//id 7
        addStandardCategoryController.addStandardCategory("Repairs", 5);//id 8
        addStandardCategoryController.addStandardCategory("Parking", 5);//id 9
        addStandardCategoryController.addStandardCategory("Taxes", 0);//id 10
        addStandardCategoryController.addStandardCategory("income", 10);//id11
        addStandardCategoryController.addStandardCategory("Sales", 10);//id 12
        addStandardCategoryController.addStandardCategory("Gas", 1);//id 13
        addStandardCategoryController.addStandardCategory("Food", 0);//id 14
        addStandardCategoryController.addStandardCategory("Others", 0);//id 15
        addStandardCategoryController.addStandardCategory("Groceries", 14);//id 16
        addStandardCategoryController.addStandardCategory("restaurants", 14);//id 17
        addStandardCategoryController.addStandardCategory("Bus", 6);//id 18
        addStandardCategoryController.addStandardCategory("Taxi", 6);//id 19

**Test 1:** Compare the returned array of categories with the expected array

    @Test
    public void compareCategoryTree() {
        CategoryTreeDTO categoryTree = app.getCategoryService().getCategoryTree(10, app.getFamilyService());
        assertArrayEquals(categoryTree.getArrayOfStandardCategoriesNames(), expected);
    }

**Test 2:** Test a successful flow of the getCategoryTreeController function

    @Test
    public void getCategoryTreeTest() {
        assertTrue(categoryTreeController.getCategoryTree(10, 12));
    }

**Test 3:** Verify that an exception is thrown when there is no family with the
inserted ID

    @Test
    public void getCategoryTreeNoFamilyWithThatIDTest() {
    assertFalse(categoryTreeController.getCategoryTree(11, 12));
    }

**Test 4:** Verify that an exception is thrown when the inserted familyMemberID
is not the FamilyAdmin

    @Test
    public void getCategoryTreeNotAnAdminTest() {
        assertFalse(categoryTreeController.getCategoryTree(11, 13));

    }

**Test 5:** Verify that an exception is thrown when the inserted familyMemberID
is not of any member of the family.

    @Test
    public void getCategoryTreeNoSuchFamilyMemberID() {
        assertFalse(categoryTreeController.getCategoryTree(10, 1));

    }

# 4. Implementation

**Fetching the Family and Category services**

In order to find the relevant Family and validate the Admin permissions, we need
to use the FamilyService. To fetch the StandardCategory List and continue the
flow of retriveing the CategoryTree, we need to use the CategoryService. The
controller for this function will fetch both services from the Application and
start the process after the Admin validation:

     public boolean getCategoryTree(int familyID, int familyMemberID) {
        FamilyService familyService = this.ffmApp.getFamilyService();
        CategoryService categoryService = this.ffmApp.getCategoryService();
        try {
            if (familyService.verifyAdministratorPermission(familyID, familyMemberID)) {
                CategoryTreeDTO categoryTree = categoryService.getCategoryTree(familyID, familyService);
                categoryTree.printTree();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

**Creating the CategoryTreeDTO object and Adding the Categories Objects**

The CategoryTreeDTO accepts both services and the familyID as arguments. It will
add the categories to its own Lists upon instantiating.

    public CategoryTreeDTO(CategoryService categoryService, FamilyService familyService, int familyID) {
        this.standardCategories.addAll(categoryService.getStandardCategories());
        this.customCategories.addAll(familyService.getCustomCategories(familyID));
    }

# 5. Integration/Demonstration

As of this sprint, this function integrates with the FamilyService, needing it
to verify the Administrator Permission. It also integrates with the
CategoryService and Family objects, in order to retrieve the Standard and Custom
categories, respectively.

# 6. Observations

In the future, the FamilyMemberID and FamilyID would ideally have to be
retrieved by a method that checks the log in info of the current user, instead
of the IDs being manually inputted. The CustomCategories, once correctly
implemented, would also be presented to the User.  



