package tech.bmportfolio.employeeservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.bmportfolio.employeeservice.EmployeeNotFoundException;
import tech.bmportfolio.employeeservice.jpa.EmployeeRepository;
import tech.bmportfolio.employeeservice.model.Employee;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Unit Test.")
class EmployeeServiceTest
{
    private EmployeeService testSubject;

    @Mock
    private EmployeeRepository mockRepository;

    @BeforeEach
    public void setUp()
    {
        testSubject = new EmployeeService(mockRepository);
    }

    @Nested
    @DisplayName("GET")
    public class Get
    {
        @Test
        @DisplayName("Returns list of meeting provided by the repository.")
        public void returnAllMeetings()
        {
            // Arrange
            List<Employee> expected = Collections.singletonList(new Employee());

            Mockito.when(mockRepository.findAll()).thenReturn(expected);

            // Act
            List<Employee> actual = testSubject.getAll();

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Returns single employee found by id.")
        public void foundById()
        {
            // Arrange
            Employee expected = new Employee();

            Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));

            // Act
            Employee actual = testSubject.findById(Mockito.anyLong());

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Throws exception when employee id not found.")
        public void notFoundById()
        {
            assertThrows(EmployeeNotFoundException.class, () -> testSubject.findById(Mockito.anyLong()));
        }
    }

    @Nested
    @DisplayName("POST")
    public class Post
    {
        @Test
        @DisplayName("Returns new meeting provided by repository.")
        public void newMeeting()
        {
            // Arrange
            Employee expected = new Employee();

            Mockito.when(mockRepository.save(expected)).thenReturn(expected);

            // Act
            Employee actual = testSubject.save(expected);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("PUT")
    public class Put
    {
        @Test
        @DisplayName("Returns replaced employee when id matches.")
        public void idMatch()
        {
            // Arrange
            Employee expected = Mockito.mock(Employee.class);

            Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(expected));
            Mockito.when(mockRepository.save(expected)).thenReturn(expected);

            // Act
            Employee actual = testSubject.replaceEmployee(expected, Mockito.anyLong());

            // Assert
            assertEquals(actual, expected);
            Mockito.verify(expected, Mockito.times(0)).setId(Mockito.anyLong());

        }

        @Test
        @DisplayName("Returns saved new employee when employee is not found by the repository.")
        public void idNotFound()
        {
            // Arrange
            Employee expected = Mockito.mock(Employee.class);

            Mockito.when(mockRepository.save(expected)).thenReturn(expected);

            // Act
            Employee actual = testSubject.replaceEmployee(expected, Mockito.anyLong());

            // Assert
            assertEquals(actual, expected);
            Mockito.verify(expected, Mockito.times(1)).setId(Mockito.anyLong());
        }
    }

    @Nested
    @DisplayName("DELETE")
    public class Delete
    {
        @Test
        @DisplayName("Repository delete employee method is called")
        public void deleteEmployee()
        {
            //Act
            testSubject.deleteEmployee(Mockito.anyLong());

            // Assert
            Mockito.verify(mockRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
        }
    }
}