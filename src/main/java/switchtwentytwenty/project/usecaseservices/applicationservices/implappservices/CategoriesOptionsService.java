package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.CategoryRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICategoriesOptionsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CategoriesOptionsService implements ICategoriesOptionsService {

    public OptionsDTO getCategoriesOptions() {

        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToCategoriesOptions = linkTo(methodOn(CategoryRESTController.class).categoriesOptions()).withSelfRel();
        Link linkToCreateStandardCategory = linkTo(methodOn(CategoryRESTController.class).createStandardCategory(new CreateCategoryDTO())).withRel("Add new Standard Category");

        optionsDTO.add(linkToCategoriesOptions);
        optionsDTO.add(linkToCreateStandardCategory);

        return optionsDTO;
    }

}
