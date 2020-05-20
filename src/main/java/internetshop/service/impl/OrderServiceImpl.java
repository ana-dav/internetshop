package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService cartService;

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        Order order = new Order(products, userId);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
