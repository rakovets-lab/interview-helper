package by.rakovets.interview.servlet;

import by.rakovets.interview.exception.ValidationException;
import by.rakovets.interview.model.Role;
import by.rakovets.interview.model.User;
import by.rakovets.interview.model.View;
import by.rakovets.interview.service.UserService;
import by.rakovets.interview.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

@WebServlet({
        "/form/registration",
        "/api/registration"
})
public class RegistrationServlet extends HttpServlet {
    public static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final UserService instance = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        try {
            user = createUserOrThrowException(req);
            boolean executed = instance.save(user);
            if (executed) {
                req.getRequestDispatcher(View.LOGIN.getViewPath()).forward(req, resp);
            } else {
                req.setAttribute("error", "Username or email is exists");
                req.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(req, resp);
            }
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(req, resp);
        }
    }

    private User createUserOrThrowException(HttpServletRequest req) throws ValidationException {
        User user = new User();
        user.setId(UUID.randomUUID());
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        if (firstName != null && firstName.length() >= 2
                && lastName != null && lastName.length() >= 2) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
        } else {
            throw new ValidationException("First name and last name must be at least 2 characters");
        }
        String email = req.getParameter("email");
        if (email != null && EMAIL_VALIDATION_PATTERN.matcher(email).matches()) {
            user.setEmail(email);
        } else {
            throw new ValidationException("Email is not correct");
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username != null && username.length() >= 8
                && password != null && password.length() >= 8) {
            user.setUsername(username);
            user.setPassword(password);
        } else {
            throw new ValidationException("Username and password must be at least 8 characters");
        }
        user.setRole(Role.USER);
        return user;
    }
}
