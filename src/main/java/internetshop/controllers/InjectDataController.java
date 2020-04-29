package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/injectData")
public class InjectDataController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("internetshop");
    private UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("bob", "login Bob", "kuhkug");
        User alisa = new User("alisa", "log", "pass");
        userService.create(bob);
        userService.create(alisa);

        Product product1 = new Product("soap", BigDecimal.valueOf(50));
        Product product2 = new Product("perfume", BigDecimal.valueOf(1999));
        productService.create(product1);
        productService.create(product2);

        ShoppingCart shoppingCart = new ShoppingCart(bob);
        shoppingCartService.create(shoppingCart);
        shoppingCartService.addProduct(shoppingCart, product1);

        req.getRequestDispatcher("WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
