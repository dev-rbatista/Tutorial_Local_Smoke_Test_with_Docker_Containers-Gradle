package switchtwentytwenty.project.usecaseservices.irepositories;

import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.PersonID;

import java.util.List;

public interface IPersonRepository extends Repository<Person, PersonID> {


    Person add(Person entity);


    boolean isPersonIDAlreadyRegistered(PersonID personID);

    Person updatePerson(Person person);

    List<Person> findAllByFamilyID(FamilyID internalFamilyID);
}