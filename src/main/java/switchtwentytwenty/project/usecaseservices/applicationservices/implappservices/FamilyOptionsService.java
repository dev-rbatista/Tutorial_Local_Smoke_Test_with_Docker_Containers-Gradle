package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IFamilyOptionsService;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FamilyOptionsService implements IFamilyOptionsService {


    private final IFamilyRepository iFamilyRepository;

    @Autowired
    public FamilyOptionsService(IFamilyRepository iFamilyRepository){
        this.iFamilyRepository = iFamilyRepository;
    }

    @Override
    public OptionsDTO getFamilyOptions(String familyID) {

        FamilyID iD = new FamilyID(familyID);

        iFamilyRepository.checkIfFamilyExists(iD);

        OptionsDTO options = new OptionsDTO();
        Link optionOne = linkTo(methodOn(FamilyRESTController.class).getFamily(familyID)).withSelfRel();
        Link optionTwo = linkTo(methodOn(FamilyRESTController.class).createRelation(new CreateRelationDTO(), familyID)).withRel("relations");
        Link optionThree = linkTo(methodOn(FamilyRESTController.class).getCategoriesOptions(familyID)).withRel("categories");

        options.add(optionOne);
        options.add(optionTwo);
        options.add(optionThree);

        return options;
    }
}