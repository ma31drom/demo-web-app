package by.pvt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.pvt.repository.model.User;
import by.pvt.service.UserFilter;
import by.pvt.service.UserService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/users")
public class UserManagementControler {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public User getById(@PathVariable Integer id) {

		return userService.getById(id);
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@PostMapping(path = "/query}")
	public List<User> getByFilter(@RequestBody UserFilter filter) {
		return userService.getByFilter(filter);
	}

}
