# US173 Add a credit card account

# 1. Requirements

## 1.1. Client Notes

As a family member, I want to add to a credit card account I have:

**Demonstration 1** As a Family Member, I want to...

- 1.1. Add a credit card account I have.

**Extracted from communications with the Product Owner**

- > *"A card does not have to be associated with a bank account."*;
- > *"Balance of a credit card account is the amount due at that moment"*;
- > *"Must have interest (which maybe just a fraction of the total amount in debt due to the fractioning of payments into the future)"*.

We interpreted this requirement as the function of a family member to add a new credit card account to his account
portfolio. The Credit Card Account must have a Balance, which is the value due to the moment, and from that total the
amount that is due in interest.

## 1.2. System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US173

actor "Family Member" as familyMember
participant ": System" as system

activate familyMember
familyMember-> system : add Credit Card Account    
activate system

system -> familyMember : ask data                
deactivate system 

familyMember -> system : inputs required data
activate system

alt failure
system -> familyMember : Inform Failure

else sucess
system -> familyMember : Inform Sucess

end

deactivate system

deactivate familyMember
```

## 1.3. Dependencies from other User Stories

This user story is dependent on the following:

- US010_Add Family: to create a family;
- US011_Add Family Administrator: to add an Administrator, that he is allowed to add a Family Member;
- US101_Add Family Members: to add a Credit Card Account.

# 2. Analysis

For the fulfillment of the raised requirements, we analyze that for the accomplishment of the US we need, at this
moment, the input of the family administrator of the following data:

- Self ID (User who wants to add a credit card account);
- Family ID (User's Family);
- Card description (Card Description);
- Withdrawal limit (Card usage limit);
- Total Debt (Total debt owned at the moment);
- Interest Debt (Fraction of Total Debt, which is due to interest);
- Currency (Currency of the Credit Card).

The account will be stored inside the Family Member. The Family and Family Member IDs will be used to identify the
correct user where to add the account.

## 2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram US173

class Family {
- Name
- UniqueID
- RegistrationDate

}

class FamilyMember {
- Name
- BirthDate
}

class CreditCardAccount {
- balance
- description
}

Family "1" -down-> "1..* " FamilyMember : has Family members
FamilyMember "1" -> "0..* " CreditCardAccount  : has
```

# 3. Design

The process to fulfill the requirement we need the input of data from a UI to create a CreditCardAccount object and add
it to a specific FamilyMember(familyMemberID) in a given Family(familyID). To create a valid CreditCardAccount object
the constructor must acept an id, that is generated in AccountService. A default Card Description is generated if has
been inputed a invalid Card Description (null, empty or blank).

The controller will return:

- True, if a CreditCardAccount as been successfully created and assigned.
- False, if catches on of the following throws ("Family don't exist", "Family Member don't exist")

## 3.1. Functionality Use

``` puml
autonumber 1
title addCreditCardAccount
actor "Actor" as actor
participant ": UI" as UI
participant ": AddCreditCardAccount\n Controller" as controller
participant ": Application" as app
participant "aAccountService : AccountService" as aserv
participant "aFamilyService : FamilyService" as fserv
participant "aFamily : Family" as family
participant "aCreditCardAccount : CreditCard Account" as credit
participant "aFamilyMember : FamilyMember" as familyMember

activate actor
actor -> UI : add a Credit Card Account
activate UI
UI -> actor : ask: familyMemberID, familyID, cardDescription, withdrawLimit
deactivate UI
actor -> UI : inputs required data
activate UI
UI -> controller : addCreditCardAccountToFamilyMember(familyMemberID, familyID, cardDescription, withdrawLimit)

activate controller
controller -> app : getFamilyService()
activate app
app -> controller : aFamilyService
deactivate app

controller -> fserv : getFamily(familyID)
activate fserv
fserv -> controller : aFamily
deactivate fserv

controller -> family : getFamilyMember(familyMemberID)
activate family
family -> controller : aFamilyMember
deactivate family

app -> controller : getAccountService()
activate app
controller -> app : aAccountService
deactivate app


controller -> aserv : addCreditCardAccount(aFamilyMember, cardDescription, withdrawLimit)
activate aserv

aserv -> aserv : id = generateID()

aserv -> credit** : createCreditCardAccount(id, cardDescription, withdrawLimit)

aserv -> familyMember : addAccountToList(aCreditCardAccount)
activate familyMember 
familyMember -> aserv : inform sucess
deactivate familyMember
aserv -> controller : inform sucess
deactivate aserv

controller -> UI : inform sucess
deactivate controller

UI -> actor : inform sucess
deactivate UI
deactivate actor
```

