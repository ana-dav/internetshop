package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/complete")
public class CreateOrderController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Long user = shoppingCart.getUserId();
        List<Product> products = List.copyOf(shoppingCart.getProducts());
        shoppingCartService.clear(shoppingCart);
        orderService.completeOrder(products, user);
        resp.sendRedirect(req.getContextPath() + "/user/orders");
    }
}
