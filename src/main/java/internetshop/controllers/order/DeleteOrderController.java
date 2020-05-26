package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/delete")
public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        orderService.delete(userId);
        resp.sendRedirect(req.getContextPath() + "/user/orders");
    }
}
