package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.person.InputGetProfileDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;

@Service
public interface IGetFamilyMemberProfileService {

    OutputPersonDTO getFamilyMemberProfile(InputGetProfileDTO internalGetProfileDTO);

}
