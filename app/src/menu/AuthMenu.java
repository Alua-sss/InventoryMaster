package menu;
import controllers.UserController;
import controllers.interfaces.IUserController;
import menu.interfaces.Menu;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import services.AuthService;
import java.util.Scanner;

public class AuthMenu implements Menu {

    @Override
    public void onLoad() {

        IUserRepository userRepository = new UserRepository();
        IUserController userController = new UserController(userRepository);

        AuthService authService = AuthService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Выйти");
            System.out.println("4. Завершить программу");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Имя пользователя: ");
                    String username = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();

                    authService.login(userController.getUser(username), password);

                    if (authService.isLoggedIn()){

                        MainMenu mainMenu = new MainMenu();
                        mainMenu.onLoad();

                    }
                    break;

                case 2:
                    System.out.print("Имя пользователя: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String newPassword = scanner.nextLine();

                    System.out.println(userController.registerUser(newUsername, newPassword));
                    break;

                case 3:
                    authService.logout();
                    break;

                case 4:
                    System.out.println("Программа завершена.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

}

