package switchtwentytwenty.project.datamodel.domainjpa;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor

@Entity
@Table(name = "address")
public class AddressJPA {

    /**
     * Por não existir nenhum atributo que possa ser usados como ID. Definiu-se atributo chave id (do tipo long)
     * com a anotação @GeneratedValue
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    private String street;
    @Getter
    private String city;
    @Getter
    private String zipCode;
    @Getter
    private String doorNumber;

    @Getter
    @OneToOne()
    @JoinColumn(name = "person", nullable = false)
    //because it is in the same aggregate, a foreign key constraint is used
    private PersonJPA person;

    public AddressJPA(Long id, String street, String city, String zipCode, String doorNumber, PersonJPA personjpa) {
        if (id != null) {
            this.id = id;
        }
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.doorNumber = doorNumber;
        this.person = personjpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressJPA that = (AddressJPA) o;
        return id == that.id && street.equals(that.street) && city.equals(that.city) && zipCode.equals(that.zipCode) && doorNumber.equals(that.doorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, zipCode, doorNumber);
    }
}