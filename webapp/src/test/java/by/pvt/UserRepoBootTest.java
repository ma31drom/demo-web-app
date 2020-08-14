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
	public void deleteVSdeleteBatchTest() throws InterruptedException {

		List<User> users = new ArrayList<User>();

		users.add(repo.save(getUser("first", "last", "pass2", "log")));
		users.add(repo.save(getUser("first1", "last", "pass", "log")));
		users.add(repo.save(getUser("first", "last2", "pass", "log6")));
		users.add(repo.save(getUser("first1", "last", "pass", "log")));
		users.add(repo.save(getUser("first", "last2", "pass5", "log")));
		users.add(repo.save(getUser("first1", "last2", "pass", "log8")));
		users.add(repo.save(getUser("first", "last", "pass", "log")));

		repo.deleteAll(users);

		List<User> users2 = new ArrayList<User>();

		users2.add(repo.save(getUser("first", "last", "pass2", "log")));
		users2.add(repo.save(getUser("first1", "last", "pass", "log")));
		users2.add(repo.save(getUser("first", "last2", "pass", "log6")));
		users2.add(repo.save(getUser("first1", "last", "pass", "log")));
		users2.add(repo.save(getUser("first", "last2", "pass5", "log")));
		users2.add(repo.save(getUser("first1", "last2", "pass", "log8")));
		users2.add(repo.save(getUser("first", "last", "pass", "log")));

		repo.deleteInBatch(users2);
	}

	@Test
	public void pageTest() throws InterruptedException {

		List<User> users = new ArrayList<User>();

		users.add(repo.save(getUser("first3", "last", "pass2", "log")));
		users.add(repo.save(getUser("first1", "last", "pass", "log")));
		users.add(repo.save(getUser("first7", "last", "pass", "log")));
		users.add(repo.save(getUser("first2", "last2", "pass", "log6")));
		users.add(repo.save(getUser("first8", "last2", "pass5", "log")));
		users.add(repo.save(getUser("first1", "last2", "pass", "log8")));
		users.add(repo.save(getUser("first1", "last", "pass", "log")));
		
		
		List<User> findByLogin = repo.findByLogin("log", PageRequest.of(0, 3, Sort.by(Direction.ASC, "firstName")));

		Set<String> vv = findByLogin.stream().map(User::getFirstName).collect(Collectors.toSet());
		HashSet<String> expected = new HashSet<String>();
		expected.add("first1");
		expected.add("first3");
		assertEquals(expected, vv);

		assertEquals(3, findByLogin.size());
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
