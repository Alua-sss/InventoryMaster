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
    private IProductRepository productRepository = new ProductRepository();
    private IProductService productService = new ProductService(productRepository);

    public StockTransactionService(IStockTransactionRepository stockTransactionRepository) {
        this.stockTransactionRepository = stockTransactionRepository;
    }

    @Override
    public boolean recordTransaction(StockTransaction transaction) {
        Product product = transaction.getProduct();

        switch (transaction.getTransactionType()) {
            case "IN" -> {
                product.setQuantity(product.getQuantity() + transaction.getQuantity());
                productService.updateProduct(product);
            }
            case "OUT" -> {
                if (product.getQuantity() < transaction.getQuantity()) {
                    System.out.println("Недостаточное количество товара на складе.");
                    return false;
                }
                product.setQuantity(product.getQuantity() - transaction.getQuantity());
                productService.updateProduct(product);
            }
            default -> {
                System.out.println(" Некорректный тип транзакции!");
                return false;
            }
        }

        return stockTransactionRepository.addTransaction(transaction);
    }


    @Override
    public List<StockTransaction> getFullStockTransactionDetails(int productId) {
        return stockTransactionRepository.getFullStockTransactionDetails(productId);
    }
}
