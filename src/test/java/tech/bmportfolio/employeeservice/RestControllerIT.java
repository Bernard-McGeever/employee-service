package tech.bmportfolio.employeeservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.runner.RunWith;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tech.bmportfolio.employeeservice.model.Department;
import tech.bmportfolio.employeeservice.model.Employee;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestControllerIT
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("GET")
    public class Get
    {
        @Test
        @DisplayName("Gets all employees")
        public void getAll() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").toString();

            // Act
            final ResultActions resultActions = mockMvc.perform(get(requestUrl)).andDo(print()).andExpect(status().isOk());
            final MvcResult result = resultActions.andReturn();
            final String contcatAsString = result.getResponse().getContentAsString();
            final Employee[] testSubject = objectMapper.readValue(contcatAsString, Employee[].class);

            // Assert
            assertThat(testSubject.length).isEqualTo(2);
            assertThat(testSubject[0].getName()).isEqualTo("Bernard McGeever");
            assertThat(testSubject[1].getName()).isEqualTo("Danny McGeever");
        }

        @Test
        @DisplayName("Get employee by ID")
        public void getByIdHappy() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").append("/1").toString();

            // Act
            final ResultActions resultActions = mockMvc.perform(get(requestUrl)).andDo(print()).andExpect(status().isOk());
            final MvcResult result = resultActions.andReturn();
            final String contentAsString = result.getResponse().getContentAsString();
            final Employee testSubject = objectMapper.readValue(contentAsString, Employee.class);

            // Assert
            assertThat(testSubject.getName()).isEqualTo("Bernard McGeever");
        }

        @Test
        @DisplayName("ID not found")
        public void getByIdUnhappy() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").append("/3").toString();

            // Act
            final MvcResult result = mockMvc.perform(get(requestUrl)).andDo(print()).andExpect(status().isNotFound()).andReturn();

            // Assert
            assertThat(result.getResponse().getContentAsString()).isEqualTo("Could not find employee 3");
        }
    }

    @Nested
    @DisplayName("POST")
    public class Post
    {
        @Test
        @DisplayName("Add new employee.")
        public void addNewEmployee() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").toString();

            // Arrange and Assert
            mockMvc.perform(post(requestUrl)
                                .content(objectMapper.writeValueAsString(new Employee("Dan McGeever",
                                                                                      "24 Hillway Cresent, Plymoth, Pl2 6FD",
                                                                                      Department.ADVETISING,
                                                                                      "Apprentice")))
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(MockMvcResultMatchers.jsonPath("name").value("Dan McGeever"));
        }
    }

    @Nested
    @DisplayName("PUT")
    public class Put
    {
        @Test
        @DisplayName("Repace an employee")
        public void replaceEmployee() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").append("/1").toString();

            // Act and Assert
            mockMvc.perform(put(requestUrl)
                   .content(objectMapper.writeValueAsString(new Employee("Bernard McGeever",
                                                                         "65 Kingsway, Coventry, CV2 4EX",
                                                                         Department.DEVELOPMENT,
                                                                         "Full Stack Software Developer")))
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                   .andExpect(MockMvcResultMatchers.jsonPath("name").value("Bernard McGeever"))
                   .andExpect(MockMvcResultMatchers.jsonPath("address").value("65 Kingsway, Coventry, CV2 4EX"))
                   .andExpect(MockMvcResultMatchers.jsonPath("department").value("DEVELOPMENT"))
                   .andExpect(MockMvcResultMatchers.jsonPath("role").value("Full Stack Software Developer"));
        }
    }

    @Nested
    @DisplayName("DELETE")
    public class Delete
    {
        @Test
        @DisplayName("Delete an employee.")
        public void deleteEmployee() throws Exception
        {
            // Arrange
            final String requestUrl = new StringBuilder("/employees").append("/1").toString();

            // Act and Assert
            mockMvc.perform(delete(requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk());
        }
    }
}
