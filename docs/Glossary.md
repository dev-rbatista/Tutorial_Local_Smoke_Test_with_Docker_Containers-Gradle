| Term/Class Name    | Description | Type
|------------|----------|---------|
| Account | Interface that all Accounts implement | Interface |
| AccountData | Storage object to hold account data | Class |
| AccountIDAndDescriptionDTO | Output DTO with ID and Description of a given Account | Class |
| AccountService | Service that manages all Accounts | Class |
| AccountTypeEnum | Enum of AccountTypes | Enum |
| AddBankAccountDTO | Input DTO with information to create a Bank Account | Class |
| AddBankSavingsDTO | Input DTO with information to create a Bank Savings Account | Class |
| AddCashAccountDTO | Input DTO with information to create a Cash Account | Class |
| AddCreditCardAccountDTO | Input DTO with information to create a Credit Card Account | Class |
| AddFamilyMemberDTO | Input DTO with information to create a Family Member | Class |
| Address | Address object|Class|
| BankAccount | Bank Account | Class |
| BankSavingsAccount | Bank Savings Account | Class |
| CashAccount| CashAccount object |Class|
| CashTransaction | Transaction that represents physical currency | Class |
| CashTransferDTO | Input DTO with information to execute a cash transfer | Class |
| Category | Interface that all Categories implement | Interface |
| CategoryTreeDTO|Object to transfer Standard and Custom categories|Class|
| CreditCardAccount | Credit Card Account | Class |
| CurrencyEnum | Enum of Currencies | Enum |
| CustomCategories | Attribute of the Family class |List|
| CustomCategory| Custom financial transaction category, may have children | Class|
| EmailService | Service that manages all Emails | Class |
| FamilyCashTransferDTO | Input DTO with information to execute a cash transfer | Class |
| FamilyMemberRelationDTO|Object to transfer Relation Designations|Class|
| FamilyMember|FamilyMember/User object | Class |
| FamilyService| FamilyService object | Class|
| FamilyWithoutAdministratorDTO|Object to transfer FamilyName and FamilyID of families without Admin|Class|
| Family| Family object | Class|
| InvalidAccountDesignationException | Exception to be thrown when the Account Designation is invalid | Exception |
| MemberProfileDTO|Object to transfer Member Profile Info|Class|
| MoneyValue | Object that contains value and currency | Class |
| NoParentalPermissionException | Exception to be thrown when there is no parental permission | Exception |
| NotSameCurrencyException | Exception to be thrown when there is currency incompatibility | Exception |
| PhoneNumber| PhoneNumber object |Class|
| Relation  | Relation between family members object|Class|
| RelationService | Service that manages all Relations | Class |
| StandardCategories | Attribute of the categoryService class |List|
| StandardCategoryDTO|Object to transfer StandardCategory names|Class|
| StandardCategory| Built-in financial transaction category, cannot be deleted and may have children | Class|
| Transaction | Interface that all Transactions implement | Interface |
| TransactionData | Storage object to hold transaction data | Class |
| TransactionDataDTO | Output DTO with information about a transaction | Class |
| TransactionService | Service that manages all Transactions | Class |
| VatNumber | VatNumber object|Class|
| administrator | Attribute of FamilyMember Class |Boolean|
| balance |Attribute of CashAccount class | Double|
| birthdate|Attribute of FamilyMember class  |Date|
| cashAccountID| Attribute of CashAccount class | Integer|
| categoryID | Attribute of Category class | Integer|
| categoryName | Attribute of Category class | String|
| categoryService | categoryService object | Class|
| ccNumber| Attribute of FamilyMember class, unique ID relative to the CC Number  |String|
| city|Attribute of Street class |String|
| emails| Attribute of FamilyMember class |ArrayList|
| families | Attribute of the FamilyService class |ArrayList|
| familyCashAccount | Attribute of Family class| CashAccount|
| familyMembers| Attribute of the family class  |ArrayList|
| local|Attribute of Street class |String|
| parentCategory| Attribute of Standard/CustomCategory class | Standard/Custom category|
| phoneNumber|Attribute of the PhoneNumber class |Integer|
| postalCode|Attribute of Street class |String|
| relationDesignation| Attribute of Relation Class |String|
| street|Attribute of Street class |String|
| vatNumber|Attribute of the VatNumber class |Integer|
