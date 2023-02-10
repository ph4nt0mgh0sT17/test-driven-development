package com.mikrite;

import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final int wage;

    public Employee(long id, String firstName, String lastName, LocalDate birthDate, int wage) {
        if (id < 0)
            throw new IllegalArgumentException("The 'id' cannot be negative.");

        if (firstName == null)
            throw new IllegalArgumentException("The 'firstName' cannot be null.");

        if (lastName == null)
            throw new IllegalArgumentException("The 'lastName' cannot be null.");

        if (birthDate == null)
            throw new IllegalArgumentException("The 'birthDate' cannot be null.");

        if (!birthDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("The 'birthDate' cannot be in the future.");

        if (wage < 0)
            throw new IllegalArgumentException("The 'wage' cannot be negative.");

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.wage = wage;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWage() {
        return wage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Employee o) {
        return equals(o) ? 1 : -1;
    }
}
