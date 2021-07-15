package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputRelationDTO;

import switchtwentytwenty.project.dto.family.OutputRelationDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateRelationService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

@Service
public class CreateRelationService implements ICreateRelationService {

    private IFamilyRepository familyRepository;
    private FamilyDTODomainAssembler familyDTODomainAssembler;

    @Autowired
    public CreateRelationService(IFamilyRepository familyRepository, FamilyDTODomainAssembler familyDTODomainAssembler) {
        this.familyRepository = familyRepository;
        this.familyDTODomainAssembler = familyDTODomainAssembler;
    }

    @Override
    public OutputRelationDTO createRelation(InputRelationDTO inputRelationDTO){

        //creating Domain valueobjects
        PersonID personIDOne = familyDTODomainAssembler.personIDOneToDomain(inputRelationDTO);
        PersonID personIDTwo = familyDTODomainAssembler.personIDTwoToDomain(inputRelationDTO);
        RelationDesignation relationDesignation = familyDTODomainAssembler.relationDesignationToDomain(inputRelationDTO);
        FamilyID familyID = familyDTODomainAssembler.familyIDToDomain(inputRelationDTO);

        //creating Relation in Domain
        Relation relation = new Relation(personIDOne, personIDTwo, relationDesignation);
        Family family = familyRepository.getByID(familyID);
        family.addRelation(relation);

        //retrieving saved relation
        Family savedFamily = familyRepository.add(family);
        RelationID relationID = relation.getId();
        Relation savedRelation = savedFamily.getRelationByID(relationID);

        return familyDTODomainAssembler.toOutputRelationDTO(savedRelation);
    }
}