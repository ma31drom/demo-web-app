package by.pvt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.pvt.repository.model.User;
import by.pvt.service.UserService;

@Controller
@RequestMapping(path = "/users")
public class UserController {

	private static final Integer DEFAULT_PAGE_NUM = 1;
	private static final Integer DEFAULT_PAGE_SIZE = 5;

	@Autowired
	UserService userService;

	@GetMapping(path = "/list")
	public String getUserList(@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer pageNumber, Model model) {
		pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
		pageNumber = pageNumber == null ? DEFAULT_PAGE_NUM : pageNumber;

		model.addAttribute("userList", userService.getPage(pageNumber, pageSize));

		return "list";
	}

	@GetMapping(path = "/new")
	public String createUserPage(Model model) {
		if (model.getAttribute("user") == null) {
			model.addAttribute("user", new User());
		}
		return "createUser";
	}

	@PostMapping
	public String createUser(User user, Model model) {

		userService.save(user);

		model.addAttribute("message", "User sucessfully created");

		return "index";
	}

}
