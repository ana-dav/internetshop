package internetshop.controllers.admin;

import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/admin")
public class AllOrders extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> allOrders = orderService.getAll();
        req.setAttribute("orders", allOrders);
        req.getRequestDispatcher("/WEB-INF/views/admin/orders_all.jsp").forward(req, resp);
    }
}
