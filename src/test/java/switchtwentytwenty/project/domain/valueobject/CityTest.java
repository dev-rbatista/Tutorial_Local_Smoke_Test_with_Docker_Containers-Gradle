package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidCityException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    final String VALIDCITY = "Porto";

    @Test
    @Tag("US010")
    void constructorTestValidData(){
        City result = new City(VALIDCITY);
        assertNotNull(result);
    }

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"  "})
    @NullAndEmptySource
    @DisplayName("Test if a City Object throws an error if the String is empty, blank or null")
    void constructorTestInValidData(String value){
        assertThrows(InvalidCityException.class, () -> new City(value));
    }

    @Test
    @Tag("US010")
    void equalsTestSameCity(){
        City cityOne = new City(VALIDCITY);
        City cityTwo = cityOne;

        assertEquals(cityOne,cityTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentTypesOfObject(){
        City cityOne = new City(VALIDCITY);
        LocalDate notACity = LocalDate.of(2001,1,1);

        assertNotEquals(cityOne,notACity);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull(){
        City city = new City(VALIDCITY);
        String nullString = null;

        assertNotEquals(city,nullString);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualCities(){
        City cityOne = new City(VALIDCITY);
        City cityTwo = new City(VALIDCITY);

        assertEquals(cityOne,cityTwo);
        assertNotSame(cityOne,cityTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentCities(){
        City cityOne = new City(VALIDCITY);
        City cityTwo = new City("Matosinhos");

        assertNotEquals(cityOne,cityTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        City cityOne = new City(VALIDCITY);
        City cityTwo = new City(VALIDCITY);

        assertEquals(cityOne.hashCode(), cityTwo.hashCode());
        assertNotSame(cityOne,cityTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        City cityOne = new City(VALIDCITY);
        City cityTwo = new City("Matosinhos");

        assertNotEquals(cityOne.hashCode(), cityTwo.hashCode());
    }
}