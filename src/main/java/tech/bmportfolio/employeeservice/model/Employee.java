package tech.bmportfolio.employeeservice.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee
{
    private @Id @GeneratedValue Long id;
    private String name;
    private String address;
    private Department department;
    private String role;

    public Employee()
    {
    }

    public Employee(String name, String address, Department department, String role)
    {
        this.name = name;
        this.address = address;
        this.department = department;
        this.role = role;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(address,
                                                                                                        employee.address)
            && department == employee.department && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, address, department, role);
    }

    @Override
    public String toString()
    {
        return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", department=" + department +
            ", role='" + role + '\'' +
            '}';
    }
}
