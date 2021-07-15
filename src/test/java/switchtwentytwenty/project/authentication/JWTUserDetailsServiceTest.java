package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JWTUserDetailsServiceTest {

    @Mock
    UserDao userDao;

    @Mock
    PasswordEncoder bcryptEncoder;

    @InjectMocks
    JWTUserDetailsService jwtUserDetailsService;

    // User strings
    String username = "tonyze@latinlover.com";
    String password = "password";
    String role = "systemManager";

    @Test
    void loadUserByUsernameThrowsNoSuchUser() {
        when(userDao.findByUsername(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));
    }

    @Test
    void loadUserByUsernameSuccess() {
        DAOUser daoUser = new DAOUser();
        daoUser.setUsername(username);
        daoUser.setPassword(password);
        daoUser.setRole(role);

        when(userDao.findByUsername(anyString())).thenReturn(daoUser);

        UserDetails expected = new SecurityUser(daoUser.getUsername(), daoUser.getPassword(), daoUser.getRole());

        UserDetails result = jwtUserDetailsService.loadUserByUsername(username);

        assertEquals(expected, result);
    }

    @Test
    void saveSuccess() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setRole(role);

        DAOUser expected = new DAOUser();
        expected.setUsername(username);
        expected.setPassword(password);
        expected.setRole(role);

        when(bcryptEncoder.encode(anyString())).thenReturn(password);
        when(userDao.save(any())).thenReturn(expected);

        DAOUser result = jwtUserDetailsService.save(userDTO);

        assertEquals(expected, result);
    }

    @Test
    void saveThrowsNullPassword() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(null);
        userDTO.setRole(role);

        when(bcryptEncoder.encode(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> jwtUserDetailsService.save(userDTO));
    }
}