# US130 Transfer Money from Family Cash Account To a Personal Cash Account
==========================================================================


# 1. Requirements

*As a family administrator, I want to transfer money from the family cash account to any family's member cash account*


**1** As a family administrator, I want to transfer money from the family cash account to...

- 1.1. The already existing cash account of a family member;

- 1.2. A new cash account of a family member;

We interpreted this requirement as a function for the family administrator to transfer money from the family cash account
to the cash account of any family member.

## System Sequence Diagram

````puml
autonumber
title System Sequence Diagram - US130

Actor "FamilyAdmin" as Actor
Participant "System" as System

activate Actor
Actor -> System : Transfer Money \nto Family Member
activate System
System --> Actor : ask for required data
Actor -> System : required data
System --> Actor : inform success
deactivate System
````

# 2. Analysis

In order to fulfill this requirement we need information from the family administrator. Those are:

1. FamilyID - the identifier of the current family
2. FamilyMemberCC - the CCNumber used to identify the family member that will receive the transaction
3. AccountID - the identifier of the cash account
4. TransferAmount and its Currency - the amount to be transferred, and the currency type of the transaction
5. CategoryID - the identifier of the category for the transaction
6. TransactionDesignation - a designation for the transaction
7. TransactionDate - the date of the transaction, if the information is not given the current system date will be used

If the family member doesn't have a cash account a new cash account will be created
The money transfer must be a positive value and of the same currency as the family and destination cash account.
The money transfer will only occur if the family has a cash account and if that cash account has enough money for the transaction.

# 3. Design

This functionality allows the family administrator to make a cash transfer between its family cash account and the cash account of one of the family's members.
The transfer is done by first removing the value from the family account (after checking if the transaction can occur) and then adding that money to the personal cash account.
We decided that if the family member had no cash account, a new cash account should be created for him.
After the transfer is done the data is recorded in each of the accounts as a new transaction.

## 3.1. Functionality Use

````puml
@startuml
title US130 - Transfer Money from Family Cash Account To a Personal Cash Account - SequenceDiagram
autonumber

Actor "FamilyAdmin" as Actor
Participant "UI" as UI
Participant ": TransferCashFromFamilyAccount\nToPersonalAccountController" as controller
Participant ": Application" as App
Participant ": FamilyService" as FamilyService
Participant "aFamily\n : Family" as Family
Participant ": CategoryService" as CategoryService
Participant ": AccountService" as AccountService


Actor -> UI : Transfer Money \nto Family Member
activate UI
UI --> Actor : ask for required data
deactivate UI

Actor -> UI : input required data
activate UI
UI -> controller : transferCashFrom\nFamilyToFamilyMember\n(FamilyCashTransferDTO)
activate controller
controller -> App : getFamilyService()
activate App
App --> controller : FamilyService
deactivate App

controller -> FamilyService : getFamily(familyID)
activate FamilyService
FamilyService --> controller : aFamily
deactivate FamilyService

controller -> Family : getFamilyMember(familyMemberCC)
activate Family
Family --> controller : aFamilyMember
deactivate Family

alt StandardCategory
controller -> App : getCategoryService()
activate App
App --> controller : CategoryService
deactivate App
controller -> CategoryService : getStandardCategoryByID(categoryID)
activate CategoryService
CategoryService --> controller : aCategory
deactivate CategoryService

else CustomCategory

controller -> Family : getCustomCategoryByID(categoryID)
activate Family
Family --> controller : aCategory
deactivate Family

end

controller -> App : getAccountService()
activate App
App --> controller : AccountService
deactivate App

controller -> AccountService : transferCashFromFamilyToFamilyMember\n(aFamily, aFamilyMember, familyCashTransferDTO)
activate AccountService

ref over AccountService

Transfer Cash From 
Family To Family Member

end ref

AccountService --> controller : success
deactivate AccountService

controller -> AccountService : getAccount(familyMember,AccountID)
activate AccountService
AccountService --> controller : personalCashAccount
controller -> AccountService : getFamilyCashAccount(family)
AccountService --> controller : familyCashAccount
deactivate AccountService

controller -> App : getTransactionService()
activate App
App --> controller : TransactionService
deactivate App

controller -> TransactionService : registerCashTransfer\n(familyCashAccount, personalCashAccount, familyCashTransferDTO)
activate TransactionService

ref over TransactionService

Register Cash Transfer

end ref

TransactionService --> controller : success
deactivate TransactionService

controller --> UI : success
deactivate controller

UI --> Actor : inform success
deactivate UI

@enduml

````

````puml
title Transfer Cash From Family To Family Member
autonumber

Participant ": AccountService" as AccountService
Participant "aFamily\n : Family" as Family
Participant "transferAmount\n : MoneyValue" as transferMoneyValue
Participant "familyCashAccount\n : CashAccount" as familyCashAccount
Participant "aFamilyMember\n : FamilyMember" as FamilyMember
Participant "personalCashAccount\n : CashAccount" as personalCashAccount


