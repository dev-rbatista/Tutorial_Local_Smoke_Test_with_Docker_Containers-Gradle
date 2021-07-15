package switchtwentytwenty.project.domain.aggregates.family;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.RelationAlreadyRegisteredException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    @Test
    @Tag("US010")
    void familyConstructorTest_validFamily() {
        //Arrange
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        //Act
        Family result = new Family(familyID, familyName, registrationDate, adminEmail);
        //Assert
        assertNotNull(result);
    }

    @Test
    @Tag("US010")
    void isIDofThisFamilyTest_sameFamilyID() {
        //Arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family aFamily = new Family(familyID, familyName, registrationDate, adminEmail);
        FamilyID sameID = new FamilyID("admin@gmail.com");
        //Act
        boolean result = aFamily.hasID(sameID);
        //Assert
        assertTrue(result);
    }

    @Test
    @Tag("US010")
    void isIDofThisFamilyTest_differentFamilyID() {
        //Arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family aFamily = new Family(familyID, familyName, registrationDate, adminEmail);
        UUID differentID = UUID.randomUUID();
        FamilyID otherFamilyID = new FamilyID("admin2@gmail.com");
        //Act
        boolean result = aFamily.hasID(otherFamilyID);
        //Assert
        assertFalse(result);
    }

    @Test
    @Tag("US010")
    void testEquals_equalNotSame() {
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyID, familyName, registrationDate, adminEmail);
        Family familyTwo = new Family(familyID, familyName, registrationDate, adminEmail);

        assertNotSame(familyOne, familyTwo);
        assertEquals(familyOne, familyTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_equalSame() {
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyID, familyName, registrationDate, adminEmail);
        Family familyTwo = familyOne;

        assertSame(familyOne, familyTwo);
        assertEquals(familyOne, familyTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqual() {
        UUID id = UUID.randomUUID();
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyIDOne, familyName, registrationDate, adminEmail);
        UUID idTwo = UUID.randomUUID();
        FamilyID familyIDTwo = new FamilyID("admin2@gmail.com");
        Family familyTwo = new Family(familyIDTwo, familyName, registrationDate, adminEmail);

        assertNotEquals(familyOne, familyTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqualDifferentObject() {
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyID, familyName, registrationDate, adminEmail);

        assertNotEquals(familyOne, registrationDate);
    }

    @Test
    @Tag("US010")
    void testHashCode_sameHashCode() {
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyID, familyName, registrationDate, adminEmail);
        Family familyTwo = new Family(familyID, familyName, registrationDate, adminEmail);

        assertEquals(familyOne.hashCode(), familyTwo.hashCode());
    }

    @Test
    @Tag("US010")
    void testHashCode_differentHashCode() {
        UUID id = UUID.randomUUID();
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family familyOne = new Family(familyIDOne, familyName, registrationDate, adminEmail);
        UUID idTwo = UUID.randomUUID();
        FamilyID familyIDTwo = new FamilyID("admin2@gmail.com");
        Family familyTwo = new Family(familyIDTwo, familyName, registrationDate, adminEmail);

        assertNotEquals(familyOne.hashCode(), familyTwo.hashCode());
    }

    /*
    @Test
    void shouldCreateAValidFamilyInstanceWithBuilder() {
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        LocalDate date = LocalDate.of(2021, 3, 17);
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        EmailAddress adminEmail = new EmailAddress(emailString);
        //Act
        Family result = new Family.Builder(familyID)
                .withName(familyName)
                .withRegistrationDate(registrationDate)
                .withAdmin(adminEmail)
                .build();
        //Assert
        assertNotNull(result);
    }*/

    @Test
    void isPersonTheAdminTestTrue() {
        //Arrange
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family aFamily = new Family(familyID, familyName, registrationDate, adminEmail);
        //Act
        boolean result = aFamily.isPersonTheAdmin(adminEmail);
        //Assert
        assertTrue(result);
    }

    @Test
    void isPersonTheAdminTestFalse() {
        //Arrange
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        PersonID adminEmail = new PersonID("admin@gmail.com");
        String emailString = "other@gmail.com";
        PersonID notAdminEmail = new PersonID(emailString);
        Family aFamily = new Family(familyID, familyName, registrationDate, adminEmail);
        //Act
        boolean result = aFamily.isPersonTheAdmin(notAdminEmail);
        //Assert
        assertFalse(result);
    }

    @Test
    void addRelation() {
        FamilyID familyID = new FamilyID("@admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        PersonID adminEmail = new PersonID("admin@gmail.com");
        String emailString = "other@gmail.com";
        PersonID otherEmail = new PersonID(emailString);
        Family expected = new Family(familyID, familyName, registrationDate, adminEmail);
        Name memberAName = new Name("memberA");
        Name memberBName = new Name("memberB");
        BirthDate birthDate = new BirthDate("20/02/1990");
        VATNumber vatNumber = new VATNumber(111222333);

        Person memberA = new Person(adminEmail, memberAName, birthDate, vatNumber, familyID);
        Person memberB = new Person(otherEmail, memberBName, birthDate, vatNumber, familyID);
        RelationDesignation relationDesignation = new RelationDesignation("BFFs");
        Relation relation = new Relation(memberA.id(), memberB.id(), relationDesignation);

        expected.addRelation(relation);

        Family result = new Family(familyID, familyName, registrationDate, adminEmail);
        result.addRelation(relation);

        assertEquals(expected.getRelationByID(relation.getId()), result.getRelationByID(relation.getId()));
    }

    @Test
    void addRelationExpectingThrows() {
        FamilyID familyID = new FamilyID("@admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        PersonID adminEmail = new PersonID("admin@gmail.com");
        String emailString = "other@gmail.com";
        PersonID otherEmail = new PersonID(emailString);
        Family expected = new Family(familyID, familyName, registrationDate, adminEmail);
        Name memberAName = new Name("memberA");
        Name memberBName = new Name("memberB");
        BirthDate birthDate = new BirthDate("20/02/1990");
        VATNumber vatNumber = new VATNumber(111222333);

        Person memberA = new Person(adminEmail, memberAName, birthDate, vatNumber, familyID);
        Person memberB = new Person(otherEmail, memberBName, birthDate, vatNumber, familyID);
        RelationDesignation relationDesignation = new RelationDesignation("BFFs");
        Relation relation = new Relation(memberA.id(), memberB.id(), relationDesignation);

        expected.addRelation(relation);

        assertThrows(RelationAlreadyRegisteredException.class, () -> {
            expected.addRelation(relation);
        });

    }

    @Test
    void getRelationByID() {
        FamilyID familyID = new FamilyID("@admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        PersonID adminEmail = new PersonID("admin@gmail.com");
        String emailString = "other@gmail.com";
        PersonID otherEmail = new PersonID(emailString);
        Family expected = new Family(familyID, familyName, registrationDate, adminEmail);
        Name memberAName = new Name("memberA");
        Name memberBName = new Name("memberB");
        BirthDate birthDate = new BirthDate("20/02/1990");
        VATNumber vatNumber = new VATNumber(111222333);

        Person memberA = new Person(adminEmail, memberAName, birthDate, vatNumber, familyID);
        Person memberB = new Person(otherEmail, memberBName, birthDate, vatNumber, familyID);
        RelationDesignation relationDesignation = new RelationDesignation("BFFs");
        Relation relation = new Relation(memberA.id(), memberB.id(), relationDesignation);

        expected.addRelation(relation);

        assertThrows(IllegalArgumentException.class, () -> {
            expected.getRelationByID(new RelationID(3));
        });
    }

    @Test
    void isRelationAlreadyRegisteredTest() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relation = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(12));
        family.addRelation(relation);

        boolean result = family.isRelationAlreadyRegistered(relation);

        assertTrue(result);
    }

    @Test
    void isRelationAlreadyRegisteredNotRegisteredYetEmptyListTest() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relation = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(12));

        boolean result = family.isRelationAlreadyRegistered(relation);

        assertFalse(result);
    }

    @Test
    void isRelationAlreadyRegisteredNotRegisteredYetNotEmptyListTest() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relation = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(12));
        Relation relationTwo = new Relation(new PersonID("teste@gmail.com"), new PersonID("teste2@gmail.com"), new RelationDesignation("Friends"), new RelationID(10));
        family.addRelation(relationTwo);

        boolean result = family.isRelationAlreadyRegistered(relation);

        assertFalse(result);
    }

    @Test
    void getRelationByIDTest() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));
        Relation relationTwo = new Relation(new PersonID("teste@gmail.com"), new PersonID("teste2@gmail.com"), new RelationDesignation("Friends"), new RelationID(2));
        family.addRelation(relationOne);
        family.addRelation(relationTwo);

        Relation result = family.getRelationByID(new RelationID(1));

        assertEquals(relationOne, result);
    }

    @Test
    void getRelationByIDRelationNotFoundTest() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));
        Relation relationTwo = new Relation(new PersonID("teste@gmail.com"), new PersonID("teste2@gmail.com"), new RelationDesignation("Friends"), new RelationID(2));
        family.addRelation(relationOne);
        family.addRelation(relationTwo);

        assertThrows(IllegalArgumentException.class, () -> family.getRelationByID(new RelationID(20)));


    }


    @Test
    void getRelations() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("tony@gmail.com"), new PersonID("admin@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));
        Relation relationTwo = new Relation(new PersonID("teste@gmail.com"), new PersonID("teste2@gmail.com"), new RelationDesignation("Friends"), new RelationID(2));
        family.addRelation(relationOne);
        family.addRelation(relationTwo);

        List<Relation> expected = new ArrayList<>();
        expected.add(relationOne);
        expected.add(relationTwo);

        List<Relation> result = family.getRelations();
        assertEquals(expected, result);
    }

    @Test
    void getRelationsByPersonID() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("admin@gmail.com"), new PersonID("tony@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));
        Relation relationTwo = new Relation(new PersonID("teste@gmail.com"), new PersonID("teste2@gmail.com"), new RelationDesignation("Friends"), new RelationID(2));
        family.addRelation(relationOne);
        family.addRelation(relationTwo);

        List<Relation> expected = new ArrayList<>();
        expected.add(relationOne);


        List<Relation> result = family.getRelationsByPersonID(new PersonID("admin@gmail.com"));
        assertEquals(expected, result);
    }

    @Test
    void changeRelation() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("admin@gmail.com"), new PersonID("tony@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));
        family.addRelation(relationOne);

        RelationDesignation newDesignation = new RelationDesignation("Primos");
        RelationID relationID = new RelationID(1);


        Relation expected = new Relation(new PersonID("admin@gmail.com"), new PersonID("tony@gmail.com"), new RelationDesignation("Primos"), new RelationID(1));
        Relation result = family.changeRelation(relationID, newDesignation);


        assertEquals(expected, result);
        assertNotSame(expected, result);
        assertNotNull(result);
    }


    @Test
    void setRelations() {
        Family family = new Family(new FamilyID("@admin@gmail.com"), new FamilyName("Silva"), new RegistrationDate("12/01/1999"), new PersonID("admin@gmail.com"));
        Relation relationOne = new Relation(new PersonID("admin@gmail.com"), new PersonID("tony@gmail.com"), new RelationDesignation("Amigos"), new RelationID(1));


        List<Relation> expected = new ArrayList<>();
        expected.add(relationOne);

        assertDoesNotThrow(() -> family.setRelations(expected));

    }

    @Test
    void noArgsConstructorTest() {
        Family family = new Family();
        assertNotNull(family);
    }
}