package controllers.interfaces;

import models.User;

public interface IUserController {
    void login();
    void registerUser();
    void logoutUser();
    User getCurrentUser();
}
