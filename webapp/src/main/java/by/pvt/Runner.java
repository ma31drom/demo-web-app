package by.pvt;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import by.pvt.repository.UserRepository;
import by.pvt.repository.model.Role;
import by.pvt.repository.model.User;
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
		User entity = new User();
		entity.setFirstName("Maxim");
		entity.setLastName("Naumovich");
		entity.setPassword("maxim");
		entity.setLogin("maxim");
		entity.setRole(Role.ADMIN);
		repo.save(entity);

	}

}
