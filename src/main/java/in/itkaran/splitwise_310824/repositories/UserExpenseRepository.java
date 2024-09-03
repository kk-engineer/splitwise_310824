package in.itkaran.splitwise_310824.repositories;

import in.itkaran.splitwise_310824.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findAllByUserId(Long userId);
    List<UserExpense> findAllByExpenseId(Long expenseId);
}
