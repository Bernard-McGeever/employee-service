package tech.bmportfolio.employeeservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tech.bmportfolio.employeeservice.jpa.EmployeeRepository;
import tech.bmportfolio.employeeservice.model.Department;
import tech.bmportfolio.employeeservice.model.Employee;

@Configuration
public class LoadData
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
            LOGGER.info("Preloading " + repository.save(new Employee("Bernard McGeever",
                                                                     "121 Something Rd, Town, London, AA1 AA1",
                                                                     Department.DEVELOPMENT,
                                                                     "Full Stack Software Developer")));
            LOGGER.info("Preloading " + repository.save(new Employee("Teja McGeever",
                                                                     "65 Something Rd, City, BB1 BB1",
                                                                     Department.HR,
                                                                     "HR Team Lead")));
        };
    }
}
