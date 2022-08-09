package by.rakovets.interview.dao;

import by.rakovets.interview.exception.UserNotFoundException;
import by.rakovets.interview.model.Role;
import by.rakovets.interview.model.User;
import by.rakovets.interview.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDaoImpl implements UserDao {
    private static final Object LOCK = new Object();
    private static UserDaoImpl INSTANCE = null;
    private final DataSourceUtil DATA_SOURCE_UTIL;

    public static UserDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    public UserDaoImpl() {
        DATA_SOURCE_UTIL = DataSourceUtil.getInstance();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        DataSource dataSource = DATA_SOURCE_UTIL.getDataSource();
        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(UUID.fromString(resultSet.getString("user_id")));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return user;
            } else {
                throw new UserNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserNotFoundException();
        }
    }

    @Override
    public boolean save(User user) {
        DataSource dataSource = DATA_SOURCE_UTIL.getDataSource();
        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("INSERT INTO users(first_name, last_name, email, username, password, role, user_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.setObject(7, user.getId());
            int resultSet = preparedStatement.executeUpdate();
            return resultSet != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
