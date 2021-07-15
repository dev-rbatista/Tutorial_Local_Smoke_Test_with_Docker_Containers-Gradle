package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RelationIDTest {
    int idOne = 100;
    int idTwo = 200;

    @Test
    void constructorTestValidObject() {
        RelationID relationID = new RelationID(idOne);

        assertNotNull(relationID);
    }


    @Test
    void equalsTestEqualObjects() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idOne);

        assertEquals(relationIDOne, relationIDTwo);
        assertNotSame(relationIDOne, relationIDTwo);
    }

    @Test
    void equalsTestSameObject() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = relationIDOne;

        assertEquals(relationIDOne, relationIDTwo);
    }

    @Test
    void equalsTestDifferentRelationIDs() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idTwo);

        assertNotEquals(relationIDOne, relationIDTwo);
    }

    @Test
    void equalsTestDifferentObjects() {
        RelationID relationID = new RelationID(idOne);
        LocalDate notRelationID = LocalDate.now();

        assertNotEquals(relationID, notRelationID);
    }

    @Test
    void hashCodeSameHashCode() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idOne);

        assertEquals(relationIDOne.hashCode(), relationIDTwo.hashCode());
        assertNotSame(relationIDOne, relationIDTwo);
    }

    @Test
    void hashCodeDifferentHashCode() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idTwo);

        assertNotEquals(relationIDOne.hashCode(), relationIDTwo.hashCode());
    }

    @Test
    void getRelationSameID() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idOne);
        int result = relationIDOne.getId();
        int expected = relationIDTwo.getId();

        assertEquals(result, expected);
        assertSame(result, expected);
    }

    @Test
    void getRelationDifferentID() {
        RelationID relationIDOne = new RelationID(idOne);
        RelationID relationIDTwo = new RelationID(idTwo);
        int result = relationIDOne.getId();
        int expected = relationIDTwo.getId();

        assertNotEquals(result, expected);
    }

    @Test
    void testToString() {
        RelationID relationIDOne = new RelationID(idOne);
        String expected = "100";
        String result = relationIDOne.toString();

        assertEquals(expected, result);
        assertNotNull(result);
    }
}