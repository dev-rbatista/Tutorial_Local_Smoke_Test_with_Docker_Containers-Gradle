# US173 Add a credit card account

# 1. Requirements

## 1.1. Client Notes

As a family administrator, I want to add to a credit card account I have:

**Demonstration 1** As administrator, I want to...

- 1.1. Check Family Cash Account;
- 1.2. Check a Family Member Cash Account.

**Extracted from communications with the Product Owner**

- >*"As a family administrator, I want to check the balance of the family’s cash account or of a given family member."*;

We interpreted this requirement as the function of a family administrator wants to check Family or a Family Member Cash Account.

## 1.2. System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US135

actor "Family Administrator" as familyMember
participant ": System" as system

activate familyMember

familyMember -> system : check balance of Family or Family Member Cash Account
activate system
system -> familyMember : ask: choose Family or Family Member
deactivate system

familyMember -> system : chooses Family or Family Member
activate system
system -> familyMember : ask data
deactivate system

familyMember -> system : inputs required data
activate system
alt failure
system -> familyMember : Inform Failure

else sucess
system -> familyMember : Balance

end

deactivate system

deactivate familyMember
```

## 1.3. Dependencies from other User Stories

This user story is dependent on the following:

- US010_Add Family: to create a family;
- US011_Add Family Administrator: to add an Administrator, that he is allowed to add a Family Member;
- US101_Add Family Members: to add a Cash Account;
- US120_Create Family Cash Account: to create a Family Cash Account;
- US170_Create Personal Cash Account: to create a Personal Cash Account.

# 2. Analysis

For the fulfillment of the raised requirements, we analyze that for the accomplishment of the US we need, at this moment, the input of the family administrator of the following data:

    * For check Family Cash Account:
        - Self ID (Administrator ID);
        - Family ID (User's Family).
        
    * For check Family Member Cash Account:
        - Self ID (Administrator ID);
        - Other ID (Other Family Memeber ID);
        - Account ID (Cash Account ID);
        - Family ID (User's Family).
        
As the accounts are stored within the FamilyMember and Family Class, we will need to know the target
FamilyMember or target Family. This will be obtained through the ID. 

As it's a requirement that the actor is administrator of a given Family or in a target Family Member Family's, we have also to verify if he is and administrator.

As a Family Member can have multiple accounts, and within the accounts several money accounts, we will also need the AccountID to verify if in a given Family Member that Account exists and if it's a Cash Account.

In the future the ID's necessity will be overcome by the Log In information, along with the respective
permissions, or lack of them.

##2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram US135

class Family {
- name
- uniqueID
- registrationDate

}

class FamilyMember {
- name
- birthDate
- address
- vatNumber
- email
- telephone
- CCnumber
}

class CashAccount {
- balance
- description
}

Family "1" -down-> "1..* " FamilyMember : has Family members
FamilyMember "1" -> "0..* " CashAccount  : has
Family "1" -> "0..1 " CashAccount : has
```

# 3. Design

This functionality allows the actor to check the Balance of it's Family or a Family Member in it's Family.

Thus, we had to divide it into two methods in the controller (CheckChildCashAccountBalanceController),
one to return the Balance of the Family Cash Account and another to return the Balance of the target Family Member target Cash Account.

As extra functionality, a method was developed that returns a list of Cash Accounts of a given Family Member.

Administrator permission is checked for each part of the functionality, in order to confirm the actor's permission
to check Balance in a Family or a Family Member.

For each funtionality the controller will return:
    
    - checkFamilyCashAccountBalance( ):
        * Success Case : A MoneyValue (with amount and currency) of the Family Cash Account;
        * Insuccess Case : If not an administrator, a MoneyValue with a negative one with a null Currency;
        * Insuccess Casa : If Exception was thrown, a MoneyValue with a negative two with a null Currency:
            - No Family with such ID;
            - Family does not have a Cash Account.
    
    - checkFamilyMemberCashAccountBalance( ):
        * Success Case : A MoneyValue (with amount and currency) of the Family Member Cash Account;
        * Insuccess Case : If not an administrator, a MoneyValue with a negative one with a null Currency;
        * Insuccess Casa : If Exception was thrown, a MoneyValue with a negative two with a null Currency:
            - No Family with such ID;
            - No Family Member with such ID;
            - Family Member does not have a Account id given ID;
            - Account of given ID is not a Cash Account.
        
    - getListOfCashAccountsOfAFamilyMember( )
        * Success Case : A List of Cash Account (AccountIDAndDescriptionDTO) of a given Family Member, if there is none, return an empty list of AccountIDAndDescriptionDTO;
        * Insuccess Casa : If not and administrartor, a emptyList.

