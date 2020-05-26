package internetshop.dao.interfaces;

import internetshop.model.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    public List<Order> getUserOrders(Long userId);

}
