package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDTO;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputAddFamilyMemberDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddFamilyMemberService;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class AddFamilyMemberService implements IAddFamilyMemberService {

    private final IPersonRepository personRepository;
    private final PersonDTODomainAssembler personDTODomainAssembler;
    private final JWTUserDetailsService jwtUserDetailsService;


    @Autowired
    public AddFamilyMemberService(IPersonRepository personRepository, PersonDTODomainAssembler personDTODomainAssembler, JWTUserDetailsService jwtUserDetailsService) {
        this.personRepository = personRepository;
        this.personDTODomainAssembler = personDTODomainAssembler;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Transactional
    public OutputPersonDTO addPerson(InputAddFamilyMemberDTO internalAddFamilyMemberDTO) {

        PersonID loggedUserID = new PersonID(internalAddFamilyMemberDTO.getAdminID());

        FamilyID familyID = personRepository.getByID(loggedUserID).getFamilyID();
        Name name = personDTODomainAssembler.createName(internalAddFamilyMemberDTO);
        BirthDate birthDate = personDTODomainAssembler.createBirthDate(internalAddFamilyMemberDTO);
        VATNumber vat = personDTODomainAssembler.createVATNumber(internalAddFamilyMemberDTO);
        PhoneNumber phone = personDTODomainAssembler.createPhoneNumber(internalAddFamilyMemberDTO);
        Address address = personDTODomainAssembler.createAddress(internalAddFamilyMemberDTO);
        PersonID personID = personDTODomainAssembler.createPersonID(internalAddFamilyMemberDTO);

        Person aPerson = new Person(name, birthDate, personID, vat, phone, address, familyID);

        Person addedPerson = personRepository.add(aPerson);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(internalAddFamilyMemberDTO.unpackEmail());
        userDTO.setPassword(internalAddFamilyMemberDTO.unpackPassword());
        String role = "familyMember";
        userDTO.setRole(role);
        jwtUserDetailsService.save(userDTO);

        return personDTODomainAssembler.toDTO(addedPerson);
    }
}