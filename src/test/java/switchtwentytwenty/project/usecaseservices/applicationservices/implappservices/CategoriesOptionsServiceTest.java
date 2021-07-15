package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.CategoryRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICategoriesOptionsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
class CategoriesOptionsServiceTest {

    @Autowired
    ICategoriesOptionsService categoriesOptionsService;

    @Test
    void getCategoriesOptions() {
        OptionsDTO expected = new OptionsDTO();
        Link linkToPeopleOptions = linkTo(methodOn(CategoryRESTController.class).categoriesOptions()).withSelfRel();
        Link linkToPOST = linkTo(methodOn(CategoryRESTController.class).createStandardCategory(new CreateCategoryDTO())).withRel("Add new Standard Category");
        expected.add(linkToPeopleOptions);
        expected.add(linkToPOST);

        OptionsDTO result = categoriesOptionsService.getCategoriesOptions();

        assertNotNull(result);
        assertEquals(expected,result);
        assertNotSame(expected,result);

    }
}