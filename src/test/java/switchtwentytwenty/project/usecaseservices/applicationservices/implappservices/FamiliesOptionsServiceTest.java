package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IFamiliesOptionsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class FamiliesOptionsServiceTest {

    IFamiliesOptionsService service = new FamiliesOptionsService();

    @Test
    void getFamiliesOptions() {
        OptionsDTO expected = new OptionsDTO();
        Link linkToFamilyOptions = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withSelfRel();
        Link linkToAddFamily = linkTo(methodOn(FamilyRESTController.class).createFamilyAndSetAdmin(new AddFamilyAndSetAdminDTO())).withRel("Add new Family");
        expected.add(linkToFamilyOptions);
        expected.add(linkToAddFamily);

        OptionsDTO result = service.getFamiliesOptions();

        assertEquals(expected, result);
    }
}