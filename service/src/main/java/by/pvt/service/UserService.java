package by.pvt.service;

import java.util.List;

import by.pvt.repository.model.User;

public interface UserService extends CrudService<User> {

	List<User> getByFilter(UserFilter filter);

	User getByLogin(String login);

	boolean checkLoginPresent(String login);
	
	List<User> getPage(Integer pageNum, Integer pageSize);
}
