package in.itkaran.splitwise_310824.commands;

import in.itkaran.splitwise_310824.controllers.UserController;
import in.itkaran.splitwise_310824.dtos.UserRequestDto;
import in.itkaran.splitwise_310824.dtos.UserResponseDto;

import java.sql.SQLOutput;
import java.util.List;

public class RegisterUserCommand implements Command {

    private UserController userController;

    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        /* e.g: Register vinsmokesanji 003 namisswwaann */
        System.out.println("Checking if input matches RegisterUserCommand");
        List<String> words = List.of(input.split(" "));
        return words.size()==4 && words.get(0).equals(CommandKeyWords.REGISTER_USER_COMMAND);
    }

    @Override
    public void execute(String input) {
        System.out.println("Executing RegisterUserCommand");
        List<String> words = List.of(input.split(" "));
        String username = words.get(1);
        String phoneNumber = words.get(2);
        String password = words.get(3);
        System.out.println("Registering user: "+username+" with phoneNum: "+phoneNumber+" and password: "+password);

        // TODO : Call appropriate UserController method to register user

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(username);
        userRequestDto.setPhoneNumber(phoneNumber);
        userRequestDto.setPassword(password);

        UserResponseDto userResponseDto = userController.registerUser(userRequestDto);
        System.out.println(userResponseDto);
    }
}
