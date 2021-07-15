US188 Check Children's Cash Account Balance
=======================================

# 1. Requirements

### 1.1 Client Notes

**Demo1** - As a parent, I want to check the balance of one of my children's Cash Account.

We interpreted this requirement as the function of obtaining the current balance (at a given moment) of one
of the user's children's cash accounts.


### 1.2 System Sequence Diagram

````puml
autonumber

title CheckChildrenCashAccountBalance
actor "Parent" as Parent
participant "System" as System

activate Parent
Parent -> System: checkChildrenCashAccountBalance
activate System
System --> Parent: request data
Parent -> System: input data
alt failure
System --> Parent: inform failure
else success
System --> Parent: inform success
deactivate System
end alt
deactivate Parent

````

### 1.3 Dependencies

This user story has a dependency with these user stories:


- **[US010](US010_AddFamily.md)** *(As a system manager, I want to create a family)*
   - In order to be added a family member, the system needs to have a family;

- **[US011](US011_AddFamilyAdministrator.md)** *(As a system manager, I want to add a family administrator)*
   - In order to be added a family member, the family needs to have a family administrator;

- **[US101](US101_AddFamilyMember.md)** *(As a family Administrator, I want to add a familyMember to a family)*
   - In order to add a bank account, the system needs to have that Family Member.

- **[US171](US170_CreatePersonalCashAccount.md)** *(As a family member, I want to create a personal cash account.)*
   - In order to be get cash account balance, there needs to be a cash account in the family member.
   
- **[US105](US105_AddRelation.md)** *(As an administrator, I want to create a relation between two family members.)*
   - In order to obtain a Relation and verify the parenthood, there has to be a relation.



# 2. Analysis

Our analysis of this US is as follows:

To check the balance of a child's cash account we need to know:

1. The Family (Obtained by FamilyID)
2. The corresponding FamilyMembers (both obtained by ID)
3. The Account ID

As the accounts are stored within the FamilyMembers Class, we will need to know the target
FamilyMember. This will be obtained through the ID.  

As it is a requirement that the actor is a parent or has parental permissions over the child, we will also have to verify the parental relation
between both members.  
That is one of the reasons we will need both FamilyMembers ID.

As a FamilyMember can have multiple accounts, we will also need the AccountID which is unique
for each account in each FamilyMember.

In the future the ID's necessity will be overcome by the Log In information, along with the respective
permissions, or lack of them.

# 2.1 Domain Model

```puml
hide empty members
hide circle
title Domain Model Diagram US188

class Family {
- Name
- UniqueID
- RegistrationDate

}

class FamilyMember {
- Name
- BirthDate
- CCnumber
}

class CashAccount {
- balance
- description
}

Family "1" -down-> "1..* " FamilyMember : has Family members
FamilyMember "1 " ---> "0..* " CashAccount  : has
FamilyMember "1" --> "0..*     " FamilyMember : parenthoodRelation 
```



# 3. Design

This functionality allows the actor to check the Balance of one of his/her children's cash accounts.

A parental permission verification will have to take place, in order to confirm the actor's permission
over the children's account. 

Since we don't have an existing UI at the moment, all the necessary data will have to be manually inserted by the user.

The actor will need to insert the following data: familyID, familyMemberID (actor), 
(child) FamilyMemberID and accountID.



````puml
autonumber


title CheckChildrenCashAccountBalance
actor "Family Member" as FamilyMember
participant "UI" as UI
participant ": CheckChildrenCash\nAccountBalanceController" as Controller
participant "ffmApplication:\n Application" as App
participant "familyService :\n FamilyService" as FamilyService
participant "relationService: \nRelationService" as Relation
participant "aFamily : \nFamily" as Family
participant "accountService:\n AccountService" as AccountService
participant "child : \nFamilyMember" as aFamilyMember
participant "aCashAccount : \nCashAccount" as account


