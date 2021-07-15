package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTRequestTest {

    @Test
    void getUsernameTest() {
        JWTRequest jwtRequest = new JWTRequest("TonyZe","password");
        String expected = "TonyZe";

        String result = jwtRequest.getUsername();

        assertEquals(expected,result);
    }

    @Test
    void setUsernameTest() {
        JWTRequest jwtRequest = new JWTRequest();
        String expected = "TonyZe";

        jwtRequest.setUsername("TonyZe");
        String result = jwtRequest.getUsername();

        assertEquals(expected,result);
    }

    @Test
    void getPasswordTest() {
        JWTRequest jwtRequest = new JWTRequest("TonyZe","password");
        String expected = "password";

        String result = jwtRequest.getPassword();

        assertEquals(expected,result);
    }
}