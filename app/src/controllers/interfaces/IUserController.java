package controllers.interfaces;

import models.User;

public interface IUserController {
    User getUser(String username);
    String registerUser(String username, String password);
}
