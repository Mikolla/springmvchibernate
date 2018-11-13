package ru.springmvchibernate;


import ru.springmvchibernate.model.User;
import ru.springmvchibernate.service.abstraction.user.UserService;
import ru.springmvchibernate.service.impl.user.UserServiceImpl;

import java.util.List;

public class testclass {
    public static void main(String[] args) throws  IllegalAccessException, InstantiationException, ClassNotFoundException {
        UserService service = new UserServiceImpl();
        List<User> users = service.getAllUsers();
    }
}
