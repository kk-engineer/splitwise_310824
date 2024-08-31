package in.itkaran.splitwise_310824.repositories;

import in.itkaran.splitwise_310824.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
