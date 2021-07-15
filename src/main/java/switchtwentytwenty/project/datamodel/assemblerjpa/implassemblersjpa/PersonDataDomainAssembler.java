package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.IPersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.*;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDataDomainAssembler implements IPersonDataDomainAssembler {
    /**
     * Assembler method to convert a Person domain object into a PersonJPA data object.
     *
     * @param person domain object to be converted
     * @return PersonJPA data object corresponding the inputted person.
     */
    public PersonJPA toData(Person person) {
        PersonID personID = person.id();

        PersonIDJPA personIDJPA = new PersonIDJPA(personID.toString());

        String name = person.getName().toString();
        String birthdate = person.getBirthdate().toString();

        int vat = person.getVat().toInt();
        List<PhoneNumber> phoneNumbers = person.getPhoneNumbers();

        Address address = person.getAddress();
        FamilyID familyID = person.getFamilyID();

        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.getId());

        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        AddressJPA addressJPA = new AddressJPA(address.getId(), address.getStreet(), address.getCity(), address.getZipCode(), address.getDoorNumber(), personJPA);

        List<EmailAddressJPA> emailsJPA = generateEmailAddressJPAList(person.getEmails(), personJPA);

        List<PhoneNumberJPA> phoneNumbersJPA = generetePhoneNumberJPAList(phoneNumbers, personJPA);

        personJPA.setAddress(addressJPA);
        personJPA.setPhones(phoneNumbersJPA);
        personJPA.setEmails(emailsJPA);

        return personJPA;
    }

    public Address createAddress(PersonJPA personJPA) {
        AddressJPA addressJPA = personJPA.getAddress();

        Long id = addressJPA.getId();
        Street street = new Street(addressJPA.getStreet());
        City city = new City(addressJPA.getCity());
        ZipCode zipCode = new ZipCode(addressJPA.getZipCode());
        DoorNumber doorNumber = new DoorNumber(addressJPA.getDoorNumber());

        return new Address(id, street, city, zipCode, doorNumber);

    }

    public List<PhoneNumber> createPhoneNumberList(PersonJPA personJPA) {
        return generatePhoneNumberList(personJPA.getPhones());
    }

    public List<EmailAddress> createEmailAdressList(PersonJPA personJPA) {
        return generateEmailAddressList(personJPA.getEmails());
    }

    public VATNumber createVATNumber(PersonJPA personJPA) {
        return new VATNumber(personJPA.getVat());
    }

    public PersonID createPersonID(PersonJPA personJPA) {
        return new PersonID(personJPA.getId().toString());
    }

    public Name createName(PersonJPA personJPA) {
        return new Name(personJPA.getName());
    }

    public BirthDate createBirthDate(PersonJPA personJPA) {
        return new BirthDate(personJPA.getBirthdate());
    }

    public FamilyID createFamilyID(PersonJPA personJPA) {
        return new FamilyID(personJPA.getFamilyid().toString());
    }


    private List<EmailAddressJPA> generateEmailAddressJPAList(List<EmailAddress> emailAddressList, PersonJPA personJPA) {
        List<EmailAddressJPA> emailAddressJPAList = new ArrayList<>();

        for (EmailAddress email : emailAddressList) {
            EmailAddressJPA emailAddressJPA = new EmailAddressJPA(email.getId(), email.toString(), personJPA);
            emailAddressJPAList.add(emailAddressJPA);

        }

        return emailAddressJPAList;
    }

    private List<PhoneNumberJPA> generetePhoneNumberJPAList(List<PhoneNumber> numberJPAList, PersonJPA personJPA) {
        List<PhoneNumberJPA> phoneNumberList = new ArrayList<>();

        for (PhoneNumber phoneNumber : numberJPAList) {
            PhoneNumberJPA phoneNumberJPA = new PhoneNumberJPA(phoneNumber.getId(), phoneNumber.getNumber(), personJPA);
            phoneNumberList.add(phoneNumberJPA);
        }

        return phoneNumberList;
    }

    private List<PhoneNumber> generatePhoneNumberList(List<PhoneNumberJPA> numberList) {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();

        for (PhoneNumberJPA phoneNumberJPA : numberList) {
            PhoneNumber phoneNumber = new PhoneNumber(phoneNumberJPA.getId(), phoneNumberJPA.getNumber());
            phoneNumberList.add(phoneNumber);
        }
        return phoneNumberList;
    }

    private List<EmailAddress> generateEmailAddressList(List<EmailAddressJPA> emailList) {
        List<EmailAddress> emailAddressList = new ArrayList<>();

        for (EmailAddressJPA emailAddressJPA : emailList) {
            EmailAddress emailAddress = new EmailAddress(emailAddressJPA.getId(), emailAddressJPA.getEmail());
            emailAddressList.add(emailAddress);
        }
        return emailAddressList;
    }

    public FamilyIDJPA createFamilyID(FamilyID internalFamilyID) {
        return new FamilyIDJPA(internalFamilyID.getId());
    }
}