## 3.2. Class Diagram

```puml

title Class Diagram - US173

class AddCreditCardAccountDTO{
  - familyMemberID
  - familyID
  - description
  - withdrawalLimit
  - totalDebt
  - interestDebt
  - currency
}
class AddCreditCardAccountController {
  + addBankAccount()
}

class Application {
  + getFamilyService() 
  + getAccountService()
}

class FamilyService {
  + getFamily()
}

class Family {
  - getFamilyMemberByID() 
}

class FamilyMember {
  # compareID()
  + addBankAccount()
  
}

class AccountService {
  + addBankAccount()
  + createBankAccount()
}
class BankAccount {
  - accountType
  + create()
}

interface Account{
}
class AccountData {
  - balance
  - description
  - accountID
  - creationDate
}
class MoneyValue {
  - value
  - currency
}

AddCreditCardAccountController -> Application: has
AddCreditCardAccountController <-- AddCreditCardAccountDTO: accepts
AddCreditCardAccountController -> FamilyService: calls
FamilyService -> Family: has list
Family --> FamilyMember: has list
AddCreditCardAccountController --> AccountService: calls
AccountService --> BankAccount: creates
BankAccount -|> Account: implements
BankAccount <-- AddCreditCardAccountDTO: accepts
FamilyMember --> BankAccount: adds
BankAccount *-- AccountData: contains
AccountData *-- MoneyValue: contains
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

## 3.4. Tests

### Test 1 : CreditCardAccount tests

#### Test 1.1 : verify constructor

###### Success cases: 

- **1.1.1** If the object is **valid**, a new instance is created

      @Test
      void aValidInstanceOfCreditCardAccount() {
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID,
            cardDescriptionOne, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne); CreditCardAccount
            creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
            assertNotNull(creditCardAccount);
      }
  
- **1.1.2** If the description is **null**, a valid instance of CreditCardAccount is created with a standard name **"Credit Card Account#" + "ID"**

      @Test
      void aValidInstanceOfCreditCardAccountWithNullDescription() {
            String cardDescriptionNull = null;
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionNull, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
            CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
            String expected = "Credit Card Account - Account #1";
            assertNotNull(creditCardAccount);
            assertEquals(creditCardAccount.getDescription(), expected);
      }

- **1.1.3** If the description is **empty**, a valid instance of CreditCardAccount is created with a standard name **"Credit Card Account#" + "ID"**

      @Test
      void aValidInstanceOfCreditCardAccountWithEmptyDescription() {
            String cardDescriptionEmpty = "";
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionEmpty, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
            CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
            String expected = "Credit Card Account - Account #1";
            assertNotNull(creditCardAccount);
            assertEquals(creditCardAccount.getDescription(), expected);
      }

###### Failure cases:

- **1.1.4** If the family does **not exist** no new instance of CreditCardAccount is created
  
      @Test
      void addCreditCardAccountToFamilyMemberFalseFamilyDoesNotExist() {
          AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, 2, "MasterCard do Diogo", 5000.00, 100.00, 50.00, CurrencyEnum.EURO);
          assertFalse(addCreditCardAccountController.addCreditCardAccountToFamilyMember(addCreditCardAccountDTO));
      }

##### Test 1.2 : debit and credit 

###### Success cases: 

- **1.2.1** Credit

    @Test
    void creditSuccess() {
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
        CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
        MoneyValue balanceChange = new MoneyValue(50.0, CurrencyEnum.EURO);

        creditCardAccount.credit(balanceChange);

        Double expected = 50.00;

        Double result = creditCardAccount.getMoneyBalance().getValue();

        assertEquals(expected, result);
    }
    
    
- **1.2.2** Debit

    @Test
    void debitSuccess() {
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
        CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
        MoneyValue balanceChange = new MoneyValue(1.0, CurrencyEnum.EURO);
        creditCardAccount.debit(balanceChange);
        MoneyValue expected = new MoneyValue(101.0, CurrencyEnum.EURO);
        MoneyValue result = creditCardAccount.getMoneyBalance();
        assertEquals(expected, result);
    }

###### Failure cases: 
  
- **1.2.2** If the balance is **equal or greater than** the withdrawal limit an exception is thrown

    @Test
    void debitFailure() {
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
        CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
        MoneyValue balanceChange = new MoneyValue(1000000.0, CurrencyEnum.EURO);

        assertThrows(IllegalArgumentException.class, () -> {
            creditCardAccount.debit(balanceChange);
        });
    }

- **1.2.3** Balance must be Zero or greater

    @Test
    void creditInsuccess() {
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, withdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
        CreditCardAccount creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, idOne);
        MoneyValue balanceChange = new MoneyValue(100.1, CurrencyEnum.EURO);

        assertThrows(IllegalArgumentException.class, () -> creditCardAccount.credit(balanceChange));
    }

##### Test 1.3 : verify withdrawal limit

###### Failure cases:

- **1.3.1** If the withdrawal limit is **< 0** an exception is thrown

      @Test
      void assertThrowWithdrawLimitInvalidLessThanZero() {
        double invalidWithdrawLimit = -1;
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, invalidWithdrawLimit, totalDebtOne, interestDebtOne, currencyEnumOne);
        assertThrows(Exception.class, () -> new CreditCardAccount(addCreditCardAccountDTO, idOne));
      }

- **1.3.2** If the withdrawal limit is **null** an exception is thrown

      @Test
      void creditCardAccountNullWithdrawalLimit() {
            Double nullWithdrawlLimitOne = null;
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(familyMemberID, familyID, cardDescriptionOne, nullWithdrawlLimitOne, totalDebtOne, interestDebtOne, currencyEnumOne);
            assertThrows(IllegalArgumentException.class, () -> new CreditCardAccount(addCreditCardAccountDTO, idOne));
      }

#### Test 2 : AccountService tests

###### Success cases:

- **2.1** The AccountService adds the account to the FamilyMember accounts list and returns **true**

      @Test
      void createPersonalCreditCardAccountTrue() {
            FamilyMember familyMember = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, family1ID, "Visa do Diogo", 5000.00, 100.00, 50.00, CurrencyEnum.EURO);
            assertTrue(accountService.createPersonalCreditCardAccount(addCreditCardAccountDTO, familyMember));
      }

##### Failure cases:

- **2.2** The AccountService does not add the account to the FamilyMember accounts

      @Test
      void createPersonalCreditCardAccountAssertThrowInvalidWithrawalLimit() {       
            FamilyMember familyMember = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, family1ID, "Conta da Maria", -100.00, 100.00, 50.00, CurrencyEnum.EURO);
            assertThrows(IllegalArgumentException.class, () -> accountService.createPersonalCreditCardAccount(addCreditCardAccountDTO, familyMember));
      }

#### Test 3 : Controller tests

###### Success cases: 

- **3.1** If **all** the required data is valid, a Credit Card Account is added to the Family Member                    
 
        @Test
        void addCreditCardAccountToFamilyMemberTrue() {
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, family1ID, "Visa do Diogo", 5000.00, 100.00, 50.00, CurrencyEnum.EURO);
            assertTrue(addCreditCardAccountController.addCreditCardAccountToFamilyMember(addCreditCardAccountDTO)); 
        }

##### Failure cases: 

- **3.2** If the **Family doesn't exist**, the controller catches the exception and returns **false**

      @Test
      void addCreditCardAccountToFamilyMemberFalseFamilyDoesNotExist() {
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, 2, "MasterCard do Diogo", 5000.00, 100.00, 50.00, CurrencyEnum.EURO);
            assertFalse(addCreditCardAccountController.addCreditCardAccountToFamilyMember(addCreditCardAccountDTO));
      }

- **3.3** If the **FamilyMember doesn't exist**, the controller catches the exception and returns **false**

      @Test
      void addCreditCardAccountToFamilyMemberFalseFamilyMemberDoesNotExist() {
            AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO("3", family1ID, "MasterCard do Diogo", 5000.00, 100.00, 50.00, CurrencyEnum.EURO);
            assertFalse(addCreditCardAccountController.addCreditCardAccountToFamilyMember(addCreditCardAccountDTO));
      }

## 4. Implementation

#### 4.1.Add a Credit Card Account to a Family Member

The method in the AccountService will generate an unique ID and associate it with the given FamilyMember. Then it will
instantiate a new CreditCardAccount Object and add it to the target FamilyMember.

      public boolean createPersonalCreditCardAccount(AddCreditCardAccountDTO addCreditCardAccountDTO,FamilyMember targetMember) {
            int accountID = generateID(targetMember);
            Account creditCardAccount = new CreditCardAccount(addCreditCardAccountDTO, accountID);
            targetMember.addAccount(creditCardAccount);
            return true;
      }

#### 4.2.Generate a new Credit Card Account

The Credit Card Account Class will have three crucial methods, the first two refer to
attribute validation of withdrawal limit and  interestRate so that these attributes are
valid according to the business rules.
The constructor will apply such validations when instantiating
a new object of such type.

      public CreditCardAccount(AddCreditCardAccountDTO addCreditCardAccountDTO, int accountID) {
            validateWithrawalLimit(addCreditCardAccountDTO.getWithdrawalLimit());
            try {
            this.accountData = new AccountData(addCreditCardAccountDTO.getTotalDebt(), addCreditCardAccountDTO.getCardDescription(), accountID, addCreditCardAccountDTO.getCurrency());
            } catch (InvalidAccountDesignationException exception) {
            String cardDescriptionDefault = "Credit Card Account " + "-" + " Account #" + accountID;
            this.accountData = new AccountData(addCreditCardAccountDTO.getTotalDebt(), cardDescriptionDefault, accountID, addCreditCardAccountDTO.getCurrency());
            }

            this.withdrawalLimit = new MoneyValue(addCreditCardAccountDTO.getWithdrawalLimit(), addCreditCardAccountDTO.getCurrency());

            if (validateInterestDebt(addCreditCardAccountDTO.getInterestDebt())) {
                  validateTotalDebt(addCreditCardAccountDTO.getTotalDebt());
                  interestDebtLessThanTotalDebt(addCreditCardAccountDTO.getInterestDebt(), addCreditCardAccountDTO.getTotalDebt());
            
                  this.interestDebt = new MoneyValue(addCreditCardAccountDTO.getInterestDebt(), addCreditCardAccountDTO.getCurrency());
            } else {
                  this.interestDebt = new MoneyValue(0.00, addCreditCardAccountDTO.getCurrency());
            }
      }

# 5. Integration/Demonstration

The development of this US will have an impact on the development of the US185 (Get Account Balance), as it was
returning the balance of an Account of a Family Member. So, the [US185](US185_GetAccountBalance.md) uses the
implementation of this US.

# 6. Observations

In the future we're thinking about implementing a forecast feature to calculate expected interest debt based on the past
data.

Also in the future, the two issues we have to deal with are the following:

- The familyID and FamilyMemberID will be solved when the UI and login layer are set up,
- The IBAN will be implemented when the App requires it.
