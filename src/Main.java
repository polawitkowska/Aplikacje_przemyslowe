import exception.ApiException;
import model.Employee;
import model.ImportSummary;
import model.Position;
import service.ApiService;
import service.CompanySystem;
import service.ImportService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws ApiException {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Jan",  "Kowalski", "jan.kowalski@gmail.com", "TechCorp", Position.PREZES, Position.PREZES.getBaseSalary());
        Employee emp2 = new Employee("Anna", "Nowak", "anna.nowak@gmail.com", "TechCorp", Position.WICEPREZES, Position.WICEPREZES.getBaseSalary());
        Employee emp3 = new Employee("Karol", "Anioł", "karol.aniol@gmail.com", "TechCorp", Position.MANAGER, Position.MANAGER.getBaseSalary());
        Employee emp4 = new Employee("Joanna", "Piotrowicz", "asia.piotrowicz@gmail.com", "TechCorp", Position.MANAGER, Position.MANAGER.getBaseSalary()-200);

        system.addEmployee(emp1);
        system.addEmployee(emp2);
        system.addEmployee(emp3);
        system.addEmployee(emp4);
        system.showEmployees();
        system.showEmployeesSorted();

        system.findByCompany("TechCorp");

        Map<Position, List<Employee>> grouped = system.groupByPosition();
        Map<Position, Integer> counted = system.countByPosition();
        double average = system.countAverageSalary("TechCorp");
        Optional<Employee> mostPaying = system.findByHighestSalary("TechCorp");

        System.out.println("\nPracownicy pogrupowani po stanowiskach:\n" + grouped);
        System.out.println("\nZliczona ilość pracowników na każdym stanowisku:\n" + counted);
        System.out.println("\nŚrednie wynargordzenie:\n" + average);
        System.out.println("\nPracownik z najwyższą pensją:\n" + mostPaying);

        ImportService importService = new ImportService();
        ImportSummary imported = importService.importFromCsv("./resources/employees.csv", system);
        System.out.println("\nImported: " + imported.getImported() + "\nBłędy przy importowaniu: " + imported.getErrors());

        ApiService apiService = new ApiService();
        System.out.println(apiService.fetchEmployeesFromApi("https://jsonplaceholder.typicode.com/users"));

        System.out.println("\nPracownicy z wynagrodzeniem niższym niż ich bazowe: " + system.validateSalaryConsistency());
        System.out.println("\nPodsumowanie " + system.getCompanyStatistics());
    }
}
