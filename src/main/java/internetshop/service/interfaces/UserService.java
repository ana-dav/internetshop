package internetshop.service.interfaces;

import internetshop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
