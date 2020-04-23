package internetshop.dao.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.db.Storage;
import internetshop.model.ShoppingCart;
import java.util.NoSuchElementException;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart bucket) {
        // TODO: 4/23/20  
        return null;
    }

    @Override
    public ShoppingCart get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with if " + id));
    }

    @Override
    public ShoppingCart update(ShoppingCart bucket) {
        // TODO: 4/23/20  
        return null;
    }
}
