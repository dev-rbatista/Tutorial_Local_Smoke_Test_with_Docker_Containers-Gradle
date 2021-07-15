# US151 Add email to profile
=======================================


# 1. Requirements

*As a family member, I want to add an email account to my profile*


**Demo1** As a family member, I want to add...

- Demo1.1. The new email familymember@gmail.com

- Demo1.2. The already existing email familymember@gmail.com

We interpreted this requirement as the function of a user to add an email account to his profile information. The email accounts must not previously exist on his profile, and it must be a valid email format.

# 2. Analysis

In order to fulfill this requirement, we need two main data pieces:
- e-mail address to add
- Family Member ID of the actor's profile

At a later iteration, the family member's ID would be acquired through the Log In information. For this sprint, the ID will have to be inputted along with the e-mail.



# 3. Design

The main process to fulfill this requirement would require the actor to select they want to add an email in the UI, which would then prompt the input of the email address. In lieu of not having an UI, the Int *FamilyMemberID* and String *emailAddress* will be directly inputted into the AddEmailController. 
````puml
@startuml
autonumber
title addEmail
actor "Family Member" as actor
participant ": UI" as UI
participant ": addEmailController" as controller
participant ": FFMApplication" as application
participant ": familyService" as service
participant ": Family" as family
participant "ID : FamilyMember" as familym
participant "anEmail: Email" as email

activate actor
actor -> UI: Add Email
activate UI
UI -> actor: Request Necessary Data
deactivate UI
actor -> UI: Input Data(email, familyID, familyMemberID)
activate UI
UI -> controller: addEmail(email, familyID, familyMemberID)
activate controller
controller -> application: getFamilyService
activate application
application -> controller: return familyService
deactivate application
controller -> service: addEmail(email, familyID, familyMemberID)
activate service
loop find family
    service -> service: findFamily(familyID)
    end
service -> family: addEmail(email, familyMemberID)
activate family
loop find family member
    family -> family: findFamilyMember(familyMemberID)
    end
family -> familym: addEmail(email)
activate familym

alt check if email already present TRUE

    familym -> familym: email already registered

else check if email already present FALSE
    familym -> email: createEmail(email)
    activate email
    end

email -> email: validate(email)

alt email valid
    
    familym -> familym: storeEmail(anEmail)
    deactivate email
    familym -> family: Ok
    family -> service: Ok
    service -> controller: Ok
    controller -> UI: Ok
    UI -> actor: Success

else email invalid
    
    familym -> family: Fail
    deactivate familym
    family -> service: Fail
    deactivate family
    service -> controller: Fail
    deactivate service
    
    controller -> UI: Fail
    deactivate controller
    UI -> actor: Failure
    deactivate UI
    deactivate actor
    end
@enduml
````




## 3.1. Functionality Use
The AddEmailController will invoke the Application object, which stores the FamilyService object. The Application will return the FamilyService, so that the addEmail method can be called. The FamilyService will find the correct Family object by the familyID, and the Family object will find the correct FamilyMember object by the familyMemberID.
Upon finding the corresponding FamilyMember object to the *FamilyMemberID*, it will call its addEmail method. This will involve running the checkIfEmailPresent method. If false, it will then create an Email object after passing a validation of the String *emailAdress* in the Email constructor. This Email object will be stored on the FamilyMember object, and a confirmation will return to the Controller (and at a later stage, the UI). 



## 3.2. Class Diagram
The main Classes involved are:
- AddEmailController
- Application
- Family
- FamilyMember
- Email

