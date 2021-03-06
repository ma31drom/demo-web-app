package by.pvt;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import by.pvt.repository.UserRepository;
import by.pvt.repository.model.Role;
import by.pvt.repository.model.User;
import by.pvt.service.config.EmailProperties;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableConfigurationProperties(value = EmailProperties.class)
public class Runner implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Autowired
	UserRepository repo;

	@Override
	public void afterPropertiesSet() throws Exception {
		repo.save(user("Maxim", "Naumovich", "maxim", "maxim", Role.ADMIN));
		repo.save(user("Maxim1", "Naumovich", "maxim", "maxim1", Role.USER));
		repo.save(user("Maxim2", "Naumovich", "maxim", "maxim2", Role.ADMIN));
		repo.save(user("Maxim3", "Naumovich", "maxim", "maxim3", Role.USER));
		repo.save(user("Maxim4", "Naumovich", "maxim", "maxim4", Role.ADMIN));
		repo.save(user("Maxim5", "Naumovich", "maxim", "maxim5", Role.USER));
		repo.save(user("Maxim6", "Naumovich", "maxim", "maxim6", Role.ADMIN));
		repo.save(user("Maxim7", "Naumovich", "maxim", "maxim7", Role.USER));
	}

	private User user(String fname, String lname, String passw, String login, Role role) {
		User entity = new User();
		entity.setFirstName(fname);
		entity.setLastName(lname);
		entity.setPassword(passw);
		entity.setLogin(login);
		entity.setRole(role);
		entity.setActive(true);
		return entity;
	}

}
