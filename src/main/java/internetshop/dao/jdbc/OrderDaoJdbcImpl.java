package internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import internetshop.dao.OrderDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                Long orderId = resultSet.getLong(1);
                order.setId(orderId);
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order", e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create statement", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all products from orders", e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order);
        addProductsToOrder(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id=?"
                + "DELETE FROM orders_products WHERE order_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order", e);
        }
    }

//    //добавить поиск ордера по айди юзера - как get только по айди юзера
//    public Optional<Order> searchByUserId(Long userId) {
//        String query = "SELECT * FROM orders WHERE user_id=?";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setLong(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                //here duplicate order search by User id?
//                // Order order = getOrderFromResultSet(resultSet);
//                return Optional.of(order);
//            }
//        } catch (SQLException e) {
//            throw new DataProcessingException("Can't create statement", e);
//        }
//        return Optional.empty();
//    }

    private void addProductsToOrder(Order order) {
        String query = "INSERT INTO orders_products (order_id, product_id) values(?,?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Product product : order.getProducts()) {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to order", e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong(1);
        Long userId = resultSet.getLong(2);
        Order order = new Order(getProductsFromOrder(orderId), userId);
        order.setId(orderId);
        return order;
    }

    private List<Product> getProductsFromOrder(Long orderId) {
        String query = "SELECT product_id, products.name, products.price"
                + "FROM orders_products"
                + "INNER JOIN products"
                + "ON  orders_products.product_id=products.product_id"
                + "WHERE orders_products.order_id = ?;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setId(productId);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't receive orders products", e);
        }
    }

    private void deleteProductsFromOrder (Order order) {
        String query = "DELETE FROM orders_products WHERE order_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete products from orders_products", e);
        }
    }
}
