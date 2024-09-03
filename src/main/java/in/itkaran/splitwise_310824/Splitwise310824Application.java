package in.itkaran.splitwise_310824;

import in.itkaran.splitwise_310824.commands.CommandExecutor;
import in.itkaran.splitwise_310824.commands.CreateExpenseCommand;
import in.itkaran.splitwise_310824.commands.RegisterUserCommand;
import in.itkaran.splitwise_310824.controllers.ExpenseController;
import in.itkaran.splitwise_310824.controllers.UserController;
import in.itkaran.splitwise_310824.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;

import java.util.Scanner;

@SpringBootApplication
public class Splitwise310824Application implements CommandLineRunner {
    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private UserController userController;

    @Autowired
    private ExpenseController expenseController;

    public static void main(String[] args) {
        SpringApplication.run(Splitwise310824Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Add commands to CommandExecutor
        commandExecutor.addCommand(new RegisterUserCommand(userController));
        commandExecutor.addCommand(new CreateExpenseCommand(expenseController));

        while (true) {
            System.out.println("Enter Command");
            String input = scanner.nextLine();
            commandExecutor.execute(input);
        }

    }
}
