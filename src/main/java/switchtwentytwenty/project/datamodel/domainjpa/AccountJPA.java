package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountJPA {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private OwnerIDJPA ownerID;
    @Getter
    private String designation;
    @Getter
    private String accountType;
    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private List<MovementJPA> movements = new ArrayList<>();

    /**
     * Constructor for AccountJPA. Movements are set by the Repository.
     * @param id AccountIDJPA - it's own class to facilitate foreign key usage
     * @param ownerID PersonIDJPA - is a foreign key to PersonJPA
     * @param designation String - name of the account
     * @param accountType String - Type of account
     */
    public AccountJPA (Long id, OwnerIDJPA ownerID, String designation, String accountType) {
        this.id = id;
        this.ownerID = ownerID;
        this.designation = designation;
        this.accountType = accountType;
    }

    public AccountJPA (OwnerIDJPA ownerID, String designation, String accountType) {
        this.ownerID = ownerID;
        this.designation = designation;
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountJPA accountJPA = (AccountJPA) o;
        return id.equals(accountJPA.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

