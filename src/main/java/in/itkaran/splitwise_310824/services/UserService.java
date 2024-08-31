package in.itkaran.splitwise_310824.services;

import in.itkaran.splitwise_310824.models.User;
import in.itkaran.splitwise_310824.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name,
                         String phoneNumber,
                         String password) {
        System.out.println("UserService: Registering User");
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
