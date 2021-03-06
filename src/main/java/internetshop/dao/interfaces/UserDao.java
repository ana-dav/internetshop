package internetshop.dao.interfaces;

import internetshop.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> findByLogin(String login);
}
