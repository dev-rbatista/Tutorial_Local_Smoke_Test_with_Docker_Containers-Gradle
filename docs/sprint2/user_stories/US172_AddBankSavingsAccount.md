# US172 Add a Bank Savings Account
=======================================

# 1. Requirements

### 1.1 Client Notes

**Demo1** - As a family member, I want to add a bank savings account I have.

We interpreted this requirement as the function of adding a Bank Savings Account Data and linking it to a Family Member.

### 1.2 System Sequence Diagram

````puml
autonumber

skinparam sequence {
ArrowColor black
LifeLineBorderColor black
LifeLineBackgroundColor white
ParticipantBorderColor black
ParticipantBackgroundColor white
ParticipantFontColor black
ActorBorderColor black
ActorBackgroundColor white
}

title AddBankSavingsAccount
actor "Family Member" as FamilyMember
participant "System" as System

activate FamilyMember
FamilyMember -> System: addBankSavingsAccount
activate System
System --> FamilyMember: request data
FamilyMember -> System: input data
alt failure
System --> FamilyMember: inform failure
else success
System --> FamilyMember: inform success
deactivate System
end alt
deactivate FamilyMember

````

# 2. Analysis

Our analysis of this US is as follows:

To create a Bank Savings Account we need to have:

1. An account name
   > *Question:*  
   > *Pegando numa resposta anterior em relação à criação de contas (sejam cash, bank, savings ou credit card),
   > devemos incluir uma designação (exemplo: "Conta do Banco") para que o utilizador possa personalizar as contas.*
   >
   > PO:  
   > Claro que tem de ter uma designação compreensível.

2. An account ID  
   An unique account ID is going to be necessary in order to differentiate accounts. Product Owner said specifically to
   not use the IBAN as an unique identifier:
   > PO:   
   >Para começar, não peçam aos utilizadores para conhecerem/identificarem as contas pelo IBAN ou pelo número do cartão.  
   > Está completamente fora de questão.  
   >A forma como identificam as contas no código é um problema de implementação. Pensem numa solução.

3. An Interest rate  
   We've decided to add this attribute in order to distinguish bewteen account types. In the future it will allow the
   user to forecast future earnings, review earnings to-date, etc..


4. A Family Member  
   The user to whom the account will be added. At the moment there is no business rule, limiting the number of Family
   Members linked to the same account.

It is expected of a Savings Account to have a balance. At the moment, the Product Owner referred that defining the
balance it would be a future feature regarding bank data importation. Having this in mind we decided to initialize all
accounts with 0 as the initial balance.

# 3. Design

This functionality allows the actor to add a Bank Savings Account to the given Family Member profile.

Since we don't have an existing UI at the moment, all the necessary data will have to be manually inserted by the user.

The actor will need to insert the following data: familyID, familyMemberID, balance, accountName and interestRate.

````puml
autonumber


title AddBankSavingsAccount
actor "Family Member" as FamilyMember
participant "UI" as UI
participant ": AddSavings\nAccountController" as Controller
participant ": Application" as App
participant ": FamilyService" as FamilyService
participant "aFamily : \nFamily" as Family
participant "aFamilyMember : \nFamilyMember" as aFamilyMember
participant ": AccountService" as AccountService
participant "aBankSavingsAccount : \nBankSavingsAccount" as BankAccount

