package switchtwentytwenty.project.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddFamilyMemberDTO {

    private String adminID;
    private String emailID;
    private String name;
    private String birthDate;
    private int vatNumber;
    private Integer phone;
    private String street;
    private String city;
    private String houseNumber;
    private String zipCode;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFamilyMemberDTO)) return false;
        AddFamilyMemberDTO that = (AddFamilyMemberDTO) o;
        return vatNumber == that.vatNumber && Objects.equals(adminID, that.adminID) && Objects.equals(emailID, that.emailID) && Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate) && Objects.equals(phone, that.phone) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(houseNumber, that.houseNumber) && Objects.equals(zipCode, that.zipCode) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminID, emailID, name, birthDate, vatNumber, phone, street, city, houseNumber, zipCode, password);
    }
}