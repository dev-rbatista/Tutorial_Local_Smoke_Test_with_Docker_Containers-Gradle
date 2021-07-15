package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;

public interface ICreateFamilyService {

    OutputFamilyDTO createFamilyAndAddAdmin(InputFamilyDTO inputFamilyDTO, InputPersonDTO inputPersonDTO);

}
