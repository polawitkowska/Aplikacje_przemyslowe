package dto;

import java.util.Map;

public class CompanyStatisticsDTO {
    private String companyName;
    private Map<String, Integer> employeeCount;
    private int averageSalary;
    private String topEarnerName;

    public CompanyStatisticsDTO() {}

    public CompanyStatisticsDTO(String companyName, Map<String, Integer> employeeCount, int averageSalary, String highestSalaryEmp) {
        this.companyName = companyName;
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.topEarnerName = highestSalaryEmp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Map<String, Integer> getEmployeeCount() {
        return employeeCount;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public String getHighestSalaryEmp() {
        return topEarnerName;
    }
}
