package internetshop.service;

import java.util.List;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userID);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

}
