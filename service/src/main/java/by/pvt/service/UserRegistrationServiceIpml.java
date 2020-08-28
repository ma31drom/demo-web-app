package by.pvt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.pvt.repository.model.Role;
import by.pvt.repository.model.User;
import by.pvt.service.config.EmailProperties;
import by.pvt.service.dto.UserDetailsDTO;

@Service
public class UserRegistrationServiceIpml implements RegistrationService {

	@Autowired
	private UserService userService;

	@Override
	public void registerUser(UserDetailsDTO user) {
		if (userService.checkLoginPresent(user.getLogin())) {
			throw new RuntimeException("User is present");
		}

		User u = new User();
		u.setLogin(user.getLogin());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		u.setActive(Boolean.FALSE);
		u.setRole(Role.USER);

		userService.save(u);
	}

	@Override
	public void activateUser(String login) {
		User byLogin = userService.getByLogin(login);
		byLogin.setActive(Boolean.TRUE);
		userService.save(byLogin);
	}

}
