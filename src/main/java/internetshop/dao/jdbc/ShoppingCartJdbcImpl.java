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
@Override
public ShoppingCart create(ShoppingCart cart) {
    String query = "INSERT INTO shopping_carts (user_id) VALUES (?);";
    try (Connection connection = ConnectionUtil.getConnection()) {
        PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, cart.getUserId());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        cart.setId(resultSet.getLong(1));
        addCartsProducts(cart);
        return cart;
    } catch (SQLException e) {
        throw new DataProcessingException("Unable to create " + cart, e);
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
    public ShoppingCart update(ShoppingCart cart) {
        String query = "UPDATE shopping_carts SET user_id = ? "
                + "WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cart.getId());
            statement.executeUpdate();
            deleteShoppingCartFromCartsProducts(cart.getId());
            //doesn't add
            addCartsProducts(cart);
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + cart, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteShoppingCartQuery = "DELETE FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteShoppingCartFromCartsProducts(id);
            PreparedStatement statement = connection.prepareStatement(deleteShoppingCartQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
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

    private void addCartsProducts(ShoppingCart cart) {
        String insertCartsProductsQuery = "INSERT INTO shopping_cart_products (cart_id, product_id) VALUES (?, ?)";
        /////////////doesn't add here!!!!!!!!!!!!!!!!
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertCartsProductsQuery);
            for (Product product : cart.getProducts()) {
                preparedStatement.setLong(1, cart.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add cart products", e);
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
