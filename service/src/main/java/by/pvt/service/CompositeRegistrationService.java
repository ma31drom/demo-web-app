package by.pvt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import by.pvt.service.dto.UserDetailsDTO;

@Service
@Primary
public class CompositeRegistrationService implements RegistrationService {

	@Autowired
	private List<RegistrationService> services;

	@Override
	public void registerUser(UserDetailsDTO user) {
		services.forEach(s -> s.registerUser(user));
	}

	@Override
	public void activateUser(String login) {
		services.forEach(s -> s.activateUser(login));
	}

}
