package internetshop.dao.jdbc;

import internetshop.dao.UserDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t find user with login ", e);
        }
    }


    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password) values (?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                addUserRoles(user);
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create user", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                user.setRoles(getUserRoles(id));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get user with id ", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get list of users", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? "
                + "WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            deleteUserRoles(user.getId());
            addUserRoles(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update user", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteUserRoles(id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete user", e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User user = new User(name, login, password);
        user.setId(id);
        getUserRoles(user.getId());
        return user;
    }

    private Set<Role> getUserRoles(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query =  "SELECT role_name FROM users_roles"
                + " JOIN roles USING (role_id) WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get roles of user", e);
        }
        return roles;
    }

    private void deleteUserRoles(Long id) {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete roles of user", e);
        }
    }

    private void addUserRoles(User user) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, (SELECT role_id from roles WHERE role_name = ?));";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setLong(1, user.getId());
                statement.setString(2, String.valueOf(role.getRoleName()));
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long roleId = resultSet.getLong(2);
                    role.setId(roleId);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add roles of user", e);
        }
    }
}