-> AccountService : transferCashFromFamilyToFamilyMember\n(aFamily, aFamilyMember,\n familyCashTransferDTO)
activate AccountService

AccountService -> Family : getFamilyCashAccount()
activate Family
Family --> AccountService : familyCashAccount
deactivate Family

AccountService -> transferMoneyValue ** : create(transferValue, currency)

AccountService -> familyCashAccount : hasEnoughMoneyForTransaction(transferAmount)
activate familyCashAccount

opt not enough money for transaction
familyCashAccount --> AccountService : false
<-- AccountService : failure

else enough money for transaction
familyCashAccount --> AccountService : true
deactivate familyCashAccount
end

AccountService -> familyCashAccount : checkCurrency(currency)
activate familyCashAccount

opt different currency
familyCashAccount --> AccountService : false
<-- AccountService : failure

else same currency
familyCashAccount --> AccountService : true
deactivate familyCashAccount
end opt

AccountService -> FamilyMember : getAccount(personalAccountID)
activate FamilyMember
FamilyMember --> AccountService : personalCashAccount
deactivate FamilyMember

opt if (personalCashAccount == null)
AccountService -> personalCashAccount ** : create
end

AccountService -> personalCashAccount : checkCurrency(currency)
activate personalCashAccount

opt different currency
personalCashAccount --> AccountService : false
<-- AccountService : failure
deactivate personalCashAccount
end


AccountService -> familyCashAccount : debit(transferAmount)

AccountService -> personalCashAccount : credit(transferAmount)
<-- AccountService : success
deactivate AccountService

````

````puml
autonumber
title Register Cash Transfer

Participant ": TransactionService" as TransactionService
Participant "familyCashAccount\n : CashAccount" as familyCashAccount
Participant "personalCashAccount\n : CashAccount" as personalCashAccount

-> TransactionService : registerCashTransfer\n(familyCashAccount, personalCashAccount, familyCashTransferDTO)
activate TransactionService

TransactionService -> familyCashAccount : getMoneyBalance()
activate familyCashAccount
familyCashAccount --> TransactionService : remainingBalanceOrigin
deactivate familyCashAccount

TransactionService -> familyCashAccount : registerTransaction(personalCashAccount, category,\n isCredit, remainingBalanceOrigin, familyCashTransferDTO)
activate familyCashAccount
familyCashAccount --> TransactionService : true
deactivate familyCashAccount

TransactionService -> personalCashAccount : getMoneyBalance()
activate personalCashAccount
personalCashAccount --> TransactionService : remainingBalanceDestination
deactivate personalCashAccount

TransactionService -> personalCashAccount : registerTransaction(familyCashAccount, category, isCredit,\n remainingBalanceDestination, familyCashTransferDTO)
activate personalCashAccount
personalCashAccount --> TransactionService : true
deactivate personalCashAccount

<-- TransactionService : success
deactivate TransactionService

````

## 3.2. Class Diagram


```puml
left to right direction
title Class Diagram - US130
skinparam linetype ortho

hide empty members

class TransferCashFromFamilyAccountTo\nPersonalAccountController {
+ transferCashFromFamilytoFamilyMember()
}

class Application {
+ getFamilyService()
+ getCategoryService()
+ getAccountService()
+ getTransactionService()
}

class FamilyService {
+ getFamily()
}


class Family {
+ getFamilyMember()
+ getFamilyCashAccount()
+ getCustomCategoryByID()
}

class FamilyMember {
+ hasCashAccount()
+ getAccount()
}


class CategoryService {
+ getStandardCategoryByID()
}

class AccountService {
+ verifyAccountType()
+ transferCashFromFamilyToFamilyMember()
}

class TransactionService {
+ registerCashTransfer()
}


interface Category{
}

class StandardCategory {
}

class CustomCategory {
}

interface Account {
}

class CashAccount {
+ debit()
+ credit()
+ hasEnoughMoneyForTransaction()
+ checkCurrency()
+ registerTransaction()
}


class MoneyValue {
+ debit()
+ credit()
}


class CashTransaction {
}

class TransactionData {
}



TransferCashFromFamilyAccountTo\nPersonalAccountController --> Application : has
TransferCashFromFamilyAccountTo\nPersonalAccountController --> FamilyService : calls
TransferCashFromFamilyAccountTo\nPersonalAccountController -right-> CategoryService : calls
TransferCashFromFamilyAccountTo\nPersonalAccountController --> AccountService : calls
TransferCashFromFamilyAccountTo\nPersonalAccountController --> TransactionService : calls

CategoryService --> StandardCategory
'CategoryService -left-> CategoryTreeDTO : creates
'FamilyService -right-> FamilyWithoutAdministratorDTO : creates
FamilyService --> Family : has list
Family --> CashAccount : has
Family --> CustomCategory : \n\n has
Family --> FamilyMember : has list

Category <|- StandardCategory : implements
Category <|-- CustomCategory : implements
FamilyMember --> Account : has list

Account <|-- CashAccount : implements

Account -* AccountData : contains

AccountService --> Account: handles

TransactionService --> CashTransaction: handles
CashAccount --> CashTransaction: has list
AccountData ---* MoneyValue : contains
CashTransaction -* TransactionData : contains

```


