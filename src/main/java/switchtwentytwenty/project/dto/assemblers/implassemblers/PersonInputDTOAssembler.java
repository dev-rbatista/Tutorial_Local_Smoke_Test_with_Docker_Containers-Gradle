package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.person.*;

@Component
public class PersonInputDTOAssembler {

    public InputAddFamilyMemberDTO toInputAddFamilyMemberDTO(AddFamilyMemberDTO addFamilyMemberDTO) {

        InputAddFamilyMemberDTO internalAddFamilyMemberDTO = new InputAddFamilyMemberDTO();
        internalAddFamilyMemberDTO.setAdminID(addFamilyMemberDTO.getAdminID());
        internalAddFamilyMemberDTO.setEmailID(addFamilyMemberDTO.getEmailID());
        internalAddFamilyMemberDTO.setName(addFamilyMemberDTO.getName());
        internalAddFamilyMemberDTO.setBirtDate(addFamilyMemberDTO.getBirthDate());
        internalAddFamilyMemberDTO.setVatNumber(addFamilyMemberDTO.getVatNumber());
        internalAddFamilyMemberDTO.setPhone(addFamilyMemberDTO.getPhone());
        internalAddFamilyMemberDTO.setStreet(addFamilyMemberDTO.getStreet());
        internalAddFamilyMemberDTO.setCity(addFamilyMemberDTO.getCity());
        internalAddFamilyMemberDTO.setHouseNumber(addFamilyMemberDTO.getHouseNumber());
        internalAddFamilyMemberDTO.setZipCode(addFamilyMemberDTO.getZipCode());
        internalAddFamilyMemberDTO.setPassword(addFamilyMemberDTO.getPassword());

        return internalAddFamilyMemberDTO;
    }

    public InputGetProfileDTO toInternalGetProfileDTO(String personID){
        InputGetProfileDTO internalGetProfileDTO = new InputGetProfileDTO();
        internalGetProfileDTO.setId(personID);
        return internalGetProfileDTO;
    }

    /**
     * This method receives an External DTO and converts it to an Internal DTO.
     * @param addEmailDTO
     * @return InternalEmailDTO (Contains info required to add an Email to an already existing person)
     */
    public InputEmailDTO toInputEmailDTO(AddEmailDTO addEmailDTO, String personID) {
        InputEmailDTO inputEmailDTO = new InputEmailDTO();

        inputEmailDTO.setEmail(addEmailDTO.unpackEmail());
        inputEmailDTO.setId(personID);

        return inputEmailDTO;
    }

    public InputPersonDTO toInputPersonDTO(AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO) {
        return new InputPersonDTO(addFamilyAndSetAdminDTO.getEmailID(), addFamilyAndSetAdminDTO.getName(), addFamilyAndSetAdminDTO.getBirthDate(), addFamilyAndSetAdminDTO.getVatNumber(), addFamilyAndSetAdminDTO.getPhone(), addFamilyAndSetAdminDTO.getStreet(), addFamilyAndSetAdminDTO.getCity(), addFamilyAndSetAdminDTO.getHouseNumber(), addFamilyAndSetAdminDTO.getZipCode(), addFamilyAndSetAdminDTO.getPassword());
    }

    public InputRemoveEmailDTO toInputRemoveEmail(String emailToDelete, String userID){
        return new InputRemoveEmailDTO(emailToDelete, userID);
    }

}