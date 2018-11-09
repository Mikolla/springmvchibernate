package ru.springmvchibernate.service.impl.user;

import ru.springmvchibernate.dao.abstraction.user.UserDao;
import ru.springmvchibernate.dao.impl.user.DaoFactory;
import ru.springmvchibernate.model.User;
import ru.springmvchibernate.service.abstraction.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {

        this.userDao = new DaoFactory().makeDao();
    }

    @Override
    public long saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {
       return userDao.getUserById(id);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
         userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
