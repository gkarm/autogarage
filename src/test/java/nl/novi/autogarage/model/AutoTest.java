package nl.novi.autogarage.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {

    @Test
    @DisplayName("Should keep auto in dbase")
    public void test1() {

//        Arrange
        Auto auto = new Auto();
        auto.setMerk("Toyota");

//        Act
        String expectedMerk = "Toyota";

//        Assert
        assertEquals("Toyota", expectedMerk);
    }

}