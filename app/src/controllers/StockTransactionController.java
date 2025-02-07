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
        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();
        if (productService.getProductById(productId) == null) {
            System.out.println("Product not found");
            return;
        }
        Product product = productService.getProductById(productId);
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        User user = userController.getCurrentUser();
        scanner.nextLine();
        StockTransaction transaction = new StockTransaction(product, user, quantity, transactionType);
        if (stockTransactionService.recordTransaction(transaction)) {
            System.out.println("The transaction is successfully saved!");
        } else {
            System.out.println("An error when recording a transaction.");
        }
    }


    @Override
    public void getFullStockTransactionDetails() {
        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();
        if (productService.getProductById(productId) == null) {
            System.out.println("Product not found");
            return;
        }


        List<StockTransaction> transactions = stockTransactionService.getFullStockTransactionDetails(productId);
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions for this product.");
        } else {
            System.out.println("\n--- Details of transactions ---");
            for (StockTransaction transaction : transactions) {
                System.out.println(
                        "ID: " + transaction.getId() +
                                ", Product: " + transaction.getProduct().getName() +
                                ", Category: " + transaction.getProduct().getCategory().getName() +
                                ", Quantity: " + transaction.getQuantity() +
                                ", Type transactions: " + transaction.getTransactionType() +
                                ", User: " + transaction.getUser().getUsername() +
                                ", Date: " + transaction.getTimestamp()
                );
            }
    }
}
}
