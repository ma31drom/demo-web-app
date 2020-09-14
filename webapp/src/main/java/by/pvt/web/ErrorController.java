package by.pvt.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@RequestMapping("/error")
	String getError(HttpServletRequest req, Throwable e) {
		req.getPathInfo();
		return "redirect:/index";
	}

	@Override
	public String getErrorPath() {
		return "";
	}

}
