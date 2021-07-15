package switchtwentytwenty.project.interfaceadapters.implrepositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements IPersonRepository {

    private IPersonRepositoryJPA personRepositoryJPA;

    private PersonDataDomainAssembler personAssembler;


    @Autowired
    public PersonRepository(IPersonRepositoryJPA iPersonRepositoryJPA, PersonDataDomainAssembler personDataDomainAssembler) {
        this.personRepositoryJPA = iPersonRepositoryJPA;
        this.personAssembler = personDataDomainAssembler;
    }

    /**
     * Method to check if a PersonID is already registered in the database
     * Optional says yes or no when you ask something. In this case it says if there is something in the personRepositoryJPA.
     * After you can obtain that something with optional.get().
     *
     * @param personID PersonID to check if it's already present in the database
     * @return true if there is already a person registered with that PersonID
     */
    @Override
    public boolean isPersonIDAlreadyRegistered(PersonID personID) {
        boolean emailIsRegistered = false;
        Optional<PersonJPA> optional = personRepositoryJPA.findById(new PersonIDJPA(personID.toString()));
        if (optional.isPresent()) {
            emailIsRegistered = true;
        }
        return emailIsRegistered;
    }

    @Override
    public Person getByID(PersonID personID) {
        return retrievePersonFromRepository(personID);
    }

    private Person retrievePersonFromRepository(PersonID id) {
        PersonIDJPA personIDJPA = new PersonIDJPA(id.toString());
        Optional<PersonJPA> personJPA = personRepositoryJPA.findById(personIDJPA);
        if (personJPA.isPresent()) {
            return createPerson(personJPA.get());
        } else {
            throw new EmailNotRegisteredException();
        }

    }

    /**
     * Method to add the inputted Person domain object into the repository.
     * The Person domain object will be converted into a PersonJPA data object and sent to the repository.
     *
     * @param person domain object we want to add to the person repository
     */
    @Override
    public Person add(Person person) {
        PersonJPA registeredPersonJPA;
        Person savedPerson;
        if (!isPersonIDAlreadyRegistered(person.id())) {
            PersonJPA personJPA = personAssembler.toData(person);
            registeredPersonJPA = personRepositoryJPA.save(personJPA);
            savedPerson = createPerson(registeredPersonJPA);
        } else {
            throw new PersonAlreadyRegisteredException("Person is already registered in the database");
        }
        return savedPerson;
    }

    @Override
    public Person updatePerson(Person person) {
        PersonJPA personJPA = personAssembler.toData(person);
        PersonJPA updatedPersonJPA = personRepositoryJPA.save(personJPA);
        return createPerson(updatedPersonJPA);
    }

    @Override
    public List<Person> findAllByFamilyID(FamilyID internalFamilyID) {
        FamilyIDJPA familyIDJPA = personAssembler.createFamilyID(internalFamilyID);
        List<PersonJPA> listPersonJPA = personRepositoryJPA.findAllByFamilyid(familyIDJPA);
        List<Person> personList = new ArrayList<>();
        for (PersonJPA personJPA : listPersonJPA) {
            personList.add(createPerson(personJPA));
        }
        return personList;
    }

    private Person createPerson(PersonJPA personJPA) {

        PersonID personID = personAssembler.createPersonID(personJPA);
        Name name = personAssembler.createName(personJPA);
        BirthDate birthDate = personAssembler.createBirthDate(personJPA);
        List<EmailAddress> emails = personAssembler.createEmailAdressList(personJPA);
        VATNumber vat = personAssembler.createVATNumber(personJPA);
        List<PhoneNumber> phoneNumbers = personAssembler.createPhoneNumberList(personJPA);
        Address address = personAssembler.createAddress(personJPA);
        FamilyID familyID = personAssembler.createFamilyID(personJPA);

        return new Person(personID, name, birthDate, emails, vat, phoneNumbers, address, familyID);
    }

}