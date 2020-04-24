package internetshop.service;

import internetshop.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItem(Long bucketId, Long itemId);
}
