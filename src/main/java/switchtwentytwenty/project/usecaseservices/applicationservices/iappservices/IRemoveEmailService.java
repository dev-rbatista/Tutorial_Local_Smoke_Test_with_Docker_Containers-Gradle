package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.person.InputRemoveEmailDTO;
import switchtwentytwenty.project.dto.person.OutputRemoveEmailDTO;

public interface IRemoveEmailService {

    OutputRemoveEmailDTO removeEmail(InputRemoveEmailDTO inputRemoveEmailDTO);

}
