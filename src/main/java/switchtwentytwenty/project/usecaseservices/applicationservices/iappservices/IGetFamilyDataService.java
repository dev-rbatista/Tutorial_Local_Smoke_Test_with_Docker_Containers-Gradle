package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.family.OutputFamilyDTO;

public interface IGetFamilyDataService {
    OutputFamilyDTO getFamilyData(String familyID);
}
