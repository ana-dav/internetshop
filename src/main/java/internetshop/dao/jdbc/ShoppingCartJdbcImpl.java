package internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import internetshop.dao.ShoppingCartDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartJdbcImpl implements ShoppingCartDao {
//    @Override
//    public ShoppingCart create(ShoppingCart element) {
//        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(query,
//                    PreparedStatement.RETURN_GENERATED_KEYS);
//            statement.setLong(1, element.getUserId());
//            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            resultSet.next();
//            element.setId(resultSet.getLong(1));
//            addCartsProducts(element);
//            return element;
//        } catch (SQLException e) {
//            throw new DataProcessingException("Unable to create " + element, e);
//        }
//    }
//
//    @Override
//    public Optional<ShoppingCart> get(Long id) {
//        String query = "SELECT * FROM shopping_carts WHERE cart_id = ?;";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
//                shoppingCart.setProducts(getProductsFromShoppingCartId(id));
//                return Optional.of(shoppingCart);
//            }
//            return Optional.empty();
//        } catch (SQLException e) {
//            throw new DataProcessingException("Unable to get cart with ID " + id, e);
//        }
//    }
//
//    @Override
//    public ShoppingCart update(ShoppingCart element) {
//        String query = "UPDATE shopping_carts SET user_id = ? "
//                + "WHERE cart_id = ?;";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setLong(1, element.getUserId());
//            statement.setLong(2, element.getId());
//            statement.executeUpdate();
//            deleteShoppingCartFromCartsProducts(element.getId());
//            addCartsProducts(element);
//            return element;
//        } catch (SQLException e) {
//            throw new DataProcessingException("Unable to update ", e);
//        }
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        String deleteShoppingCartQuery = "DELETE FROM shopping_carts WHERE cart_id = ?;";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            deleteShoppingCartFromCartsProducts(id);
//            PreparedStatement statement = connection.prepareStatement(deleteShoppingCartQuery);
//            statement.setLong(1, id);
//            int numberOfRowsDeleted = statement.executeUpdate();
//            return numberOfRowsDeleted != 0;
//        } catch (SQLException e) {
//            throw new DataProcessingException("Unable to delete cart with ID " + id, e);
//        }
//    }
//
//    @Override
//    public List<ShoppingCart> getAll() {
//        String query = "SELECT * FROM shopping_carts;";
//        List<ShoppingCart> allShoppingCarts = new ArrayList<>();
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
//                shoppingCart.setProducts(getProductsFromShoppingCartId(shoppingCart.getId()));
//                allShoppingCarts.add(shoppingCart);
//            }
//            return allShoppingCarts;
//        } catch (SQLException e) {
//            throw new DataProcessingException("Unable to retrieve all carts", e);
//        }
//    }
//
//    private void addCartsProducts(ShoppingCart shoppingCart) throws SQLException {
//        String insertCartsProductsQuery = "INSERT INTO shopping_cart_products (cart_id, product_id) "
//                + "VALUES (?, ?);";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            for (Product product : shoppingCart.getProducts()) {
//                PreparedStatement addStatement =
//                        connection.prepareStatement(insertCartsProductsQuery);
//                addStatement.setLong(1, shoppingCart.getUserId());
//                addStatement.setLong(2, product.getId());
//                addStatement.executeUpdate();
//            }
//        }
//    }
//
//    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet) throws SQLException {
//        Long id = resultSet.getLong("cart_id");
//        Long userId = resultSet.getLong("user_id");
//        ShoppingCart shoppingCart = new ShoppingCart(userId);
//        shoppingCart.setId(id);
//        return shoppingCart;
//    }
//
//    private List<Product> getProductsFromShoppingCartId(Long shoppingCartId) throws SQLException {
//        String selectProductIdQuery = "SELECT products.* FROM shopping_cart_products "
//                + "JOIN products USING (product_id) WHERE cart_id = ?;";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(selectProductIdQuery);
//            statement.setLong(1, shoppingCartId);
//            ResultSet resultSet = statement.executeQuery();
//            List<Product> products = new ArrayList<>();
//            while (resultSet.next()) {
//                Long id = resultSet.getLong("product_id");
//                String name = resultSet.getString("name");
//                BigDecimal price = resultSet.getBigDecimal("price");
//                Product product = new Product(name, price);
//                product.setId(id);
//                products.add(product);
//            }
//            return products;
//        }
//    }
//
//    private void deleteShoppingCartFromCartsProducts(Long shoppingCartId) throws SQLException {
//        String deleteShoppingCartQuery = "DELETE FROM shopping_cart_products WHERE cart_id = ?;";
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(deleteShoppingCartQuery);
//            statement.setLong(1, shoppingCartId);
//            statement.executeUpdate();
//        }
//    }
@Override
public ShoppingCart create(ShoppingCart element) {
    String query = "INSERT INTO shopping_carts (user_id) VALUES (?);";
    try (Connection connection = ConnectionUtil.getConnection()) {
        PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, element.getUserId());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        element.setId(resultSet.getLong(1));
        addCartsProducts(element);
        return element;
    } catch (SQLException e) {
        throw new DataProcessingException("Unable to create " + element, e);
    }
}

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * FROM shopping_carts WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCart.setProducts(getProductsFromShoppingCartId(id));
                return Optional.of(shoppingCart);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get cart with ID " + id, e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        String query = "UPDATE shopping_carts SET user_id = ? "
                + "WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, element.getUserId());
            statement.setLong(2, element.getId());
            statement.executeUpdate();
            deleteShoppingCartFromCartsProducts(element.getId());
            addCartsProducts(element);
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteShoppingCartQuery = "DELETE FROM shopping_carts WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteShoppingCartFromCartsProducts(id);
            PreparedStatement statement = connection.prepareStatement(deleteShoppingCartQuery);
            statement.setLong(1, id);
            int numberOfRowsDeleted = statement.executeUpdate();
            return numberOfRowsDeleted != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete cart with ID " + id, e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts;";
        List<ShoppingCart> allShoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCart.setProducts(getProductsFromShoppingCartId(shoppingCart.getId()));
                allShoppingCarts.add(shoppingCart);
            }
            return allShoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all carts", e);
        }
    }

    private void addCartsProducts(ShoppingCart shoppingCart) throws SQLException {
        String insertCartsProductsQuery = "INSERT INTO shopping_cart_products (cart_id, product_id) "
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : shoppingCart.getProducts()) {
                PreparedStatement addStatement =
                        connection.prepareStatement(insertCartsProductsQuery);
                addStatement.setLong(1, shoppingCart.getUserId());
                addStatement.setLong(2, product.getId());
                addStatement.executeUpdate();
            }
        }
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(id);
        return shoppingCart;
    }

    private List<Product> getProductsFromShoppingCartId(Long shoppingCartId) throws SQLException {
        String selectProductIdQuery = "SELECT products.* FROM shopping_cart_products "
                + "JOIN products USING (product_id) WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectProductIdQuery);
            statement.setLong(1, shoppingCartId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }

    private void deleteShoppingCartFromCartsProducts(Long shoppingCartId) throws SQLException {
        String deleteShoppingCartQuery = "DELETE FROM shopping_cart_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteShoppingCartQuery);
            statement.setLong(1, shoppingCartId);
            statement.executeUpdate();
        }
    }
}
