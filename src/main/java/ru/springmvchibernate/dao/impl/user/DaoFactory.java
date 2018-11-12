package ru.springmvchibernate.dao.impl.user;

import ru.springmvchibernate.dao.abstraction.user.UserDao;
public class DaoFactory {
    public UserDao makeDao() {
            return  new UserDaoImplHibernate();
    }
}
