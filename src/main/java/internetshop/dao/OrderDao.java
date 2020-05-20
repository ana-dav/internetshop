package internetshop.dao;

import java.util.List;
import internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    public List<Order> getUserOrders(Long userId);

}