activate FamilyMember
FamilyMember -> UI: I want to add a \nBank Savings Account
activate UI
UI -> Controller : addBankSavingsAccount(familyID, \nfamilyMemberID, balance, \nname, interestRate)
activate Controller
Controller -> App : getFamilyService()
activate App
App --> Controller : familyService
deactivate App
Controller -> FamilyService : getFamily(familyID)
activate FamilyService
FamilyService -> FamilyService : getFamily(familyID)
FamilyService --> Controller : Family
deactivate FamilyService
Controller -> Family : getFamilyMember\n(familyMemberID)
Activate Family
Family -> Family : getFamilyMember\n(familyMemberID)
Family --> Controller : aFamilyMember
Deactivate Family
Controller -> AccountService ** : createAccountService()
Controller -> AccountService : addBankSavingsAccount(balance, \nname, InterestRate, aFamilyMember)
activate AccountService
AccountService -> AccountService : generateID(aFamilyMember)
AccountService -> BankAccount ** : createBankSavingsAccount\n(balance, name, InterestRate)
ref over AccountService
AddBankSavingsAccount 2
end ref
AccountService -> aFamilyMember : addAccount\n(aBankSavingsAccount) 
activate aFamilyMember
aFamilyMember --> AccountService : Success
deactivate aFamilyMember
AccountService --> Controller : Success
deactivate AccountService
Controller --> UI : Success
deactivate Controller
UI --> FamilyMember : Success
deactivate UI
deactivate FamilyMember
````

````puml
autonumber

title AddBankSavingsAccount 2

participant ": AccountService" as accountservice
participant "aBankSavingsAccount: \nBankSavingsAccount" as account
participant "anAccountData : \nAccountData" as data
participant "aFamilyMember : \nFamilyMember" as FamilyMember


-> accountservice : addBankSavingsAccount(balance, name, \ninterestRate, aFamilyMember)
activate accountservice
accountservice -> accountservice : generateID(aFamilyMember)
accountservice -> account ** : BankSavingsAccount(balance, name, \ninterestRate, aFamilyMember, accountID)
activate account

account -> account: validateBalance(balance)
alt failure : balance is null, empty or blank
account -> account : balance = 0
else success : balance = balance
end

account -> data ** : AccountData(name, balance, \naccountID)
activate data
data -> data : validateName(name)
alt failure : Name is null, empty or blank
data -> data : name = "Bank Savings Account"
else success : Name = Name
end

data -> account : anAccountData


note left : anAccountData is aBankSavingsAccount attribute
deactivate data
account -> account : validateInterestRate
alt failure : interestRate is null, empty or blank
account -> account : interestRate = 0
else success : interestRate = interestRate
end
accountservice -> FamilyMember : addAccount (aBankSavingsAccount)
activate FamilyMember
FamilyMember --> accountservice : Success
deactivate account
deactivate FamilyMember
<-- accountservice : Success
````

## 3.1. Functionality Use

The Controller will receive the input from the Family Member and with that information will invoke Application (which
has the Family Service), and will obtain Family Service.

Family Service will provide the family associated with the given ID (familyID). Afterwards it will look for the given
Family Member stored in such Family.

The next step is going to be the creation of the Account Service. This Account Service will only exist during the scope
of the functionality as it holds no relevant data.

Then, the Controller will invoke the addBankSavingsAccount method from the Account Service. This will generate an unique
accountID associated with the previously obtained FamilyMember. It will use the former inserted data to create a Bank
Savings Account object.

The constructor will validate the data and if the data comes in an invalid state the constructor will use default values
in order to make it valid. It will start by validating the Balance, then uses the data to create an Account Data object,
which in turn will validate the name. If the name is invalid th Bank Savings Account constructor will catch the
exception and provide a default valid name.

Finally, it will validate the interestRate in the same way as it validates the name.

It will return sucess back to the Account Service and in turn, to the Controller and in the future to the UI and the
user.

## 3.2. Class Diagram

