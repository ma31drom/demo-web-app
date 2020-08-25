package by.pvt.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
public class LoginController {

	@PostMapping("/login")
	String login(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			model.addAttribute("login", ((User) principal).getUsername());
		} else {
			return "redirect:/login";
		}
		return "redirect:/index";
	}
	
	@GetMapping
	String loginE(ModelMap model) {
		return "login";
	}

}
