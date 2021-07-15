package switchtwentytwenty.project.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountNotRegisteredExceptionTest {

    @Test
    void accountAlreadyRegisteredException() {
        assertThrows(AccountNotRegisteredException.class, () -> {
            throw new AccountNotRegisteredException();
        });
    }

}