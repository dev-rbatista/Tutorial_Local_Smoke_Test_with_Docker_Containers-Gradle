package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyDataService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetFamilyDataServiceITDB {

    @Autowired
    IGetFamilyDataService getFamilyDataService;

    @Test
    void getFamilyDataAssertNotNull() {
        OutputFamilyDTO result = getFamilyDataService.getFamilyData("@tonyze@latinlover.com");
        assertNotNull(result);
    }
}