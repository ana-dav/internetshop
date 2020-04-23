package internetshop.dao;

import internetshop.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart bucket);

    ShoppingCart get(Long id);

    ShoppingCart update(ShoppingCart bucket);
}
