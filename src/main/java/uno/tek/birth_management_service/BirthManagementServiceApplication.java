package uno.tek.birth_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class,

})
public class BirthManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthManagementServiceApplication.class, args);
	}

}
