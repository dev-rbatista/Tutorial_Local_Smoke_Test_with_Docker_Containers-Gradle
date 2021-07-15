package switchtwentytwenty.project.dto.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CreateAccountDTO {

    private String designation;
    private BigDecimal initialAmount;
    private String currency;
    private String ownerID;
    private String accountType;


    public CreateAccountDTO (String designation, BigDecimal initialAmount, String currency, String ownerID, String accountType){
        this.designation = designation;
        this.initialAmount = initialAmount;
        this.currency = currency;
        this.ownerID = ownerID;
        this.accountType = accountType;
    }



}
