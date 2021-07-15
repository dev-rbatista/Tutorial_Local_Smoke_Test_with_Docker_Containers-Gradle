package switchtwentytwenty.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class OptionsDTOTest {

    @Test
    void testEquals() {
        OptionsDTO optionsDTO1 = new OptionsDTO();
        OptionsDTO optionsDTO2 = new OptionsDTO();
        OptionsDTO optionsDTO3 = new OptionsDTO();

        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        optionsDTO1.add(link);
        optionsDTO2.add(link);


        assertEquals(optionsDTO1, optionsDTO2);
        assertNotEquals(optionsDTO1,optionsDTO3);
    }

    @Test
    void testEqualsSucessSelf() {
        OptionsDTO optionsDTO1 = new OptionsDTO();
        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        optionsDTO1.add(link);
        assertTrue(optionsDTO1.equals(optionsDTO1));
    }

    @Test
    void testEqualsFailNull() {
        OptionsDTO optionsDTO1 = new OptionsDTO();
        OptionsDTO nullDTO = null;
        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        optionsDTO1.add(link);
        assertFalse(optionsDTO1.equals(nullDTO));
    }

    @Test
    void testEqualsFailNotSameClass() {
        OptionsDTO optionsDTO1 = new OptionsDTO();
        String notADTO = "string";
        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        optionsDTO1.add(link);
        assertFalse(optionsDTO1.equals(notADTO));
    }


    @Test
    void testHashCode() {
        OptionsDTO optionsDTO1 = new OptionsDTO();
        OptionsDTO optionsDTO2 = new OptionsDTO();
        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        optionsDTO1.add(link);
        optionsDTO2.add(link);

        assertEquals(optionsDTO1.hashCode(), optionsDTO2.hashCode());
        assertNotEquals(0, optionsDTO1.hashCode());
    }

}