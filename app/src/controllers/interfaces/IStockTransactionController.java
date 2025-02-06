package controllers.interfaces;

public interface IStockTransactionController {
    public void recordTransaction(String transactionType);
    public void getFullStockTransactionDetails();
}
