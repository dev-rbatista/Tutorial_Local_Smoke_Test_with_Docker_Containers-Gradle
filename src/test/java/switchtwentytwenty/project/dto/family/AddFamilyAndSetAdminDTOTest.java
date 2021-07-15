package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddFamilyAndSetAdminDTOTest {

    @Test
    @DisplayName("Should return true if two identical AddFamilyAndSetAdminDTO objects are compared with the equals method")
    void testEquals() {
        AddFamilyAndSetAdminDTO dto1 = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dto2 = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertEquals(dto1,dto2);
        assertNotSame(dto1,dto2);
    }

    @Test
    @DisplayName("Should return false if two different AddFamilyAndSetAdminDTO objects are compared with the equals method")
    void testEqualsFailAllDifferent() {
        AddFamilyAndSetAdminDTO dto1 = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silvaaa", "13/12/1222", 999499999, 919499999, "Ruaaaa", "Cidaaade", "12aB", "4300-123", "passwordaa", "Silvaaa", "12/11/2000");
        AddFamilyAndSetAdminDTO dto2 = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dto1,dto2);
    }

    @Test
    @DisplayName("Should return false if two different AddFamilyAndSetAdminDTO objects are compared with the equals method")
    void testEqualsFail() {
        AddFamilyAndSetAdminDTO dto1 = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dto2 = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dto1,dto2);
    }
    @Test
    void testEqualsFailDifferentNames() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Costa", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentBirthDates() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "10/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentVatNumbers() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 199999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentPhoneNumbers() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 939999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentStreets() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua de Cima", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentCities() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Vila Norte", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentHouseNumbers() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "1200F", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentZipCodes() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-001", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentPasswords() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-123", "admin", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentFamilyName() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-001", "password", "Costa", "12/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-001", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFailDifferentRegistrationDate() {
        AddFamilyAndSetAdminDTO dtoOne = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-001", "password", "Silva", "01/12/2000");
        AddFamilyAndSetAdminDTO dtoTwo = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4000-001", "password", "Silva", "12/12/2000");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void getLocalDate() {
        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        String expectedLocalDate = "12/12/2000";

        String resultLocalDate = dto.getRegistrationDate();

        assertEquals(expectedLocalDate, resultLocalDate);
    }

    @Test
    void equalsSameObject() {
        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        assertEquals(dto, dto);
    }

    @Test
    void equalsWithNull() {
        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        AddFamilyAndSetAdminDTO nullDTO = null;

        assertNotEquals(dto, nullDTO);
    }

    @Test
    void equalsWithDifferentType() {
        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        String dto2 = "ttony@email.com";

        assertNotEquals(dto, dto2);
    }

    @Test
    void hashTestEquals() {
        AddFamilyAndSetAdminDTO dto1 = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dto2 = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        int hashDto1 = dto1.hashCode();
        int hashDto2 = dto2.hashCode();

        assertEquals(hashDto1,hashDto2);
    }

    @Test
    void hashTestNotEquals() {
        AddFamilyAndSetAdminDTO dto1 = new AddFamilyAndSetAdminDTO("ttony@email.com", "Pereira", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
        AddFamilyAndSetAdminDTO dto2 = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        int hashDto1 = dto1.hashCode();
        int hashDto2 = dto2.hashCode();

        assertNotEquals(hashDto1,hashDto2);
    }
}