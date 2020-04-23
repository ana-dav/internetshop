package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.math.BigDecimal;
import java.util.List;

public class App {
    // TODO: 4/23/20
    private static Injector injector =
            Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService =
                (ProductService) injector.getInstance(ProductService.class);

        initializeDb(productService);

        List<Product> allProducts = productService.getAll();
        for (Product product: allProducts) {
            System.out.println(product.toString());
        }
    }

    private static void initializeDb(ProductService productService) {
        Product product1 = new Product("Apple", BigDecimal.valueOf(10));
        Product product2 = new Product("Orange", BigDecimal.valueOf(10));
        Product product3 = new Product("Banana", BigDecimal.valueOf(10));
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);

    }
}
