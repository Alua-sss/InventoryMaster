package repositories.interfaces;

import models.User;

public interface IUserRepository {

    boolean registerUser(User user);
    User getUser(String username);

    }
