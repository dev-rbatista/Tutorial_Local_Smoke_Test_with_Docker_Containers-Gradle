package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.family.InputChangeRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;

public interface IChangeRelationService {

    OutputRelationDTO changeRelation(InputChangeRelationDTO inputChangeRelationDTO);
}
