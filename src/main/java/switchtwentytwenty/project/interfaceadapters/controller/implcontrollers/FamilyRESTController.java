package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.CategoryInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.RelationInputDTOAssembler;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.dto.family.*;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;
import switchtwentytwenty.project.exceptions.InvalidEmailException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IFamilyRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/families")
@CrossOrigin(value = "*")
public class FamilyRESTController implements IFamilyRESTController {

    private static final String ERROR = "Error: ";
    private final ICreateFamilyService createFamilyService;
    private final FamilyInputDTOAssembler familyAssembler;
    private final PersonInputDTOAssembler personAssembler;
    private final CategoryInputDTOAssembler categoryAssembler;
    private final RelationInputDTOAssembler relationAssembler;
    private final IGetFamilyMembersAndRelationshipService getFamilyMembersAndRelationshipService;
    private final IFamiliesOptionsService familiesOptionsService;
    private final IFamilyOptionsService familyOptionsService;
    private final ICreateRelationService createRelationService;
    private final IGetCustomCategoriesService customCategoriesService;
    private final IGetFamilyDataService getFamilyDataService;
    private final ICreateCustomCategoryService createCustomCategoryService;
    private final IChangeRelationService changeRelationService;


    @Autowired
    public FamilyRESTController(ICreateFamilyService createFamilyService, FamilyInputDTOAssembler familyAssembler, RelationInputDTOAssembler relationAssembler, PersonInputDTOAssembler personAssembler, IGetFamilyMembersAndRelationshipService getFamilyMembersAndRelationshipService, IFamiliesOptionsService familiesOptionsService, IFamilyOptionsService familyOptionsService, ICreateRelationService createRelationService, IGetCustomCategoriesService customCategoriesService, IGetFamilyDataService getFamilyDataService, CategoryInputDTOAssembler categoryAssembler, ICreateCustomCategoryService createCustomCategoryService, IChangeRelationService changeRelationService) {
        this.createFamilyService = createFamilyService;
        this.familyAssembler = familyAssembler;
        this.personAssembler = personAssembler;
        this.relationAssembler = relationAssembler;
        this.getFamilyMembersAndRelationshipService = getFamilyMembersAndRelationshipService;
        this.familiesOptionsService = familiesOptionsService;
        this.familyOptionsService = familyOptionsService;
        this.createRelationService = createRelationService;
        this.customCategoriesService = customCategoriesService;
        this.getFamilyDataService = getFamilyDataService;
        this.categoryAssembler = categoryAssembler;
        this.createCustomCategoryService = createCustomCategoryService;
        this.changeRelationService = changeRelationService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<OptionsDTO> familiesOptions() {

        OptionsDTO options = familiesOptionsService.getFamiliesOptions();


        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "POST, OPTIONS");

        return new ResponseEntity(options, header, HttpStatus.OK);
    }

