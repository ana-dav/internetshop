package internetshop.service.impl;

import internetshop.dao.ProductDao;
import internetshop.dao.ShoppingCartDao;
import internetshop.lib.Dao;
import internetshop.lib.Service;
import internetshop.model.ShoppingCart;
import internetshop.service.ShoppingCartService;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Dao
    private ShoppingCartDao bucketDao;
    private ProductDao productDao;

    @Override
    public ShoppingCart addItem(Long bucketId, Long itemId) {
        ShoppingCart shoppingCart = bucketDao.get(bucketId);
        Optional product = productDao.get(itemId);
        // TODO: 4/23/20  
        //shoppingCart.getProducts().add(product);
        return bucketDao.update(shoppingCart);
    }
}
