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
import java.math.BigDecimal;
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
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("bob", "userTest", "1234");
        User alisa = new User("alisa", "adminTest", "1234");
        bob.setRoles(Set.of(Role.of("USER")));
        alisa.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(bob);
        userService.create(alisa);

        Product product1 = new Product("shampoo", BigDecimal.valueOf(299));
        Product product2 = new Product("conditioner", BigDecimal.valueOf(3459));
        productService.create(product1);
        productService.create(product2);

        ShoppingCart shoppingCart = new ShoppingCart(bob.getId());
        shoppingCartService.create(shoppingCart);

        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
}
