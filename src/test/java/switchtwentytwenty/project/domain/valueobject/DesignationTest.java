package switchtwentytwenty.project.domain.valueobject;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class DesignationTest {

    @Test
    void testEqualsTrue() {
        Designation designation = new Designation("cash");
        Designation designation2 = new Designation("cash");

        assertEquals(designation, designation2);
        assertNotSame(designation, designation2);
    }

    @Test
    void testEqualsFalse() {
        Designation designation = new Designation("cash");
        Designation designation1 = new Designation("cashhh");
        Designation designationNull = null;
        String notDesignation = "a string";

        assertNotEquals(designation, designation1);
        assertNotEquals(designation, designationNull);
        assertNotEquals(designation, notDesignation);
        assertNotSame(designation, designation1);

    }

    @Test
    void testHashCodeEquals() {
        Designation designation = new Designation("cash");
        Designation designation1 = new Designation("cash");

        assertEquals(designation.hashCode(), designation1.hashCode());

    }

    @Test
    void testHashCodeNotEquals() {
        Designation designation = new Designation("cash");
        Designation designation1 = new Designation("cashhhhh");

        assertNotEquals(designation.hashCode(), designation1.hashCode());

    }

    @Test
    void testToString() {
        Designation designation = new Designation("cash");
        String result = designation.toString();
        assertNotNull(result);
        assertTrue(result.length()>0);
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testToStringNullEmpty(String value) {
        assertThrows(IllegalArgumentException.class, ()->new Designation(value));
    }
}