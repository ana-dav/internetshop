package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.Role;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/injectData")
public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("bob", "login Bob", "kuhkug");
        User alisa = new User("alisa", "log", "pass");
        userService.create(bob);
        userService.create(alisa);
        User admin = new User("admin", "guru", "111");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

//        ShoppingCart shoppingCart = new ShoppingCart(bob);
//        shoppingCartService.create(shoppingCart);

        req.getRequestDispatcher("WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
