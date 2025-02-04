package controllers;

import controllers.interfaces.IUserController;
import models.User;
import services.interfaces.IUserService;

import java.util.Scanner;

public class UserController implements IUserController {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerUser() {
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        User user = new User(userName, password);

        if (userService.registerUser(user)) {
            System.out.println("User successfully registered!");
        }else {
            System.out.println("Error when registering!");
        }
    }


    @Override
    public void login() {
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        User user = userService.login(userName, password);
        if (user != null) {
            System.out.println("User successfully logged in!");
            currentUser = user;
        } else {
            System.out.println("Error when logging in!");
        }
    }

    @Override
    public void logoutUser() {
        if(currentUser != null) {
            System.out.println("User successfully logged out!");
            currentUser = null;
        }
        else {
            System.out.println("No one entered the system!");
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }


}
