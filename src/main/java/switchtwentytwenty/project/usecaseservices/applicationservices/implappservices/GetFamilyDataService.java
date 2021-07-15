package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyDataService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

@Service
public class GetFamilyDataService implements IGetFamilyDataService {

    IFamilyRepository familyRepository;
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @Autowired
    public GetFamilyDataService(IFamilyRepository familyRepository, FamilyDTODomainAssembler familyDTODomainAssembler) {
        this.familyRepository = familyRepository;
        this.familyDTODomainAssembler = familyDTODomainAssembler;
    }

    @Override
    public OutputFamilyDTO getFamilyData(String familyIDString) {
        FamilyID familyID = familyDTODomainAssembler.familyIDToDomain(familyIDString);
        Family family = familyRepository.getByID(familyID);
        return familyDTODomainAssembler.toOutputFamilyDTO(family);
    }
}
