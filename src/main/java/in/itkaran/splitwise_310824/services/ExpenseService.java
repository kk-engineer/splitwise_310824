package in.itkaran.splitwise_310824.services;


import in.itkaran.splitwise_310824.exceptions.InvalidUserException;
import in.itkaran.splitwise_310824.models.Expense;
import in.itkaran.splitwise_310824.models.User;
import in.itkaran.splitwise_310824.models.UserExpense;
import in.itkaran.splitwise_310824.models.UserExpenseType;
import in.itkaran.splitwise_310824.repositories.ExpenseRepository;
import in.itkaran.splitwise_310824.repositories.GroupRepository;
import in.itkaran.splitwise_310824.repositories.UserExpenseRepository;
import in.itkaran.splitwise_310824.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;
    private UserExpenseRepository userExpenseRepository;
    private GroupRepository groupRepository;

    public ExpenseService(UserRepository userRepository,
                          ExpenseRepository expenseRepository,
                          UserExpenseRepository userExpenseRepository,
                          GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.groupRepository = groupRepository;
    }


    public Expense addExpense(Long paidByUserId,
                           List<Long> owedByUserIds,
                           int amount,
                           Long groupId,
                           String description) {
        System.out.println("Adding Expense");
        /*
        1. Find the paidBy user by id
        a. If user not found, throw UserNotFoundException
        2. find the group by id
        a. If group not found, throw GroupNotFoundException
        3. Create an expense object
        4. Save to DB
        5. Create corresponding UserExpense objects
        6. Save to DB
         */

        Expense expense = new Expense();
        Optional<User> optionalPaidByUser = userRepository.findById(paidByUserId);
        if (!optionalPaidByUser.isPresent()) {
            throw new InvalidUserException("User not found");
        }

        expense.setCreatedBy(optionalPaidByUser.get());
        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setGroup(groupId == null ? null: groupRepository.findById(groupId).orElse(null));
        expenseRepository.save(expense);

        // Create corresponding UserExpense objects
        int participantsCount = owedByUserIds.size() + 1;
        int share = amount / participantsCount;

        // add paidByUserExpense
        UserExpense paidByUserExpense = new UserExpense();
        paidByUserExpense.setUser(optionalPaidByUser.get());
        paidByUserExpense.setExpense(expense);
        paidByUserExpense.setAmount(share*(participantsCount-1));
        paidByUserExpense.setUserExpenseType(UserExpenseType.PAID);
        userExpenseRepository.save(paidByUserExpense);


        // add OwedByUserExpenses
        for(Long userId: owedByUserIds){
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                throw new InvalidUserException("User not found");
            }
            UserExpense userExpense = new UserExpense();
            userExpense.setUser(optionalUser.get());
            userExpense.setExpense(expense);
            userExpense.setAmount((-1)*share);
            userExpense.setUserExpenseType(UserExpenseType.OWED);
            userExpenseRepository.save(userExpense);
        }

        return expense;
    }
}
