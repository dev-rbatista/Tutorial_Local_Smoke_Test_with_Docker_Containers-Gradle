package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.person.AddFamilyMemberDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.PersonRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IPeopleOptionsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
class PeopleOptionsServiceTest {

    @Autowired
    IPeopleOptionsService peopleOptionsService;

    @Test
    void getPeopleOptions() {
        OptionsDTO expected = new OptionsDTO();
        Link linkToPeopleOptions = linkTo(methodOn(PersonRESTController.class).peopleOptions()).withSelfRel();
        Link linkToPOST = linkTo(methodOn(PersonRESTController.class).addFamilyMember(new AddFamilyMemberDTO())).withRel("POST - Add Family Member");
        expected.add(linkToPeopleOptions);
        expected.add(linkToPOST);

        OptionsDTO result = peopleOptionsService.getPeopleOptions();

        assertNotNull(result);
        assertEquals(expected,result);
        assertNotSame(expected,result);
    }
}