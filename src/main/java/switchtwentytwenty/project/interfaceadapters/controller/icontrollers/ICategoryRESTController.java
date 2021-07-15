package switchtwentytwenty.project.interfaceadapters.controller.icontrollers;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;


public interface ICategoryRESTController {

    ResponseEntity<OutputCategoryDTO> createStandardCategory(CreateCategoryDTO createCategoryDTO);

    ResponseEntity<OptionsDTO> categoriesOptions();

    ResponseEntity<Object> getCategory(String categoryID);

    ResponseEntity<Object> getOwnCategories();

    ResponseEntity<Object> getAllCategories();

}
