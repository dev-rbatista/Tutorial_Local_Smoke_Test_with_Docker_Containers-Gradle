package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDTO;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateFamilyService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class CreateFamilyService implements ICreateFamilyService {

    private final IPersonRepository personRepository;
    private final IFamilyRepository familyRepository;
    private final PersonDTODomainAssembler personDTODomainAssembler;
    private final FamilyDTODomainAssembler familyDTODomainAssembler;
    private final JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    public CreateFamilyService(IPersonRepository personRepository, IFamilyRepository familyRepository, PersonDTODomainAssembler personDTODomainAssembler, FamilyDTODomainAssembler familyDTODomainAssembler, JWTUserDetailsService jwtUserDetailsService) {
        this.personRepository = personRepository;
        this.familyRepository = familyRepository;
        this.personDTODomainAssembler = personDTODomainAssembler;
        this.familyDTODomainAssembler = familyDTODomainAssembler;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    /**
     * Service method to create a Family and set its administrator
     *
     * @param inputFamilyDTO DTO that contains the Family's information
     * @param inputPersonDTO DTO that contains the Family Administrator's information
     */
    @Transactional
    public OutputFamilyDTO createFamilyAndAddAdmin(InputFamilyDTO inputFamilyDTO, InputPersonDTO inputPersonDTO) {
        PersonID adminID = new PersonID(inputPersonDTO.unpackEmail());
        FamilyID familyID = new FamilyID(inputPersonDTO.unpackEmail());

        Name name = personDTODomainAssembler.createName(inputPersonDTO);
        BirthDate birthDate = personDTODomainAssembler.createBirthDate(inputPersonDTO);
        VATNumber vat = personDTODomainAssembler.createVATNumber(inputPersonDTO);
        PhoneNumber phone = personDTODomainAssembler.createPhoneNumber(inputPersonDTO);
        Address address = personDTODomainAssembler.createAddress(inputPersonDTO);

        FamilyName familyName = familyDTODomainAssembler.createFamilyName(inputFamilyDTO);
        RegistrationDate registrationDate = familyDTODomainAssembler.createRegistrationDate(inputFamilyDTO);

        Person admin = new Person(name, birthDate, adminID, vat, phone, address, familyID);
        Family family = new Family(familyID, familyName, registrationDate, adminID);

        personRepository.add(admin);
        Family registeredFamily = familyRepository.add(family);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(inputPersonDTO.unpackEmail());
        userDTO.setPassword(inputPersonDTO.unpackPassword());
        String role = "familyAdministrator";
        userDTO.setRole(role);
        jwtUserDetailsService.save(userDTO);

        return familyDTODomainAssembler.toOutputFamilyDTO(registeredFamily);
    }
}