package by.pvt.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@PostMapping
	public String login(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			model.addAttribute("login", ((User) principal).getUsername());
		} else {
			return "redirect:/login";
		}
		return "redirect:/index";
	}

	@GetMapping
	String loginPage(ModelMap model) {
		return "login";
	}

}
