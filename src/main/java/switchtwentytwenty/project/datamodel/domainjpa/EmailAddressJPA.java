package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "emailAddresses")
public class EmailAddressJPA {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    private String email;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "person", nullable = true)
    //because it is in the same aggregate, a foreign key constraint is used
    private PersonJPA personID;

    public EmailAddressJPA(Long id, String email, PersonJPA personJPA) {
        this.email = email;
        if (id != null) {
            this.id = id;
        }
        this.personID = personJPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddressJPA that = (EmailAddressJPA) o;
        return id == that.id && email == that.email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}