package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputGetProfileDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyMemberProfileService;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class GetFamilyMemberProfileService implements IGetFamilyMemberProfileService {

    IPersonRepository personRepository;

    PersonDTODomainAssembler assembler;

    @Autowired
    public GetFamilyMemberProfileService(IPersonRepository iPersonRepository, PersonDTODomainAssembler personDTODomainAssembler) {
        this.assembler = personDTODomainAssembler;
        this.personRepository = iPersonRepository;
    }


    public OutputPersonDTO getFamilyMemberProfile(InputGetProfileDTO internalGetProfileDTO) {
        PersonID personID = assembler.createPersonID(internalGetProfileDTO);

        Person person = personRepository.getByID(personID);

        return assembler.toDTO(person);
    }

}
