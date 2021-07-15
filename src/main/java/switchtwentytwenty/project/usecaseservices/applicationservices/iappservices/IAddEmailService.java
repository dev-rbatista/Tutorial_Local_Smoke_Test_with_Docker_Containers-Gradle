package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.person.InputEmailDTO;
import switchtwentytwenty.project.dto.person.OutputEmailDTO;


public interface IAddEmailService {
    OutputEmailDTO addEmail(InputEmailDTO internalEmailDTO);
}