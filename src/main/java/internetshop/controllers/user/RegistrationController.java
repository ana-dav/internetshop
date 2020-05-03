package internetshop.controllers.user;

import internetshop.lib.Injector;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        String repeatPassword = req.getParameter("pass-confirm");
        if (password.equals(repeatPassword)) {
            //here I create user and shoppingcart
            resp.sendRedirect(req.getContextPath() + "/");

            //the same user, added
            User user = userService.create(new User(name, login, password));
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartService.create(shoppingCart);
        } else {
            req.setAttribute("message", "Passwords are different");
            req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
