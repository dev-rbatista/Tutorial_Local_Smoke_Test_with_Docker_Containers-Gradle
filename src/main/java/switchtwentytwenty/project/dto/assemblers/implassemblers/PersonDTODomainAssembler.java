package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IPersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDTODomainAssembler implements IPersonDTODomainAssembler {


    public Address createAddress(IInputPersonDTO inputPersonDTO) {
        return new Address(inputPersonDTO.unpackStreet(), inputPersonDTO.unpackCity(), inputPersonDTO.unpackZipCode(), inputPersonDTO.unpackHouseNumber());
    }


    public PhoneNumber createPhoneNumber(IInputPersonDTO inputPersonDTO) {
        return new PhoneNumber(inputPersonDTO.unpackPhone());
    }

    public VATNumber createVATNumber(IInputPersonDTO inputPersonDTO) {
        return new VATNumber(inputPersonDTO.unpackVAT());
    }

    public PersonID createPersonID(IInputPersonDTO inputPersonDTO) {
        return new PersonID(inputPersonDTO.unpackEmail());
    }


    public Name createName(IInputPersonDTO inputPersonDTO) {
        return new Name(inputPersonDTO.unpackName());
    }

    public BirthDate createBirthDate(IInputPersonDTO inputPersonDTO) {
        return new BirthDate(inputPersonDTO.unpackBirthDate());
    }


    public OutputPersonDTO toDTO(Person savedPerson) {
        OutputPersonDTO outputPersonDTO = new OutputPersonDTO();
        outputPersonDTO.setId(savedPerson.id().toString());
        outputPersonDTO.setName(savedPerson.getName().toString());
        outputPersonDTO.setBirthdate(savedPerson.getBirthdate().toString());
        outputPersonDTO.setVat(savedPerson.getVat().toString());
        outputPersonDTO.setStreet(savedPerson.getAddress().getStreet());
        outputPersonDTO.setCity(savedPerson.getAddress().getCity());
        outputPersonDTO.setZipCode(savedPerson.getAddress().getZipCode());
        outputPersonDTO.setDoorNumber(savedPerson.getAddress().getDoorNumber());
        outputPersonDTO.setFamilyID(savedPerson.getFamilyID().toString());

        outputPersonDTO.setEmails(getPersonStringEmailList(savedPerson));
        outputPersonDTO.setPhoneNumbers(getPersonStringPhoneNumberList(savedPerson));

        return outputPersonDTO;
    }

    private List<Integer> getPersonStringPhoneNumberList(Person savedPerson) {
        List<Integer> phoneNumbers = new ArrayList<>();
        List<PhoneNumber> phoneNumbersObjects = savedPerson.getPhoneNumbers();
        for (PhoneNumber phoneNumberObject : phoneNumbersObjects) {
            phoneNumbers.add(phoneNumberObject.getNumber());
        }
        return phoneNumbers;
    }

    private List<String> getPersonStringEmailList(Person savedPerson) {
        List<String> emails = new ArrayList<>();
        List<EmailAddress> emailObjects = savedPerson.getEmails();
        for (EmailAddress emailObject : emailObjects) {
            emails.add(emailObject.toString());
        }
        return emails;
    }

    public OutputEmailDTO toEmailDTO(Person savedPerson) {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        int index = savedPerson.getEmails().size() - 1;

        outputEmailDTO.setEmail(savedPerson.getEmails().get(index).toString());
        return outputEmailDTO;
    }

    public PersonID createPersonID(InputGetProfileDTO internalGetProfileDTO) {
        return new PersonID(internalGetProfileDTO.unpackID());
    }

    public OutputRemoveEmailDTO toOutputRemoveEmailDTO(List<EmailAddress> emails){
        List<String> convertedEmails = new ArrayList<>();
        for (EmailAddress email: emails) {
            String emailAddress = email.toString();
            convertedEmails.add(emailAddress);
        }
        OutputRemoveEmailDTO outputRemoveEmailDTO = new OutputRemoveEmailDTO();
        outputRemoveEmailDTO.setEmailAddresses(convertedEmails);
        return outputRemoveEmailDTO;
    }
}