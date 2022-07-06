package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();

    User showId(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
