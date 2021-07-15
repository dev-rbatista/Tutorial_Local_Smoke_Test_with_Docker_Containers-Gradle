package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class FamilyOptionsServiceTest {

    @Mock
    IFamilyRepository iFamilyRepository;

    @InjectMocks
    FamilyOptionsService familyOptionsService;

    @DisplayName("Get Family Options in Service - Success")
    @Test
    void getFamilyOptions() {
        String familyID = "@tonyze@email.com";

        doNothing().when(iFamilyRepository).checkIfFamilyExists(any(FamilyID.class));

        // Expected
        OptionsDTO options = new OptionsDTO();
        Link optionOne = linkTo(methodOn(FamilyRESTController.class).getFamily(familyID)).withSelfRel();
        Link optionTwo = linkTo(methodOn(FamilyRESTController.class).createRelation(new CreateRelationDTO(), familyID)).withRel("relations");
        Link optionThree = linkTo(methodOn(FamilyRESTController.class).getCategoriesOptions(familyID)).withRel("categories");

        options.add(optionOne);
        options.add(optionTwo);
        options.add(optionThree);

        OptionsDTO result = familyOptionsService.getFamilyOptions(familyID);

        assertNotNull(result);
        assertEquals(options, result);

    }

    @DisplayName("Get Family Options in Service - Failure - throw AccountNotRegisteredException")
    @Test
    void getFamilyOptionsFailure() {
        String familyID = "@tonyze@email.com";

        doThrow(AccountNotRegisteredException.class).when(iFamilyRepository).checkIfFamilyExists(any(FamilyID.class));

        assertThrows(AccountNotRegisteredException.class, () -> familyOptionsService.getFamilyOptions(familyID));
    }
}