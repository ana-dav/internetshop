package internetshop.controllers.user;

import internetshop.lib.Injector;
import internetshop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/delete")
public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String userId = req.getParameter("id");
        Long id = Long.valueOf(userId);
        userService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
