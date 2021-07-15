package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void setUsernameTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("TonyZe");
        String expected = "TonyZe";

        String result = userDTO.getUsername();

        assertEquals(expected,result);
    }

    @Test
    void setPasswordTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password");
        String expected = "password";

        String result = userDTO.getPassword();

        assertEquals(expected,result);
    }

    @Test
    void setRoleTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("familyAdministrator");
        String expected = "familyAdministrator";

        String result = userDTO.getRole();

        assertEquals(expected, result);
    }
}