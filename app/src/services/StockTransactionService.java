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

        if (Objects.equals(transaction.getTransactionType(), "IN")) {
           Product product = transaction.getProduct();
           product.setQuantity(product.getQuantity() + transaction.getQuantity());
           productService.updateProduct(product);
        }
        if (Objects.equals(transaction.getTransactionType(), "OUT")) {
            Product product = transaction.getProduct();
            product.setQuantity(product.getQuantity() - transaction.getQuantity());
            productService.updateProduct(product);
        } else {
            System.out.println("Wrong!");
            return false;
        }

       return stockTransactionRepository.addTransaction(transaction);

    }

    @Override
    public List<StockTransaction> getFullStockTransactionDetails(int productId) {
        return stockTransactionRepository.getFullStockTransactionDetails(productId);
    }
}
