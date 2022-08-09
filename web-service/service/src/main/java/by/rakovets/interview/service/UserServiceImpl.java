package by.rakovets.interview.service;

import by.rakovets.interview.dao.UserDao;
import by.rakovets.interview.dao.UserDaoImpl;
import by.rakovets.interview.exception.UserNotFoundException;
import by.rakovets.interview.model.User;

public class UserServiceImpl implements UserService {
    private static final Object LOCK = new Object();
    private static UserServiceImpl INSTANCE = null;

    private final UserDao userDao;

    public static UserServiceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new UserServiceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public UserServiceImpl() {
        this.userDao = UserDaoImpl.getInstance();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }
}
