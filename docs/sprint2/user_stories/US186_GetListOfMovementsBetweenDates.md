# US186 Get List of Movements Between Dates
=======================================


# 1. Requirements

## 1.1. Client Notes

As a family member, I want to get the list of movements on one of my accounts between two dates.


The interpretation made of this requirement is that a Family Member can check the list of movements between two given dates in any of his accounts.

## 1.2 System Sequence Diagram

```puml
autonumber
title System Sequence Diagram - US011

actor "Family Member" as member
participant "System" as system

activate member
member -> system : choose account
activate system
member -> system : select dates

system --> member : return list

deactivate system
```

# 2. Analysis

Our analysis of this US is as follows:

To create a list of movements between a given date we need to have:

1. The ID of the target Account
2. The ID of the FamilyMember who owns the Account
3. The ID of the Family tho which the FamilyMember belongs
4. The first and last date

In turn, every movement in the list should have a description, a category, date and time, amount of money moved and remaining balance, as per this interaction with the PO:

> Question:  
> Relativamente à US186 (As a family member, I want to get the list of movements on one of my accounts between two dates), gostaríamos de saber se a informação abaixo se enquadra no que o professor quer obter juntamente com a lista de movimentos e se há mais alguma informação que gostaria que incluíssemos.
> - Descrição de cada movimento
> - Categoria de cada movimento
> - Data e hora de cada movimento
> - Quantidade de dinheiro envolvida
> - Saldo da conta após cada movimento
> 
> PO:  
> Parece-me bem.

This functionality will only fail if the Family, FamilyMember or Account do not exist. If the account does exist, a list
should always be returned. If the account does not have any movements registered, an empty list will be returned. If the
account does not have any movements between given dates registered, an empty list will be returned.

## 2.1 Domain Model Diagram

````puml
hide empty members
hide circle
title Domain Model Diagram

class Account {
- accountID
- name
- balance
}

class Transaction {
- description
- category
- date
- amount
- remainingBalance
}

Account "1" -down- "0..* " Transaction : has list of >
````

# 3. Design

This functionality allows the actor to access a list to see all movements in a given account between two dates.

Since we don't have an existing UI at the moment, all the necessary data will have to be manually inserted by the user.

The actor will need to insert the following data: familyID, familyMemberID, accountID, startDate and endDate

````puml
autonumber

title GetListOfMovementsBetweenDates(getAccount)
actor "Family Member" as FamilyMember
participant "UI" as UI
participant ": GetListOfMovements\nBetweenDatesController" as Controller
participant ": Application" as App
participant ": FamilyService" as FamilyService
participant "aFamily \n: Family" as Family
participant ": AccountService" as AccountService
participant "aFamilyMember \n: FamilyMember" as aFamilyMember

activate FamilyMember
FamilyMember -> UI: I want to add a \nBank Savings Account
activate UI
UI -> Controller : getListOfMovementsBetweenDates\n(familyID, familyMemberID, accountID, \nstartDate, endDate)
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

Controller -> App : getAccountService()
activate App
App --> Controller : AccountService
deactivate App

Controller -> AccountService : getAccount(aFamilyMember, accountID)
activate AccountService

AccountService -> aFamilyMember : getAccount(accountID)
activate aFamilyMember
aFamilyMember -> aFamilyMember : getAccount\n(accountID)
aFamilyMember --> AccountService : anAccount
deactivate aFamilyMember

AccountService --> Controller : anAccount
deactivate AccountService

ref over Controller
GetListOfMovements
BetweenDates(getList)

end ref

Controller --> UI : ListOfMovements\nBetweenDates
deactivate Controller
UI --> FamilyMember : Display list
deactivate UI
deactivate FamilyMember
````

````puml
autonumber

title GetListOfMovementsBetweenDates(getList)

participant ": GetListOfMovements\nBetweenDatesController" as Controller
participant ": Application" as App
participant ": TransactionService" as TransactionService
participant "anAccount\n: Account" as Account
participant "aTransaction\n: Transaction" as Transaction
participant "aTransactionDataDTO\n: TransactionDataDTO" as TransactionDataDTO

activate Controller
Controller -> App : getTransactionService()
activate App
App --> Controller : TransactionService
deactivate App

Controller -> TransactionService : createListOfMovementsBetweenDates\n(anAccount, startDate, endDate)
activate TransactionService

TransactionService -> Account : getListOfMovements()
activate Account
Account --> TransactionService : aListOfMovements
deactivate Account

loop for every movement in aListOfMovements
TransactionService -> Transaction : isMovementBetweenDates\n(startDate, endDate)
activate Transaction
alt false
Transaction --> TransactionService : false
else true
Transaction --> TransactionService : true

TransactionService -> Transaction : createTransactionDataDTO()
Transaction -> TransactionDataDTO ** : create
Transaction --> TransactionService : aTransactionDataDTO

deactivate Transaction

TransactionService --> TransactionService : addTransactionDataDTOToList\n(aTransactionDataDTO)
end
end

TransactionService --> Controller : ListOfMovementsBetweenDates
deactivate TransactionService
````

## 3.1. Functionality Use

The controller will initiate a sequence to acquire the Account with the given accountID. First he will access the
FamilyService, where he will obtain the Family with the given ID. Then he will access the said Family to obtain the
FamilyMember with the givenID. 

The AccountService will be obtained from the Application. This AccountService will in turn get the Account object to be
consulted.

Then a TransactionService will be obtained from the Application. Like the AccountService, this TransactionService will 
only exist during the scope of the functionality as it holds no relevant data.

