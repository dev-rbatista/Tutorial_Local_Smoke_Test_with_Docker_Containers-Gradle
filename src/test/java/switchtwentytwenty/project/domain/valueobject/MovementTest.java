package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void getMonetaryTest(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(100));
        Movement movement = new Movement(monetaryValue);
        MonetaryValue expected = new MonetaryValue("EUR",BigDecimal.valueOf(100));

        MonetaryValue result = movement.getMonetaryValue();

        assertEquals(expected,result);
    }

    @Test
    void setMonetaryTest(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(100));
        Movement movement = new Movement(monetaryValue);
        MonetaryValue expected = new MonetaryValue("USD",BigDecimal.valueOf(200));

        movement.setMonetary(new MonetaryValue("USD",BigDecimal.valueOf(200)));
        MonetaryValue result = movement.getMonetaryValue();

        assertEquals(expected,result);
    }

    @Test
    void equalsTestEqualMovements(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movementOne = new Movement(monetaryValue);
        Movement movementTwo = new Movement(monetaryValue);

        assertEquals(movementOne,movementTwo);
        assertNotSame(movementOne,movementTwo);
    }

    @Test
    void equalsTestSameMovement(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movementOne = new Movement(monetaryValue);
        Movement movementTwo = movementOne;

        assertEquals(movementOne,movementTwo);
    }

    @Test
    void equalsTestDifferentMovements(){
        MonetaryValue monetaryValueValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movementOne = new Movement(monetaryValueValueOne);
        MonetaryValue monetaryValueValueTwo = new MonetaryValue("USD",BigDecimal.valueOf(100));
        Movement movementTwo = new Movement(monetaryValueValueTwo);

        assertNotEquals(movementOne,movementTwo);
    }

    @Test
    void equalsTestDifferentObjects(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movement = new Movement(monetaryValue);
        String notAMovement = "not a movement";

        assertNotEquals(movement,notAMovement);
    }

    @Test
    void equalsTestDifferentFromNull(){
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movement = new Movement(monetaryValue);
        String nullString = null;

        assertNotEquals(movement,nullString);
    }

    @Test
    void hashCodeTestSameHashCode() {
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movementOne = new Movement(monetaryValue);
        Movement movementTwo = new Movement(monetaryValue);

        assertEquals(movementOne.hashCode(),movementTwo.hashCode());
        assertNotSame(movementOne,movementTwo);
    }

    @Test
    void hashCodeTestDifferentHashCodes() {
        MonetaryValue monetaryValueValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(200));
        Movement movementOne = new Movement(monetaryValueValueOne);
        MonetaryValue monetaryValueValueTwo = new MonetaryValue("USD",BigDecimal.valueOf(100));
        Movement movementTwo = new Movement(monetaryValueValueTwo);

        assertNotEquals(movementOne.hashCode(),movementTwo.hashCode());
    }

}