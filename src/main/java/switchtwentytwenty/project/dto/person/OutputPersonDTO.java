package switchtwentytwenty.project.dto.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class OutputPersonDTO extends RepresentationModel<OutputPersonDTO> {

    private String id;
    private String name;
    private String birthdate;
    private List<String> emails = new ArrayList<>();
    private List<Integer> phoneNumbers = new ArrayList<>();
    private String vat;
    private String street;
    private String city;
    private String zipCode;
    private String doorNumber;
    private String familyID;

    public OutputPersonDTO(String id, String name, String birthdate, List<String> emails, List<Integer> phoneNumbers, String vat, String street, String city, String zipCode, String doorNumber, String familyID) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        if (emails != null) {
            this.emails.addAll(emails);
        }
        if (phoneNumbers != null) {
            this.phoneNumbers.addAll(phoneNumbers);
        }
        this.vat = vat;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.doorNumber = doorNumber;
        this.familyID = familyID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputPersonDTO that = (OutputPersonDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(birthdate, that.birthdate) && Objects.equals(emails, that.emails) && Objects.equals(phoneNumbers, that.phoneNumbers) && Objects.equals(vat, that.vat) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(doorNumber, that.doorNumber) && Objects.equals(familyID, that.familyID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, birthdate, emails, phoneNumbers, vat, street, city, zipCode, doorNumber, familyID);
    }

}