## 3.1. Functionality Use

**checkCashAccountBalance( ).1**
``` puml
autonumber 1
title checkFamilyOrFamilyMemberCashAccountBalance( )
actor "Family Administrator" as actor
participant ": UI" as UI

activate actor
actor -> UI : check Family or Family Member Cash Account
activate UI
UI -> actor : ask: choose Family or Family Member
deactivate UI

ref over actor : Check Family Cash Account \n - checkCashAccountBalance( ).2

ref over actor : Check Family Member Cash Account\n - checkCashAccountBalance( ).3
```
**checkCashAccountBalance( ).2**
```puml
autonumber 1
title checkFamilyCashAccountBalance( )
actor "Family Administrator" as actor
participant ": UI" as UI
participant ": CheckCashAccountBalance\n Controller" as controller
participant ": Application" as app
participant "aAccountService : AccountService" as aserv
participant "aFamilyService : FamilyService" as fserv
participant "aFamily : Family" as family
participant "aCashAccount : CashAccount" as cash

activate actor
actor -> UI : check Family
activate UI
UI -> actor : ask: selfID, familyId
deactivate UI

actor -> UI : inputs required data
activate UI
UI -> controller : checkFamilyCashAccountBalance(selfID, familyID)

activate controller
controller -> app : getFamilyService()
activate app
app -> controller : aFamilyService
deactivate app

controller -> fserv : verifyAdministratorPermission(selfID, familyID)
alt isAdministrator
activate fserv
fserv -> controller : inform success
deactivate fserv

controller -> fserv : getFamily(familyID)
activate fserv
fserv -> controller : aFamily
deactivate fserv

controller -> app : getAccountService()
activate app
controller <- app : aAccountService
deactivate app

controller -> aserv : getFamilyCashAccountBalance(aFamily)
activate aserv

aserv -> family : getFamilyCashAccount( )
activate family
family -> aserv : aCashAccount
deactivate family

aserv -> cash : getBalance( )
activate cash
cash -> aserv : balance
deactivate cash

aserv -> controller : balance
deactivate aserv


controller -> UI : balance


UI -> actor : balance


else !isAdministrator
controller -> UI : Value with error code (-1.00)
deactivate controller

UI -> actor : Value with error code (-1.00)
deactivate UI
deactivate actor

end
```
**checkCashAccountBalance( ).3**
```puml
autonumber 1
title checkFamilyMemberCashAccountBalance( )
actor "Family Administrator" as actor
participant ": UI" as UI
participant ": CheckCashAccountBalance\n Controller" as controller
participant ": Application" as app
participant "aAccountService : AccountService" as aserv
participant "aFamilyService : FamilyService" as fserv
participant "aFamily : Family" as family
participant "aFamilyMember : FamilyMember" as familyMember
participant "aCashAccount : CashAccount" as cash

activate actor
actor -> UI : check Family Member
activate UI
UI -> actor : ask: selfID, otherID, familyId
deactivate UI

ref over actor : List of Cash Account of a Family Member\n - checkCashAccountBalance( ).4

actor -> UI : inputs required data
activate UI
UI -> controller : checkFamilyMemberCashAccountBalance(selfID, otherID, accountID, familyID)

activate controller
controller -> app : getFamilyService()
activate app
app -> controller : aFamilyService
deactivate app

controller -> fserv : verifyAdministratorPermission(selfID, familyID)
alt isAdministrator
activate fserv
fserv -> controller : inform success
deactivate fserv

controller -> fserv : getFamily(familyID)
activate fserv
fserv -> controller : aFamily
deactivate fserv

controller -> family : getFamilyMember(otherID)
activate family
family -> controller : aFamilyMember
deactivate family

controller -> app : getAccountService()
activate app
controller <- app : aAccountService
deactivate app

controller -> aserv : getFamilyMemberCashAccountBalance(aFamilyMember, accountID)
activate aserv
aserv -> familyMember : getAccount(accountID)
activate familyMember
familyMember -> aserv : aCashAccount
deactivate familyMember

aserv -> cash : getBalance( )
activate cash
cash -> aserv : balance
deactivate cash

aserv -> controller : balance
deactivate aserv


controller -> UI : balance


UI -> actor : balance


else !isAdministrator
controller -> UI : Value with error code (-1.00)
deactivate controller

UI -> actor : Value with error code (-1.00)
deactivate UI
deactivate actor

end
```

