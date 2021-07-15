# US120 Create family cash account.
=======================================

# 1. Requirements

## 1.1. Client Notes

**Demo1** US120 As a family administrator, I want to create a family cash account.

- Demo1.1. The family already has a cash account

- Demo1.2. The family does not yet have a cash account

Our interpretation of this requirement was to create the function for a family administrator to be able to create a cash account for the family he administrates. As the family may only have one cash account, we need to check if one exists already.

## 1.2. System Sequence Diagram

```puml
autonumber 1
title System Sequence Diagram - US120
actor "FamilyAdministrator" as familyAdmin
participant "System" as System


activate familyAdmin
familyAdmin -> System: create a Family Cash \n Account (familyID, balance)
activate System

alt failure
System --> familyAdmin: Inform Failure - \nFamily already has account

else success

System --> familyAdmin: Inform Success - \nAccount created
deactivate System
end

deactivate familyAdmin
```

# 2. Analysis

The concepts Family and Cash Account are closely coupled as a family can have one or no cash accounts.

A Family Administrator is required to create a cash account for the Family.

In the future, it is expected that the Family Members will also have their own cash accounts, at which point the relation between all involved classes has to be analyzed.

As per the following interaction with the PO, a Cash Account will be initialized with a given balance.
> Q: Relativamente à US120 em que "As a family administrator, I want to create a family cash account." o administrador numa fase inicial cria apenas uma conta que por defeito guarda um montante de 0 euros ou poderá definir o montante inicial?
>
> PO: Definir um valor inicial parece-me bem.

## 2.1. Domain Model Diagram

```puml
hide empty members
hide circle
title Domain Model Diagram - US120

class Family {
Name
UniqueID
Registration Date
}

Class FamilyMember {
Name
Birth Date
}

class CashAccount {
UniqueID
Balance
}

FamilyMember --> CashAccount : administrator \n can create \n cash account
(FamilyMember, CashAccount) .. Family : stored
```

# 3. Design

[AccountDiagrams](../../../assets/AccountDiagrams.pdf)

The controller requests the Application class for the service that handles families.

The Class FamilyService keeps a list of families where the family with a given ID can be obtained and the user verified to be an administrator of said family.

The family class will be the creator of the cash account object, because it has the information necessary to create one (the familyID it will be associated with) and will also record its instance.

## 3.1. Functionality Use

```puml
autonumber 1
title Create Account

participant ": IAccountRESTController" as controller
participant ": ICreateAccountService" as service
participant ": IAccountRepository" as repository
participant ": IAccountRepositoryJPA" as repositoryJPA


-> controller : createAccount(createAccountDTO)
activate controller

ref over controller
CreateAccountDTO to InputAccountDTO

inputAccountDTO = accountInputDTOAssembler.toInputDTO(createAccountDTO)
end

controller -> service : createAccount(inputAccountDTO)
activate service

ref over service
InputAccountDTO to Account

account = accountDTODomainAssembler.toDomain(inputAccountDTO)
end

service -> repository : add(account)
activate repository

ref over repository
Account to AccountJPA

accountJPA = accountDataDomainAssembler.toData(account)
end

repository -> repositoryJPA : save(accountJPA)
activate repositoryJPA
return savedAccountJPA

ref over repository
AccountJPA to Account

savedAccount = accountDataDomainAssembler.toDomain(savedAccountJPA)
end

return savedAccount

ref over service
Account to OutputAccountDTO

outputAccountDTO = accountDTODomainAssembler.toDTO(savedAccount)
end

return outputAccountDTO

ref over controller
Add Links to OutputAccountDTO
end

return responseEntity

```

```puml
autonumber
title InputAccountDTO to Account

participant ": AccountDTODomainAssembler" as assembler
participant ": OwnerID" as owner
participant ": Balance" as balance
participant ": Description" as description


```

For this function the actor Family Administrator requests the creation of a cash account for the family he administrates to the UI.

The UI will forward this request to the CreateFamilyCashAccountController, who will in turn call the Application class to receive the FamilyService class.

In the FamilyService a Family can be searched for with an ID. After obtaining said family, the request is forwarded to the family object.

The Family object will first check if it already has a cash account, at which point a fail message can be sent back if there is an associated cash account.

If there is no associated cash account, the Family object will first create a new CashAccount object and then record its existence. At this point, a success message will be sent back to the user.

## 3.2. Class Diagram

```puml

title Class Diagram - US120

class CreateFamilyCashAccountController {
  - Application app
  + createFamilyCashAccount()
}

class Application {
  - FamilyService familyService
  + getFamilyService()
}

class FamilyService {
  - List<Family> families
  - getFamilyByID()
  + createFamilyCashAccount()
}

class Family {
  - int familyID
  - CashAccount cashAccount
  - hasCashAccount()
  + createFamilyCashAccount()
}

class CashAccount {
  - int CashAccountID
  - double balance
  + getBalance()
  + changeBalance(double value)
}

CreateFamilyCashAccountController --> Application
Application --> FamilyService
Family --> CashAccount
FamilyService --> Family
```

## 3.3. Applied Patterns

For this user story the main GRASP principles used were the Creator, in this case the Family class that records instances of CashAccount, and the Controller, a class created to deal with all system events related.

## 3.4. Tests

The creation of a cash account faces one main conditions that must be true for its success:

The given family does not yet have a cash account.

**Test 1:** An account is not created because the family already has one

    @Test
    void createFamilyCashAccountResultFalseAccountAlreadyExists() {
        String familyName = "Simpson";
        int familyID = 1;
        double balance = 0;
        Family familyOne = new Family(familyName, familyID);
        familyOne.createFamilyCashAccount(balance);

        boolean result = familyOne.createFamilyCashAccount(balance);

        assertFalse(result);
    }

**Test 2:** An account is created

    @Test
    void createFamilyCashAccountResultTrueAccountCreated() {
        String familyName = "Simpson";
        int familyID = 1;
        double balance = 0;
        Family familyOne = new Family(familyName, familyID);

        boolean result = familyOne.createFamilyCashAccount(balance);

        assertTrue(result);
    }

# 4. Implementation

**Checking if the user is the given family's administrator**

    public boolean isAdmin(String ccNumber) {
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.getFamilyMemberID().equals(ccNumber))
                return familyMember.isAdmin();
        }
        return false;
    }

**Checking if the family already has a cash acccount**

    private boolean hasCashAccount() {
        boolean hasCashAccount = false;
        if (this.familyCashAccount != null) {
            hasCashAccount = true;
        }
        return hasCashAccount;
    }

# 5. Integration/Demonstration

As of this sprint, this function has no integration with other functions.

# 6. Observations

Later in this project cash accounts will be associated with either families or family members. Right now the class cash account is closely related only to the family class, as the family member does not yet have the ability to create or own a cash account. Can high coupling be avoided between the three classes?
