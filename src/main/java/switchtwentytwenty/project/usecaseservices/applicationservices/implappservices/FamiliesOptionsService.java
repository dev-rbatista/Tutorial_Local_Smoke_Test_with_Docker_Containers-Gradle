package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IFamiliesOptionsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FamiliesOptionsService implements IFamiliesOptionsService {

    @Override
    public OptionsDTO getFamiliesOptions() {

        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToFamilyOptions = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withSelfRel();
        Link linkToAddFamily = linkTo(methodOn(FamilyRESTController.class).createFamilyAndSetAdmin(new AddFamilyAndSetAdminDTO())).withRel("Add new Family");

        optionsDTO.add(linkToFamilyOptions);
        optionsDTO.add(linkToAddFamily);

        return optionsDTO;
    }
}
