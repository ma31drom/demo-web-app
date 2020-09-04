package by.pvt.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(path = "/users/{userId}/files", 
			produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImg(@PathVariable Integer userId) {
		User byId = service.getById(userId);
		if (byId.getFilePath() == null) {
			return new byte[0];
		}
		String filePath = byId.getFilePath();
		InputStream read = storageService.read(filePath);
		try {
			return IOUtils.toByteArray(read);
		} catch (IOException e) {
			return new byte[0];
		}
	}

}
