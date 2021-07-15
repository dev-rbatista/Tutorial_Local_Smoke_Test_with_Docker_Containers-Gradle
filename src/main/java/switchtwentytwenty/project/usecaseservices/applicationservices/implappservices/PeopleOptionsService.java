package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.person.AddFamilyMemberDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.PersonRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IPeopleOptionsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PeopleOptionsService implements IPeopleOptionsService {

    public OptionsDTO getPeopleOptions(){
        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToPeopleOptions = linkTo(methodOn(PersonRESTController.class).peopleOptions()).withSelfRel();
        Link linkToPOST = linkTo(methodOn(PersonRESTController.class).addFamilyMember(new AddFamilyMemberDTO())).withRel("POST - Add Family Member");

        optionsDTO.add(linkToPeopleOptions);
        optionsDTO.add(linkToPOST);

        return optionsDTO;

    }

}


