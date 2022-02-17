package tech.bmportfolio.employeeservice.web;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.bmportfolio.employeeservice.model.Employee;
import tech.bmportfolio.employeeservice.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Unit Test.")
class EmployeeControllerTest
{
    private EmployeeController testSubject;

    @Mock
    private EmployeeService mockEmployeeService;

    @BeforeEach
    public void setUp()
    {
        testSubject = new EmployeeController(mockEmployeeService);
    }

    @Nested
    @DisplayName("GET")
    public class Get
    {
        @Test
        @DisplayName("Returns list of employees provided by the service.")
        public void returnAllMeetings()
        {
            // Arrange
            List<Employee> expected = Collections.singletonList(new Employee());

            Mockito.when(mockEmployeeService.getAll()).thenReturn(expected);

            // Act
            List<Employee> actual = testSubject.getAll();

            // Assert
            Mockito.verify(mockEmployeeService, Mockito.times(1)).getAll();
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Returns employee by id provided by the service.")
        public void getById()
        {
            // Arrange
            Employee expected = new Employee();

            Mockito.when(mockEmployeeService.findById(Mockito.anyLong())).thenReturn(expected);

            // Act
            Employee actual = testSubject.findById(Mockito.anyLong());

            //Assert
            Mockito.verify(mockEmployeeService, Mockito.times(1)).findById(Mockito.anyLong());
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("POST")
    public class Post
    {
        @Test
        @DisplayName("Returns employee saved by service.")
        public void saveEmployee()
        {
            // Arrange
            Employee expected = new Employee();

            Mockito.when(mockEmployeeService.save(expected)).thenReturn(expected);

            // Act
            Employee actual = testSubject.saveEmployee(expected);

            // Assert
            Mockito.verify(mockEmployeeService, Mockito.times(1)).save(expected);
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("PUT")
    public class Put
    {
        @Test
        @DisplayName("Returns employee replaced by service.")
        public void replaceEmployee()
        {
            // Arrange
            Employee expected = new Employee();

            Mockito.when(mockEmployeeService.replaceEmployee(expected, 1L)).thenReturn(expected);

            // Act
            Employee actual = testSubject.replaceEmployee(expected, 1L);

            // Assert
            Mockito.verify(mockEmployeeService, Mockito.times(1)).replaceEmployee(expected, 1L);
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("DELETE")
    public class Delete
    {
        @Test
        @DisplayName("Returns employee deleted by service.")
        public void saveEmployee()
        {
            // Act
            testSubject.deleteEmployee(Mockito.anyLong());

            // Assert
            Mockito.verify(mockEmployeeService, Mockito.times(1)).deleteEmployee(Mockito.anyLong());
        }
    }
}