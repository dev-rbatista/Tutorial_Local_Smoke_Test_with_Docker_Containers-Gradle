package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.person.AddEmailDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.PersonRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class PersonOptionsServiceTest {

    PersonOptionsService personOptionsService = new PersonOptionsService();
    String personID = "tonyze@gmail.com";

    @Test
    void getPersonOptionsSuccess() {
        OptionsDTO result = personOptionsService.getPersonOptions(personID);

        OptionsDTO expected = new OptionsDTO();
        Link linkToPersonOptions = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
        Link linkToAddEmail = linkTo(methodOn(PersonRESTController.class).addEmail(new AddEmailDTO(), personID)).withRel("POST - Add new Email");
        Link linkToGetProfileInfo = linkTo(methodOn(PersonRESTController.class).getProfileInfo(personID)).withRel("GET - Get Profile Info");
        expected.add(linkToPersonOptions);
        expected.add(linkToAddEmail);
        expected.add(linkToGetProfileInfo);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected.getLinks(), result.getLinks());

    }

    @Test
    void getPersonOptionsFailure() {
        OptionsDTO result = personOptionsService.getPersonOptions(personID);

        OptionsDTO expected = new OptionsDTO();
        Link linkToPersonOptions = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
        Link linkToAddEmail = linkTo(methodOn(PersonRESTController.class).addEmail(new AddEmailDTO(), personID)).withRel("POST - Add new Email");
        Link linkToGetProfileInfo = linkTo(methodOn(PersonRESTController.class).getProfileInfo("notanemail")).withRel("GET - Get Profile Info");

        expected.add(linkToPersonOptions);
        expected.add(linkToAddEmail);
        expected.add(linkToGetProfileInfo);

        assertNotNull(result);
        assertNotEquals(expected.getLinks(), result.getLinks());
    }
}