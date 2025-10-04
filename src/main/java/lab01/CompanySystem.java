package lab01;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class CompanySystem {
    private List<Employee> employees;

    public CompanySystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee newEmployee) {
        boolean exists = employees.stream()
                .anyMatch(emp -> emp.getEmail().equals(newEmployee.getEmail()));

        if (exists) {
            System.out.println("Pracownik z mailem " + newEmployee.getEmail() + " już istnieje.");
        } else {
            employees.add(newEmployee);
            System.out.println("Dodano nowego pracownika: " + newEmployee.getName() + ".");
        }
    }

    public void showEmployees() {
        System.out.println("\nLista pracowników: ");
        employees.forEach(System.out::println);
    }

    public List<Employee> findByCompany(String company) {
        List<Employee> result = employees.stream()
                .filter(emp -> emp.getCompany().equals(company))
                .toList();

        System.out.println("\nLista pracowników z " + company + ": ");
        result.forEach(System.out::println);
        return result;
    }

    public List<Employee> sortByLastName() {
        return employees.stream()
                .sorted(Comparator.comparing(
                        emp -> emp.getName().split(" ")[1]
                ))
                .toList();
    }

    public void showEmployeesSorted() {
        List<Employee> sorted = sortByLastName();
        System.out.println("\nLista pracowników w kolejności alfabetycznej: ");
        sorted.forEach(System.out::println);
    }

    public Map<Position, List<Employee>> groupByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
    }
}
