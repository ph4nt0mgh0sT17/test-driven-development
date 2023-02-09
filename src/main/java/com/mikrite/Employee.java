package com.mikrite;

import java.time.LocalDate;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final int wage;

    public Employee(String firstName, String lastName, LocalDate birthDate, int wage) {
        if (firstName == null)
            throw new IllegalArgumentException("The 'firstName' cannot be null.");

        if (lastName == null)
            throw new IllegalArgumentException("The 'lastName' cannot be null.");

        if (birthDate == null)
            throw new IllegalArgumentException("The 'birthDate' cannot be null.");

        if (!birthDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("The 'birthDate' cannot be in the future.");

        if (wage < 0)
            throw new IllegalArgumentException("The 'wage' cannot be null.");

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.wage = wage;
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
}
