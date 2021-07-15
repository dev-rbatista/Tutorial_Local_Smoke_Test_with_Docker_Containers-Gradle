package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.person.*;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IPersonRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.*;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.PeopleOptionsService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.PersonOptionsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/people")
@CrossOrigin(value = "*")
public class PersonRESTController implements IPersonRESTController {

    private static final String ERROR = "Error: ";
    private IGetFamilyMemberProfileService getFamilyMemberProfileService;
    private IAddFamilyMemberService addFamilyMemberService;
    private IAddEmailService addEmailService;
    private IRemoveEmailService removeEmailService;
    private PersonInputDTOAssembler personInputDTOAssembler;
    private PersonInputDTOAssembler profileInternalExternalAssembler;
    private IPersonOptionsService personOptionsService;
    private IPeopleOptionsService peopleOptionsService;

    @Autowired
    public PersonRESTController(PeopleOptionsService peopleOptionsService, PersonOptionsService personOptionsService, PersonInputDTOAssembler profileInternalExternalAssembler, IGetFamilyMemberProfileService getFamilyMemberProfileService, IAddFamilyMemberService addFamilyMemberService, PersonInputDTOAssembler personInputDTOAssembler, IAddEmailService addEmailService, IRemoveEmailService removeEmailService) {
        this.getFamilyMemberProfileService = getFamilyMemberProfileService;
        this.addFamilyMemberService = addFamilyMemberService;
        this.personInputDTOAssembler = personInputDTOAssembler;
        this.profileInternalExternalAssembler = profileInternalExternalAssembler;
        this.addEmailService = addEmailService;
        this.personOptionsService = personOptionsService;
        this.peopleOptionsService = peopleOptionsService;
        this.removeEmailService = removeEmailService;
    }

    @Override
    @PostMapping(value = "/{personID}/emails")
    public ResponseEntity<Object> addEmail(@RequestBody AddEmailDTO addEmailDTO, @PathVariable String personID) {
        InputEmailDTO inputEmailDTO = personInputDTOAssembler.toInputEmailDTO(addEmailDTO, personID);

        HttpStatus status;
        OutputEmailDTO outputEmailDTO;

        try {
            outputEmailDTO = addEmailService.addEmail(inputEmailDTO);
            status = HttpStatus.OK;

            Link personSelfLink = linkTo(methodOn(PersonRESTController.class).getProfileInfo(personID)).withSelfRel();
            outputEmailDTO.add(personSelfLink);
            return new ResponseEntity<>(outputEmailDTO, status);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException | IllegalStateException e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(ERROR + e.getMessage(), status);
        }
    }

    @Override
    @DeleteMapping(value = "/{personID}/emails/{email}")
    public ResponseEntity<Object> removeEmailAddress( @PathVariable String email, @PathVariable String personID) {
        InputRemoveEmailDTO inputRemoveEmailDTO = personInputDTOAssembler.toInputRemoveEmail(email, personID);
        OutputRemoveEmailDTO outputRemoveEmailDTO;

        try {
            outputRemoveEmailDTO = removeEmailService.removeEmail(inputRemoveEmailDTO);
            return new ResponseEntity<>(outputRemoveEmailDTO, HttpStatus.OK);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseEntity<>(ERROR + e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<OutputPersonDTO> addFamilyMember(@RequestBody AddFamilyMemberDTO addFamilyMemberDTO) {
        InputAddFamilyMemberDTO internalAddFamilyMemberDTO = personInputDTOAssembler.toInputAddFamilyMemberDTO(addFamilyMemberDTO);

        HttpStatus status;
        OutputPersonDTO outputPersonDTO;

        try {
            outputPersonDTO = addFamilyMemberService.addPerson(internalAddFamilyMemberDTO);
            status = HttpStatus.CREATED;
            Link personOptionsLink = linkTo(methodOn(PersonRESTController.class).personOptions(outputPersonDTO.getId())).withRel("Person Options");
            outputPersonDTO.add(personOptionsLink);
            return new ResponseEntity<>(outputPersonDTO, status);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException | IllegalStateException e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity(ERROR + e.getMessage(), status);
        }

    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<OptionsDTO> peopleOptions() {
        OptionsDTO optionsDTO = peopleOptionsService.getPeopleOptions();

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "POST, OPTIONS");

        return new ResponseEntity<>(optionsDTO, header, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personID}", method = RequestMethod.OPTIONS)
    public ResponseEntity<OptionsDTO> personOptions(@PathVariable String personID) {
        OptionsDTO optionsDTO = personOptionsService.getPersonOptions(personID);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "OPTIONS");

        return new ResponseEntity<>(optionsDTO, header, HttpStatus.OK);
    }


    @GetMapping(value = "/{personID}")
    public ResponseEntity<OutputPersonDTO> getProfileInfo(@PathVariable String personID) {

        try {
            InputGetProfileDTO inputGetProfileDTO = profileInternalExternalAssembler.toInternalGetProfileDTO(personID);
            OutputPersonDTO outputPersonDTO = getFamilyMemberProfileService.getFamilyMemberProfile(inputGetProfileDTO);

            Link link = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
            Link familyLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(outputPersonDTO.getFamilyID())).withRel("Family Link");
            outputPersonDTO.add(link);
            outputPersonDTO.add(familyLink);
            return new ResponseEntity<>(outputPersonDTO, HttpStatus.OK);

        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException | IllegalStateException e) {

            return new ResponseEntity(ERROR + e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        }

    }


}