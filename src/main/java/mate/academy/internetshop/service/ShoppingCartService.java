package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userID);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

}
