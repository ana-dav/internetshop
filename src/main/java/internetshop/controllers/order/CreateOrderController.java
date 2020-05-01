package internetshop.controllers.order;

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
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;

@WebServlet("/order")
public class CreateOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //when i create order i pass in an empty list

        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        User user = shoppingCart.getUser();
        List<Product> products = List.copyOf(shoppingCart.getProducts());
        orderService.completeOrder(products, user);
        //req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+ "/orders");
        shoppingCartService.clear(shoppingCart);
    }
}
