package dto;

public class CompanyStatisticsDTO {
    private String companyName;
    private int employeeCount;
    private int averageSalary;
    private String topEarnerName;

    public CompanyStatisticsDTO() {}

    public CompanyStatisticsDTO(String companyName, int employeeCount, int averageSalary, String highestSalaryEmp) {
        this.companyName = companyName;
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.topEarnerName = highestSalaryEmp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        return topEarnerName;
    }

    public void setHighestSalaryEmp(String highestSalaryEmp) {
        if (highestSalaryEmp == null) {
            throw new IllegalArgumentException("Pracownik nie może być null");
        }
        this.topEarnerName = highestSalaryEmp;
    }
}
