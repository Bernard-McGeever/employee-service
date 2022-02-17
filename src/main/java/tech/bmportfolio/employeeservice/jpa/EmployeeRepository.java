package tech.bmportfolio.employeeservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.bmportfolio.employeeservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