![Class Diagram](https://i.imgur.com/aIvHqZg.png)

## 3.3. Applied Patterns
We applied the principles of Controller, Information Expert, Creator and PureFabrication from the GRASP pattern.
We also used the SOLID SRP principle.

## 3.4. Tests 
    
The following preparation was made for the execution of the tests:

    FamilyMember familyMember1 = new FamilyMember(666);
    FamilyMember familyMember2 = new FamilyMember(777);
    Family testFamily = new Family(familyMember1, familyMember2);
    Application app = new Application(testFamily);
    AddEmailController controller = new AddEmailController(app);
    

**Test 1:** Verify that a correct email is accepted

    @Test
    public void checkifEmailAdded() {
    assertTrue(controller.addEmail("test@isep.ipp.pt", 666));
    }

**Test 2:** Verify that a correct email is not accepted if already entered before

    @Test
    public void checkEmailAlreadyPresent() {
        controller.addEmail("test2@isep.ipp.pt", 666);
        assertFalse(controller.addEmail("test2@isep.ipp.pt", 666));

    }
**Test 3:** Verify that an exception is thrown when there is no family member with the inserted ID

    @Test
    public void checkIfThrowsWhenNoSuchID() {
        assertThrows(IllegalArgumentException.class, () -> controller.addEmail("test3@isep.ipp.pt", 888));
    }
**Test 4:** Verify that an exception is thrown when there is a space in the inserted email string

    @Test
    public void CreatingEmailAddressWithSpace() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    EmailAddress badEmail = new EmailAddress("11207 17@isep.ipp.pt");
                });
    }
**Test 5:** Verify that an exception is thrown when there is an illegal character in the inserted email string

    @Test
    public void CreatingEmailAddressWithIllegalCharacters() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    EmailAddress badEmail = new EmailAddress("!1120717@isep.ipp.pt");
                });
    }
**Test 6:** Verify that an exception is thrown when there are two Ats in the inserted email string

    @Test
    public void CreatingEmailAddressWithTwoAts() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    EmailAddress badEmail = new EmailAddress("1120717@@isep.ipp.pt");
                });
    }
**Test 7:** Verify that an exception is thrown when the inserted email is Blank

    @Test
    public void CreatingEmotyEmailAddress() {
    Throwable exception =
    assertThrows(IllegalArgumentException.class, () -> {
    EmailAddress badEmail = new EmailAddress("");
    });
    }
**Test 8:** Verify that an exception is thrown when the inserted email is null

    @Test
    public void CreatingNullEmailAddress() {
        String nullEmail = null;
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    EmailAddress badEmail = new EmailAddress(nullEmail);
                });
    }

# 4. Implementation

**Finding the correct FamilyMember**

In order to find the relevant FamilyMember by its ID, a method was constructed to retrieve their index in the FamilyMember array in the Family Class:

    private int findFamilyMemberIndexByID(int familyMemberID){
        int index = 0;
        for (FamilyMember member : this.family) {
            if (member.getID() == familyMemberID) {
            return index;
            }
            index++;
        }
        throw new IllegalArgumentException("No family member with that ID was found");
    }

Following that, we can use it to retrieve the correct FamilyMember object:

    public boolean addEmail(String emailToAdd, int familyMemberID) {
        return family.get(findFamilyMemberIndexByID(familyMemberID)).addEmail(emailToAdd);
    }

**Creating and Adding the EmailAddress Object**

In the FamilyMember Class, we must first check if the email to add is already present in the EmailAddress array list. For that, the following method is used:

    private boolean isEmailAlreadyPresent(String emailToCheck){
        for (EmailAddress email : emails) {
            if (email.getEmail().equalsIgnoreCase(emailToCheck)) {
                return true;
            }
        }
        return false;
        }

So we can now create and add the EmailAddress object to the array list (emails):

    public boolean addEmail(String emailToAdd) {
        if (!isEmailAlreadyPresent(emailToAdd)) {
            EmailAddress newEmail = new EmailAddress(emailToAdd);
            emails.add(newEmail);
            return true;
        }
        return false;
    }

# 5. Integration/Demonstration

As of this sprint, this function has no integration with other functions.

# 6. Observations

In the future, the Family Member ID would ideally have to be retrieved by a method that checks the log in info of the current user, instead of the ID being manually inputted.  



