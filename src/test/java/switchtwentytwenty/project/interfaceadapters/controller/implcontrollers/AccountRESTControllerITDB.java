package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountRESTControllerITDB {


    @Autowired
    AccountRESTController accountRESTController;

    @Test
    void testAccountAlreadyRegistered() {
        CreateAccountDTO tonyaccount = new CreateAccountDTO("Lavagem de dinheiro", BigDecimal.valueOf(12.3), "EUR", "@tonyze@latinlover.com", "cash");

        assertThrows(InvalidDataAccessApiUsageException.class, () -> accountRESTController.createAccount(tonyaccount));
    }

    @Test
    void testAccountSuccess() {
        CreateAccountDTO rifensAccount = new CreateAccountDTO("Lavagem de dinheiro", BigDecimal.valueOf(12.3), "EUR", "@rifens@ravens", "cash");

        assertDoesNotThrow(() -> accountRESTController.createAccount(rifensAccount));
    }

}
