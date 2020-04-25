package internetshop.dao;

import java.util.List;
import java.util.Optional;
import internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean delete(Long id);
}
