package switchtwentytwenty.project.interfaceadapters.controller.icontrollers;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.person.AddEmailDTO;
import switchtwentytwenty.project.dto.person.AddFamilyMemberDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;

public interface IPersonRESTController {


    ResponseEntity<Object> addEmail(AddEmailDTO addEmailDTO, String personID);

    ResponseEntity<OutputPersonDTO> getProfileInfo(String personID);

    ResponseEntity<OptionsDTO> personOptions(String personID);

    ResponseEntity<OptionsDTO> peopleOptions();

    ResponseEntity<OutputPersonDTO> addFamilyMember(AddFamilyMemberDTO addFamilyMemberDTO);

    ResponseEntity<Object> removeEmailAddress(String emailToDelete, String personID);
}