Then, the Controller will invoke the createListOfMovementsBetweenDates method from the TransactionService. This method
will first get a ListOfMovements from the Account. For every movement registered in this List, the method will first 
check if they occurred between the given dates, then create a DTO with the movement data and finally add said DTO to 
a list.

This List will be returned to the Controller.

## 3.2. Class Diagram

```puml

title Class Diagram
hide empty members

class GetListOfMovements\nBetweenDatesController {
+ getListOfMovementsBetweenDates()
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
+ getAccount()
}

class AccountService {
+ getAccount()
}

class TransactionService {
+ createListOfMovements\nBetweenDates()
}

interface Transaction {
}

class TransactionDataDTO {
}

interface Account {}

GetListOfMovements\nBetweenDatesController --> Application : has
Application --> FamilyService : has
Application --> AccountService : has
Application --> TransactionService : has
FamilyService --> Family : has list
Family --> FamilyMember : has list
FamilyMember --> Account : has list
TransactionService --> Transaction : handles
Transaction --> TransactionDataDTO : creates


```

## 3.3. Applied Patterns

We applied the principles of Controller, Information Expert and PureFabrication from the GRASP pattern. We also
used the SOLID SRP principle.

The Information Expert and the Creator patterns would make all Account type classes responsible for creating and
handling Movement operations, as the instances of these classes hold all cash movement data. To avoid what we felt
was a more complicated solution that would promote high coupling and low cohesion we decided to apply the Pure
Fabrication Principle and created a MovementService Class.

This Class is responsible for all operations regarding Movements, thus providing low Coupling and high
Cohesion, keeping one Class as the Information Expert and applying the Single Responsibility Principle.

## 3.4. Tests

**Test 1, 2 and 3:** Controller: If any exception is thrown (Family, FamilyMember or Account with given IDs do not exist), a null
list will be returned.

    @Test
    void checkIfNullWhenNoSuchMemberID() {
        assertNull(controller.getListOfMovementsBetweenDates(testFamilyID, "110142608ZZ1", cashAccountID, startDateOne, endDateThree));
    }

    @Test
    void checkIfNullWhenNoSuchFamilyID() {
        assertNull(controller.getListOfMovementsBetweenDates(11, cc, cashAccountID, startDateOne, endDateThree));
    }

    @Test
    void checkIfNullWhenNoSuchAccountID() {
        assertNull(controller.getListOfMovementsBetweenDates(testFamilyID, cc, 5, startDateOne, endDateThree));
    }


**Test 2:** TransactionService: if the account holds no movements, an empty list will be returned.

    @Test
    void createListOfMovementsBetweenDates_ResultEmptyListNoMovements() {
        // arrange
        CashAccount cashAccount = new CashAccount(cashAccountDTO,accountID);
        TransactionService service = new TransactionService();
        Date startDate = new Date(2021, Calendar.JANUARY, 22);
        Date endDate = new Date(2021, Calendar.JANUARY, 30);
        List<TransactionDataDTO> expected = new ArrayList<>();

        // act
        List<TransactionDataDTO> result = service.createListOfMovementsBetweenDates(cashAccount, startDate, endDate);

        // assert
        assertEquals(expected, result);
        assertTrue(result.isEmpty());
    }

**Test 3:** TransactionService: if the account holds no movements that occurred between the given dates, an empty list 
will be returned.

    @Test
    void createListOfMovementsBetweenDates_ResultEmptyListNoMovementsBetweenDates() {
        // arrange
        CashAccount cashAccount = new CashAccount(cashAccountDTO,accountID);
        TransactionService service = new TransactionService();
        service.registerPaymentMyCashAccount(cashAccount, categoria1, transacaoDTO1);
        Date startDate = new Date(2021, Calendar.JANUARY, 22);
        Date endDate = new Date(2021, Calendar.JANUARY, 30);
        List<TransactionDataDTO> expected = new ArrayList<>();

        // act
        List<TransactionDataDTO> result = service.createListOfMovementsBetweenDates(cashAccount, startDate, endDate);

        // assert
        assertEquals(expected, result);
        assertTrue(result.isEmpty());
    }

# 4. Implementation

### Check if movement happened between two given dates

    private boolean checkIfMovementBetweenDates(Transaction aTransaction, Date startDate, Date endDate) {

        // Switch dates if endDate is earlier than startDate
        if (startDate.after(endDate)) {
            Date temp = (Date) startDate.clone();
            startDate = endDate;
            endDate = temp;
        }

        boolean isBetweenDates = false;
        Date transactionDate = aTransaction.getTransactionDate();

        if (transactionDate.after(startDate) && transactionDate.before(endDate)) {
            isBetweenDates = true;
        }
        return isBetweenDates;
    }

### Create TransactionDataDTO list

    public List<TransactionDataDTO> createListOfMovementsBetweenDates(Account anAccount, Date startDate, Date endDate) {
        List<Transaction> listOfMovements = anAccount.getListOfMovements();
        List<TransactionDataDTO> listOfMovementsBetweenDates = new ArrayList<>();

        for (Transaction transaction : listOfMovements)
            if (checkIfMovementBetweenDates(transaction, startDate, endDate)) {
                TransactionDataDTO transactionDTO = new TransactionDataDTO(transaction.getTransactionData());
                listOfMovementsBetweenDates.add(transactionDTO);
            }

        return listOfMovementsBetweenDates;
    }

# 5. Integration

To implement this user story, previous user stories regarding account creation (US170-173) and movement registration 
(US180 and US181) had to be completed beforehand. Without these, there would be no account from which to get movements
and there would be no movements kept by these accounts. With these classes implemented, the main requirements of this 
user story are a method that will verify the movement's date and a method to obtain the list of existing movements and
transform these into data that can be sent back to the UI.

# 6. Observations
