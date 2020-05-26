package internetshop.dao.interfaces;

import internetshop.dao.interfaces.GenericDao;
import internetshop.model.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    public List<Order> getUserOrders(Long userId);

}
