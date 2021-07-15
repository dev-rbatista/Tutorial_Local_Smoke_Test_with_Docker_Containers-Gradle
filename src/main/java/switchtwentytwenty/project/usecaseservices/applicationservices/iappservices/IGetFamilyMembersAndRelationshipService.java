package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import org.springframework.stereotype.Service;
import switchtwentytwenty.project.dto.family.FamilyMemberAndRelationsListDTO;

@Service
public interface IGetFamilyMembersAndRelationshipService {
    FamilyMemberAndRelationsListDTO getFamilyMembersAndRelations(String familyID);
}
