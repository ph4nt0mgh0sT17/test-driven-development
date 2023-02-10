package com.mikrite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeDatabaseTest {
    @Test
    @DisplayName("addEmployee() throws IllegalArgumentException when employee is null")
    public void addEmployee_ThrowsIllegalArgumentException_WhenEmployeeIsNull() {
        // Arrange
        var employeeDatabase = new EmployeeDatabase();
        Employee employee = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            employeeDatabase.addEmployee(employee);
        });
    }

    @Test
    @DisplayName("addEmployee() throws IllegalStateException when employee already exists")
    public void addEmployee_ThrowsIllegalStateException_WhenEmployeeAlreadyExists() {
        // Arrange
        Employee employee = new Employee(1, "David", "Miko", LocalDate.of(1968,5,28), 25000);
        var list = new ArrayList<Employee>();
        list.add(employee);
        var employeeDatabase = new EmployeeDatabase(list);
        Employee newEmployee = new Employee(1, "David", "Miko", LocalDate.of(1968,5,28), 25000);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> {
            employeeDatabase.addEmployee(newEmployee);
        });
    }
}
