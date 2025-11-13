package controller;

import dto.EmployeeDTO;
import model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CompanySystem;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController @RequestMapping("/api/employees")
public class EmployeeController {

    private final CompanySystem companySystem;

    public EmployeeController(CompanySystem companySystem) {
        this.companySystem = companySystem;
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<Employee> employees = companySystem.getEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO dto = new EmployeeDTO(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getCompany(),
                    employee.getPosition(),
                    employee.getSalary()
            );
            employeeDTOs.add(dto);
        }

        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/api/employees/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = companySystem.findByEmail(email);

        if (employee != null) {
            EmployeeDTO dto = new EmployeeDTO(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getCompany(),
                    employee.getPosition(),
                    employee.getSalary()
            );

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/employees?company=X")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByCompany(@RequestParam(name = "company", required = false) String company) {
        List<Employee> employees = (company != null)
            ? companySystem.findByCompany(company)
            : companySystem.getEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO dto = new EmployeeDTO(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getCompany(),
                    employee.getPosition(),
                    employee.getSalary()
            );
            employeesDTO.add(dto);
        }

        return ResponseEntity.ok(employeesDTO);
    }

    @PostMapping("/api/employees")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO request) {
        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Employee employee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getCompany(),
                request.getPosition(),
                request.getSalary()
        );

        companySystem.addEmployee(employee);

        EmployeeDTO dto = new EmployeeDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCompany(),
                employee.getPosition(),
                employee.getSalary()
        );

        return ResponseEntity.created(
                URI.create("/api/employees/")
        ).body(dto);
    }

    @PutMapping("/api/employees/{email}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO request, @PathVariable String email) {
        Employee employee = companySystem.findByEmail(email);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.getFirstName() != null) employee.setFirstName(request.getFirstName());
        if (request.getLastName() != null) employee.setLastName(request.getLastName());
        if (request.getCompany() != null) employee.setCompany(request.getCompany());
        if (request.getPosition() != null) employee.setPosition(request.getPosition());
        if (request.getSalary() != 0) employee.setSalary(request.getSalary());

        EmployeeDTO dto = new EmployeeDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCompany(),
                employee.getPosition(),
                employee.getSalary()
        );

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/api/employees/{email}")
    public ResponseEntity<List<Employee>> removeEmployee(@PathVariable String email) {
        Employee employeeFound = companySystem.findByEmail(email);

        if (employeeFound == null) {
            return ResponseEntity.notFound().build();
        }

        List<Employee> employees = companySystem.getEmployees();
        employees.removeIf(e -> Objects.equals(e.getEmail(), email));

        return ResponseEntity.noContent().build();
    }
}
