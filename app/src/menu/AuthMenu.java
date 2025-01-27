package menu;
import menu.interfaces.Menu;
import services.AuthService;
import java.util.Scanner;

public class AuthMenu implements Menu {

    @Override
    public void onLoad() {

        AuthService authService = AuthService.getInstance();
        Scanner scanner = new Scanner(System.in);

        // Тестовая подгрузка пользователей, потом надо удалить
        authService.register("admin", "admin123", "Admin");
        authService.register("user", "user123", "User");

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
                    authService.login(username, password);
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
                    String role = scanner.nextLine();
                    authService.register(newUsername, newPassword, role);
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

