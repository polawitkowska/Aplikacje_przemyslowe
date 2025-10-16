package model;

public class CompanyStatistics {
    private int employeeCount;
    private int averageSalary;
    private String highestSalaryEmp;

    public CompanyStatistics() {}

    public CompanyStatistics(int employeeCount, int averageSalary, String highestSalaryEmp) {
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.highestSalaryEmp = highestSalaryEmp;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        if (employeeCount < 0) {
            throw new IllegalArgumentException("Liczba pracowników nie może być ujemna");
        }
        this.employeeCount = employeeCount;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        if (averageSalary < 0) {
            throw new IllegalArgumentException("Średnie wynagrodzenie nie może być ujemne");
        }
        this.averageSalary = averageSalary;
    }

    public String getHighestSalaryEmp() {
        return highestSalaryEmp;
    }

    public void setHighestSalaryEmp(String highestSalaryEmp) {
        if (highestSalaryEmp == null) {
            throw new IllegalArgumentException("Pracownik nie może być null");
        }
        this.highestSalaryEmp = highestSalaryEmp;
    }
}
