package services.interfaces;

import models.User;

public interface IUserService {
    User login(String username, String password);
    boolean registerUser(User user);
}