**checkCashAccountBalance( ).4**
```puml
autonumber 1
title getListOfCashAccountsOfAFamilyMember( )
actor "Family Administrator" as actor
participant ": UI" as UI
participant ": CheckCashAccountBalance\n Controller" as controller
participant ": Application" as app
participant "aAccountService : AccountService" as aserv
participant "aFamilyService : FamilyService" as fserv
participant "aFamily : Family" as family
participant "aFamilyMember : FamilyMember" as familyMember

activate actor
actor -> UI : inputs required data
activate UI
UI -> controller : getListOfCashAccountsOfAFamilyMember(selfID, otherID, familyID)

activate controller
controller -> app : getFamilyService()
activate app
app -> controller : aFamilyService
deactivate app

controller -> fserv : verifyAdministratorPermission(selfID, familyID)
alt isAdministrator
activate fserv
fserv -> controller : inform success
deactivate fserv

controller -> fserv : getFamily(familyID)
activate fserv
fserv -> controller : aFamily
deactivate fserv

controller -> family : getFamilyMember(otherID)
activate family
family -> controller : aFamilyMember
deactivate family

controller -> app : getAccountService()
activate app
controller <- app : aAccountService
deactivate app

controller -> aserv : getListOfCashAccountsOfAFamilyMember(aFamilyMember)
activate aserv
aserv -> familyMember : geAccounts( )
activate familyMember

familyMember -> aserv : listOfAccounts
deactivate familyMember

aserv -> aserv : createListOfCashAccounts(listOfAccounts)
aserv -> controller : listOfCashAccounts
deactivate aserv

controller -> UI : listOfCashAccounts
UI -> actor : listOfCashAccounts
else !isAdiministrator

controller -> UI : emptyList
deactivate controller

UI -> actor : emptyList
deactivate UI
deactivate actor

end
```

## 3.2. Class Diagram

```puml
skinparam linetype ortho
title US135 Class Diagram
hide empty members

class CheckCashAccountBalanceController {
+ getListOfCashAccountsOfAFamilyMember()
+ checkFamilyCashAccountBalance()
+ checkFamilyMemberCashAccountBalance()
}

class Application {
+ getFamilyService()
+ getAccountService()
}

'class CashAccount {
'- accountType
'+ checkAccountType()
'}

'class AccountData {
'- description
'- accountID
'- List<Transaction> transactions
'}


class AccountService {
+ getFamilyMemberCashAccountBalance()
+ getFamilyCashAccountBalance()
}

class FamilyService {
+ getFamily()
+ verifyAdministratorPermission()
}

class Family {
+ getFamilyMember()
+ getFamilyCashAccount()
}

class FamilyMember {
+ getAccount()
}

interface Account {
'+ getAccountID()
'+ isIDOfThisAccount(int accountID)
'+ hasEnoughMoneyForTransaction(MoneyValue value )
+ checkAccountType(AccountTypeEnum accountType)
'+ String getDescription()
+ MoneyValue getMoneyBalance()
'+ checkCurrency(CurrencyEnum currency)
'+ getListOfMovements()
'+ debit(MoneyValue value)
'+ credit(MoneyValue value)
'+ getAccountIDAndDescriptionDTO()
}

class MoneyValue {
- amount
- currency
}

CheckCashAccountBalanceController -up-> Application : ffmapplication 
'AccountService <---* Application : accountService
'FamilyService <---* Application : familyService

CheckCashAccountBalanceController -.> AccountService
CheckCashAccountBalanceController ---.> FamilyService
CheckCashAccountBalanceController ---.>  Family
CheckCashAccountBalanceController ---.> FamilyMember
'Family *--> "0..1" CashAccount : familyCashAccount
'FamilyMember *-> "0..*" Account  : accountList
'CashAccount .--|> Account
'CashAccount *-> AccountData : accountData
CheckCashAccountBalanceController -.> MoneyValue
AccountService .-> Account
'AccountService .-> CashAccount 
AccountService .-> MoneyValue
AccountService .-> Family
AccountService .-> FamilyMember
```

## 3.3. Applied Patterns

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

## 3.4. Tests

Several cases where analyzed by functionality:

### getListOfCashAccountsOfAFamilyMember( ):

**Test 1:** List of Cash Accounts of a Family Member with just one Cash Account
    
    @Test
    void getListOfCashAccountsOfAFamilyMemberListWithOneCashAccount() {
        CreatePersonalCashAccountController createPersonalCashAccountController = new CreatePersonalCashAccountController(application);
        AddCashAccountDTO addCashAccountDTO = new AddCashAccountDTO(valueOne, accountDescriptionOne, id2, familyOneID,currency);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTO);
        
        List<AccountIDAndDescriptionDTO> expectedList = new ArrayList<>();
        AccountIDAndDescriptionDTO expectedDTO = new AccountIDAndDescriptionDTO(accountIDOne, accountDescriptionOne);
        expectedList.add(expectedDTO);
        
        List<AccountIDAndDescriptionDTO> result = checkCashAccountBalanceController.getListOfCashAccountsOfAFamilyMember(cc, id2, familyOneID);
        
        assertEquals(expectedList, result);
    }
    
