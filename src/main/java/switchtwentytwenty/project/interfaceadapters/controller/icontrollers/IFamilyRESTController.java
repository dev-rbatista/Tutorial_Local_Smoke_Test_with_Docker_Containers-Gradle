package switchtwentytwenty.project.interfaceadapters.controller.icontrollers;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

import switchtwentytwenty.project.dto.family.*;

import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.family.ChangeRelationDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;


public interface IFamilyRESTController {

    ResponseEntity<OutputRelationDTO> changeRelation(ChangeRelationDTO changeRelationDTO, String familyID, String relationID);

    ResponseEntity<OutputFamilyDTO> createFamilyAndSetAdmin(AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO);

    ResponseEntity<OptionsDTO> familiesOptions();

    ResponseEntity<OptionsDTO> getFamilyOptions(String familyID);

    ResponseEntity<Object> createRelation(CreateRelationDTO createRelationDTO, String familyID);

    ResponseEntity<OutputFamilyDTO> getFamily(String familyName);

    ResponseEntity<Object> getCategories(String familyID);

    ResponseEntity<OutputCategoryDTO> addCustomCategory(String familyID, CreateCategoryDTO createCategoryDTO);


}