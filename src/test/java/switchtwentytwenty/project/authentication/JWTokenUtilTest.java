package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class JWTokenUtilTest {

    String secret = "apestogetherstrong";

    JWTokenUtil jwTokenUtil = new JWTokenUtil(secret);

    // User
    String username = "tonyze@latinlover.com";
    String password = "password";
    String role = "systemManager";

    @Test
    void generateTokenSuccess() {
        DAOUser daoUser = new DAOUser();
        daoUser.setUsername(username);
        daoUser.setPassword(password);
        daoUser.setRole(role);

        UserDetails userDetails = new SecurityUser(daoUser.getUsername(), daoUser.getPassword(), daoUser.getRole());

        String jwtEncryptionExpected = "eyJhbGciOiJIUzUxMiJ9";

        String result = jwTokenUtil.generateToken(userDetails);

        assertNotNull(result);
        assertEquals(jwtEncryptionExpected, result.split("\\.")[0]);
        assertTrue(jwTokenUtil.validateToken(result, userDetails));
        assertEquals(jwTokenUtil.getUsernameFromToken(result), username);
    }

}