package com.mikrite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Employee employee = new Employee(1, "David", "Miko", LocalDate.of(1968, 5, 28), 25000);
        var list = new ArrayList<Employee>();
        list.add(employee);
        var employeeDatabase = new EmployeeDatabase(list);
        Employee newEmployee = new Employee(1, "David", "Miko", LocalDate.of(1968, 5, 28), 25000);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> {
            employeeDatabase.addEmployee(newEmployee);
        });
    }

    @Test
    @DisplayName("addEmployee() adds an employee successfully")
    public void addEmployee_AddsEmployeeSuccessfully() {
        // Arrange
        var employeeDatabase = new EmployeeDatabase();
        Employee employee = new Employee(1, "David", "Miko", LocalDate.of(1968, 5, 28), 25000);

        // Act + Assert
        employeeDatabase.addEmployee(employee);
    }

    @Test
    @DisplayName("getEmployee by id throws IllegalArgumentException when id is negative")
    void getEmployeeById_ThrowsIllegalArgumentException_WhenIdIsNegative() {
        // Arrange
        var id = -1L;
        var employeeDatabase = new EmployeeDatabase();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            var employee = employeeDatabase.getEmployee(id);
        });
    }

    @Test
    @DisplayName("getEmployee by id returns null when employee is not found")
    void getEmployeeById_ReturnsNull_WhenEmployeeIsNotFound() {
        // Arrange
        var id = 1L;
        var employeeDatabase = new EmployeeDatabase();

        // Act
        var employee = employeeDatabase.getEmployee(id);

        // Assert
        assertNull(employee);
    }

    @Test
    @DisplayName("getEmployee by id returns employee when found")
    void getEmployeeById_ReturnsEmployee_WhenPresent() {
        // Arrange
        var id = 1L;
        var employeeList = List.of(new Employee(1L, "David", "Miko", LocalDate.of(1968,5,28), 25000));
        var employeeDatabase = new EmployeeDatabase(employeeList);

        // Act
        var employee = employeeDatabase.getEmployee(id);

        // Assert
        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("David", employee.getFirstName());
        assertEquals("Miko", employee.getLastName());
        assertEquals(LocalDate.of(1968, 5, 28), employee.getBirthDate());
        assertEquals(25000, employee.getWage());
    }
}
