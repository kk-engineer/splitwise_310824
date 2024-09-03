package in.itkaran.splitwise_310824.services.strategies;

import in.itkaran.splitwise_310824.models.Transaction;
import in.itkaran.splitwise_310824.models.UserExpense;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<UserExpense> userExpenses);
}
