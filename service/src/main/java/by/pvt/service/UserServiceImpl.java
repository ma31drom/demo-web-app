package by.pvt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.pvt.repository.UserRepository;
import by.pvt.repository.model.User;

@Service
public class UserServiceImpl extends CrudServiceJpaRepoImpl<User> implements UserService {

	@Autowired
	public UserRepository repo;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public List<User> getByFilter(UserFilter filter) {
		return repo.findAll(Example.of(MAPPER.convertValue(filter, User.class)));
	}
}
