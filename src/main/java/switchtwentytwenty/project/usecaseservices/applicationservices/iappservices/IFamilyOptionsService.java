package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.OptionsDTO;

public interface IFamilyOptionsService {

    OptionsDTO getFamilyOptions(String familyID);
}
