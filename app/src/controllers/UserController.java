package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String registerUser(String username, String password) {
        User user = new User(0, username, password, "USER");
        boolean success = repo.registerUser(user);
        return success ? "Пользователь успешно зарегистрирован!" : "Ошибка при регистрации пользователя.";
    }


    @Override
    public User getUser(String username) {
        User user = repo.getUser(username);
        if (user == null) {
            System.out.println("Пользователь не найден");
            return null;
        }
        return user;
    }
}
