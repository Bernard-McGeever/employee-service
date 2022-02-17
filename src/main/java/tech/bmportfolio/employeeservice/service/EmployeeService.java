package tech.bmportfolio.employeeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.bmportfolio.employeeservice.EmployeeNotFoundException;
import tech.bmportfolio.employeeservice.jpa.EmployeeRepository;
import tech.bmportfolio.employeeservice.model.Employee;

@Service
public class EmployeeService
{
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository)
    {
        this.repository = repository;
    }

    // GET

    public List<Employee> getAll()
    {
        return repository.findAll();
    }

    public Employee findById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // POST

    public Employee save(Employee newEmployee)
    {
        return repository.save(newEmployee);
    }

    // PUT

    public Employee replaceEmployee(Employee newEmployee, Long id)
    {
        return repository.findById(id)
                         .map(employee -> {
                             employee.setName(newEmployee.getName());
                             employee.setAddress(newEmployee.getAddress());
                             employee.setDepartment(newEmployee.getDepartment());
                             employee.setRole(newEmployee.getRole());
                             return repository.save(employee);
                         })
                         .orElseGet(() -> {
                             newEmployee.setId(id);
                             return repository.save(newEmployee);
                         });
    }

    // DELETE

    public void deleteEmployee(Long id)
    {
        repository.deleteById(id);
    }
}
