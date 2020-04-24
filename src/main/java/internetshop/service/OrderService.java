package internetshop.service;

import java.util.List;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.model.User;

public interface OrderService {

    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);

}
