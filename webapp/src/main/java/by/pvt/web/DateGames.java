package by.pvt.web;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/date")
public class DateGames {

	@PostMapping
	String postDate(@RequestParam LocalDateTime date) {
		System.out.println();
		return "index";
	}

}
