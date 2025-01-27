package controllers.interfaces;

public interface IUserController {
    String getUser(String username);
    String registerUser(String username, String password);
}
