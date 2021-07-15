package switchtwentytwenty.project.dto.person;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class InputAddFamilyMemberDTO implements IInputPersonDTO {

    private String adminID;
    private String emailID;
    private String name;
    private String birtDate;
    private int vatNumber;
    private Integer phone;
    private String street;
    private String city;
    private String houseNumber;
    private String zipCode;
    private String password;


    public String getAdminID() {
        return adminID;
    }

    public String unpackEmail() {
        return emailID;
    }

    public String unpackName() {
        return name;
    }

    public String unpackBirthDate() {
        return birtDate;
    }

    public int unpackVAT() {
        return vatNumber;
    }

    public Integer unpackPhone() {
        return phone;
    }

    public String unpackStreet() {
        return street;
    }

    public String unpackCity() {
        return city;
    }

    public String unpackHouseNumber() {
        return houseNumber;
    }

    public String unpackZipCode() {
        return zipCode;
    }

    public String unpackPassword() { return password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputAddFamilyMemberDTO)) return false;
        InputAddFamilyMemberDTO that = (InputAddFamilyMemberDTO) o;
        return unpackVAT() == that.unpackVAT() && Objects.equals(getAdminID(), that.getAdminID()) && Objects.equals(unpackEmail(), that.unpackEmail()) && Objects.equals(unpackName(), that.unpackName()) && Objects.equals(unpackBirthDate(), that.unpackBirthDate()) && Objects.equals(unpackPhone(), that.unpackPhone()) && Objects.equals(unpackStreet(), that.unpackStreet()) && Objects.equals(unpackCity(), that.unpackCity()) && Objects.equals(unpackHouseNumber(), that.unpackHouseNumber()) && Objects.equals(unpackZipCode(), that.unpackZipCode()) && Objects.equals(unpackPassword(), that.unpackPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdminID(), unpackEmail(), unpackName(), unpackBirthDate(), unpackVAT(), unpackPhone(), unpackStreet(), unpackCity(), unpackHouseNumber(), unpackZipCode(), unpackPassword());
    }
}