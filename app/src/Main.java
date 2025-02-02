
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;

import models.Product;
import repositories.ProductRepository;
import repositories.UserRepository;

import repositories.interfaces.IProductRepository;
import repositories.interfaces.IUserRepository;


public class Main {

    public static void main(String[] args) {

        AuthMenu authMenu = new AuthMenu();
        authMenu.onLoad();

        IDB db = PostgresDB.getInstance();
        db.close();
    }

}