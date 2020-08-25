package by.pvt;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import by.pvt.repository.UserRepository;
import by.pvt.repository.model.User;
import by.pvt.service.UserFilter;
import by.pvt.service.UserService;

@SpringBootTest
public class UserRepoBootTest {

	@Autowired
	public UserRepository repo;

	@Autowired
	public UserService service;

	@BeforeEach
	public void init() {
		repo.deleteAll();
	}

	@Test
	public void findByExampleTest() throws InterruptedException {
		repo.save(getUser("first", "last", "pass2", "log"));
		repo.save(getUser("first1", "last", "pass", "log"));
		repo.save(getUser("first", "last2", "pass", "log6"));
		repo.save(getUser("first1", "last", "pass", "log"));
		repo.save(getUser("first", "last2", "pass5", "log"));
		repo.save(getUser("first1", "last2", "pass", "log8"));
		repo.save(getUser("first", "last", "pass", "log"));

		assertEquals(3, service.getByFilter(UserFilter.builder().firstName("first1").build()).size());

		assertEquals(2, service.getByFilter(UserFilter.builder().firstName("first").lastName("last2").build()).size());
	}

	@Test
	public void findByQuery() throws InterruptedException {

		User save = repo.save(getUser("first", "last", "pass2", "log"));
		User save2 = repo.save(getUser("first1", "last", "pass", "log5"));
		User save3 = repo.save(getUser("first3", "last2", "pass", "log6"));

		User findByLoginOrFirstName = repo.findByLoginOrFirstName("log", "first").iterator().next();
		User findWithQuery = repo.findWithQuery("log", "first").iterator().next();

		assertEquals(findByLoginOrFirstName.getId(), findWithQuery.getId());
	}

	private User getUser(String fname, String lname, String pass, String login) {
		User user = new User();
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setPassword(pass);
		user.setLogin(login);
		return user;
	}

}
