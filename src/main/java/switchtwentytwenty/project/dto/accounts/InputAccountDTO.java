package switchtwentytwenty.project.dto.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class InputAccountDTO {

    private String designation;
    private BigDecimal initialAmount;
    private String currency;
    private String ownerID;
    private String accountType;


    public InputAccountDTO(String designation, BigDecimal initialAmount, String currency, String ownerID, String accountType) {
        this.designation = designation;
        this.initialAmount = initialAmount;
        this.currency = currency;
        this.ownerID = ownerID;
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputAccountDTO that = (InputAccountDTO) o;
        return Objects.equals(designation, that.designation) && Objects.equals(initialAmount, that.initialAmount) && Objects.equals(currency, that.currency) && Objects.equals(ownerID, that.ownerID) && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation, initialAmount, currency, ownerID, accountType);
    }

    }