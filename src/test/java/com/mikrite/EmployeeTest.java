package com.mikrite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeTest {
    @ParameterizedTest
    @MethodSource("constructor_InvalidParameters")
    @DisplayName("constructor throws IllegalArgumentException when invalid arguments are supplied")
    public void constructor_ThrowsIllegalArgumentException_WhenInvalidArgumentsSupplied(
            long id, String firstName, String lastName,
            LocalDate birthDate, int wage) {
        // Arrange
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(id, firstName, lastName, birthDate, wage);
        });
    }

    public static Stream<Arguments> constructor_InvalidParameters() {
        return Stream.of(
                arguments(-5, null, null, null, 0),
                arguments(1, "David", null, null, 0),
                arguments(1, "David", "Miko", null, 0),
                arguments(1, "David", "Miko", LocalDate.now().plus(1, ChronoUnit.DAYS), 0),
                arguments(1, "David", "Miko", LocalDate.of(1968, 5, 28), -50)
        );
    }

    @Test
    @DisplayName("constructor passes successfully")
    public void constructor_Passes() {
        var employee = new Employee(1, "David", "Miko", LocalDate.of(1968, 5, 28), 25000);

        // Assert
        assertEquals(1, employee.getId());
        assertEquals("David", employee.getFirstName());
        assertEquals("Miko", employee.getLastName());
        assertEquals(LocalDate.of(1968, 5, 28), employee.getBirthDate());
        assertEquals(25000, employee.getWage());
    }

}
