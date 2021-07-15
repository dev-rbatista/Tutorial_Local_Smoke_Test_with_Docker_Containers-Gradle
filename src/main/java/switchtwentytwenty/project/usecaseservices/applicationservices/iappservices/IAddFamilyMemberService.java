package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.person.InputAddFamilyMemberDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;

public interface IAddFamilyMemberService {
    OutputPersonDTO addPerson(InputAddFamilyMemberDTO internalAddFamilyMemberDTO);

}