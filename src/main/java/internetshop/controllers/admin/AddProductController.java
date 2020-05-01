package internetshop.controllers.admin;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newprod")
public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/newprod.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(priceStr));

        productService.create(new Product(name, price));
        req.getRequestDispatcher("/WEB-INF/views/products/newprod.jsp").forward(req, resp);
    }
}
