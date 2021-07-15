package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.EmailAddress;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IPersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputEmailDTO;
import switchtwentytwenty.project.dto.person.OutputEmailDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddEmailService;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class AddEmailService implements IAddEmailService {

    private final IPersonRepository personRepository;
    private final IPersonDTODomainAssembler personDTODomainAssembler;

    @Autowired
    public AddEmailService (IPersonRepository personRepository, IPersonDTODomainAssembler personDTODomainAssembler){
        this.personRepository = personRepository;
        this.personDTODomainAssembler = personDTODomainAssembler;
    }

    /**
     * Method was changed because it was previously a void method. Now it receives an InternalEmailDTO (Converted from an addEmailDTO)
     * and returns an OutputEmailDTO
     * @param internalEmailDTO
     * @return OutputEmailDTO
     */
    @Override
    public OutputEmailDTO addEmail(InputEmailDTO internalEmailDTO){

        EmailAddress email = new EmailAddress(internalEmailDTO.unpackEmail());

        PersonID loggedUserID = new PersonID(internalEmailDTO.unpackUserID());
        Person person = personRepository.getByID(loggedUserID);

        person.addEmail(email);
        Person savedPerson = personRepository.updatePerson(person);

        return personDTODomainAssembler.toEmailDTO(savedPerson);
    }
}