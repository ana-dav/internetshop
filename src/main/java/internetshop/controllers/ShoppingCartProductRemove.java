package internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.lib.Injector;
import internetshop.service.ProductService;

@WebServlet("/products/delete")
public class ShoppingCartProductRemove extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("internetshop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");

        Long id = Long.valueOf(productId);
        productService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
