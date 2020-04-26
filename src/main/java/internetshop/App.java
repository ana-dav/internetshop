package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class App {
    private static final Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        checkProductDB(productService);
        checkUserDB(userService);

        productService.getAll().forEach(System.out::println);
        userService.getAll().forEach(System.out::println);
    }

    private static void checkProductDB(ProductService productService) {
        List<Product> list = generateProducts();
        productService.create(list.get(0));
        productService.create(list.get(1));
        productService.create(list.get(2));
        productService.create(list.get(3));

        productService.delete(list.get(0).getId());

        list.get(1).setPrice(BigDecimal.valueOf(10));
        productService.update(list.get(1));
    }

    private static void checkUserDB(UserService userService) {
        var user1 = new User("name1", "login1", "123");
        var user2 = new User("name2", "login2", "124");

        userService.create(user1);
        userService.create(user2);

        userService.delete(user1.getId());

        user2.setLogin("loginChanged");
        userService.update(user2);
    }

    private static List<Product> generateProducts() {
        var product1 = new Product("Cucumber", BigDecimal.valueOf(10));
        var product2 = new Product("Cherry", BigDecimal.valueOf(11));
        var product3 = new Product("Milk", BigDecimal.valueOf(12));
        var product4 = new Product("Cheese", BigDecimal.valueOf(13));
        return Arrays.asList(product1, product2, product3, product4);
    }
}

