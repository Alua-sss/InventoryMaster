package menu;

import controllers.ProductController;
import controllers.interfaces.IProductController;
import menu.interfaces.Menu;
import models.Warehouse;

import models.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IProductRepository;
import services.AuthService;
import services.CategoryService;
import services.ProductService;
import services.interfaces.ICategoryService;
import services.interfaces.IProductService;

import java.util.Scanner;

public class MainMenu implements Menu {


    @Override
    public void onLoad() {

        IProductRepository productRepository = new ProductRepository();
        IProductService productService = new ProductService(productRepository);
        ICategoryRepository categoryRepository = new CategoryRepository();
        ICategoryService categoryService = new CategoryService(categoryRepository);

        IProductController productController = new ProductController(productService, categoryService);

        AuthService authService = AuthService.getInstance();

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
                    if (!authService.isAdmin()) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                   productController.addProduct();
                    break;

                case 3:
                    if (!authService.isAdmin()) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                    productController.deleteProduct();
                    break;

                case 4:
                    productController.getProductById();
                    break;

                case 5:
                    if (!authService.isAdmin()) {
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
