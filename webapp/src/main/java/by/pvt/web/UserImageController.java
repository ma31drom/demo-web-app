package by.pvt.web;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.pvt.repository.model.User;
import by.pvt.service.StorageService;
import by.pvt.service.UserService;

@Controller
public class UserImageController {

	@Autowired
	private UserService service;
	@Autowired
	private StorageService storageService;

	@PostMapping("/users/{userId}/files")
	String saveUserImage(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) throws IOException {

		User byId = service.getById(userId);
		byId.setMimeType(file.getContentType());
		byId.setFileName(file.getOriginalFilename());

		String save = storageService.save(file.getInputStream());

		byId.setFilePath(save);
		service.save(byId);

		return "redirect:/";
	}

	@GetMapping(path = "/users/{userId}/files", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public void getImg(@PathVariable Integer userId, HttpServletResponse response) {
		User byId = service.getById(userId);
		if (byId.getFilePath() == null) {
			return;
		}
		String filePath = byId.getFilePath();
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try {
			IOUtils.copy(storageService.read(filePath), response.getOutputStream());
		} catch (IOException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}

}
