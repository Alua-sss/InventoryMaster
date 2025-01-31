
import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;
import models.User;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;


public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "new_password", "inventorymaster");
        db.getConnection();

        AuthMenu authMenu = new AuthMenu();
        authMenu.onLoad();

        IUserRepository userRepository = new UserRepository(db);
        User user = new User("admin", "admin");

        userRepository.registerUser(user);

        db.close();
    }

}