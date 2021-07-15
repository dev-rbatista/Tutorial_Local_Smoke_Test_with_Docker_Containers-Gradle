# US181 Add a credit card account

# 1. Requirements

## 1.1. Client Notes

- As a family member, I want to register a payment that I have made using one of my cash accounts.

We interpreted this requirement as the function of a familyMember to register a payment in one of his CashAccounts and generating a transaction.



## 1.2. System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US181

actor "Family Member" as familyMember
participant ": System" as system

activate familyMember
familyMember-> system : register Payment on 1 of my Cash Accounts
activate system
system -> familyMember : asks required data
familyMember -> system : inputs required data

alt failure
system -> familyMember : Inform Failure

else sucess
system -> familyMember : Inform Sucess

end

deactivate system

deactivate familyMember
```

## 1.3. Dependencies from other User Stories

This user story has a dependency with these **3** user stories:

- **[US010](US101_AddFamily.md)** *(As a system manager, I want to create a family.)*
    - In order to have a FamilyMember, the system needs to have that Family.

- **[US011](US101_AddFamilyAdministrator.md)** *(As a system manager, I want to add a familyAdministrator to a family)*
  - In order to have a FamilyMember, the system needs to have that FamilyAdministrator.  

- **[US101](US101_AddFamilyMember.md)** *(As a family Administrator, I want to add a familyMember to a family)*
    - In order to create a Cash account, the system needs to have that Family Member.
  
- **[US170](US170_CreatePersonalCashAccount.md)** *(As a family member, I want to create a personal cash account)*
    - In order to register a payment in a Cash account, the system needs to have a Cash account.

# 2. Analysis

In order to fulfill this requirement, we need 6 main data pieces that need to be inserted by the User:
- accountID
- Amount  
- currency
- categoryID 
- Designation
- transactionDate

While these 2 data pieces are already given by the app.
- familyID
- familyMemberCC


##2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram US173

class Family {
- FamilyID
}

class FamilyMember {
- selfCC
}

class Account {
- AccountID 
- AccountType
}

class Transaction {
- transactionDate
- ammount
- category
- designation
- remainingBalance
- currency
- recipient
}


Family "1" -down-> "0..*" FamilyMember : has Family Members
FamilyMember "1" -down-> "0..*" Account  : has List of 
Account "1" -> "0..*" Transaction : has a List of
```

# 3. Design

````puml
@startuml
autonumber
title US181 - Register Payment In Cash Account Sequece Diagram

actor "FamilyMember" as actor
participant ": UI" as UI
participant ": addBankAccountController" as controller
participant ": FFM Application" as app
participant "famServ : FamilyService" as famService
participant "catServ : CategoryService" as catService
participant "accServ : AccountService" as accService
participant "aFamilyMember : FamilyMember" as familyMember

activate actor
actor -> UI: registerPaymentInCashAccount(familyID,selfCC,accountID,paymentDate,ammount,categoryID)
activate UI
UI -> controller: registerPaymentInCashAccount(transactionDTO)
activate controller
controller -> app: getCashAccount(familyID,selfCC,accountID)
activate app
app -> famService: getCashAccount(familyID,selfCC,accountID)
activate famService
famService -> familyMember: getCashAccount(familyID,selfCC,accountID)
activate familyMember
familyMember -> familyMember : getAccount(accountID)
familyMember -> famService: ok
deactivate familyMember
famService -> app: ok
deactivate famService
app -> controller: ok
deactivate app

controller -> app : getCategory(categoryID)
activate app
app -> catService : getCategory(categoryID)
activate catService
catService -> catService : getCategory(categoryID)
catService -> app : getCategory(categoryID)
deactivate catService
app -> controller : getCategory(categoryID)
deactivate app

controller -> app : verifyCashAccount(Account)
activate app
app -> accService : verifyCashAccount(Account)
activate accService
accService -> accService : verifyAccountType(Account,CashAccount)
alt Account is not CashAccount - FALSE
  accService -> app : fail
  app -> controller : fail
else Account is a CashAccount - TRUE
  accService -> app : ok
  deactivate accService
  app -> controller : ok
  deactivate app
end

