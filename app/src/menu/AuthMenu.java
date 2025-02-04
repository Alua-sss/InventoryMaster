package menu;
import controllers.UserController;
import controllers.interfaces.IUserController;
import menu.interfaces.Menu;
import models.User;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import services.UserService;
import services.interfaces.IUserService;

import java.util.Scanner;

public class AuthMenu implements Menu {

    @Override
    public void onLoad() {

        IUserRepository userRepository = new UserRepository();
        IUserService userService = new UserService(userRepository);
        IUserController userController = new UserController(userService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Registration");
            System.out.println("2. Login");
            System.out.println("3. Logout session");
            System.out.println("0. Close program");

            System.out.print("Choose the action: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

               case 1 -> userController.registerUser();

               case 2 -> {
                   userController.login();
                   MainMenu menu = new MainMenu(userController);
                   menu.onLoad();
               }

               case 3 -> userController.logoutUser();

               case 0 -> {
                   System.out.println("Goodbye!");
                   return;
               }

               default -> {
                   System.out.println("Invalid choice");
               }
            }
        }
    }

}

