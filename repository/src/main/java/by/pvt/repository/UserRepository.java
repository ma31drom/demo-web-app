package by.pvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.pvt.repository.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByLoginOrFirstName(String username, String firstName);
	
}
