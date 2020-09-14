package by.pvt.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler
	public String handleException(Throwable e, ModelAndView mw) {

		System.out.println();
		return "/";
	}
}
