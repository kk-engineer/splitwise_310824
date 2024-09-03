package in.itkaran.splitwise_310824.repositories;

import in.itkaran.splitwise_310824.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
}
