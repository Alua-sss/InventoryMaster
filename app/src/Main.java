import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;

public class Main {

    public static void main(String[] args) {

        AuthMenu authMenu = new AuthMenu();
        authMenu.onLoad();

        IDB db = PostgresDB.getInstance();
        db.close();
    }

}