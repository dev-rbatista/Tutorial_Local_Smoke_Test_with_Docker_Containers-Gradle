package switchtwentytwenty.project.dto.person;

import java.util.Objects;

public class InputPersonDTO implements IInputPersonDTO {

    private final String emailID;
    private final String name;
    private final String birthDate;
    private final int vatNumber;
    private final Integer phone;
    private final String street;
    private final String city;
    private final String houseNumber;
    private final String zipCode;
    private final String password;


    // Partimos do pressuposto que quem está a usar esta funcionalidade tem autenticação efetuada corretamente.
    // O userID vem na mesma no megaDTO externo mas é extraído à parte para obter a family e este não fica "poluído"(João Pinto).

    public InputPersonDTO(String emailID, String name, String birthDate, int vatNumber, Integer phone, String street, String city, String houseNumber, String zipCode, String password) {

        this.emailID = emailID;
        this.name = name;
        this.birthDate = birthDate;
        this.vatNumber = vatNumber;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.password = password;

    }

    public String unpackEmail() {
        return this.emailID;
    }

    public String unpackName() {
        return this.name;
    }

    public String unpackBirthDate() {
        return this.birthDate;
    }

    public int unpackVAT() {
        return this.vatNumber;
    }

    public Integer unpackPhone() {
        return this.phone;
    }

    public String unpackStreet() {
        return this.street;
    }

    public String unpackCity() {
        return this.city;
    }

    public String unpackHouseNumber() {
        return this.houseNumber;
    }

    public String unpackZipCode() {
        return this.zipCode;
    }

    public String unpackPassword() { return this.password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputPersonDTO that = (InputPersonDTO) o;
        return vatNumber == that.vatNumber && emailID.equals(that.emailID) && name.equals(that.name) && birthDate.equals(that.birthDate) && Objects.equals(phone, that.phone) && street.equals(that.street) && city.equals(that.city) && houseNumber.equals(that.houseNumber) && zipCode.equals(that.zipCode) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailID, name, birthDate, vatNumber, phone, street, city, houseNumber, zipCode, password);
    }
}