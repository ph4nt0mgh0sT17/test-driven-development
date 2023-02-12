package com.mikrite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EmployeeDatabase {
    private final List<Employee> _employees;

    public EmployeeDatabase() {
        _employees = new ArrayList<>();
    }

    EmployeeDatabase(List<Employee> employees) {
        if (employees == null)
            throw new IllegalArgumentException("The 'employees' cannot be null.");

        _employees = new ArrayList<>(employees);
    }

    public void addEmployee(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("The 'employee' cannot be null.");

        if (_employees.contains(employee)) {
            throw new IllegalStateException("The employee with id " + employee.getId() + " already exists in the database.");
        }
    }

    public Employee findEmployeeById(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The employee ID cannot be negative.");
        }

        return _employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee findEmployee(Predicate<Employee> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("The 'predicate' cannot be null.");
        }

        return _employees.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployees(Predicate<Employee> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("The 'predicate' cannot be null.");
        }

        return _employees.stream()
                .filter(predicate)
                .toList();
    }
}
