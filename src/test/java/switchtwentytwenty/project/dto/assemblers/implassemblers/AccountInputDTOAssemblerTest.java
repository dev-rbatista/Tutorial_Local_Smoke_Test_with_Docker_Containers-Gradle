package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountInputDTOAssemblerTest {

    AccountInputDTOAssembler accountInputDTOAssembler = new AccountInputDTOAssembler();
    String designation = "Conta";
    String designationTwo = "Account";
    BigDecimal initialAmount = BigDecimal.valueOf(3);
    BigDecimal initialAmountTwo = BigDecimal.valueOf(4);
    String currency = "EUR";
    String currencyTwo = "USD";
    String ownerID = "tonyze@tonyze.com";
    String ownerIDTwo = "tonyze@tony.com";
    String accountType = "cash";
    String accountTypeTwo = "bank";
    CreateAccountDTO createAccountDTO = new CreateAccountDTO(designation, initialAmount, currency, ownerID, accountType);




    @Test
    @DisplayName("Test successful conversion to InputAccountDTO")
    void toInputDTOCreateSuccessfulDTO() {
        InputAccountDTO expected = new InputAccountDTO(designation, initialAmount, currency, ownerID, accountType);

        InputAccountDTO result = accountInputDTOAssembler.toInputDTO(createAccountDTO);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test different DTOs expecting NotEquals")
    void verifyNotEqualsInInputDTOsWithDifferentDesignation() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        InputAccountDTO result = accountInputDTOAssembler.toInputDTO(createAccountDTO);

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different DTOs expecting NotEquals")
    void verifySameHashcodeInDifferentObjectsWithSameInformation() {
        InputAccountDTO expected = new InputAccountDTO(designation, initialAmount, currency, ownerID, accountType);

        InputAccountDTO result = accountInputDTOAssembler.toInputDTO(createAccountDTO);

        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    @DisplayName("Test different DTOs expecting NotEquals hashcode")
    void verifyDifferentHashcodeInDifferentDTOsExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        InputAccountDTO result = accountInputDTOAssembler.toInputDTO(createAccountDTO);

        assertNotEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    @DisplayName("Test same object expecting Equals")
    void verifySameObjectsExpectingEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        InputAccountDTO result = expected;

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test different objects expecting NotEquals")
    void verifyNullObjectExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        String result = null;

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different type object expecting Equals")
    void verifyDifferentTypeObjectsExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        String result = "3";

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different object expecting NotEquals due to different amount")
    void verifyObjectsWithDifferentAmount() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);
        InputAccountDTO result = new InputAccountDTO(designationTwo, initialAmountTwo, currency, ownerID, accountType);

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different object expecting NotEquals due to different currency")
    void verifyObjectsWithDifferentCurrencyExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currencyTwo, ownerID, accountType);
        InputAccountDTO result = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different object expecting NotEquals due to different ownerID")
    void verifyObjectsWithDifferentOwnerIDExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);
        InputAccountDTO result = new InputAccountDTO(designationTwo, initialAmount, currency, ownerIDTwo, accountType);

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test different object expecting NotEquals due to different ownerID")
    void verifyObjectsWithDifferentAccountTypeExpectingNotEquals() {
        InputAccountDTO expected = new InputAccountDTO(designationTwo, initialAmount, currency, ownerID, accountType);
        InputAccountDTO result = new InputAccountDTO(designationTwo, initialAmount, currency, ownerIDTwo, accountTypeTwo);

        assertNotEquals(expected, result);
    }
}