**Test 2:** List of Cash Accounts of a Family Member with two Cash Accounts    

    @Test
    void getListOfCashAccountsOfAFamilyMemberListWithTwoCashAccount() {
        CreatePersonalCashAccountController createPersonalCashAccountController = new CreatePersonalCashAccountController(application);
        AddCashAccountDTO addCashAccountDTOOne = new AddCashAccountDTO(valueOne, accountDescriptionOne, id2, familyOneID, currency);
        AddCashAccountDTO addCashAccountDTOTwo = new AddCashAccountDTO(valueTwo, accountDescriptionTwo, id2, familyOneID, currency);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTOOne);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTOTwo);
    
        List<AccountIDAndDescriptionDTO> expectedList = new ArrayList<>();
        AccountIDAndDescriptionDTO expectedDTOOne = new AccountIDAndDescriptionDTO(accountIDOne, accountDescriptionOne);
        AccountIDAndDescriptionDTO expectedDTOTwo = new AccountIDAndDescriptionDTO(accountIDTwo, accountDescriptionTwo);
        expectedList.add(expectedDTOOne);
        expectedList.add(expectedDTOTwo);
    
        List<AccountIDAndDescriptionDTO> result = checkCashAccountBalanceController.getListOfCashAccountsOfAFamilyMember(cc, id2, familyOneID);
    
        assertEquals(expectedList, result);
    }
    
**Test 3:** List of Cash Accounts of a Family Member with zero Cash Accounts

    @Test
    void getListOfCashAccountsOfAFamilyMemberListWithZeroCashAccount() {
        List<AccountIDAndDescriptionDTO> expected = new ArrayList<>(); // empty List

        List<AccountIDAndDescriptionDTO> result = checkCashAccountBalanceController.getListOfCashAccountsOfAFamilyMember(cc, id2, familyOneID);

        assertEquals(expected, result);
    }
 
**Test 4:** Actor not an Administrator
 
    @Test
    void getListOfCashAccountsOfAFamilyMemberListWithZeroCashAccountNoAdministrator() {
        List<AccountIDAndDescriptionDTO> expected = new ArrayList<>(); // empty List

        List<AccountIDAndDescriptionDTO> result = checkCashAccountBalanceController.getListOfCashAccountsOfAFamilyMember(id2, cc, familyOneID);

        assertEquals(expected, result);
    } 

### checkFamilyCashAccountBalance( ):

**Test 5:** Check Family Cash Account Success

    @Test
    void checkFamilyCashAccountBalanceFamilyWithCashAccount() {
        CreateFamilyCashAccountController createFamilyCashAccountController = new CreateFamilyCashAccountController(application);
        createFamilyCashAccountController.createFamilyCashAccount(familyOneID, accountDescriptionOne, valueOne, cc);

        MoneyValue expected = new MoneyValue(valueOne, CurrencyEnum.EURO);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyCashAccountBalance(cc, familyOneID);

        assertEquals(expected, result);
    }
    
