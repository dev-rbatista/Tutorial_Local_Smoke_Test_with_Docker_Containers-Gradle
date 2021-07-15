package switchtwentytwenty.project.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountAlreadyRegisteredExceptionTest {

    @Test
    void accountAlreadyRegisteredException() {
        assertThrows(AccountAlreadyRegisteredException.class, () -> {
            throw new AccountAlreadyRegisteredException();
        });
    }
}