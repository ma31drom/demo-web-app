package by.pvt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import by.pvt.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService service;

	@GetMapping
	String loginPage(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			String username = ((User) principal).getUsername();
			model.addAttribute("login", username);
			model.addAttribute("user", service.getByLogin(username));
		}
		return "index";
	}

	@GetMapping("/index")
	String samePage(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			String username = ((User) principal).getUsername();
			model.addAttribute("login", username);
			model.addAttribute("user", service.getByLogin(username));
		}
		return "index";
	}
}