**Test 6:** Check Family Cash Account Insuccess - Family Without Cash Account  
    
    @Test
    void checkFamilyCashAccountBalanceFamilyWithOutCashAccount() {
        MoneyValue expected = new MoneyValue(-2.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyCashAccountBalance(cc, familyOneID);

        assertEquals(expected, result);
    }    
    
**Test 6:** Check Family Cash Account Insuccess - Actor isn't an Administrator    
    
    @Test
    void checkFamilyCashAccountBalanceFamilyWithCashAccountNoAdministrator() {
        CreateFamilyCashAccountController createFamilyCashAccountController = new CreateFamilyCashAccountController(application);
        createFamilyCashAccountController.createFamilyCashAccount(familyOneID, accountDescriptionOne, valueOne, cc);

        MoneyValue expected = new MoneyValue(-1.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyCashAccountBalance(id2, familyOneID);

        assertEquals(expected, result);
    }  
    
**Test 6:** Check Family Cash Account Insuccess - Family Does not Exist    
    
    @Test
    void checkFamilyCashAccountBalanceFamilyWithCashAccountFamilyDoesNotExist() {
        MoneyValue expected = new MoneyValue(-2.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyCashAccountBalance(cc, familyIDDoesNotExist);

        assertEquals(expected, result);
    }  
    
### checkFamilyMemberCashAccountBalance( ):

**Test 7:** Check Family Cash Account Success

    @Test
    void checkFamilyMemberCashAccountBalance() {
        CreatePersonalCashAccountController createPersonalCashAccountController = new CreatePersonalCashAccountController(application);
        AddCashAccountDTO addCashAccountDTO = new AddCashAccountDTO(valueOne, accountDescriptionOne, id2, familyOneID, currency);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTO);

        MoneyValue expected = new MoneyValue(valueOne, CurrencyEnum.EURO);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyMemberCashAccountBalance(cc, id2, accountIDOne, familyOneID);

        assertEquals(expected, result);
    }
    
**Test 8:** Check Family Cash Account Insuccess - Actor isn't an Administrator

    @Test
    void checkFamilyMemberCashAccountBalanceNoAdministrator() {
        CreatePersonalCashAccountController createPersonalCashAccountController = new CreatePersonalCashAccountController(application);
        AddCashAccountDTO addCashAccountDTO = new AddCashAccountDTO(valueOne, accountDescriptionOne, id2, familyOneID,currency);
        createPersonalCashAccountController.createPersonalCashAccount(addCashAccountDTO);

        MoneyValue expected = new MoneyValue(-1.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyMemberCashAccountBalance(id2, cc, accountIDOne, familyOneID);

        assertEquals(expected, result);
    }
    
**Test 9:** Check Family Cash Account Insuccess - Family Member does not have Cash Account with given ID    

    @Test
    void checkFamilyMemberCashAccountBalanceNoCashAccount() {
        MoneyValue expected = new MoneyValue(-2.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyMemberCashAccountBalance(cc, id2, accountIDOne, familyOneID);

        assertEquals(expected, result);
    }

**Test 10:** Check Family Cash Account Insuccess - Family Member does not exist

    @Test
    void checkFamilyMemberCashAccountBalanceFamilyMemberDoesNotExist() {
        MoneyValue expected = new MoneyValue(-2.00, null);

        MoneyValue result = checkCashAccountBalanceController.checkFamilyMemberCashAccountBalance(cc, familyMemberIDDoesNotExist, accountIDOne, familyOneID);

        assertEquals(expected, result);
    }

## 4. Implementation

**Verification if actor is a administrator in a given family**

    public boolean verifyAdministrator(String ccNumber) {
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.compareID(ccNumber))
                return familyMember.isAdministrator();
        }
        return false;
    }
    
**Instancing of AccountIDAndDescriptionDTO in Cash Account**
    
    public AccountIDAndDescriptionDTO getAccountIDAndDescriptionDTO(){
        AccountIDAndDescriptionDTO accountIDAndDescriptionDTO = new AccountIDAndDescriptionDTO(this.accountData.getAccountID(), accountData.getDescription());
        return accountIDAndDescriptionDTO;
    }
    
**Creation of AccountIDAndDescriptionDTO List**    
    
    private List<AccountIDAndDescriptionDTO> createListOfCashAccounts(List<Account> listOfAccounts) {
        List<AccountIDAndDescriptionDTO> accountIDAndDescriptionDTOS = new ArrayList<>();
        for (Account account : listOfAccounts) {
            if (account.checkAccountType(CASHACCOUNT)) {
                AccountIDAndDescriptionDTO accountIDAndDescriptionDTO = account.getAccountIDAndDescriptionDTO();
                accountIDAndDescriptionDTOS.add(accountIDAndDescriptionDTO);
            }
        }
        return accountIDAndDescriptionDTOS;
    }    
    
**Get Family Cash Account**      
    
    public MoneyValue getFamilyCashAccountBalance(Family family) {
        Account cashAccount = family.getFamilyCashAccount();
        return cashAccount.getMoneyBalance();
    }
  
**Get Family Member Cash Account**   
  
    public MoneyValue getFamilyMemberCashAccountBalance(FamilyMember familyMember, int accountID) {
        Account cashAccount = getAccount(familyMember, accountID);
        if (cashAccount.checkAccountType(CASHACCOUNT)) // verify if is a Cash Account
            return cashAccount.getMoneyBalance();
        else
            throw new IllegalArgumentException("Not a Cash Account");
    }  

# 5. Integration/Demonstration

The development of this US will not have an impact on the development of the others Users Stories of this Sprint.

# 6. Observations

Was not asked to fulfill the US, but, for future use, a feature was developed that returns a list of Cash Accounts of a given Family Member. This list can be used to be presented in a future UI, in which the user will choose the account he wants to consult the balance through the description.