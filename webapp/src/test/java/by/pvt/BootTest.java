package by.pvt;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import by.pvt.repository.UserRepository;
import by.pvt.repository.model.User;

@SpringBootTest
public class BootTest {

	@Autowired
	public UserRepository repo;

	@Test
	public void test() throws InterruptedException {
		User user = new User();

		user.setFirstName("FirstName");
		user.setLastName("LastName");
		user.setPassword("Pass");
		user.setLogin("login");
		User save = repo.save(user);

		User findById = repo.findById(save.getId()).get();

		assertEquals(findById.getFirstName(), "FirstName");
		assertEquals(findById.getLastName(), "LastName");

	}

	@Test
	public void testCustomMethod() {
		User user = new User();

		user.setFirstName("FirstName1");
		user.setLastName("LastName1");
		user.setPassword("Pass1");
		user.setLogin("login1");
		User save = repo.save(user);

		User findById = repo.findById(save.getId()).get();

		User findByUserNameOrFirstName = repo.findByLoginOrFirstName("login1", "FirstName1");

		
		/*
		Method[] declaredMethods = repo.getClass().getDeclaredMethods();
		
		Method ourMethod = null;
		for (Method m : declaredMethods) {
			if (m.getName().startsWith("findByLogin")) {
				ourMethod = m;
			}
		}
		*/
		//findBy{field1Name}OR{field2nameIn} (String field1Name, List<String> field2name)
		
		//SELECT * {entity} FROM {field1Name} = ? OR {field2name} IN ?
		
		//ourMethod.getClass();
		
		
		assertEquals("FirstName1", findByUserNameOrFirstName.getFirstName());
	}

}
