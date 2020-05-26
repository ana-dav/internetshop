package internetshop.security;

import internetshop.exceptions.AuthenticationException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.User;
import internetshop.service.interfaces.UserService;
import internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new ArithmeticException("Incorrect login or password"));
        if (HashUtil.hashPassword(password,
                userFromDB.getSalt()).equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