## 3.3. Applied Patterns

We applied the following principles:

- GRASP:
    - Information expert:
        - This pattern was used in classes that implemented the Account interface, as well as the CashTransactions;
        
    - Controller:
        - To deal with the responsibility of receiving input from outside the system (first layer after the UI) we implemented a use-case controller.
        
    - Pure Fabrication:
        - The creation of classes like AccountService and TransactionService which don't have domain model equivalents allowed to reduce the responsabilities of the other classes (Family and FamilyMember for example)
        
    - High cohesion and Low Coupling:
        - The creation of the Service classes provided low Coupling and high Cohesion, keeping one Class as the Information Expert.
                    
- SOLID:
    - Single-responsibility principle:
        - this pattern was used in the AccountService class, in which the only responsibility is to manage account operations while all the transaction registration responsibilities were delegated to the TransactionService.

## 3.4. Tests

In order to test the possible cases of success and failure we developed tests that simulated those situations

**Test 1: Success**
    
```    @Test
    void transferCashFromFamilyToFamilyMember_validTransference() {
        familyCashTransferDTO = new FamilyCashTransferDTO(familyID,familyMemberCC,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);
        TransferCashFromFamilyAccountToPersonalAccountController controller = new TransferCashFromFamilyAccountToPersonalAccountController(ffmApplication);

        boolean result = controller.transferCashFromFamilyToFamilyMember(familyCashTransferDTO);

        Assertions.assertTrue(result);
    }
  ```
We tested the success if we selected a Standard or a Custom Category, and if the target family member didn't have a cash account.
  
The cases of failure we tested included the family not having a cash account, any of the cash accounts involved having a different currency,
selecting a category that doesn't exist, and if the family account lacks enough money for the transaction. A few examples are shown bellow.

**Test 2: Invalid Category**

    @Test
    void transferCashFromFamilyToFamilyMember_invalidCategory() {
        int categoryID = 100;
        familyCashTransferDTO = new FamilyCashTransferDTO(familyID,familyMemberCC,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);
        TransferCashFromFamilyAccountToPersonalAccountController controller = new TransferCashFromFamilyAccountToPersonalAccountController(ffmApplication);

        boolean result = controller.transferCashFromFamilyToFamilyMember(familyCashTransferDTO);

        Assertions.assertFalse(result);
    }
    
**Test 3: Insufficient Funds**

    @Test
    void transferCashFromFamilyToFamilyMember_NotEnoughMoneyInvalid() {
        //Common Data
        int familyID = 1;
        int accountID = 1;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas", null, 100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 100.0;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance, "Diogo's Wallet", id, 1, CurrencyEnum.EURO);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createFamilyCashAccount(ribeiro, "Familia Ribeiro's Wallet", 100);
        accountService.createPersonalCashAccount(diogo, cashAccountDTO);
        //Transference Data
        double transferAmount = 200.0;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID, id, accountID, transferAmount, currency, categoryID, transactionDesignation, transactionDate);

        boolean result = accountService.transferCashFromFamilyToFamilyMember(ribeiro, diogo, familyCashTransferDTO);

        Assertions.assertFalse(result);
    }
    
**Test 4: Invalid Currency**

    @Test
    void transferCashFromFamilyToFamilyMember_DifferentCurrency() {
        //Common Data
        int familyID = 1;
        int accountID = 1;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas", null, 100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 100.0;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance, "Diogo's Wallet", id, 1,CurrencyEnum.DOLLAR);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createPersonalCashAccount(diogo, cashAccountDTO);
        //Transference Data
        double transferAmount = 200.0;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID, id, accountID, transferAmount, currency, categoryID, transactionDesignation, transactionDate);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            accountService.transferCashFromFamilyToFamilyMember(ribeiro, diogo, familyCashTransferDTO);
        });
    }

# 4. Implementation

In order to implement this user story we had several steps that required some extra attention in particular the large number of verifications that had to be performed before the transfer was allowed.
This requirement was verified by creating unitary tests for the conditions where the transfer should not occur. The data involved in the transfer was also recorded in the form of a Transaction allowing other US to interact with that data.

# 5. Integration
 
This user story depends on the US170 - CreatePersonalCashAccount as it uses the Cash Account class created during that user story. It also interacts with elements developed in the US185 - GetAccountBalance and with US186 - GetListOfMovementsBetweenTwoDates.
It also relates to the US001, US010, US101 and US120 from previous sprints.

#6. Observations

Although this user story had an increased complexity in regard to its interaction with other parts of the system we believe we achieved a working solution.
The challenges presented, also allowed us to check the integrity of the other parts of our system and to try to improve upon them.