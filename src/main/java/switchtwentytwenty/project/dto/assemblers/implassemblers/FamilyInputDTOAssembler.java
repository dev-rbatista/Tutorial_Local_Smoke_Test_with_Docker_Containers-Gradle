package switchtwentytwenty.project.dto.assemblers.implassemblers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.dto.family.InputRelationDTO;

@Component
@NoArgsConstructor
public class FamilyInputDTOAssembler {

    // Tirei os statics e meti como component, João Pinto

    public InputFamilyDTO toInputFamilyDTO(AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO) {
        return new InputFamilyDTO(addFamilyAndSetAdminDTO.getFamilyName(), addFamilyAndSetAdminDTO.getRegistrationDate());

    }

    public InputRelationDTO toInputRelationDTO(CreateRelationDTO createRelationDTO, String familyID) {
        InputRelationDTO inputRelationDTO = new InputRelationDTO();

        inputRelationDTO.setPersonIDOne(createRelationDTO.getMemberOneID());
        inputRelationDTO.setPersonIDTwo(createRelationDTO.getMemberTwoID());
        inputRelationDTO.setDesignation(createRelationDTO.getRelationDesignation());
        inputRelationDTO.setFamilyID(familyID);

        return inputRelationDTO;
    }
}
