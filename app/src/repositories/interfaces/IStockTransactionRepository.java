package repositories.interfaces;

import models.StockTransaction;

import java.util.List;

public interface IStockTransactionRepository {
    public boolean addTransaction(StockTransaction transaction);
    public List<StockTransaction> getFullStockTransactionDetails(int productId);
}