activate FamilyMember
FamilyMember -> UI: I want to check one of my \nchildren's cash account balance
activate UI
UI -> Controller : checkCashBalanceAccount(familyID, \nparentID, childID, accountID)
activate Controller
Controller -> App : getFamilyService()
activate App
App --> Controller : familyService
deactivate App
Controller -> FamilyService : getFamily(familyID)
activate FamilyService
FamilyService -> FamilyService : getFamily(familyID)
FamilyService --> Controller : aFamily
deactivate FamilyService
Controller -> Family : getFamilyMember(parentID)
Activate Family
Family -> Family : getFamilyMember\n(parentID)
Family --> Controller : familyMemberA
Controller -> Family : getFamilyMember(childID)
Family -> Family : getFamilyMember\n(childID)
Family --> Controller : familyMemberB
Controller -> App : getRelationService()
activate App
App --> Controller : relationService
deactivate App
Controller -> Relation : verifyParenthood(aFamily, \nfamilyMemberA,\n familyMemberB)
activate Relation
Relation -> Family : verifyParenthood\n(familyMemberA,\n familyMemberB)
ref over Relation, Family
CheckChildCashAccountBalance 2
end ref
Family --> Relation : True
Relation --> Controller : True


Deactivate Family
deactivate Relation


Controller -> App : getAccountService()
activate App
App --> Controller : accountService
deactivate App
Controller -> AccountService : checkCashAccountBalance\n(accountID, familyMemberB)
activate AccountService
ref over AccountService, aFamilyMember, account
CheckChildCashAccountBalance 3
end
AccountService -> aFamilyMember : getAccount(accountID)
activate aFamilyMember
aFamilyMember  --> AccountService : anAccount
deactivate aFamilyMember

AccountService -> account : getBalance()
activate account
account --> AccountService : cashAccountBalance
deactivate account
AccountService --> Controller : cashAccountBalance
deactivate AccountService
Controller --> UI : cashAccountBalance
Deactivate Controller
UI --> FamilyMember : Child's Cash Account Balance
Deactivate UI
Deactivate FamilyMember
deactivate Controller
deactivate UI

````



````puml
autonumber

title CheckChildrenCashAccountBalance 2

participant "CheckChildrenCash\nAccountBalanceController" as controller
participant "ffmApplication: \nApplication" as App
participant "relationService: \nRelationService" as relation
participant "aFamily: Family" as family

activate controller
controller -> App : getRelationService()
activate App
App --> controller : relationService
deactivate App

activate relation 
controller -> relation : verifyParenthood(aFamily, \nfamilyMemberA, familyMemberB)
relation -> family : verifyParenthood(familyMemberA,\n familyMemberB)
activate family
alt false : A is not parent of B
family --> relation : Failure
controller <-- relation : Failure 
else success : A is parent of B
family --> relation : True
deactivate family
relation --> controller : True
deactivate relation
deactivate controller
end
````


````puml
autonumber

title CheckChildrenCashAccountBalance 3

participant "CheckChildrenCash\nAccountBalanceController" as controller
participant "ffmApplication: \nApplication" as App
participant "accountService: \nAccountService" as account
participant "child : \nFamilyMember" as child
participant "aCashAccount :\n CashAccount" as cash

activate controller

activate controller
controller -> App : getAccountService()
activate App
App --> controller : accountService
deactivate App
controller -> account : checkChildCashAccountBalance\n(accountID, child)
activate account
account -> child : getAccount(accountID)
activate child
alt failure : "no account with such id"
child --> account : failure
account --> controller : failure
else success : "anAccount"
account <-- child : anAccount
deactivate child
end
account -> account : verifyAccountType(accountID, accountType)
alt false : Account is not CashAccount
account --> controller : failure
else success : Is a Cash Account
account -> cash : getBalance()
activate cash
cash --> account : cashAccountBalance
deactivate cash
account --> controller : cashAccountBalance
deactivate account
deactivate controller

end


deactivate controller

````

## 3.1. Functionality Use

The Controller will receive the input from the Family Member and with that information will invoke Application (which
has the Family Service), and will obtain Family Service.

Family Service will provide the family associated with the given ID (familyID).  
Afterwards it will look for the given Family Members stored in such Family and return them to the Controller.

Since the relation atribute is now possible between any two given Family Members there is a need for a
Relation Service. So, after the Controller obtains the FamilyMembers, the next step will be instancing a Relation Service. Then, this service will verify
the relation between both FamilyMembers to validate (or not) the parental permission to check the balance.

After validating the permission, an Account Service will also be instanced. (Both Relation and Account Service only
exist during the scope in which they are needed. They are both "created as needed" as they both hold no relevant data).

The AccountService will be given the target FamilyMember and respective accountID in order to verify if
the given account is actually a cash account. If it is, it will invoke the method to check the Balance
of given account.

It will then return the value to the Controller, and in the future to the UI.


## 3.2. Class Diagram

The main Classes involved are:
- CheckChildrenCashAccountBalanceController
- Application
- FamilyService
- Family
- FamilyMember
- RelationService
- AccountService
- CashAccount

