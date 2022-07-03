package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User show(int id) {
        return index().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
