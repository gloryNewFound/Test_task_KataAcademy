package com.dvn.test_task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @DisplayName("FROM KataAcademy mentor: Checking of rome digits calculation")
    void romeDigitsCalcTest() throws Exception {
        String test = Main.calc("I + III");
        Assertions.assertEquals("IV", test);
    }

    @Test
    @DisplayName("FROM KataAcademy mentor: Checking of rome digits calculation result lower than I")
    void romeDigitsCalcResultLowerThanITest() throws Exception {
        Assertions.assertThrows(Exception.class, () -> Main.calc("I - III"));
    }

    @Test
    @DisplayName("FROM KataAcademy mentor: Checking of arab digits between 1 and 10")
    void arabDigitsDataTest() throws Exception {
        Assertions.assertThrows(Exception.class, () -> Main.calc("0 + 5"));
        Assertions.assertThrows(Exception.class, () -> Main.calc("11 + 5"));
        Assertions.assertThrows(Exception.class, () -> Main.calc("4 + 0"));
        Assertions.assertThrows(Exception.class, () -> Main.calc("4 + 11"));
        Assertions.assertThrows(Exception.class, () -> Main.calc("-1 + 5"));
        Assertions.assertThrows(Exception.class, () -> Main.calc("4 + -1"));
    }

    @Test
    @DisplayName("Checking of summation")
    void sumTest() throws Exception {
        String test = Main.calc("2 + 2");
        int testResult = 2 + 2;
        Assertions.assertEquals(Integer.toString(testResult), test, "Sum is not working");
    }

    @Test
    @DisplayName("Checking of subtraction")
    void subsTest() throws Exception {
        String test = Main.calc("2 - 2");
        int testResult = 2 - 2;
        Assertions.assertEquals(Integer.toString(testResult), test, "Subtraction is not working");
    }

    @Test
    @DisplayName("Checking of multiplication")
    void multiplierTest() throws Exception {
        int testResult = 2 * 2;
        String test = Main.calc("2 * 2");
        Assertions.assertEquals(Integer.toString(testResult), test, "Multiplication is not working");
    }

    @Test
    @DisplayName("Checking of division")
    void divisionTest() throws Exception {
        int testResult = 2 / 2;
        String test = Main.calc("2 / 2");
        Assertions.assertEquals(Integer.toString(testResult), test, "Division is not working");
    }

    @Test
    @DisplayName("Checking of throwing exception not correct operator")
    void wrongOperatorExceptionThrowingTest() throws Exception {
        Assertions.assertThrows(Exception.class,() -> Main.calc("2 % 2"));
    }

    @Test
    @DisplayName("Checking of throwing exception not correct data")
    void wrongDataTypeExceptionThrowingTest() throws Exception {
        Assertions.assertThrows(Exception.class,() -> Main.calc("a % 2"));
        Assertions.assertThrows(Exception.class,() -> Main.calc("2 % b"));
        Assertions.assertThrows(Exception.class,() -> Main.calc("I % 2"));
        Assertions.assertThrows(Exception.class,() -> Main.calc("2 % V"));
    }

}