```puml

title US188 Class Diagram
hide empty members
skinparam linetype ortho

class CheckChildrenCashAccountBalanceController {
+ checkChildrenCashAccountBalance()
}

class Application {
+ getFamilyService()
+ getAccountService()
+ getRelationService()
}

class RelationService {
+ verifyParenthood()
}

class AccountService {
+ checkChildCashBalance()
}

class FamilyService {
+ getFamily()
}


class Family {
+ getFamilyMember()
}

class FamilyMember {
+ getAccount()
}


interface Account {}

class CashAccount {
- accountType
}

class AccountData {
- MoneyValue currentBalance
- String description
- int accountID
- List<Transaction> transactions
}


CheckChildrenCashAccountBalanceController ---> Application : has
CheckChildrenCashAccountBalanceController --> FamilyService : calls
CheckChildrenCashAccountBalanceController ----> AccountService : calls
CheckChildrenCashAccountBalanceController ----> RelationService : calls

FamilyService --> Family : has list
Family --> FamilyMember : has list

FamilyMember -> CashAccount : has list
CashAccount .--|> Account
CashAccount *- AccountData : contains
RelationService -> Family : verifies relation

AccountService --> CashAccount : verifies account type and retrieves balance


```

## 3.3. Applied Patterns

We applied the principles of Controller, Information Expert, Creator e PureFabrication from the GRASP pattern. We also
used the SOLID SRP principle.

Since Account type Classes would be coupled with Family and Family Member, this would result in high Coupling and Low
Cohesion between these Classes.

Besides, the Information Expert and the Creator patterns would be divided by two Classes: Family and Family Member.
Following that, we decided to apply the Pure Fabrication Principle and created an Account Service Class.

This Class is responsible for all operations regarding Account type Classes, thus providing low Coupling and high
Cohesion, keeping one Class as the Information Expert and applying the Single Responsibility Principle.

Also, when changing the Relation and allowing it to be between any two FamilyMembers, it stopped making sense to
keep the Relations in the FamilyMembers. So, the relation List is stored in each Family. Besides that there is
a need of managing all sorts of Relations now and to verify some permissions like Parenthood.
So, applying the Pure Fabrication Principle we've created an RelationService which deals with everything
surrounding Relations. This also allows for a Class being the Information Expert.

## 3.4. Tests

**Test 1:** Controller: Success obtaining cash balance.

      @Test
      //Arrange
      void checkChildCashAccountBalance_PositiveMoneyValue() {
      int familyID = family.getFamilyID();
      family.addFamilyMember(diogo);
      family.addFamilyMember(jorge);
      jorge.addAccount(cashAccount);
      int cashAccountID = cashAccount.getAccountID();
      Relation parent = new Relation("Pai", diogo, jorge, true);
      familyService.addFamily(family);
      relationService.addRelation(family, diogo, jorge, "Pai", true);
      accountService.createPersonalCashAccount(jorge, accountDTO);
      String parentID = diogo.getID();
      String childID = jorge.getID();

      Double expected = cashAccount.getBalance();
      
      //Act
      Double result = childCashController.checkChildCashAccountBalance(familyID, parentID, childID, cashAccountID);
      
      //Assert
      assertEquals(expected, result, 0.001);

    }

**Test 2:** Controller: Obtaining expected -1.00 (not a valid cash balance) for not finding the given Family.

      @Test
        //Arrange
        void checkChildCashAccountBalance_ExpectingNegativeBalance_WrongFamily() {
        family.addFamilyMember(diogo);
        family.addFamilyMember(jorge);
        jorge.addAccount(cashAccount);
        int cashAccountID = cashAccount.getAccountID();
        familyService.addFamily(family);
        relationService.addRelation(family, diogo, jorge, "Pai", false);
        accountService.createPersonalCashAccount(jorge, accountDTO);
        String parentID = diogo.getID();
        String childID = jorge.getID();

        Double expected = -1.00;

        //Act
        Double result = childCashController.checkChildCashAccountBalance(-90, parentID, childID, cashAccountID);

        //Assert
        assertEquals(expected, result, 0.001);
    }

