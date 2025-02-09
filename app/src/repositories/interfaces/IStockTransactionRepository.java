package repositories.interfaces;

import models.StockTransaction;

import java.util.List;

public interface IStockTransactionRepository {
    boolean addTransaction(StockTransaction transaction);
    List<StockTransaction> getFullStockTransactionDetails(int productId);
}
