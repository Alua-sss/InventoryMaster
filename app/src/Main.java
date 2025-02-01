
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;

import repositories.UserRepository;
import repositories.interfaces.IUserRepository;


public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "new_password", "inventorymaster");
        db.getConnection();


        IUserRepository userRepository = new UserRepository(db);
        IUserController userController = new UserController(userRepository);


        AuthMenu authMenu = new AuthMenu(userController);
        authMenu.onLoad();


        db.close();
    }

}