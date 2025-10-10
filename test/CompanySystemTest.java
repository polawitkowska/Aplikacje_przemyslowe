
import model.Employee;
import model.Position;
import org.junit.jupiter.api.Test;
import service.CompanySystem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CompanySystemTest {
    @Test
    void testAddEmployee() {
        CompanySystem system = new CompanySystem();
        Employee emp = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp);

        assertEquals(1, system.getEmployees().size());
    }

    @Test
    void emailsShouldBeUnique() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Janek Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);

        assertThrows(RuntimeException.class, () -> system.addEmployee(emp2));
    }

    @Test
    void testFindByCompany() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Anna Nowak", "anna.nowak@gmail.pl", "InnaFirma", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);

        assertEquals(1, system.findByCompany("TechCorp").size());
    }

    @Test
    void testSortByLastName() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Anna Nowak", "anna.nowak@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);

        List<Employee> sorted = system.sortByLastName();

        assertEquals(emp2, sorted.getFirst());
    }

    @Test
    void groupByPosition() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Anna Nowak", "anna.nowak@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);

        Map<Position, List<Employee>> grouped = system.groupByPosition();

        assertEquals(Map.of(Position.MANAGER, List.of(emp1, emp2)), grouped);
    }

    @Test
    void countByPosition() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Anna Nowak", "anna.nowak@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);

        Map<Position, Integer> counted = system.countByPosition();

        assertEquals(Map.of(Position.MANAGER, 2), counted);
    }

    @Test
    void testCountAverageSalary() {
        CompanySystem system = new CompanySystem();
        Employee emp1 = new Employee("Anna Nowak", "anna.nowak@gmail.pl", "TechCorp", Position.MANAGER);
        Employee emp2 = new Employee("Jan Kowalski", "jan.kowalski@gmail.pl", "TechCorp", Position.MANAGER);

        system.addEmployee(emp1);
        system.addEmployee(emp2);

        double average = system.countAverageSalary();

        assertEquals(12000.0, average);
    }
}
