package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputChangeRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IChangeRelationService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

@Service
@NoArgsConstructor
public class ChangeRelationService implements IChangeRelationService {

    private IFamilyRepository familyRepository;
    private FamilyDTODomainAssembler familyDTODomainAssembler;

    @Autowired
    public ChangeRelationService(IFamilyRepository familyRepository, FamilyDTODomainAssembler familyDTODomainAssembler) {
        this.familyRepository = familyRepository;
        this.familyDTODomainAssembler = familyDTODomainAssembler;
    }


    public OutputRelationDTO changeRelation(InputChangeRelationDTO inputChangeRelationDTO){

        //create VO
        FamilyID familyID = new FamilyID(inputChangeRelationDTO.getFamilyID());

        RelationID newRelationID = new RelationID(Integer.parseInt(inputChangeRelationDTO.getRelationID()));
        RelationDesignation newRelationDesignation = new RelationDesignation(inputChangeRelationDTO.getNewDesignation());

        Family family = familyRepository.getByID(familyID);
        Relation updatedRelation = family.changeRelation(newRelationID, newRelationDesignation);
        familyRepository.add(family);

        return familyDTODomainAssembler.toOutputRelationDTO(updatedRelation);
    }

}