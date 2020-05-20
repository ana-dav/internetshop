package internetshop.service;

import internetshop.model.Order;
import internetshop.model.Product;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Product> products, Long user);

    List<Order> getUserOrders(Long user);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);

}
