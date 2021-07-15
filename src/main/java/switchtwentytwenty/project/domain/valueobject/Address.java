package switchtwentytwenty.project.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
@AllArgsConstructor
public class Address implements ValueObject {

    @Getter
    private Long id;
    private final Street street;
    private final City city;
    private final ZipCode zipCode;
    private final DoorNumber doorNumber;

    public Address(String street, String city, String zipCode, String doorNumber) {
        this.street = new Street(street);
        this.city = new City(city);
        this.zipCode = new ZipCode(zipCode);
        this.doorNumber = new DoorNumber(doorNumber);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(doorNumber, address.doorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, doorNumber, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street=" + street.toString() +
                ", city=" + city.toString() +
                ", zipCode=" + zipCode.toString() +
                ", number=" + doorNumber.toString() +
                '}';
    }

    public String getStreet() {
        return this.street.toString();
    }

    public String getCity() {
        return this.city.toString();
    }

    public String getZipCode() {
        return this.zipCode.toString();
    }

    public String getDoorNumber() {
        return this.doorNumber.toString();
    }
}
