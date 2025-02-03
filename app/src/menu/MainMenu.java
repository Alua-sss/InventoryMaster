package menu;

import controllers.ProductController;
import controllers.UserController;
import controllers.interfaces.IProductController;
import controllers.interfaces.IUserController;
import menu.interfaces.Menu;

import models.User;
import repositories.CategoryRepository;
import repositories.ProductRepository;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IProductRepository;
import services.CategoryService;
import services.ProductService;
import services.interfaces.ICategoryService;
import services.interfaces.IProductService;

import java.util.Scanner;

public class MainMenu implements Menu {
    private final IUserController userController;

    public MainMenu(IUserController userController) {
        this.userController = userController;
    }


    @Override
    public void onLoad() {

        IProductRepository productRepository = new ProductRepository();
        IProductService productService = new ProductService(productRepository);
        ICategoryRepository categoryRepository = new CategoryRepository();
        ICategoryService categoryService = new CategoryService(categoryRepository);

        IProductController productController = new ProductController(productService, categoryService);

        User currentUser = userController.getCurrentUser();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все товары");
            System.out.println("2. Добавить товар (только Admin)");
            System.out.println("3. Удалить товар (только Admin)");
            System.out.println("4. Поиск товара");
            System.out.println("5. Изменить товар");
            System.out.println("6. Назад");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productController.getAllProducts();
                    break;

                case 2:
                    if (!currentUser.getRole().equals("admin")) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                   productController.addProduct();
                    break;
                case 3:
                    if (!currentUser.getRole().equals("admin")) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                    productController.deleteProduct();
                    break;

                case 4:
                    productController.getProductById();
                    break;

                case 5:
                    if (!currentUser.getRole().equals("admin")) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                   productController.updateProduct();
                   break;

                case 6:
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

    }

}
