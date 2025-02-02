
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import menu.AuthMenu;

import models.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;
import repositories.UserRepository;

import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IUserRepository;
import services.CategoryService;
import services.interfaces.ICategoryService;


public class Main {

    public static void main(String[] args) {

        AuthMenu authMenu = new AuthMenu();
        authMenu.onLoad();

        IDB db = PostgresDB.getInstance();
        db.close();
    }

}