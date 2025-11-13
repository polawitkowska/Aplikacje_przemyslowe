package service;
import model.CompanyStatistics;
import model.Employee;
import model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class CompanySystem implements CompanySystemInterface {
    private List<Employee> employees;

    public CompanySystem() {
        this.employees = new ArrayList<>();
    }

    //Dodawanie nowego pracownika do systemu z walidacją unikalności adresu email przed dodaniem
    public void addEmployee(Employee newEmployee) {
        boolean exists = employees.stream()
                .anyMatch(emp -> emp.getEmail().equals(newEmployee.getEmail()));

        if (exists) {
            throw new RuntimeException("Pracownik z mailem " + newEmployee.getEmail() + " już istnieje.");
        } else {
            employees.add(newEmployee);
            System.out.println("Dodano nowego pracownika: " + newEmployee.getFullName() + ".");
        }
    }

    //Getter listy pracownikow
    public List<Employee> getEmployees() {
        return employees;
    }

    //Wyświetlanie listy wszystkich pracowników w systemie
    public void showEmployees() {
        System.out.println("\nLista pracowników: ");
        employees.forEach(System.out::println);
    }

    //Wyszukiwanie pracowników zatrudnionych w konkretnej firmie (i od razu pokazanie ich)
    public List<Employee> findByCompany(String company) {
        List<Employee> result = employees.stream()
                .filter(emp -> emp.getCompany().equals(company))
                .toList();

        return result;
    }

    public Employee findByEmail(String email) {
        return employees.stream()
                .filter(emp -> emp.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    //Prezentacja pracowników w kolejności alfabetycznej według nazwiska
    //sortowanie
    public List<Employee> sortByLastName() {
        return employees.stream()
                .sorted(Comparator.comparing(
                        Employee::getLastName
                ))
                .toList();
    }

    //prezentacja posortowanych
    public void showEmployeesSorted() {
        List<Employee> sorted = sortByLastName();
        System.out.println("\nLista pracowników w kolejności alfabetycznej: ");
        sorted.forEach(System.out::println);
    }

    //Grupowanie pracowników według zajmowanego stanowiska
    public Map<Position, List<Employee>> groupByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
    }

    //Zliczanie liczby pracowników na każdym stanowisku
    public Map<Position, Integer> countByPosition() {
        Map<Position, List<Employee>> grouped = groupByPosition();
        return grouped.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().size()
                ));
    }

    //Obliczanie średniego wynagrodzenia w całej organizacji
    public double countAverageSalary(String company) {
        List<Employee> employeesFromComp = findByCompany(company);
        return employeesFromComp.stream()
                .mapToDouble(Employee::getSalary)
                .average().orElse(0);
    }

    //Identyfikacja pracownika z najwyższym wynagrodzeniem
    public Optional<Employee> findByHighestSalary(String company) {
        List<Employee> employeesFromComp = findByCompany(company);
        return employeesFromComp.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    public List<Employee> validateSalaryConsistency() {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            int baseSalary = e.getPosition().getBaseSalary();
            if (e.getSalary() < baseSalary) {
                result.add(e);
            }
        }
        return result;
    }

    public Map<String, CompanyStatistics> getCompanyStatistics() {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCompany,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Map<String, Integer> countByPosition = list.stream()
                                            .collect(Collectors.groupingBy(
                                                    e -> e.getPosition().toString(),
                                                    Collectors.summingInt(e -> 1)
                                            ));

                                    String companyName = list.get(0).getCompany();
                                    int avgSalary = (int) countAverageSalary(companyName);

                                    Optional<Employee> topEarner = findByHighestSalary(companyName);

                                    String topName = topEarner
                                            .map(e -> e.getFirstName() +
                                                    " " + e.getLastName()).orElse("-");

                                    return new CompanyStatistics(countByPosition, avgSalary, topName);
                                }
                        )
                ));
    }
}