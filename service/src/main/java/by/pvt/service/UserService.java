package by.pvt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import by.pvt.repository.model.User;

public interface UserService extends CrudService<User> {

	List<User> getByFilter(UserFilter filter);

	User getByLogin(String login);

	boolean checkLoginPresent(String login);
	
	Page<User> getPage(Integer pageNum, Integer pageSize);
}
