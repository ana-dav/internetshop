package internetshop.security;

import internetshop.exceptions.AuthenticationException;
import internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
