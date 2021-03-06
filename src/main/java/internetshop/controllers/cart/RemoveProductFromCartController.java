package internetshop.controllers.cart;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.service.interfaces.ProductService;
import internetshop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart/products/delete")
public class RemoveProductFromCartController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Product product = productService.get(Long.parseLong(req.getParameter("id")));
        shoppingCartService.deleteProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
