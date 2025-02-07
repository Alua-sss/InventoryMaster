package menu;

import controllers.CategoryController;
import controllers.FilterController;
import controllers.ProductController;
import controllers.StockTransactionController;
import controllers.interfaces.*;
import menu.interfaces.Menu;

import models.User;
import repositories.CategoryRepository;
import repositories.FilterRepository;
import repositories.ProductRepository;
import repositories.StockTransactionRepository;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IFilterRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IStockTransactionRepository;
import services.CategoryService;
import services.FilterService;
import services.ProductService;
import services.StockTransactionService;
import services.interfaces.ICategoryService;
import services.interfaces.IFilterService;
import services.interfaces.IProductService;
import services.interfaces.IStockTransactionService;

import java.util.Scanner;

public class MainMenu implements Menu {
    private final IUserController userController;

    public MainMenu(IUserController userController) {
        this.userController = userController;
    }


    @Override
    public void onLoad() {

        ICategoryRepository categoryRepository = new CategoryRepository();
        ICategoryService categoryService = new CategoryService(categoryRepository);
        ICategoryController categoryController = new CategoryController(categoryService);

        IProductRepository productRepository = new ProductRepository();
        IProductService productService = new ProductService(productRepository);
        IProductController productController = new ProductController(productService, categoryService);

        IFilterRepository filterRepository = new FilterRepository();
        IFilterService filterService = new FilterService(filterRepository);
        IFilterController filterController = new FilterController(filterService, categoryController);

        IStockTransactionRepository stockTransactionRepository = new StockTransactionRepository();
        IStockTransactionService stockTransactionService = new StockTransactionService(stockTransactionRepository);
        IStockTransactionController stockTransactionController = new StockTransactionController(stockTransactionService, productService, userController);

        User currentUser = userController.getCurrentUser();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Show all products");
            System.out.println("2. Add product (Admin)");
            System.out.println("3. Delete product (Admin)");
            System.out.println("4. Search product");
            System.out.println("5. Update product(Admin)");
            System.out.println("6. Category management(Admin)");
            System.out.println("7. Transaction management");
            System.out.println("8. Filter");
            System.out.println("0. Back");
            System.out.print("Choose the action: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> productController.getAllProducts();
                    case 2 -> {
                        if (!currentUser.getRole().equals("admin")) {
                            System.out.println("Not right enough to perform this operation");
                            break;
                        }
                        productController.addProduct();
                    }
                    case 3 -> {
                        if (!currentUser.getRole().equals("admin")) {
                            System.out.println("Not right enough to perform this operation");
                            break;
                        }
                        productController.deleteProduct();
                    }
                    case 4 -> productController.getProductById();
                    case 5 -> {
                        if (!currentUser.getRole().equals("admin")) {
                            System.out.println("Not right enough to perform this operation");
                            break;
                        }
                        productController.updateProduct();
                    }
                    case 6 -> {
                        if (!currentUser.getRole().equals("admin")) {
                            System.out.println("Not right enough to perform this operation");
                            break;
                        }
                        CategoryMenu categoryMenu = new CategoryMenu(categoryController);
                        categoryMenu.onLoad();
                    }
                    case 7 -> {
                        TransactionMenu transactionMenu = new TransactionMenu(stockTransactionController);
                        transactionMenu.onLoad();
                    }
                    case 8 -> {
                        FilterMenu filterMenu = new FilterMenu(filterController);
                        filterMenu.onLoad();
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("The wrong choice. Try it again.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Please enter the number.");
                scanner.nextLine();
            }
        }

    }

}
