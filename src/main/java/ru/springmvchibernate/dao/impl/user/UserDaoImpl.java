package ru.springmvchibernate.dao.impl.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.springmvchibernate.dao.abstraction.user.UserDao;
import ru.springmvchibernate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 */
@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void saveUser(User user) {
            em.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        TypedQuery<User> query = em.createQuery("DELETE from User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }


}
