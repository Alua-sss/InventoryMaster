package controllers;

import controllers.interfaces.IUserController;
import repositories.interfaces.IUserRepository;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getUser(String username) {
        return "";
    }

    @Override
    public String registerUser(String username, String password) {
        return "";
    }
}
