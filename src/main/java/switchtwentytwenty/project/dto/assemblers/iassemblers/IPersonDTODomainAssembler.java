package switchtwentytwenty.project.dto.assemblers.iassemblers;

import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.person.IInputPersonDTO;
import switchtwentytwenty.project.dto.person.OutputEmailDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;

public interface IPersonDTODomainAssembler {

    Address createAddress(IInputPersonDTO inputPersonDTO);

    PhoneNumber createPhoneNumber(IInputPersonDTO inputPersonDTO);

    VATNumber createVATNumber(IInputPersonDTO inputPersonDTO);

    PersonID createPersonID(IInputPersonDTO inputPersonDTO);

    Name createName(IInputPersonDTO inputPersonDTO);

    BirthDate createBirthDate(IInputPersonDTO inputPersonDTO);

    OutputPersonDTO toDTO(Person savedPerson);

    OutputEmailDTO toEmailDTO(Person savedPerson);

}