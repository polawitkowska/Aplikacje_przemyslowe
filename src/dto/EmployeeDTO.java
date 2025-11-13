package dto;

import model.EmploymentStatus;
import model.Position;
import service.CompanySystem;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private Position position;
    private int salary;
    private EmploymentStatus status;

    public EmployeeDTO(String firstName, String lastName, String email, String company, Position position, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.position = position;
        this.salary = salary;
        this.status = EmploymentStatus.ACTIVE;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Imię nie może być puste ani null.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwisko nie może być puste ani null.");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email, CompanySystem system) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email nie może być pusty ani null.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Email ma nieprawidłowy format.");
        }

        system.getEmployees().stream()
                .anyMatch(e -> email.equals(e.getEmail()));

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company == null || company.trim().isEmpty()) {
            throw new IllegalArgumentException("Firma nie może być pusta ani null.");
        }

        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Stanowisko nie może być null.");
        }

        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Wypłata nie może być ujemna ani 0.");
        }

        this.salary = salary;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status zatrudnienia nie może być null.");
        }
        this.status = status;
    }
}
