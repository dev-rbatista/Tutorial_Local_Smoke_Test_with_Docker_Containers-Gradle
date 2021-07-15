package switchtwentytwenty.project.dto.accounts;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InputAccountDTOTest {
    InputAccountDTO inputAccountDTO = new InputAccountDTO();

    @Test
    void createSuccessfulObjectNoArgsConstructor(){
        assertNotNull(inputAccountDTO);
    }

    @Test
    void setDesignation() {
        String expected = "Account";
        inputAccountDTO.setDesignation(expected);
        String result = inputAccountDTO.getDesignation();
        assertEquals(expected, result);
    }

    @Test
    void setInitialAmount() {
        BigDecimal expected = BigDecimal.valueOf(3);
        inputAccountDTO.setInitialAmount(expected);
        assertEquals(expected, inputAccountDTO.getInitialAmount());
    }

    @Test
    void setCurrency() {
        String expected = "EUR";
        inputAccountDTO.setCurrency(expected);
        String result = inputAccountDTO.getCurrency();
        assertEquals(expected, result);
    }

    @Test
    void setOwnerID() {
        String expected = "tonyze@latinlover.com";
        inputAccountDTO.setOwnerID(expected);
        String result = inputAccountDTO.getOwnerID();
        assertEquals(expected, result);
    }

    @Test
    void setAccountType() {
        String expected = "Cash";
        inputAccountDTO.setAccountType(expected);
        String result = inputAccountDTO.getAccountType();
        assertEquals(expected, result);
    }

    @Test
    void testEqualsDifferentDesignationsInInputAccountDTO(){
        InputAccountDTO inputAccountDTOOne = new InputAccountDTO();
        inputAccountDTOOne.setDesignation("My Account");
        InputAccountDTO inputAccountDTOTwo = new InputAccountDTO();
        inputAccountDTOTwo.setDesignation("My New Account");

        assertNotEquals(inputAccountDTOOne,inputAccountDTOTwo);
    }

    @Test
    void testEqualsDifferentInitialAmountInInputAccountDTO(){
        InputAccountDTO inputAccountDTOOne = new InputAccountDTO();
        inputAccountDTOOne.setInitialAmount(BigDecimal.valueOf(100));
        InputAccountDTO inputAccountDTOTwo = new InputAccountDTO();
        inputAccountDTOTwo.setInitialAmount(BigDecimal.valueOf(130));

        assertNotEquals(inputAccountDTOOne,inputAccountDTOTwo);
    }

    @Test
    void testEqualsDifferentCurrencyInInputAccountDTO(){
        InputAccountDTO inputAccountDTOOne = new InputAccountDTO();
        inputAccountDTOOne.setCurrency("USD");
        InputAccountDTO inputAccountDTOTwo = new InputAccountDTO();
        inputAccountDTOTwo.setCurrency("EUR");

        assertNotEquals(inputAccountDTOOne,inputAccountDTOTwo);
    }

    @Test
    void testEqualsDifferentOwnerIDsInInputAccountDTO(){
        InputAccountDTO inputAccountDTOOne = new InputAccountDTO();
        inputAccountDTOOne.setOwnerID("fernando@hotmail.com");
        InputAccountDTO inputAccountDTOTwo = new InputAccountDTO();
        inputAccountDTOTwo.setOwnerID("pinto@aeiou.pt");

        assertNotEquals(inputAccountDTOOne,inputAccountDTOTwo);
    }

    @Test
    void testEqualsDifferentAccountTypesInInputAccountDTO(){
        InputAccountDTO inputAccountDTOOne = new InputAccountDTO();
        inputAccountDTOOne.setAccountType("Cash");
        InputAccountDTO inputAccountDTOTwo = new InputAccountDTO();
        inputAccountDTOTwo.setAccountType("Credit");

        assertNotEquals(inputAccountDTOOne,inputAccountDTOTwo);
    }


}