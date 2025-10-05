package lab01;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CompanySystemInterface {
    void addEmployee(Employee employee);
    void showEmployees();
    List<Employee> findByCompany(String company);
    List<Employee> sortByLastName();
    void showEmployeesSorted();
    Map<Position, List<Employee>> groupByPosition();
    Map<Position, Integer> countByPosition();
    double countAverageSalary();
    Optional<Employee> findByHighestSalary();
}
