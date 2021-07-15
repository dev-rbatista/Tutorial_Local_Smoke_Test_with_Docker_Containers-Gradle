# US135,185,188 Check balance
=======================================

# 1. Requirements

## 1.1 Client Notes


## 1.2 Dependencies

### 1.2.1 Pre-conditions

### 1.2.2 Other User Stories

## 1.3 Acceptance Criteria

### 1.3.1 Success Cases

### 1.3.2 Failure Cases

## 1.4 SSD

# 2. Analysis

## 2.1 Summary

### Information Needed

| **_Name_**         | **_Business Rules_**                                                                   |
| :-------------------------- | :------------------------------------------------------------------------------------- |
| **AccountID**                | Required, long            |


## 2.2. Domain Model Excerpt


# 3. Design

## 3.1. Design decisions

To check the balance of an account, we will need the account's AccountID. This will be provided in the request's URI. Check balance will only allow GET requests as follows:

```
GET/ "*/accounts/{accountID}"
```

Only accounts the user has permission to check should be available from the client. Authentication and authorization will be made with a token provided by a request. An options request to an account will return the permissions a user has for this account, including whether he can check its balance.

With an AccountID, an Account can be retrieved from persistence and its balance returned to the client.

The request should return not only the balance of an account, but also its Designation.

## 3.2. Class Diagram


## 3.3. Functionality Use


## 3.4. Sequence Diagram

```puml
autonumber 1
title Check Balance

participant ": IAccountRESTController" as controller
participant ": ICheckBalanceService" as service
participant ": IAccountRepository" as repository
participant ": IAccountRepositoryJPA" as repositoryJPA


-> controller : checkBalance(long accountID)
activate controller

ref over controller
Long AccountID to InputAccountIDDTO

inputAccountIDDTO = accountIDDTOAssembler.toInputDTO(long accountID)
end

controller -> service : checkBalance(inputAccountIDDTO)
activate service

service -> service : accountID = new AccountID\n(inputAccountIDDTO.unpackAccountID)

service -> repository : getByID(accountID)
activate repository

repository -> repository : accountIDJPA = new \nAccountIDJPA(accountID)

repository -> repositoryJPA : findByID(accountIDJPA)
activate repositoryJPA
return accountJPA

ref over repository
AccountJPA to Account

account = accountDataDomainAssembler.toDomain(accountJPA)
end

return account


ref over service
Balance to OutputAccountDTO

outputAccountDTO = accountDTODomainAssembler.toDTO(account)
end

return outputAccountDTO

ref over controller
Add Links to OutputBalanceDTO
end

return responseEntity

```


## 3.5 Tests

### Controller Tests

### Service Tests


### Assembler Tests


### Repository Tests


### Account Tests


### Testes de Value Objects, JPAs e DTOs
  
    