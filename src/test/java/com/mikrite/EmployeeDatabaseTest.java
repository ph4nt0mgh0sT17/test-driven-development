package com.mikrite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDatabaseTest {
    @Test
    @DisplayName("addEmployee() throws IllegalArgumentException when employee is null")
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    public void addEmployee_AddsEmployeeSuccessfully() {
        // Arrange
        var employeeDatabase = new EmployeeDatabase();
        Employee employee = new Employee(1, "David", "Miko", LocalDate.of(1968, 5, 28), 25000);

        // Act + Assert
        employeeDatabase.addEmployee(employee);
    }

    @Test
    @DisplayName("findEmployeeById() throws IllegalArgumentException when id is negative")
    @Order(4)
    void findEmployeeById_ThrowsIllegalArgumentException_WhenIdIsNegative() {
        // Arrange
        var id = -1L;
        var employeeDatabase = new EmployeeDatabase();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            var employee = employeeDatabase.findEmployeeById(id);
        });
    }

    @Test
    @DisplayName("findEmployeeById() returns null when employee is not found")
    @Order(5)
    void findEmployeeById_ReturnsNull_WhenEmployeeIsNotFound() {
        // Arrange
        var id = 1L;
        var employeeDatabase = new EmployeeDatabase();

        // Act
        var employee = employeeDatabase.findEmployeeById(id);

        // Assert
        assertNull(employee);
    }

    @Test
    @DisplayName("findEmployeeById() returns employee when found")
    @Order(6)
    void findEmployeeById_ReturnsEmployee_WhenPresent() {
        // Arrange
        var id = 1L;
        var employeeList = List.of(new Employee(1L, "David", "Miko", LocalDate.of(1968,5,28), 25000));
        var employeeDatabase = new EmployeeDatabase(employeeList);

        // Act
        var employee = employeeDatabase.findEmployeeById(id);

        // Assert
        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("David", employee.getFirstName());
        assertEquals("Miko", employee.getLastName());
        assertEquals(LocalDate.of(1968, 5, 28), employee.getBirthDate());
        assertEquals(25000, employee.getWage());
    }

    @Test
    @DisplayName("findEmployee() throws IllegalArgumentException when predicate is null")
    @Order(7)
    void findEmployee_ThrowsIllegalArgumentException_WhenPredicateIsNull() {
        // Arrange
        var id = -1L;
        var employeeDatabase = new EmployeeDatabase();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            var employee = employeeDatabase.findEmployee(null);
        });
    }

    @Test
    @DisplayName("findEmployee() returns null when employee is not found")
    @Order(8)
    void findEmployee_ReturnsNull_WhenEmployeeIsNotFound() {
        // Arrange
        var id = 10000L;
        var employeeDatabase = new EmployeeDatabase();

        // Act
        var employee = employeeDatabase.findEmployee(employee1 -> employee1.getId() == id);

        // Assert
        assertNull(employee);
    }

    @Test
    @DisplayName("findEmployee() returns employee when found")
    @Order(9)
    void findEmployee_ReturnsEmployee_WhenPresent() {
        // Arrange
        var id = 1L;
        var employeeList = List.of(new Employee(1L, "David", "Miko", LocalDate.of(1968,5,28), 25000));
        var employeeDatabase = new EmployeeDatabase(employeeList);

        // Act
        var employee = employeeDatabase.findEmployee(employee1 -> employee1.getId() == id);

        // Assert
        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("David", employee.getFirstName());
        assertEquals("Miko", employee.getLastName());
        assertEquals(LocalDate.of(1968, 5, 28), employee.getBirthDate());
        assertEquals(25000, employee.getWage());
    }

    @Test
    @DisplayName("findEmployees() throws IllegalArgumentException when predicate is null")
    @Order(10)
    void findEmployees_ThrowsIllegalArgumentException_WhenPredicateIsNull() {
        // Arrange
        var employeeDatabase = new EmployeeDatabase();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            var employees = employeeDatabase.findEmployees(null);
        });
    }

    @Test
    @DisplayName("findEmployees() returns empty list when employee is not found")
    @Order(11)
    void findEmployees_ReturnsEmptyList_WhenEmployeeIsNotFound() {
        // Arrange
        var id = 10000L;
        var employeeDatabase = new EmployeeDatabase();

        // Act
        var employees = employeeDatabase.findEmployees(employee1 -> employee1.getId() == id);

        // Assert
        assertTrue(employees.isEmpty());
    }

    @Test
    @DisplayName("findEmployees() returns employee when found")
    @Order(12)
    void findEmployees_ReturnsEmployeeList_WhenPresent() {
        // Arrange
        var id = 1L;
        var employeeList = List.of(new Employee(1L, "David", "Miko", LocalDate.of(1968,5,28), 25000));
        var employeeDatabase = new EmployeeDatabase(employeeList);

        // Act
        var employees = employeeDatabase.findEmployees(employee1 -> employee1.getId() == id);

        // Assert
        assertNotNull(employees);
        assertEquals(1, employees.size());

        var employee = employees.get(0);

        assertEquals(1L, employee.getId());
        assertEquals("David", employee.getFirstName());
        assertEquals("Miko", employee.getLastName());
        assertEquals(LocalDate.of(1968, 5, 28), employee.getBirthDate());
        assertEquals(25000, employee.getWage());
    }
}