```puml



title Class Diagram
hide empty members

class AddBankSavingsAccountController {
+ addBankSavingsAccount()
}

class BankSavingsAccount {
- double interestRate
}

class Application {
+ getFamilyService()
}

class FamilyService {
+ getFamily()
}

class Family {
+ getFamilyMember()
}

class FamilyMember {
+ addAccount()
}

class AccountService {
+ addBankSavingsAccount()
}

class AccountData {
- double balance
- String description
- int accountID
- List<Transaction> transactions
}


interface Account {}

AddBankSavingsAccountController --> Application : has
Application --> FamilyService : has
AddBankSavingsAccountController --> AccountService : creates
FamilyService --> Family : has list
Family --> FamilyMember : has list
AccountService --> BankSavingsAccount : creates
BankSavingsAccount --|> Account : implements
BankSavingsAccount -* AccountData : contains
BankSavingsAccount <-- FamilyMember : has


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

**Test 1:** Controller: Account creation does not fail with null balance and interest rate or blank account name.

    @Test
    public void checkIfBankSavingsAccountAddedNullInput() {
        String accountName = " ";
        Double balance = null;
        Double interestRate = null;

        testFamily.addFamilyMember(familyMember1);
        assertTrue(controller.addBankSavingsAccount(testFamilyID, cc, accountName, balance, interestRate));
    }

**Test 2:** Controller: Account creation will only fail if member or family do not exist

    @Test
    public void checkIfThrowsWhenNoSuchFamilyID() {
        testFamily.addFamilyMember(familyMember3);
        assertFalse(controller.addBankSavingsAccount(11, "110142608ZZ0", "Savings 3", 1.00,5.21));
    }

    @Test
    public void checkIfThrowsWhenNoSuchMemberID() {
    assertFalse(controller.addBankSavingsAccount(10, "110142608ZZ1", "Savings 3", 1.00,5.21));
    }

**Test 3:** BankSavingsAccount: Account creation will always be successful, null or empty parameters will be handled by
the constructor

    @Test
    void ConstructorSuccessBlankNameNullBalanceAndInterestRate() {
        int accountID = 1;
        String name = " ";
        Double balance = null;
        Double interestRate = null;

        BankSavingsAccount accountOne = new BankSavingsAccount(accountID, name , balance, interestRate);
        BankSavingsAccount accountTwo = new BankSavingsAccount(accountID, name , balance, interestRate);

        assertNotNull(accountOne);
        assertEquals(accountOne, accountTwo);
        assertNotSame(accountOne, accountTwo);
    }

# 4. Implementation

### Add a Bank Savings Account to a Family Member

The method in the AccountService will generate an unique ID and associate it with the given FamilyMember. Then it will
instantiate a new bankSavingsAccount Object and add it to the target FamilyMember.

    public boolean addBankSavingsAccount(FamilyMember targetMember, String accountName, Double balance, Double interestRate) {
    int accountID = generateID(targetMember);
    Account bankSavingsAccount = new BankSavingsAccount(accountID, accountName, balance, interestRate);
    targetMember.addAccount(bankSavingsAccount);
    return true;
    }

### Create a Bank Savings Account

The Bank Savings Account Class will have three core methods.
The first two validate attributes balance and interestRate in order
to guarantee they are always valid according to business rules.
The constructor will apply such validations when instantiating
a new object of such type.

    private boolean validateBalance(Double balance) {
    boolean valid = true;
    if (balance == null) {
    valid = false;
    }
    return valid;
    }

    private boolean validateInterestRate(Double interestRate) {
        boolean valid = true;
        if (interestRate == null) {
            valid = false;
        }
        return valid;
    }

    public BankSavingsAccount(int accountID, String name, Double balance, Double interestRate) {
    try {
    if (!validateBalance(balance)) {
    balance = 0.00;
    }
    this.accountData = new AccountData(balance, name, accountID);
    } catch (InvalidAccountDesignationException exception) {
    String defaultDesignation = "Bank Savings Account with ID" + " " + accountID;
    this.accountData = new AccountData(balance, defaultDesignation, accountID);
    }
        if (!validateInterestRate(interestRate)) {
            interestRate = 0.00;
        }
        this.interestRate = interestRate;
    }

# 5. Integration

EDIT EDIT EDIT EDIT EDIT EDIT EDIT


# 6. Observations

Interest Rate will have to be manually inserted as a percentage in order to perform the correct calculations. UI will
have to deal with this.

Will Interest Rate be a Class in the future? (As it will have behaviour of its own)

In the future we're thinking about implementing a forecast feature to calculate expected earnings on a given date.

Currently we allow users to select the same name for different accounts they own. With the implementation of the UI this
could be changed.

At the moment adding a Bank Savings Account will always be successful if the Family and the FamilyMember exist. However,
account service methods will still return a boolean as in the future there can be situations where account creation will
not be possible.