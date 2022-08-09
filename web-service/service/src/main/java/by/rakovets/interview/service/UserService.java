package by.rakovets.interview.service;

import by.rakovets.interview.exception.UserNotFoundException;
import by.rakovets.interview.model.User;

/**
 * Service for working with User.
 */
public interface UserService {
    /**
     * Find user by username and password .
     *
     * @param username username
     * @param password password
     * @return user {@link User}
     * @throws UserNotFoundException error when user didn't find
     */
    User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;

    /**
     * Save user.
     *
     * @param user saving user
     * @return <ul>
     * <li>{@code true} if saved</li>
     * <li>{@code false} if didn't save</li>
     * </ul>
     */
    boolean save(User user);
}
