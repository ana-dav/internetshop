package internetshop.controllers.cart;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.service.ShoppingCartService;

@WebServlet("/cart")
public class ShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        List<Product> products = shoppingCart.getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req,resp);
    }
}
