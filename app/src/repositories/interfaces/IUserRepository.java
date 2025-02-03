package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean userExists(String username);
    boolean addUser(User user);
    User getUserByUsername(String username);

    }
