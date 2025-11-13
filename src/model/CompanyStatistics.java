package model;

import java.util.Map;

public class CompanyStatistics {
    private Map<String, Integer> employeeCount;
    private int averageSalary;
    private String highestSalaryEmp;

    public CompanyStatistics() {}

    public CompanyStatistics(Map<String, Integer> employeeCount, int averageSalary, String highestSalaryEmp) {
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.highestSalaryEmp = highestSalaryEmp;
    }

    public Map<String, Integer> getEmployeeCount() {
        return employeeCount;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public String getHighestSalaryEmp() {
        return highestSalaryEmp;
    }
}
