package services;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static AuthService instance;
    private Map<String, User> users = new HashMap<>();
    private User currentUser = null;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }


    // Регистрация нового пользователя
    public void register(String username, String password, String role) {


        if (users.containsKey(username)) {
            System.out.println("Пользователь с таким именем уже существует.");
            return;
        }
        users.put(username, new User(username, password, role));
        System.out.println("Пользователь зарегистрирован: " + username);
    }

    // Вход в систему
    public boolean login(String username, String password) {

        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Неверное имя пользователя или пароль.");
            return false;
        }

        currentUser = user;
        System.out.println("Вход выполнен: " + username);

        return true;
    }

    // Проверка авторизации
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    // Получение текущего пользователя
    public User getCurrentUser() {
        return currentUser;
    }

    // Выход из системы
    public void logout() {
        if (currentUser != null) {
            System.out.println("Пользователь " + currentUser.getUsername() + " вышел из системы.");
            currentUser = null;
        } else {
            System.out.println("Нет активного пользователя.");
        }
    }

}


