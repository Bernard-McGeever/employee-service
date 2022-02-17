package tech.bmportfolio.employeeservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.bmportfolio.employeeservice.model.Employee;
import tech.bmportfolio.employeeservice.service.EmployeeService;

@RestController
public class EmployeeController
{
    private final EmployeeService service;

    public EmployeeController(EmployeeService service)
    {
        this.service = service;
    }

    // GET
    @GetMapping("/employees")
    List<Employee> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/employees/{id}")
    Employee findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // POST

    @PostMapping("/employees")
    Employee saveEmployee(@RequestBody Employee newEmployee) {
        return service.save(newEmployee);
    }

    // PUT

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return service.replaceEmployee(newEmployee, id);
    }

    // DELETE

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id)
    {
        service.deleteEmployee(id);
    }
}
