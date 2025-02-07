package services.interfaces;

import models.StockTransaction;

import java.util.List;

public interface IStockTransactionService {
    public boolean recordTransaction(StockTransaction transaction);
    public List<StockTransaction> getFullStockTransactionDetails(int productId);
}
