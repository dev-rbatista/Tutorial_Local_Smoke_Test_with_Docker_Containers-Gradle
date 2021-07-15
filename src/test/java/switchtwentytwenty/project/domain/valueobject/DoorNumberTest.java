package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidAddressNumberException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoorNumberTest {
    final String VALIDNUMBER = "11";

    @Test
    @Tag("US010")
    void constructorTestValidData(){
        DoorNumber result = new DoorNumber(VALIDNUMBER);
        assertNotNull(result);
    }

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"  "})
    @NullAndEmptySource
    @DisplayName("Test if a DoorNumber Object throws an error if the String is empty, blank or null")
    void constructorTestInValidData(String value){
        assertThrows(InvalidAddressNumberException.class, () -> new DoorNumber(value));
    }


    @Test
    @Tag("US010")
    void equalsTestSameDoorNumber(){
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        DoorNumber doorNumberTwo = doorNumberOne;

        assertEquals(doorNumberOne,doorNumberTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentTypesOfObject(){
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        LocalDate notADoorNumber = LocalDate.of(2001,1,1);

        assertNotEquals(doorNumberOne,notADoorNumber);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull(){
        DoorNumber doorNumber = new DoorNumber(VALIDNUMBER);
        String nullString = null;

        assertNotEquals(doorNumber,nullString);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualDoorNumbers(){
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        DoorNumber doorNumberTwo = new DoorNumber(VALIDNUMBER);

        assertEquals(doorNumberOne,doorNumberTwo);
        assertNotSame(doorNumberOne,doorNumberTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentDoorNumbers(){
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        DoorNumber doorNumberTwo = new DoorNumber("23B");

        assertNotEquals(doorNumberOne,doorNumberTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        DoorNumber doorNumberTwo = new DoorNumber(VALIDNUMBER);

        assertEquals(doorNumberOne.hashCode(), doorNumberTwo.hashCode());
        assertNotSame(doorNumberOne,doorNumberTwo);
    }



    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        DoorNumber doorNumberOne = new DoorNumber(VALIDNUMBER);
        DoorNumber doorNumberTwo = new DoorNumber("23B");

        assertNotEquals(doorNumberOne.hashCode(), doorNumberTwo.hashCode());
    }
}