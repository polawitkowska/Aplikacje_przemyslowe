package lab01;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Jan Kowalski", "jan.kowalski@gmail.com", "TechCorp", Position.PREZES);
        Employee emp2 = new Employee("Anna Nowak", "anna.nowak@gmail.com", "TechCorp", Position.WICEPREZES);
        Employee emp3 = new Employee("Karol Anioł", "karol.aniol@gmail.com", "TechCorp", Position.MANAGER);
        Employee emp4 = new Employee("Joanna Piotrowicz", "asia.piotrowicz@gmail.com", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);
        system.addEmployee(emp3);
        system.addEmployee(emp4);
//        system.showEmployees();
        system.showEmployeesSorted();

//        List<Employee> empTechCorp = system.findByCompany("TechCorp");
        Map<Position, List<Employee>> grouped = system.groupByPosition();
    }
}
