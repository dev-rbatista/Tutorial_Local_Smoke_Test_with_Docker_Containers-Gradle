package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.IFamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.RelationJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamilyDataDomainAssembler implements IFamilyDataDomainAssembler {
    /**
     * Assembler method to create a Family domain object into a FamilyJPA data object.
     *
     * @param family domain object
     * @return FamilyJPA data object
     */
    public FamilyJPA toData(Family family) {
        FamilyID familyID = family.id();
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.toString());

        String name = family.getName().toString();
        String registrationDate = family.getRegistrationDate().toString();

        PersonID adminID = family.getAdmin();
        PersonIDJPA adminIDJPA = new PersonIDJPA(adminID.toString());

        FamilyJPA familyJPA = new FamilyJPA(familyIDJPA, name, registrationDate, adminIDJPA);

        List<RelationJPA> relationsJPA = generateRelationsJPAList(family.getRelations(), familyJPA);
        familyJPA.setRelationList(relationsJPA);

        return familyJPA;


    }

    public List<RelationJPA> generateRelationsJPAList(List<Relation> relations, FamilyJPA familyJPA) {
        List<RelationJPA> relationsJPA = new ArrayList<>();

        for (Relation relation : relations) {
            RelationJPA relationJPA = new RelationJPA(relation.getMemberA().toString(), relation.getMemberB().toString(), relation.getRelationDesignation().toString(), relation.getId().getId(), familyJPA);
            relationsJPA.add(relationJPA);
        }

        return relationsJPA;
    }

    public List<Relation> generateRelationList(List<RelationJPA> relationListJPA) {
        List<Relation> relationList = new ArrayList<>();
        if(!relationListJPA.isEmpty()){

            for (RelationJPA relationJPA : relationListJPA) {
                PersonID personIDOne = new PersonID(relationJPA.getPersonIDOne());
                PersonID personIDTwo = new PersonID(relationJPA.getPersonIDTwo());
                RelationDesignation relationDesignation = new RelationDesignation(relationJPA.getDesignation());
                RelationID relationID = new RelationID(relationJPA.getId());

                Relation relation = new Relation(personIDOne, personIDTwo, relationDesignation, relationID);

                relationList.add(relation);
            }
        }
        return relationList;
    }

    public FamilyID createFamilyID(FamilyJPA familyJPA) {
        return new FamilyID(familyJPA.getId().toString());
    }

    public FamilyName createFamilyName(FamilyJPA familyJPA) {
        return new FamilyName(familyJPA.getFamilyName());
    }

    public RegistrationDate createRegistrationDate(FamilyJPA familyJPA) {
        return new RegistrationDate(familyJPA.getRegistrationDate());
    }

    public PersonID createAdminID(FamilyJPA familyJPA) {
        return new PersonID(familyJPA.getAdminID().toString());
    }

    public List<Relation> createRelationList(FamilyJPA familyJPA) {
        return generateRelationList(familyJPA.getRelationList());
    }

    public FamilyIDJPA createFamilyIDJPA(FamilyID familyID) {
        return new FamilyIDJPA(familyID.getId());
    }
}