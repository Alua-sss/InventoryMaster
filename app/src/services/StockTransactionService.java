package services;

import models.Product;
import models.StockTransaction;
import repositories.ProductRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IStockTransactionRepository;
import services.interfaces.IProductService;
import services.interfaces.IStockTransactionService;

import java.util.List;
import java.util.Objects;

public class StockTransactionService implements IStockTransactionService {
    private final IStockTransactionRepository stockTransactionRepository;
    private final IProductRepository productRepository = new ProductRepository();
    private final IProductService productService = new ProductService(productRepository);

    public StockTransactionService(IStockTransactionRepository stockTransactionRepository) {
        this.stockTransactionRepository = stockTransactionRepository;
    }

    @Override
    public boolean recordTransaction(StockTransaction transaction) {
        Product product = transaction.getProduct();

        switch (transaction.getTransactionType()) {
            case "IN" -> {
                if (transaction.getQuantity() <= 0){
                    System.out.println("Incorrect quantity");
                    return false;
                }
                product.setQuantity(product.getQuantity() + transaction.getQuantity());
                productService.updateProduct(product);
            }
            case "OUT" -> {
                if (product.getQuantity() < transaction.getQuantity() || transaction.getQuantity() <= 0) {
                    System.out.println("Not enough stock or incorrect quantity");
                    return false;
                }
                product.setQuantity(product.getQuantity() - transaction.getQuantity());
                productService.updateProduct(product);
            }
            default -> {
                System.out.println("Invalid transaction type.");
                return false;
            }
        }

        return stockTransactionRepository.addTransaction(transaction);
    }


    @Override
    public List<StockTransaction> getFullStockTransactionDetails(int id) {
        if (id < 1 ) {
            System.out.println("Incorrect value of ID");
            return null;
        }
        return stockTransactionRepository.getFullStockTransactionDetails(id);
    }
}
