package internetshop.dao.impl;

import internetshop.dao.UserDao;
import internetshop.db.Storage;
import internetshop.lib.Dao;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return Storage.addUser(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(i -> Storage.users.get(i).getId().equals(user.getId()))
                .forEach(i -> Storage.users.set(i, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users
                .removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users.stream()
                .filter(i -> i.getLogin().equals(login))
                .findFirst();
    }
}
