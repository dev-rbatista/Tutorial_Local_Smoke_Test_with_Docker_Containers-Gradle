package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.family.InputRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;

@Component
public interface ICreateRelationService {

    OutputRelationDTO createRelation(InputRelationDTO inputRelationDTO);
}