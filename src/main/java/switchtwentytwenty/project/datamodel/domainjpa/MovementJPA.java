package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Table(name = "Movements")
public class MovementJPA {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    private long amount;

    @Getter
    private String currency;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "account", nullable=true)
    private AccountJPA account;


    public MovementJPA(Long amount, String currency, AccountJPA accountJPA){
        this.amount = amount;
        this.currency = currency;
        this.account = accountJPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovementJPA)) return false;
        MovementJPA that = (MovementJPA) o;
        return id == that.id && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account);
    }
}
