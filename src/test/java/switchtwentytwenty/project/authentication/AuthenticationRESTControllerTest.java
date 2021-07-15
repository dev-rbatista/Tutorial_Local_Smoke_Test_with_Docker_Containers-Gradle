package switchtwentytwenty.project.authentication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationRESTControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JWTokenUtil jwtTokenUtil;

    @Mock
    JWTUserDetailsService userDetailsService;

    @InjectMocks
    AuthenticationRESTController controller;

    @Test
    void createAuthenticationTokenDisabledUserThrowsExceptionTest() {
        JWTRequest jwtRequest = new JWTRequest("name","pass");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new DisabledException(""));

        ResponseEntity expected = new ResponseEntity("USER_DISABLED", HttpStatus.UNAUTHORIZED);

        ResponseEntity result = controller.createAuthenticationToken(jwtRequest);

        assertEquals(expected, result);
    }

    @Test
    void createAuthenticationTokenBadCredentialsThrowsExceptionTest() {
        JWTRequest jwtRequest = new JWTRequest("name","pass");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException(""));

        ResponseEntity expected = new ResponseEntity("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);

        ResponseEntity result = controller.createAuthenticationToken(jwtRequest);

        assertEquals(expected, result);
    }

    @Test
    void createAuthenticationTokenSuccessTest() {
            JWTRequest jwtRequest = new JWTRequest("name", "pass");
            UserDetails userDetails = new org.springframework.security.core.userdetails.User("Name", "Pass", new ArrayList<>());
            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(any());
            when(userDetailsService.loadUserByUsername("Name")).thenReturn(userDetails);
            when(jwtTokenUtil.generateToken(any(UserDetails.class))).thenReturn("ThisIsAToken");
            JWTResponse response = new JWTResponse("ThisIsAToken");
            ResponseEntity expected = new ResponseEntity(response, HttpStatus.OK);

            ResponseEntity result = controller.createAuthenticationToken(jwtRequest);

            assertEquals(expected, result);
    }

    @Test
    void saveUserTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Name");
        userDTO.setPassword("Pass");
        DAOUser daoUser = new DAOUser();
        daoUser.setUsername("Name");
        daoUser.setPassword("Pass");
        ResponseEntity expected = new ResponseEntity(daoUser, HttpStatus.OK);

        when(userDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);

        ResponseEntity result = controller.saveUser(userDTO);

        assertEquals(expected,result);
    }

}