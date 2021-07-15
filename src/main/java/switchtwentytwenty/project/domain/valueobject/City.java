package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidCityException;

import java.util.Objects;

public class City implements ValueObject {


    static final String INVALIDCITY = "Invalid City Name";
    private final String cityName;

    public City(String cityName) {
        this.cityName = cityName;
        validateData();
    }

    @Override
    public String toString() {
        return this.cityName;
    }

    private void validateData() {
        checkCity();
    }

    private void checkCity() {
        if (!validateCity()) {
            throw new InvalidCityException(INVALIDCITY);
        }
    }

    // Verificar com lógica de negócio
    private boolean validateCity() {
        return cityName != null && cityName.trim().length() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(cityName, city1.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName);
    }
}