controller -> app: registerPaymentInCashAccount(cashAccount,category,transactionDTO)
activate app
app -> : registerPaymentInCashAccount(cashAccount,category,transactionDTO)
app <- : ok
app -> controller: ok
deactivate app
controller -> UI: ok
deactivate controller
UI -> actor: succeed
deactivate UI
deactivate actor

@enduml
````


````puml
@startuml
autonumber
title registerPaymentMyCashAccount

participant "tranServ : TransactionService" as tranService
participant "aCashTran : CashTransaction" as cashTrans

-> tranService: registerPaymentInCashAccount(cashAccount,category,transactionDTO)
activate tranService
tranService -> tranService: hasEnoughMoneyForTransaction()
alt Balance is not enough - FALSE
  tranService -> tranService: fail
else Balance is enough - TRUE
  tranService -> cashTrans **: createCashTransaction(cashAccount,category,transactionDTO)
  tranService -> tranService: addTransaction(aCashTransaction)
end
<- tranService : ok

@enduml
````

## 3.1. Functionality Use

In order to register a payment the user will have to input data in the UI to create a transaction object and store it in the his Account.
With the FamilyID and FamilyMemberID provided by the UI, the User will be able to select one of his CashAccounts for the transaction be stored. 
When the required data is introduced, the App verifies if the CashAccount has enough balance, and the amount is positive to meet the criteria for a transaction be created.
If any data introduced has a wrong format, the App warns about the problem.

The controller will return:
- True, if the payment registration has been successfully created.
- False, if catches on of the following throws ("Not enough balance", "Insert a positive value")


## 3.2. Class Diagram

```puml

title Class Diagram - US181

class RegisterPaymentInCashAccount {
- ffmApplication
}

class Application {
getFamilyService()
getCategoryService()
getAccountService()
getTransactionService()
}

class FamilyService {
getFamily()
}

class Family {
getFamilyMember()
getCustomCategoryByID()
}
   
class FamilyMember {
}

class CategoryService {
getStandardCategoryByID()
}

class AccountService {
verifyAccountType()
}

class TransactionService {
registerPaymentMyCashAccount()
}

class CashAccount{
hasEnoughMoneyForTransaction()
registerTransaction()
debit()
getMoneyBalance()
}

class Transaction {
transactionDate
registrationDate
ammount
category
designation
remainingBalance
credit
}

class CashTransaction {
- transactionData
- otherAccount
}

RegisterPaymentInCashAccount  -> Application
RegisterPaymentInCashAccount  -down-> FamilyService
RegisterPaymentInCashAccount  -down-> TransactionService
RegisterPaymentInCashAccount --> CategoryService
RegisterPaymentInCashAccount --> AccountService
TransactionService -down-> CashTransaction : create a
FamilyService --> Family : has List<Families>
Family --> FamilyMember : has List<FamilyMember>
FamilyMember --> CashAccount : has List<Account>
CashAccount --> Account : is a
Account -left-> Transaction : has List<Transaction>
CashTransaction -down-> Transaction : is a
```

## 3.3. Applied Patterns
We applied the principles of Controller, Information Expert, Creator e PureFabrication from the GRASP pattern.
We also used the SOLID SRP principle.

## 3.4. Tests

###Test 1: CashTransaction tests

####Test 1.1: Constructor

- **3.4.1.1** - All verification are made by other classes so when this constructor is called, it gets executed successfully.
```
@Test
void cashTransactionConstructorTest_debit() {
    boolean credit = false;
    CashTransaction cashTransaction = new CashTransaction(cashAccount, category, credit, remainingBalance, dto);
    Assertions.assertNotNull(cashTransaction);
}
```


###Test 2: AccountService tests

####Test 2.1: Verify Account type

- **3.4.2.1.1** - If the account is **BankAccount**, the verification is **false** 

- **3.4.2.1.2** - If the account is **BankSavingAccount**, the verification is **false**

- **3.4.2.1.3** - If the account is **CreditCardAccount**, the verification is **false**

- **3.4.2.1.4** - If the account is **CashAccount**, the verification is **true**


###Test 3: TransactionService tests

####Test 3.1: Verify if the Account has enough money

