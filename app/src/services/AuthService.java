package services;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static AuthService instance;
    private Map<String, User> users = new HashMap<>();
    private User currentUser = null;


    private AuthService() {
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }


    public boolean login(User user, String password) {
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Неверное имя пользователя или пароль.");
            return false;
        }

        currentUser = user;
        System.out.println("Вход выполнен: " + user.getUsername());

        return true;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Пользователь " + currentUser.getUsername() + " вышел из системы.");
            currentUser = null;
        } else {
            System.out.println("Нет активного пользователя.");
        }
    }

}


