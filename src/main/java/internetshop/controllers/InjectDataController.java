package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("internetshop");
    private UserService userService =
            (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("bob", "login Bob", "kuhkug");
        User alisa = new User("alisa", "log", "pass");
        userService.create(bob);
        userService.create(alisa);

        List<User> allUsers = userService.getAll();
        req.setAttribute("users", allUsers);

        req.getRequestDispatcher("WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
