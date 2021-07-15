package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.EmailAddress;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputRemoveEmailDTO;
import switchtwentytwenty.project.dto.person.OutputRemoveEmailDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IRemoveEmailService;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class RemoveEmailService implements IRemoveEmailService {

    private final IPersonRepository personRepository;
    private final PersonDTODomainAssembler personDTODomainAssembler;

    @Autowired
    public RemoveEmailService(IPersonRepository personRepository, PersonDTODomainAssembler personDTODomainAssembler) {
        this.personRepository = personRepository;
        this.personDTODomainAssembler = personDTODomainAssembler;
    }

    @Override
    public OutputRemoveEmailDTO removeEmail(InputRemoveEmailDTO inputRemoveEmailDTO){
        PersonID userID = new PersonID(inputRemoveEmailDTO.getPersonID());
        EmailAddress emailToDelete = new EmailAddress(inputRemoveEmailDTO.getEmail());
        Person person = personRepository.getByID(userID);
        person.removeEmail(emailToDelete);
        Person savedPerson = personRepository.updatePerson(person);
        return personDTODomainAssembler.toOutputRemoveEmailDTO(savedPerson.getEmails());
    }


}
