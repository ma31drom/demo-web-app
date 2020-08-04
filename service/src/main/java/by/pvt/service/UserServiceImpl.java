package by.pvt.service;

import org.springframework.stereotype.Service;

import by.pvt.repository.model.User;

@Service
public class UserServiceImpl extends CrudServiceJpaRepoImpl<User> implements UserService {
}
