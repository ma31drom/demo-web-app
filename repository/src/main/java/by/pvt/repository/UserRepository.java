package by.pvt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import by.pvt.repository.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByLoginOrFirstName(String username, String firstName);

	@Query("select u from User u where u.login = ?1 or u.firstName = ?2")
	List<User> findWithQuery(String login, String firstName);

	User findByLogin(String login);

}
