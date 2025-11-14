package controller;

import dto.CompanyStatisticsDTO;
import dto.EmployeeDTO;
import model.CompanyStatistics;
import model.Employee;
import model.EmploymentStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CompanySystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final CompanySystem companySystem;

    public StatisticsController(CompanySystem companySystem) {
        this.companySystem = companySystem;
    }

    @GetMapping("/api/statistics/salary/average")
    public ResponseEntity<Map<String, Double>> getAverageSalary() {
        Map<String, CompanyStatistics> statistics = companySystem.getCompanyStatistics();
        double total = 0.0;
        int count = 0;

        for (CompanyStatistics cs : statistics.values()) {
            total += cs.getAverageSalary();
            count++;
        }

        double average = count == 0 ? 0.0 : total / count;
        return ResponseEntity.ok(Map.of("averageSalary", average));
    }

    @GetMapping("/api/statistics/salary/average?company=X")
    public ResponseEntity<Double> getAverageSalaryByCompany(@RequestParam(name = "company", required = false) String company) {
        if (company == null || company.isBlank()) {
            Map<String, CompanyStatistics> statistics = companySystem.getCompanyStatistics();
            double total = 0.0;
            int count = 0;
            for (CompanyStatistics cs : statistics.values()) {
                total += cs.getAverageSalary();
                count++;
            }
            double average = count == 0 ? 0.0 : total / count;
            return ResponseEntity.ok(average);
        }

        Double averageSalary = companySystem.countAverageSalary(company);

        return ResponseEntity.ok(averageSalary);
    }

    @GetMapping("/api/statistics/company/{companyName}")
    public ResponseEntity<CompanyStatisticsDTO> getCompanyStatisticsDTO(@PathVariable String companyName) {
        Map<String, CompanyStatistics> companyStatistics = companySystem.getCompanyStatistics();

        for (Map.Entry<String, CompanyStatistics> element : companyStatistics.entrySet()) {
            if (Objects.equals(element.getKey(), companyName)) {
                CompanyStatistics stats = element.getValue();

                CompanyStatisticsDTO dto = new CompanyStatisticsDTO(
                        element.getKey(),
                        stats.getEmployeeCount(),
                        stats.getAverageSalary(),
                        stats.getHighestSalaryEmp()
                );
                return ResponseEntity.ok(dto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/statistics/positions")
    public ResponseEntity<Map<String, Integer>> getEmployeeCount() {
        Map<String, CompanyStatistics> companyStatistics = companySystem.getCompanyStatistics();
        Map<String, Integer> result = new HashMap<>();

        for (CompanyStatistics stats : companyStatistics.values()) {
            Map<String, Integer> counts = stats.getEmployeeCount();
            for (Map.Entry<String, Integer> e : counts.entrySet()) {
                result.merge(e.getKey(), e.getValue(), Integer::sum);
            }
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/statistics/status")
    public ResponseEntity<Map<EmploymentStatus, Integer>> getDistributionByStatus() {
        List<Employee> employees = companySystem.getEmployees();
        Map<EmploymentStatus, Integer> statusDistribution = new HashMap<>();

        for (Employee employee : employees) {
            EmploymentStatus status = employee.getStatus();
            statusDistribution.merge(status, 1, Integer::sum);
        }

        return ResponseEntity.ok(statusDistribution);
    }
}
