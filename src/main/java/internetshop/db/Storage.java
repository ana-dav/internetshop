package internetshop.db;

import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<User> users = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<Product> products = new ArrayList<>();

    private static Long userId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long orderId = 0L;
    private static Long productId = 0L;

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);
    }

    public static User addUser(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
        return user;
    }

    public static ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartId++;
        shoppingCart.setId(shoppingCartId);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public static Order addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
        return order;
    }
}
