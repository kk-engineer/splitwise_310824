package in.itkaran.splitwise_310824.repositories;

import in.itkaran.splitwise_310824.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
