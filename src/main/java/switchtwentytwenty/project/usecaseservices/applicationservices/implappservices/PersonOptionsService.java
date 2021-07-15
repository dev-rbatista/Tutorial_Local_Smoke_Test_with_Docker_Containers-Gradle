package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.person.AddEmailDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.PersonRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IPersonOptionsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonOptionsService implements IPersonOptionsService {

    public OptionsDTO getPersonOptions(String personID) {

        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToPersonOptions = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
        Link linkToAddEmail = linkTo(methodOn(PersonRESTController.class).addEmail(new AddEmailDTO(), personID)).withRel("POST - Add new Email");
        Link linkToGetProfileInfo = linkTo(methodOn(PersonRESTController.class).getProfileInfo(personID)).withRel("GET - Get Profile Info");

        optionsDTO.add(linkToPersonOptions);
        optionsDTO.add(linkToAddEmail);
        optionsDTO.add(linkToGetProfileInfo);

        return optionsDTO;
    }
}
