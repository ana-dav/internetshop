package internetshop.controllers.user;

import internetshop.lib.Injector;
import internetshop.model.User;
import internetshop.service.interfaces.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/all")
public class GetAllUsersController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAll();
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/views/admin/users_all.jsp").forward(req, resp);
    }
}
