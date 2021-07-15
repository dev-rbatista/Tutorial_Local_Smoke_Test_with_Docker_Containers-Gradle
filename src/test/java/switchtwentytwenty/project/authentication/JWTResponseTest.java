package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTResponseTest {

    @Test
    void getTokenTest() {
        JWTResponse jwtResponse = new JWTResponse("testToken");
        String expected = "testToken";

        String result = jwtResponse.getToken();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsForEqualResponses() {
        JWTResponse jwtResponseOne = new JWTResponse("testToken");
        JWTResponse jwtResponseTwo = new JWTResponse("testToken");

        assertEquals(jwtResponseOne,jwtResponseTwo);
        assertNotSame(jwtResponseOne,jwtResponseTwo);
    }

    @Test
    void testEqualsForSameResponses() {
        JWTResponse jwtResponseOne = new JWTResponse("testToken");
        JWTResponse jwtResponseTwo = jwtResponseOne;

        assertEquals(jwtResponseOne,jwtResponseTwo);
    }

    @Test
    void testEqualsForDifferentResponses() {
        JWTResponse jwtResponseOne = new JWTResponse("testToken");
        JWTResponse jwtResponseTwo = new JWTResponse("otherTestToken");

        assertNotEquals(jwtResponseOne,jwtResponseTwo);
    }

    @Test
    void testEqualsForDifferentObjects() {
        JWTResponse jwtResponse = new JWTResponse("testToken");
        String notJWTResponse = "Not a JWTResponse";

        assertNotEquals(jwtResponse,notJWTResponse);
    }

    @Test
    void testHashCodeSameHashCode() {
        JWTResponse jwtResponseOne = new JWTResponse("testToken");
        JWTResponse jwtResponseTwo = new JWTResponse("testToken");

        assertEquals(jwtResponseOne.hashCode(),jwtResponseTwo.hashCode());
        assertNotSame(jwtResponseOne,jwtResponseTwo);
    }

    @Test
    void testHashCodeDifferentHashCodes() {
        JWTResponse jwtResponseOne = new JWTResponse("testToken");
        JWTResponse jwtResponseTwo = new JWTResponse("otherTestToken");

        assertNotEquals(jwtResponseOne.hashCode(),jwtResponseTwo.hashCode());
    }
}