package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.FamilyMemberAndRelationsListDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyMembersAndRelationshipService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import java.util.List;

@Service
public class GetFamilyMembersAndRelationshipService implements IGetFamilyMembersAndRelationshipService {

    FamilyDTODomainAssembler familyDTODomainAssembler;
    IFamilyRepository familyRepository;
    IPersonRepository personRepository;

    @Autowired
    public GetFamilyMembersAndRelationshipService(FamilyDTODomainAssembler familyDTODomainAssembler, IPersonRepository personRepository, IFamilyRepository familyRepository) {
        this.familyDTODomainAssembler = familyDTODomainAssembler;
        this.familyRepository = familyRepository;
        this.personRepository = personRepository;
    }

    @Override
    public FamilyMemberAndRelationsListDTO getFamilyMembersAndRelations(String familyID) {
        FamilyID internalFamilyID = familyDTODomainAssembler.familyIDToDomain(familyID);
        Family family = familyRepository.getByID(internalFamilyID);
        List<Person> memberList = personRepository.findAllByFamilyID(internalFamilyID);
        FamilyMemberAndRelationsListDTO familyMemberAndRelationsListDTO = new FamilyMemberAndRelationsListDTO();
        for (Person person : memberList) {
            familyMemberAndRelationsListDTO.addDTO(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(person, family));
        }
        return familyMemberAndRelationsListDTO;
    }


}
