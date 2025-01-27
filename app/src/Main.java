
import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;



public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "new_password", "inventorymaster");

        AuthMenu authMenu = new AuthMenu();
        authMenu.onLoad();
    }

}