**Test 3:** Controller: Obtaining expected -1.00 (not a valid cash balance) for not finding the given FamilyMember.
    
      @Test
        //Arrange
        void checkChildCashAccountBalance_ExpectingNegativeBalance_WrongFamilyMember() {
        int familyID = family.getFamilyID();
        family.addFamilyMember(diogo);
        family.addFamilyMember(jorge);
        jorge.addAccount(cashAccount);
        int cashAccountID = cashAccount.getAccountID();
        familyService.addFamily(family);
        relationService.addRelation(family, diogo, jorge, "Pai", false);
        accountService.createPersonalCashAccount(jorge, accountDTO);
        String parentID = diogo.getID();
        String childID = jorge.getID();

        Double expected = -1.00;
      
        //Act
        Double result = childCashController.checkChildCashAccountBalance(familyID, "000000000000", childID, cashAccountID);
        
        //Assert
        assertEquals(expected, result, 0.001);
    }


**Test 4:** Controller: Obtaining expected -1.00 (not a valid cash balance) for not being a CashAccount.

      @Test
      //Arrange
      void checkChildCashAccountBalance_NoCashAccount() {
      int familyID = family.getFamilyID();
      family.addFamilyMember(diogo);
      family.addFamilyMember(jorge);
      jorge.addAccount(savingsAccount);
      int savingsAccountID = savingsAccount.getAccountID();
      familyService.addFamily(family);
      relationService.addRelation(family, diogo, jorge, "Pai", false);
      accountService.addBankSavingsAccount(jorge, savingsAccount.getDescription(), savingsAccount.getBalance(), savingsAccount.getInterestRate());
      String parentID = diogo.getID();
      String childID = jorge.getID();

      Double expected = -1.00;
      
      //Act
      Double result = childCashController.checkChildCashAccountBalance(familyID, parentID, childID, savingsAccountID);
      
      //Assert
      assertEquals(expected, result, 0.001);
    }

**Test 5:** Controller: Obtaining expected -1.00 (not a valid cash balance) for not being a Parent.


      @Test
        void checkChildCashAccountBalance_ExpectingNegativeBalance_NotParent() {
        int familyID = family.getFamilyID();
        family.addFamilyMember(diogo);
        family.addFamilyMember(jorge);
        jorge.addAccount(cashAccount);
        int cashAccountID = cashAccount.getAccountID();
        familyService.addFamily(family);
        relationService.addRelation(family, diogo, jorge, "Pai", false);
        accountService.createPersonalCashAccount(jorge, accountDTO);
        String parentID = diogo.getID();
        String childID = jorge.getID();


        Double expected = -1.00;

        Double result = childCashController.checkChildCashAccountBalance(familyID, parentID, childID, cashAccountID);

        assertEquals(expected, result, 0.001);
      }

# 4. Implementation

### Verify a relation between two FamilyMembers

The method in the RelationService will verify if both FamilyMembers previously inserted have a parenthood
type of relation. If not, throws an Exception.

    public boolean verifyParenthood(Family targetFamily, FamilyMember memberA, FamilyMember memberB) {
        boolean parenthood = targetFamily.isAParentOfB(memberA, memberB);
        if (!targetFamily.isAParentOfB(memberA, memberB)){
            throw new NoParentalPermissionException("No parental permission");
        }
        return parenthood;
    }

### Verify if it is a Cash Account

This method verifies the type of account. As this US is related to cash Accounts, the functionality will
only have success if the account is of the correct type.


      public boolean verifyAccountType(Account account, AccountTypeEnum accountType) {
        boolean isSameType = false;
        if (account.checkAccountType(accountType)) {
            isSameType = true;
        }
        return isSameType;
    }

### Return the current Balance of a child's Cash Account

       public MoneyValue checkChildCashAccountBalance(int accountID, FamilyMember member) {
        MoneyValue currentBalance;
        Account targetAccount = member.getAccount(accountID);
        if (targetAccount == null) {
            throw new NullPointerException("No account with such ID");
        }
        if (!targetAccount.checkAccountType(CASHACCOUNT)) {
            throw new IllegalArgumentException("Not a Cash Account");
        }
        currentBalance = targetAccount.getMoneyBalance();
        return currentBalance;
    }

# 5. Integration

As previously referred this US has dependencies and integration with [US010_AddFamily], [US011_AddFamilyAdministrator],
[US101_AddFamilyMember], [US170_CreatePersonalCashAccount], [US105_AddRelation].


# 6. Observations
In the future the need for many of the validations will be made through the UI, when the user completes the Login.
This will allow the user to only have access to the features which he is permitted to access. Also, the user will only have access
inside his own family, suppressing the necessity for familyID, familyMemberID and accountID.