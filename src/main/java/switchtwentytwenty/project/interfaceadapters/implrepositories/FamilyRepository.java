package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class FamilyRepository implements IFamilyRepository {

    private final IFamilyRepositoryJPA familyRepositoryJPA;

    private final FamilyDataDomainAssembler familyAssembler;

    @Autowired
    public FamilyRepository(IFamilyRepositoryJPA iFamilyRepositoryJPA, FamilyDataDomainAssembler familyDataDomainAssembler) {
        this.familyRepositoryJPA = iFamilyRepositoryJPA;
        this.familyAssembler = familyDataDomainAssembler;
    }


    /**
     * Method to add a Family domain object to the repository.
     * The Family domain object will be converted into a FamilyJPA data object and saved in the repository.
     *
     * @param family domain object we want to add to the family repository
     * @return returns a domain object of the type Family with the id generated in the database
     */
    @Override
    public Family add(Family family) {
        FamilyJPA registeredFamilyJPA;
        Family registeredFamily;
        FamilyJPA familyJPA = familyAssembler.toData(family);
        registeredFamilyJPA = familyRepositoryJPA.save(familyJPA);
        registeredFamily = createFamily(registeredFamilyJPA);
        return registeredFamily;
    }

    @Override
    public void checkIfFamilyExists(FamilyID familyID) {
        FamilyIDJPA familyIDJPA = familyAssembler.createFamilyIDJPA(familyID);

        if (!familyRepositoryJPA.existsFamilyJPAById(familyIDJPA)){
            throw new AccountNotRegisteredException();
        }
    }

    /**
     * Method to retrieve a Family domain object by presenting a FamilyID
     *
     * @param familyID domain object of the Family we want to obtain
     * @return Family domain object
     */
    public Family getByID(FamilyID familyID) {
        FamilyIDJPA familyIDJPA = familyAssembler.createFamilyIDJPA(familyID);
        Optional<FamilyJPA> familyJPA = familyRepositoryJPA.findById(familyIDJPA);
        if (familyJPA.isPresent()) {
            return createFamily(familyJPA.get());
        } else {
            throw new IllegalArgumentException("Family does not exist");
        }
    }

    private Family createFamily(FamilyJPA familyJPA) {
        FamilyID familyID = familyAssembler.createFamilyID(familyJPA);
        FamilyName familyName = familyAssembler.createFamilyName(familyJPA);
        RegistrationDate registrationDate = familyAssembler.createRegistrationDate(familyJPA);
        PersonID adminEmail = familyAssembler.createAdminID(familyJPA);
        List<Relation> relationList = familyAssembler.createRelationList(familyJPA);

        return new Family(familyID, familyName, registrationDate, adminEmail, relationList);
    }

}