    /**
     * Method to create a family and add a person as administrator
     *
     * @param addFamilyAndSetAdminDTO
     * @return True if Family successfully created and added. False (by Exception e catch) if anything fails validation. False (by boolean false return on line 24) if admin email is already registered.
     */
    @PostMapping
    public ResponseEntity<OutputFamilyDTO> createFamilyAndSetAdmin(@RequestBody AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO) {
        InputPersonDTO inputPersonDTO = personAssembler.toInputPersonDTO(addFamilyAndSetAdminDTO);
        InputFamilyDTO inputFamilyDTO = familyAssembler.toInputFamilyDTO(addFamilyAndSetAdminDTO);

        HttpStatus status;
        OutputFamilyDTO outputFamilyDTO;

        try {
            outputFamilyDTO = createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO);
            status = HttpStatus.CREATED;

            Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamily(outputFamilyDTO.getFamilyID())).withSelfRel();
            outputFamilyDTO.add(selfLink);
            return new ResponseEntity<>(outputFamilyDTO, status);
        } catch (IllegalArgumentException | InvalidEmailException | InvalidDataAccessApiUsageException | EmailNotRegisteredException | PersonAlreadyRegisteredException e) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return new ResponseEntity(ERROR + e.getMessage(), status);

        }
    }

    @RequestMapping(value = "/{familyID}", method = RequestMethod.OPTIONS)
    public ResponseEntity<OptionsDTO> getFamilyOptions(@PathVariable String familyID) {

        OptionsDTO options = familyOptionsService.getFamilyOptions(familyID);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "OPTIONS");

        return new ResponseEntity<>(options, header, HttpStatus.OK);
    }

    @PostMapping("/{familyID}/relations")
    public ResponseEntity<Object> createRelation(@RequestBody CreateRelationDTO createRelationDTO, @PathVariable String familyID) {
        InputRelationDTO inputRelationDTO = familyAssembler.toInputRelationDTO(createRelationDTO, familyID);
        HttpStatus status;
        OutputRelationDTO outputRelationDTO;

        try {
            outputRelationDTO = createRelationService.createRelation(inputRelationDTO);
            status = HttpStatus.CREATED;
            Link optionsLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(familyID)).withSelfRel();
            outputRelationDTO.add(optionsLink);
            return new ResponseEntity<>(outputRelationDTO, status);
        } catch (Exception e) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity(ERROR + e.getMessage(), status);

        }

    }


    @PatchMapping("/{familyID}/relations/{relationID}")
    public ResponseEntity<OutputRelationDTO> changeRelation(@RequestBody ChangeRelationDTO changeRelationDTO, @PathVariable String familyID, @PathVariable String relationID) {
        InputChangeRelationDTO inputChangeRelationDTO = relationAssembler.toInputChangeRelationDTO(changeRelationDTO, familyID, relationID);

        HttpStatus status;
        OutputRelationDTO outputRelationDTO;

        try {
            outputRelationDTO = changeRelationService.changeRelation(inputChangeRelationDTO);
            status = HttpStatus.OK;
            Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations(familyID)).withSelfRel();
            outputRelationDTO.add(selfLink);
            return new ResponseEntity<>(outputRelationDTO, status);
        } catch (Exception e) {
            status = HttpStatus.NOT_MODIFIED;
            return new ResponseEntity(ERROR + e.getMessage(), status);

        }

    }


    @GetMapping("/{familyID}/relations")
    public ResponseEntity<FamilyMemberAndRelationsListDTO> getFamilyMembersAndRelations(@PathVariable String familyID) {
        HttpStatus status;
        FamilyMemberAndRelationsListDTO familyMemberAndRelationsListDTO;
        try {
            familyMemberAndRelationsListDTO = getFamilyMembersAndRelationshipService.getFamilyMembersAndRelations(familyID);
            status = HttpStatus.OK;
            Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations(familyID)).withSelfRel();
            familyMemberAndRelationsListDTO.add(selfLink);
            return new ResponseEntity<>(familyMemberAndRelationsListDTO, status);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException exception) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity(ERROR + exception.getMessage(), status);
        }

    }

    @GetMapping("/{familyID}")
    public ResponseEntity<OutputFamilyDTO> getFamily(@PathVariable String familyID) {
        HttpStatus status;
        OutputFamilyDTO outputFamilyDTO;
        try {
            outputFamilyDTO = getFamilyDataService.getFamilyData(familyID);
            status = HttpStatus.OK;
            Link optionsLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(familyID)).withSelfRel();
            outputFamilyDTO.add(optionsLink);
            return new ResponseEntity<>(outputFamilyDTO, status);
        } catch (InvalidDataAccessApiUsageException exception) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity(ERROR + exception.getMessage(), status);
        }
    }

    @GetMapping("/{familyID}/categories")
    public ResponseEntity<Object> getCategories(@PathVariable String familyID) {
        HttpStatus status;
        OutputCategoryTreeDTO outputCategoryTreeDTO;

        try {
            outputCategoryTreeDTO = customCategoriesService.getCustomCategories(familyID);
            status = HttpStatus.OK;

            Link selfLink = linkTo(methodOn(FamilyRESTController.class).getCategories(familyID)).withSelfRel();
            outputCategoryTreeDTO.add(selfLink);
            return new ResponseEntity<>(outputCategoryTreeDTO, status);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity(ERROR + e.getMessage(), status);
        }
    }

    @RequestMapping(value = "/{familyID}/categories", method = RequestMethod.OPTIONS)
    public ResponseEntity<OptionsDTO> getCategoriesOptions(@PathVariable String familyID) {
        throw new UnsupportedOperationException();
    }

    @PostMapping(value = "/{familyID}/categories")
    public ResponseEntity<OutputCategoryDTO> addCustomCategory(@PathVariable String familyID, @RequestBody CreateCategoryDTO createCategoryDTO) {
        InputCustomCategoryDTO inputCustomCategoryDTO = categoryAssembler.toInputCustomCategoryDTO(createCategoryDTO, familyID);

        HttpStatus httpStatus;
        try {
            OutputCategoryDTO outputCategoryDTO = createCustomCategoryService.createCustomCategory(inputCustomCategoryDTO);

            httpStatus = HttpStatus.CREATED;
            Link selfLink = linkTo(methodOn(FamilyRESTController.class).getCustomCategory(familyID, outputCategoryDTO.getCategoryID())).withSelfRel();
            outputCategoryDTO.add(selfLink);
            return new ResponseEntity<>(outputCategoryDTO, httpStatus);
        } catch (Exception e) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity(ERROR + e.getMessage(), httpStatus);
        }
    }

    @GetMapping(value = "/{familyID}/categories/{categoryID}")
    public ResponseEntity<OutputCategoryDTO> getCustomCategory(@PathVariable String familyID, @PathVariable String categoryID) {
        throw new UnsupportedOperationException();
    }
}