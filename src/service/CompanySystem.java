package service;
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
            System.out.println("Dodano nowego pracownika: " + newEmployee.getName() + ".");
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

        System.out.println("\nLista pracowników z " + company + ": ");
        result.forEach(System.out::println);
        return result;
    }

    //Prezentacja pracowników w kolejności alfabetycznej według nazwiska
    //sortowanie
    public List<Employee> sortByLastName() {
        return employees.stream()
                .sorted(Comparator.comparing(
                        emp -> emp.getName().split(" ")[1]
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
    public double countAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average().orElse(0);
    }

    //Identyfikacja pracownika z najwyższym wynagrodzeniem
    public Optional<Employee> findByHighestSalary() {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }
}
