package by.pvt.service;

import by.pvt.service.dto.UserDetailsDTO;

public interface RegistrationService {

	void registerUser(UserDetailsDTO user);

	void activateUser(String login);
	
}
