package controllers;

import controllers.interfaces.IStockTransactionController;
import controllers.interfaces.IUserController;
import models.Product;
import models.StockTransaction;
import models.User;
import services.UserService;
import services.interfaces.IProductService;
import services.interfaces.IStockTransactionService;
import services.interfaces.IUserService;

import java.util.List;
import java.util.Scanner;

public class StockTransactionController implements IStockTransactionController {
    private final IStockTransactionService stockTransactionService;
    private final IProductService productService;
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public StockTransactionController(IStockTransactionService stockTransactionService, IProductService productService, IUserController userController) {
        this.stockTransactionService = stockTransactionService;
        this.productService = productService;
        this.userController = userController;
    }

    @Override
    public void recordTransaction(String transactionType) {
        System.out.print("Введите ID продукта: ");
        int productId = scanner.nextInt();
        if (productService.getProductById(productId) == null) {
            System.out.println("Такого продукта нет");
            return;
        }
        Product product = productService.getProductById(productId);
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        User user = userController.getCurrentUser();
        scanner.nextLine();
        StockTransaction transaction = new StockTransaction(product, user, quantity, transactionType);
        if (stockTransactionService.recordTransaction(transaction)) {
            System.out.println("Транзакция успешно сохранена!");
        } else {
            System.out.println("Ошибка при записи транзакции.");
        }
    }

    @Override
    public void getFullStockTransactionDetails() {
        System.out.print("Введите ID продукта: ");
        int productId = scanner.nextInt();
        if (productService.getProductById(productId) == null) {
            System.out.println("Такого продукта нет");
            return;
        }
        List<StockTransaction> transactions = stockTransactionService.getFullStockTransactionDetails(productId);
        if (transactions.isEmpty()) {
            System.out.println("Нет транзакций для данного продукта.");
        } else {
            System.out.println("\n--- Детали транзакций ---");
            for (StockTransaction transaction : transactions) {
                System.out.println(
                        "ID: " + transaction.getId() +
                                ", Продукт: " + transaction.getProduct().getName() +
                                " (" + transaction.getProduct().getCategory().getName() + ")" +
                                ", Количество: " + transaction.getQuantity() +
                                ", Тип операции: " + transaction.getTransactionType() +
                                ", Работник: " + transaction.getUser().getUsername() +
                                ", Дата: " + transaction.getTimestamp()
                );
            }
    }
}
}
