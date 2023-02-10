package com.mikrite;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {
    private final List<Employee> _employees;

    public EmployeeDatabase() {
        _employees = new ArrayList<>();
    }

    public EmployeeDatabase(List<Employee> employees) {
        if (employees == null)
            throw new IllegalArgumentException("The 'employees' cannot be null.");

        _employees = employees;
    }

    public void addEmployee(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("The 'employee' cannot be null.");

        if (_employees.contains(employee)) {
            throw new IllegalStateException("The employee with id " + employee.getId() + " already exists in the database.");
        }
    }
}
