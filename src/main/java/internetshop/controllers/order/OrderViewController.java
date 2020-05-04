package internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;

@WebServlet("/order/details")
public class OrderViewController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("id");
        Long id = Long.valueOf(orderId);
        Order order = orderService.get(id);

        List<Product> products = order.getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/orders/order.jsp").forward(req,resp);
    }
}
