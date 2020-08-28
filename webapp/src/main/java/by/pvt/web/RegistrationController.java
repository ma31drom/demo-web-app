package by.pvt.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.pvt.service.RegistrationService;
import by.pvt.service.dto.UserDetailsDTO;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	@GetMapping
	String registerPage() {
		return "register";
	}
	@GetMapping("/{login}")
	String activatePage(@PathVariable String login) {
		service.activateUser(login);
		return "redirect:/login";
	}
	@PostMapping
	String registerUser(@Valid UserDetailsDTO user, BindingResult results) {
		if (results.hasErrors()) {
			return "register";
		}
		service.registerUser(user);
		return "redirect:/login";
	}
}