- **3.4.3.1** - If the account doesn't have **enough money**, it **throws an error**.
```
@Test
void NoRegisterPaymentMyCashAccount_NotEnoughMoney() {
    TransactionService service = new TransactionService();
    assertThrows(IllegalArgumentException.class, () -> {
        service.registerPaymentMyCashAccount(contaCash, categoria1, transacaoDTO2);
    });
}
```
####Test 3.2: Verify a positive amount

- **3.4.3.2** - If the amount inserted has a **negative value**, it **throws an error**.
```
@Test
void NoRegisterPaymentMyCashAccount_NegativeAmmount() {
    TransactionService service = new TransactionService();
    assertThrows(IllegalArgumentException.class, () -> {
        service.registerPaymentMyCashAccount(contaCash, categoria1, transacaoDTO3);
    });
}
```


####Test 3.3: Verify same Currency

- **3.4.3.3** - If the inserted amount is not from the same **currency type** of the account, it **throws an error**.
```
@Test
void NoRegisterPaymentMyCashAccount_DifferentMoneyValue() {
    TransactionService service = new TransactionService();
    assertThrows(IllegalArgumentException.class,()->{
        service.registerPaymentMyCashAccount(contaCash, categoria1, transacaoDTO4);
    });
}
```


####Test 3.4: Successful Transaction

- **3.4.3.4** If all data meets the criteria, the transaction is registed.
```
@Test
void RegisterPaymentMyCashAccount_SameMoneyValue() {
    TransactionService service = new TransactionService();
    boolean successTransaction = service.registerPaymentMyCashAccount(contaCash, categoria1, transacaoDTO1);
    MoneyValue expected = new MoneyValue(250.00, CurrencyEnum.EURO);
    MoneyValue result = contaCash.getMoneyBalance();
    assertEquals(expected, result);
    assertTrue(successTransaction);
}
```

###Test 4: Controller tests

####Test 4.1: Controller fails
- **3.4.4.1** - If any **previous validation fails**, the controller doesn't get executed and returns **false**.
```
@Test
void NotRegisterPaymentMyCashAccount_NotEnoughBalance() {
    Application ffmApp = new Application();
    RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
    // FamilyService
    FamilyService familyService = ffmApp.getFamilyService();
    Family family = new Family("Ribeiros",familyID);
    familyService.addFamily(family);
    FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
    family.addFamilyMember(zeManel);
    zeManel.addAccount(contaCash);
    assertFalse(controller.registerPaymentMyCashAccount(transacaoDTO3));
}
```

####Test 4.1: Controller success
- **3.4.4.2** - If all **previous validation succeed**, the controller gets executed and returns **true**.

```
@Test
void registerPaymentMyCashAccount_SameMoneyValue() {
    Application ffmApp = new Application();
    RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
    // FamilyService
    FamilyService familyService = ffmApp.getFamilyService();
    Family family = new Family("Ribeiros",familyID);
    familyService.addFamily(family);
    FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
    family.addFamilyMember(zeManel);
    zeManel.addAccount(contaCash);
    boolean successTransaction = controller.registerPaymentMyCashAccount(transacaoDTO1);
    MoneyValue expected = new MoneyValue(250.00,CurrencyEnum.EURO);
    MoneyValue result = contaCash.getMoneyBalance();
    assertEquals(expected,result);
    assertTrue(successTransaction);
}
```

# 4. Implementation

Once again the FamilyID and FamilyMemberID will also be automatically assigned by the UI.

Some validation that were created will not have any meaning when the UI gets implemented. That will go for the Account Type validation.
Both category and currency will be shown in the future UI with a dropdown too, so it's easier for the user and avoids any kind of mistake and therefore validation.

# 5. Integration/Demonstration

As it was said before, this UserStory dependes on both **[US010 - AddFamily]**, **[US011 - AddFamilyAdministrator]**, **[US101 - AddFamilyMember]** and **[US170 - CreatePersonalCashAccount]**.

# 6. Observations

In the future, the two issues we have to deal with are the following:
- The familyID and FamilyMemberID will be solved when the UI and login layer are set up,
- The Account list that will be presented to the user will be already filtered and so will be only displayed the CashAccounts