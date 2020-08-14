package by.pvt.service;

import java.util.List;

import by.pvt.repository.model.User;

public interface UserService extends CrudService<User> {

	List<User> getByFilter(UserFilter filter);

}
