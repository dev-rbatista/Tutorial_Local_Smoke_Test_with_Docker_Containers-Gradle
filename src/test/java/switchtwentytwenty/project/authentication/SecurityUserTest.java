package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUserTest {

    String username = "username";
    String password = "password";
    String role = "role";
    SecurityUser securityUser = new SecurityUser(username, password, role);

    @Test
    void getAuthorities() {
        List<GrantedAuthority> expected = new ArrayList<GrantedAuthority>();
        expected.add(new SimpleGrantedAuthority("role"));

        Collection<? extends GrantedAuthority> result = securityUser.getAuthorities();
        assertEquals(expected, result);
    }

    @Test
    void getPassword() {
        String expected = "password";
        String result = securityUser.getPassword();
        assertEquals(expected, result);
    }

    @Test
    void getUsername() {
        String expected = "username";
        String result = securityUser.getUsername();
        assertEquals(expected, result);
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(securityUser.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(securityUser.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(securityUser.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(securityUser.isEnabled());
    }

    @Test
    void testEqualsNotSameObject() {
        SecurityUser securityUserTwo = new SecurityUser("username", "password", "role");
        assertEquals(securityUser, securityUserTwo);
        assertNotSame(securityUser, securityUserTwo);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(securityUser, securityUser);
        assertSame(securityUser, securityUser);
    }

    @Test
    void testNotEqualsUsername() {
        SecurityUser securityUserTwo = new SecurityUser("other", "password", "role");
        assertNotEquals(securityUser, securityUserTwo);
    }

    @Test
    void testNotEqualsPassword() {
        SecurityUser securityUserTwo = new SecurityUser("username", "other", "role");
        assertNotEquals(securityUser, securityUserTwo);
    }

    @Test
    void testNotEqualsRole() {
        SecurityUser securityUserTwo = new SecurityUser("username", "password", "other");
        assertNotEquals(securityUser, securityUserTwo);
    }

    @Test
    void testHashCodeSameObject() {
        assertEquals(securityUser.hashCode(), securityUser.hashCode());
    }

    @Test
    void testHashCodeEqualObject() {
        SecurityUser securityUserTwo = new SecurityUser("username", "password", "role");
        assertEquals(securityUser.hashCode(), securityUserTwo.hashCode());
    }

    @Test
    void testHashCodeNotEqual() {
        SecurityUser securityUserTwo = new SecurityUser("other", "password", "role");
        assertNotEquals(securityUser.hashCode(), securityUserTwo.hashCode());
    }
}