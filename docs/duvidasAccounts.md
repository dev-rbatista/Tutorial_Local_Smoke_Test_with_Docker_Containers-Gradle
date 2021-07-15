


Opção Um:
```puml
hide empty members
skinparam linetype ortho

interface Account {
}

interface NonCashAccount{
}

class CashAccount {
}

class BankAccount {
}

class CreditCardAccount{}

class DebitCardAccount{}

class AccountData{}

CashAccount .--|> Account
NonCashAccount .-|> Account

BankAccount .-|> NonCashAccount
CreditCardAccount .-|> NonCashAccount
DebitCardAccount .-|> NonCashAccount

AccountData --* CashAccount
AccountData --* BankAccount
AccountData --* CreditCardAccount
AccountData --* DebitCardAccount

'note "Data dentro da AccountData. Cons: Codigo desnecessário (fowards em todas as contas) Pros: Private class data pattern;" as note

Note as N1
    - Data dentro da AccountData
    Cons:
    - Codigo desnecessário (forwards em todas as contas);
    - Acoplamento entre as implementações de Account e AccountData
    Pros:
    - Baixo custo de refactoring (feito no semestre passado)
    - Fácil acrescentar novos atributos
    - Private class data pattern 
end Note

@enduml
```
Opção dois:
````puml
@startuml

hide empty members
'skinparam linetype ortho

interface Account{
}

abstract class AbstractAccount {}

abstract class NonCashAccount{
}

class CashAccount{
}

class BankAccount{
}

class BankSavingsAccount{
}

class CreditCardAccount{
}

AbstractAccount .-|> Account

CashAccount --|> AbstractAccount
NonCashAccount --|> AbstractAccount

BankAccount --|> NonCashAccount
BankSavingsAccount --|> NonCashAccount
CreditCardAccount --|> NonCashAccount

'note "Data dentro da AbstractAccount. Cons: herança; Pros:" as note

Note as N1
    - Data dentro da AbstractAccount
    Cons:
    - Herança;
    - Acoplamento entre as implementações concretas de Account e AbstractAccount
    Pros:
    - Abstração da implementação;
    - Redução de codigo repetido 
    (evitar forwards para a classe que contém a data);
end Note

@enduml
````

Possibilidades Domain Model:


1. Atualmente implementada (09/04/2021):

    Accounts não conhecem os seus "owners". Person e Family é que conhecem as suas Accounts (através de um AccountID).
    
    **Vantagens**:
    Permite iterar apenas dentro do Agregado pretendido. 
    Isto é, as Accounts estão só na Person ou na Family e como tal não
    é necessário iterar por todas as Accounts existentes no Universo.
   
    **Desvantagens**:
    Necessidade de escrever em dois Agregados em simultâneo (Person + Account ou Family + Account).
    Assim, como a Account não conhece o seu OwnerID tem de se guardar a Account no AccountRepository e no Person ou FamilyRepository.



2. Account conhece o Family Member a que pertence. Account conhece PersonID.
   Account também conhece a Family a que pertence (No caso da Family Cash Account)
   
    **Vantagens**:
    Ao adicionar uma Account não necessitamos de escrever em dois repositórios (Family + Account ou Person + Account).
    Neste caso como temos o ID da Person ou Family, apenas é necessário guardar no Repository das Accounts, visto que já conhecem o seu Owner (Não tem de se escrever também
    no Repository de Person ou de Family).

    **Desvantagens**:
    Mais uma Interface que tem de ser criada (ownerID) que implementa a Interface ID e que servirá de atributo da Account 
    (Assim, este atributo é o mais genérico possível podendo ser um identificativo
    de Person, Family, ou outra coisa que possa surgir). 

    **NOTA: Inverso do que está atualmente implementado (09/04/2021)**