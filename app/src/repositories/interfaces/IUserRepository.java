package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean userExists(String username);
    boolean registerUser(User user);
    User getUser(String username);

    }
