package switchtwentytwenty.project.dto.accounts;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountDTOTest {

    String designation = "account";
    BigDecimal initialAmount = BigDecimal.valueOf(3);
    String currency = "EUR";
    String ownerID = "tonyze@latinlover.com";
    String accountType = "bank";
    CreateAccountDTO createAccountDTO = new CreateAccountDTO(designation, initialAmount, currency, ownerID, accountType);

    @Test
    void getDesignation() {
        String expected = designation;
        String result = createAccountDTO.getDesignation();
        assertEquals(expected, result);
    }

    @Test
    void getInitialAmount() {
        BigDecimal expected = initialAmount;
        BigDecimal result = createAccountDTO.getInitialAmount();
        assertEquals(expected, result);
    }

    @Test
    void getCurrency() {
        String expected = currency;
        String result = createAccountDTO.getCurrency();
        assertEquals(expected, result);
    }

    @Test
    void getOwnerID() {
        String expected = ownerID;
        String result = createAccountDTO.getOwnerID();
        assertEquals(expected, result);
    }

    @Test
    void getAccountType() {
        String expected = accountType;
        String result = createAccountDTO.getAccountType();
        assertEquals(expected, result);
    }

    @Test
    void setDesignation() {
        String expected = designation;
        createAccountDTO.setDesignation(expected);
        String result = createAccountDTO.getDesignation();
        assertEquals(expected, result);
    }

    @Test
    void setInitialAmount() {
        BigDecimal expected = initialAmount;
        createAccountDTO.setInitialAmount(expected);
        BigDecimal result = createAccountDTO.getInitialAmount();
        assertEquals(expected, result);
    }

    @Test
    void setCurrency() {
        String expected = currency;
        createAccountDTO.setCurrency(expected);
        String result = createAccountDTO.getCurrency();
        assertEquals(expected, result);
    }

    @Test
    void setOwnerID() {
        String expected = ownerID;
        createAccountDTO.setOwnerID(expected);
        String result = createAccountDTO.getOwnerID();
        assertEquals(expected, result);
    }

    @Test
    void setAccountType() {
        String expected = accountType;
        createAccountDTO.setAccountType(expected);
        String result = createAccountDTO.getAccountType();
        assertEquals(expected, result);
    }
}