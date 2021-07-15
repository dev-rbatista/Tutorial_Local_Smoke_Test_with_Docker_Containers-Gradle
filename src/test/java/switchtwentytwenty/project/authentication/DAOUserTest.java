package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserTest {

    @Test
    void setUsername() {
        DAOUser daoUser = new DAOUser();
        String expected = "TonyZe";

        daoUser.setUsername("TonyZe");
        String result = daoUser.getUsername();

        assertEquals(expected,result);
    }

    @Test
    void setPassword() {
        DAOUser daoUser = new DAOUser();
        String expected = "password";

        daoUser.setPassword("password");
        String result = daoUser.getPassword();

        assertEquals(expected,result);
    }

    @Test
    void setRole() {
        DAOUser daoUser = new DAOUser();
        String expected = "systemManager";

        daoUser.setRole("systemManager");
        String result = daoUser.getRole();

        assertEquals(expected,result);
    }

    @Test
    void getID() {
        DAOUser daoUser = new DAOUser();
        Long expected = 0L;

        Long result = daoUser.getID();

        assertEquals(expected,result);
    }
}