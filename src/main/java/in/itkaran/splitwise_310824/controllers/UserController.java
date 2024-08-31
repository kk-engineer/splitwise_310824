package in.itkaran.splitwise_310824.controllers;

import in.itkaran.splitwise_310824.dtos.UserRequestDto;
import in.itkaran.splitwise_310824.dtos.UserResponseDto;
import in.itkaran.splitwise_310824.models.User;
import in.itkaran.splitwise_310824.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        System.out.println("Registering User");
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userService.register(userRequestDto.getName(),
                userRequestDto.getPhoneNumber(),
                userRequestDto.getPassword());

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }
}
