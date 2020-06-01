package internetshop.dao.interfaces;

import internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {
    ShoppingCart getByUserId(Long userId);
}
