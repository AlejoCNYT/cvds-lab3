package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {

    private Registry registry = new Registry();

    @Test
    public void shouldBeDeadPersonWhenIsNotAlive() {
        // Arrange
        Person person = new Person("DanielJulian", 00423, 16, Gender.UNIDENTIFIED, false);
        // Act
        RegisterResult result = registry.registerVoter(person);
        // Assert
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldBeUnderagePersonWhenIsLessThan18yoAndHaveValidAgeRange() {
        // Arrange
        Person person = new Person("DanielJulian", 00423, 11, Gender.UNIDENTIFIED, true);
        // Act
        RegisterResult result = registry.registerVoter(person);
        // Assert
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldBeInvalidAgeWhenAgeIsNegative() {
        // Arrange
        Person person = new Person("DanielJulian", 00423, -17, Gender.UNIDENTIFIED, true);
        // Act
        RegisterResult result = registry.registerVoter(person);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldBeDuplicatedPersonIdWhenExistOtherPersonWithThisId() {
        // Arrange
        Person person1 = new Person("DanielJulian", 00423, 26, Gender.UNIDENTIFIED, true);
        Person person2 = new Person("JulianDaniel", 00423, 22, Gender.UNIDENTIFIED, true);
        // Act
        registry.registerVoter(person1);
        RegisterResult result = registry.registerVoter(person2);
        // Assert
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

    @Test
    public void shouldNotBeDuplicatedPersonIdWhenExistOtherPersonWithThisId() {
        // Arrange
        Person person1 = new Person("DanielJulian", 00423, 26, Gender.UNIDENTIFIED, true);
        Person person2 = new Person("JulianDaniel", 00432, 22, Gender.UNIDENTIFIED, true);
        // Act
        registry.registerVoter(person1);
        RegisterResult result = registry.registerVoter(person2);
        // Assert
        Assert.assertNotEquals(RegisterResult.DUPLICATED, result);
    }

}
